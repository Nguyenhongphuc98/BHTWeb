package bhtweb.dbaccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bhtweb.entities.BHTTag;

public class TagMapper extends DBMapper {

	private static final String fetchTagByPost = "SELECT Tag.TagID, Tag.TagContent FROM Tag JOIN PostTag ON Tag.TagID = PostTag.TagID WHERE PostID = ?";
	
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
	
}
