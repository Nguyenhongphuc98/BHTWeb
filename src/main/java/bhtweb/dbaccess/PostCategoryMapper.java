package bhtweb.dbaccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import bhtweb.dto.PostDTO;
import bhtweb.entities.BHTPostCategory;

public class PostCategoryMapper extends DBMapper {
	
	//Các chuỗi phục vụ cho preparedStatement.
	private static final String insertPostCategoryStr = "INSERT INTO PostCategory VALUES (?,?)";
	private static final String updatePostCategoryStr = "UPDATE PostCategory SET PostCategoryName = ? WHERE PostCategoryID = ?";
	
	public PostCategoryMapper() throws Exception {
		super();
		// TODO Auto-generated constructor stub
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
			postCategoryDTO.setPostCategoryID(rSet.getLong(1));
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			postCategoryDTO.setPostCategoryID(null);
		}
		return postCategoryDTO;
	}
	
}
