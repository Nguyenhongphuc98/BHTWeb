package bhtweb.dbaccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.relation.RelationNotification;
import javax.resource.spi.RetryableUnavailableException;

import org.omg.CORBA.PRIVATE_MEMBER;

import com.mysql.cj.jdbc.interceptors.ResultSetScannerInterceptor;
import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

import bhtweb.entities.BHTPost;
import bhtweb.entities.BHTUserAccount;
import bhtweb.entities.BHTUserStarredPost;
import bhtweb.utils.DateTimeUtils;

public class UserStarredPostMapper extends DBMapper {

	private static final String queryUserStarredPostStr = "SELECT * FROM UserStarredPost";
	private static final String queryUserIDStarredPostStr = "SELECT * FROM UserStarredPost WHERE UserID = ?";
	private static final String queryByUser = "SELECT * FROM UserStarredPost JOIN Post ON UserStarredPost.PostID = Post.PostID WHERE UserID = ?";
	private static final String queryByPost = "SELECT * FROM UserStarredPost JOIN UserAccount ON UserStarredPost.UserID = UserAccount.UserID WHERE PostID = ?";
	private static final String insertUserStarredPostStr = "INSERT INTO UserStarredPost(UserID, PostID) VALUES (?,?)";
	private static final String deleteUserStarredPostStr = "DELETE FROM UserStarredPost WHERE UserID = ? and PostID = ?";
	
	public UserStarredPostMapper() throws Exception{
		super();
	}
	
	public boolean insertUserStarredPost (Integer userID, Integer postID) {
		BHTUserStarredPost userStarredPost;
		try (PreparedStatement insertUserStarredPost = getConnection().prepareStatement(insertUserStarredPostStr)){
			
			insertUserStarredPost.setInt(1, userID);
			insertUserStarredPost.setInt(2, postID);
			
			//Chạy lệnh thêm mới một Post.
			int rowsEffected = insertUserStarredPost.executeUpdate();
			
			boolean result = false;
			
			if (rowsEffected > 0)
				result = true;
			
			return result;
		}catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	public boolean deleteUserStarredPost (Integer userID, Integer postID) {
		try (PreparedStatement deleteUserStarredPost = getConnection().prepareStatement(deleteUserStarredPostStr)){
			
			deleteUserStarredPost.setInt(1, userID);
			deleteUserStarredPost.setInt(2, postID);
			
			//Chạy lệnh thêm mới một Post.
			int rowsEffected = deleteUserStarredPost.executeUpdate();
						
			if (rowsEffected > 0)
				return true;
			return false;
			
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}
	
	public ArrayList<BHTUserStarredPost> fetchBySQL (PreparedStatement pst){
		ArrayList<BHTUserStarredPost> results = new ArrayList<>();
		
		try{
			ResultSet rSet = pst.executeQuery();
			
			while (rSet != null && rSet.next()) {
				Integer userID = rSet.getInt("UserID");
				Integer postID = rSet.getInt("PostID");
				BHTUserStarredPost entity = new BHTUserStarredPost(userID, postID);
				results.add(entity);
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return results;
	}
	
	
	public ArrayList<BHTUserStarredPost> fetchUserStarredPost(){
		try (PreparedStatement pStatement = getConnection().prepareStatement(queryUserStarredPostStr)) {
			return fetchBySQL(pStatement);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<BHTUserStarredPost> fetchByUserID (Integer userID){
		try(PreparedStatement pStatement = getConnection().prepareStatement(queryUserIDStarredPostStr)){
			pStatement.setInt(1, userID);
			return fetchBySQL(pStatement);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	
	//Mapping từ post đến User để lấy tất cả user đã like post.
	//Biến Integer đầu tiên là postID.
	//Biến Integer thứ hai là userID.
	//Biến Boolean cuối cùng chỉ đích danh user này có like post hay không.
	public Map<Integer, Map<Integer, Boolean>> getPostIDLikedByUserIDMapping (){
		ArrayList<BHTUserStarredPost> list = fetchUserStarredPost();
		
		Map<Integer, Map<Integer, Boolean>> results = new HashMap<>();
		
		for (BHTUserStarredPost element : list) {
			Integer postID = element.getPost().getPostID();
			Integer userID = element.getUserAccount().getUserID();
			
			Map<Integer, Boolean> map = results.get(postID);
			
			if (map == null)
				map = new HashMap<>();
			
			map.put(userID, true);
			
			results.put(postID, map);
		}
		
		return results;
		
	}

	public Map<Integer, BHTPost> getPostsLikedByUser (Integer userID){
		try (PreparedStatement pStatement = getConnection().prepareStatement(queryByUser)) {
			pStatement.setInt(1, userID);
			ArrayList<BHTUserStarredPost> relation = fetchBySQL(pStatement);
			
			Map<Integer, BHTPost> results = new HashMap<>();
			
			for (BHTUserStarredPost rel : relation) {
				BHTPost post = rel.getPost();
				results.put(post.getPostID(), post);
			}
			
			return results;
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	
	public Map<Integer,BHTUserAccount> getUsersLikedPost (Integer postID){
		try (PreparedStatement pStatement = getConnection().prepareStatement(queryByPost)) {
			pStatement.setInt(1, postID);
			ArrayList<BHTUserStarredPost> relation = fetchBySQL(pStatement);
			
			Map<Integer, BHTUserAccount> results = new HashMap<>();
			
			for (BHTUserStarredPost rel : relation) {
				BHTUserAccount user = rel.getUserAccount();
				results.put(user.getUserID(), user);
			}
			
			return results;
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	
}
