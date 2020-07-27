package bhtweb.dbaccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.resource.spi.RetryableUnavailableException;

import bhtweb.entities.BHTTag;

public class TagMapper extends DBMapper {

	private static final String fetchTagByPost = "SELECT Tag.TagID, Tag.TagContent FROM Tag JOIN PostTag ON Tag.TagID = PostTag.TagID WHERE PostID = ?";
	private static final String deleteTagOfPost = "DELETE FROM PostTag WHERE PostID = ? AND TagID = ?";
	
	public TagMapper () throws Exception{
		super();
	}
	
	public List<BHTTag> getTagsOfPostID (Integer postID){
		List<BHTTag> results = new ArrayList<BHTTag>();
	
		try(PreparedStatement fetchTagByPostSt = getConnection().prepareStatement(fetchTagByPost)){
			fetchTagByPostSt.setInt(1, postID);
			
			ResultSet rSet = fetchTagByPostSt.executeQuery();
			
			while (rSet != null && rSet.next()) {
				Integer id = rSet.getInt("TagID");
				String content = rSet.getString("TagContent");
				
				BHTTag tag = new BHTTag(id,content);
				
				results.add(tag);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		return results;
	}
	
	public List<BHTTag> getTagEntityFromName (List<String> tags){
		
		if (tags == null || tags.size() == 0)
			return null;
		
		//Lưu trữ tất cả các tags được định nghĩa bởi người dùng.
		List<BHTTag> tagEntities = new ArrayList<>();
		//Tạo câu lệnh truy vấn tất cả các tag của post.
		String sqlQuery = "SELECT * FROM Tag WHERE " + tagsToLogic(tags);
		
		//Truy vấn lấy danh sách các tags.
		try(PreparedStatement fetchAllTags = getConnection().prepareStatement(sqlQuery)){
			
			ResultSet resultSet = fetchAllTags.executeQuery();
			
			while (resultSet != null && resultSet.next()) {
				BHTTag tag = new BHTTag();
				tag.setTagID(resultSet.getInt("TagID"));
				tag.setTagContent(resultSet.getString("TagContent"));
				
				tagEntities.add(tag);
			}
			
			return tagEntities;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	
	public Boolean addPostTag (Integer postID, List<BHTTag> tags) {
		
		if (tags == null || tags.size() == 0)
			return false;
		
		//Sau khi có tag rồi thì thêm vào DB.
		String sqlRef = "INSERT INTO PostTag VALUES " + postTagToValues(postID, tags);
		
		try(PreparedStatement updatePostTag = getConnection().prepareStatement(sqlRef)){
			updatePostTag.executeUpdate();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return true;
		
	}
	
	public Boolean addTags (List<String> tags) {

		if (tags == null || tags.size() == 0)
			return false;
		
		//Tạo câu lệnh insert ignore cho tất cả các tags người dùng nhập vào.
		String sqlStr = "INSERT IGNORE INTO Tag VALUES " + tagToValues(tags);
		
		//Thêm tất cả tag vào DB.
		try(PreparedStatement insertAllTags = getConnection().prepareStatement(sqlStr)){
			
			insertAllTags.executeUpdate();
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}
	
	private String postTagToValues (Integer postID, List<BHTTag> tags) {
		String result = "";
		
		for (int i=0;i<tags.size();++i) {
			BHTTag tag = tags.get(i);
			result += "(" + postID.toString() + "," + tag.getTagID() + "),";
		}
		//Xoá ký tự cuối.
		result = result.substring(0, result.length() - 1);
		
		System.out.println("GENERATED QUERY :" + result);
		
		return result;
	}
	
	private String tagToValues (List<String> tags) {
		String result = "";
		for (String tag : tags) {
			result += "(null, " + "\'" + tag + "\'" + "),";
		}
		//Xoá ký tự cuối.
		result = result.substring(0, result.length() - 1);
		
		System.out.println("GENERATED QUERY :" + result);
		
		return result;
	}
	
	private String tagsToLogic (List<String> tags) {
		
		String result = "";
		
		for (String tag : tags) {
			result += "TagContent = " + "\'" + tag + "\'" + " OR ";
		}
		
		result += "1=0";
		
		System.out.println("GENERATED SQL : " + result);

		return result;
	}
	
}
