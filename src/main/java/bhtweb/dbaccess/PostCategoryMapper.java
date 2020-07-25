package bhtweb.dbaccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale.Category;

import bhtweb.dto.PostDTO;
import bhtweb.entities.BHTPostCategory;

public class PostCategoryMapper extends DBMapper {
	
	//Các chuỗi phục vụ cho preparedStatement.
	private static final String insertPostCategoryStr = "INSERT INTO PostCategory VALUES (?,?)";
	private static final String updatePostCategoryStr = "UPDATE PostCategory SET PostCategoryName = ? WHERE PostCategoryID = ?";
	private static final String fetchPostCategoryStr = "SELECT * FROM PostCategory";
	
	public PostCategoryMapper() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public List<BHTPostCategory> fetchPostCategories (){
		List<BHTPostCategory> result = new ArrayList<>();
		
		try(PreparedStatement fetchPostCategoryPst = getConnection().prepareStatement(fetchPostCategoryStr)){
			ResultSet rSet = fetchPostCategoryPst.executeQuery();
			
			while (rSet != null && rSet.next()) {
				BHTPostCategory postCategory = getBHTPostCategoryFromCurrentResultSet(rSet);
				result.add(postCategory);
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
	
	public Boolean updatePostCategory (BHTPostCategory postCategoryDTO) {
		try (PreparedStatement updatePostCategoryPst = getConnection().prepareStatement(updatePostCategoryStr)){
			updatePostCategoryPst.setString(1, postCategoryDTO.getPostCategoryName());
			updatePostCategoryPst.setLong(2, postCategoryDTO.getPostCategoryID());
			
			Integer rows = updatePostCategoryPst.executeUpdate();
			if (rows > 0)
				return true;
			return false;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	
	public BHTPostCategory insertPostCategory (BHTPostCategory postCategoryDTO) {
		try (PreparedStatement insertPostCategoryPst = getConnection().prepareStatement(insertPostCategoryStr, Statement.RETURN_GENERATED_KEYS)){
			insertPostCategoryPst.setLong(1, 0);
			insertPostCategoryPst.setString(2, postCategoryDTO.getPostCategoryName());
			
			//Chạy lệnh thêm mới một postCategory.
			insertPostCategoryPst.executeUpdate();
			
			ResultSet rSet = insertPostCategoryPst.getGeneratedKeys();
			rSet.next();
			postCategoryDTO.setPostCategoryID(rSet.getInt(1));
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			postCategoryDTO.setPostCategoryID(null);
		}
		return postCategoryDTO;
	}
	
	private BHTPostCategory getBHTPostCategoryFromCurrentResultSet (ResultSet rSet) {
		BHTPostCategory category = new BHTPostCategory();
		try {
			category.setPostCategoryID(rSet.getInt("PostCategoryID"));
			category.setPostCategoryName(rSet.getString("PostCategoryName"));
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return category;
	}
}
