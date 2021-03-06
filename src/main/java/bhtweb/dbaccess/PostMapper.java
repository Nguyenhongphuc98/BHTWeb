	package bhtweb.dbaccess;

import java.sql.Statement;
import java.security.KeyStore.PrivateKeyEntry;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Map;

import javax.resource.spi.RetryableUnavailableException;

import bhtweb.entities.BHTPost;
import bhtweb.entities.BHTPostCategory;
import bhtweb.entities.BHTUserAccount;
import bhtweb.utils.DataTypeUtils;
import bhtweb.utils.DateTimeUtils;

public class PostMapper extends DBMapper {

	//Hằng số để ta chỉnh pageLimit mặc định khi fetchPost, searchPost,...
	//Đây là số element tối đa của một trang.
	private static final Integer DEFAULT_PAGE_LIMIT = 10;
	private static final Integer DEFAULT_SPECIAL_TYPE_LIMIT = 3;
	
	//Các chuỗi phục vụ cho preparedStatement.
	private static final String insertPostStr = "INSERT INTO POST (PostID, PostTitle, PostContent, PostSubmitDtm, PostPublishDtm, PostReadTime, NumVote, NumView, PostSoftDeleted, PostHidden, PostApproved, PosterUserID, PostCategoryID, ImageURL, PostSummary) \r\n" + 
			"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String updatePostStr = "UPDATE POST SET PostTitle = ?, PostContent = ?, PostSubmitDtm = ?, PostPublishDtm = ?, PostReadTime = ?, NumVote = ?, NumView = ?, PostSoftDeleted = ?, PostHidden = ?, PostApproved = ?, PosterUserID = ?, PostCategoryID = ?, ImageURL = ?, PostSummary = ? WHERE PostID = ?";
	private static final String fetchPostStr = "SELECT * FROM POST JOIN UserAccount ON POST.PosterUserID = UserAccount.UserID JOIN PostCategory ON POST.PostCategoryID = PostCategory.PostCategoryID ORDER BY postPublishDtm DESC LIMIT ?,?";
	private static final String fetchHighlightStr = "SELECT * FROM POST JOIN UserAccount ON POST.PosterUserID = UserAccount.UserID JOIN PostCategory ON POST.PostCategoryID = PostCategory.PostCategoryID ORDER BY NumView DESC LIMIT ?";
	private static final String fetchNewestStr = "SELECT * FROM POST JOIN UserAccount ON POST.PosterUserID = UserAccount.UserID JOIN PostCategory ON POST.PostCategoryID = PostCategory.PostCategoryID ORDER BY PostPublishDtm DESC LIMIT ?";
	private static final String fetchNewActivities = "SELECT * FROM POST JOIN UserAccount ON POST.PosterUserID = UserAccount.UserID JOIN PostCategory ON Post.PostCategoryID = PostCategory.PostCategoryID WHERE PostCategory.IsActivity = 1 ORDER BY PostPublishDtm DESC LIMIT ?";
	
	//Chuỗi lấy số lượng trang.
	private static final String fetchPostPages = "SELECT COUNT(DISTINCT PostID) AS \"TotalPage\" FROM Post;\r\n";
	
	//preparedStatement.
	private UserStarredPostMapper userStarredPostMapper = new UserStarredPostMapper();
	
	public PostMapper() throws Exception {
		super();
		//Tạo preparedStatement.
	}
	
	public Boolean updatePost (BHTPost postDTO) {
		try (PreparedStatement updatePostPst = getConnection().prepareStatement(updatePostStr)){
			updatePostPst.setString(1, postDTO.getPostTitle());
			updatePostPst.setString(2, postDTO.getPostContent());
			updatePostPst.setTimestamp(3, DateTimeUtils.getTimestamptFromDate(postDTO.getPostSubmitDtm()));
			updatePostPst.setTimestamp(4, DateTimeUtils.getTimestamptFromDate(postDTO.getPostPublishDtm()));
			updatePostPst.setLong(5, postDTO.getPostReadTime());
			updatePostPst.setLong(6, postDTO.getNumVote());
			updatePostPst.setLong(7, postDTO.getNumView());
			updatePostPst.setBoolean(8, postDTO.getPostSoftDeleted());
			updatePostPst.setBoolean(9, postDTO.getPostHidden());
			updatePostPst.setBoolean(10, postDTO.getPostApproved());
			updatePostPst.setLong(11, postDTO.getPoster().getUserID());
			updatePostPst.setLong(12, postDTO.getPostCategory().getPostCategoryID());
			updatePostPst.setString(13, postDTO.getImageURL());
			updatePostPst.setString(14, postDTO.getPostSummary());
			updatePostPst.setLong(15, postDTO.getPostID());
			
			//Chạy lệnh cập nhật post.
			Integer rows = updatePostPst.executeUpdate();
			if (rows > 0)
				return true;
			else
				return false;
		} catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();
		}
		return false;
	}

	public BHTPost insertPost (BHTPost postDTO) {
		try (PreparedStatement insertPostPst = getConnection().prepareStatement(insertPostStr, Statement.RETURN_GENERATED_KEYS)){
			//AutoGenerated ID, vì vậy lúc nào cũng truyền vào 0 cho ID khi tạo.
			insertPostPst.setLong(1, 0);
			insertPostPst.setString(2, postDTO.getPostTitle());
			insertPostPst.setString(3, postDTO.getPostContent());
			insertPostPst.setTimestamp(4, DateTimeUtils.getTimestamptFromDate(postDTO.getPostSubmitDtm()));
			insertPostPst.setTimestamp(5, DateTimeUtils.getTimestamptFromDate(postDTO.getPostPublishDtm()));
			insertPostPst.setLong(6, postDTO.getPostReadTime());
			insertPostPst.setLong(7, postDTO.getNumVote());
			insertPostPst.setLong(8, postDTO.getNumView());
			insertPostPst.setBoolean(9, postDTO.getPostSoftDeleted());
			insertPostPst.setBoolean(10, postDTO.getPostHidden());
			insertPostPst.setBoolean(11, postDTO.getPostApproved());
			insertPostPst.setLong(12, postDTO.getPoster().getUserID());
			insertPostPst.setLong(13, postDTO.getPostCategory().getPostCategoryID());
			insertPostPst.setString(14, postDTO.getImageURL());
			insertPostPst.setString(15, postDTO.getPostSummary());
			
			//Chạy lệnh thêm mới một Post.
			insertPostPst.executeUpdate();
			
			//Lấy về ID vừa được tạo.
			ResultSet rSet = insertPostPst.getGeneratedKeys();
			rSet.next();
			postDTO.setPostID(rSet.getInt(1));
			
		}catch (Exception ex) {
			ex.printStackTrace();
			postDTO.setPostID(null);
		}
		return postDTO;
	}
	
	public Integer fetchPostPages () {
		return fetchPostPages(DEFAULT_PAGE_LIMIT);
	}
	
	public Integer fetchPostPages (Integer pageLimit) {
		Integer postPages = null;
		try(PreparedStatement fetchPostPagePt = getConnection().prepareStatement(fetchPostPages)) {
			ResultSet resultSet = fetchPostPagePt.executeQuery();
			
			while (resultSet != null && resultSet.next()) {
				postPages = resultSet.getInt("TotalPage");
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		
		postPages = (int)Math.ceil((double)postPages/pageLimit);
		
		return postPages;
	}
	
	public ArrayList<BHTPost> fetchNewActivities (){
		return fetchNewActivities(DEFAULT_SPECIAL_TYPE_LIMIT);
	}
	
	public ArrayList<BHTPost> fetchNewActivities (Integer limit){
		
//		Map<Integer, Map<Integer, Boolean>> isUserLiked = 
//				userStarredPostMapper.getPostIDLikedByUserIDMapping();
		
		ArrayList<BHTPost> postsResult = new ArrayList<>();
		try (PreparedStatement fetchPostPst = getConnection().prepareStatement(fetchNewActivities)){
			fetchPostPst.setInt(1, limit);
			ResultSet rSet = fetchPostPst.executeQuery();
			
			while (rSet != null && rSet.next()) {
				BHTPost postDTO = getBHTPostFromCurrentResultSet(rSet);
				
				//Set thêm danh sách user đã like post.
//				postDTO.setLikedUsers(isUserLiked.get(postDTO.getPostID()));
				
				postsResult.add(postDTO);
			}
		}catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();
			return null;
		}

		return postsResult;
	}
	
	public ArrayList<BHTPost> fetchNewestPosts() {
		return fetchNewestPosts(DEFAULT_SPECIAL_TYPE_LIMIT);
	}
	
	public ArrayList<BHTPost> fetchNewestPosts(Integer limit){
		
//		Map<Integer, Map<Integer, Boolean>> isUserLiked = 
//				userStarredPostMapper.getPostIDLikedByUserIDMapping();
		
		ArrayList<BHTPost> postsResult = new ArrayList<>();
		try (PreparedStatement fetchPostPst = getConnection().prepareStatement(fetchNewestStr)){
			fetchPostPst.setInt(1, limit);
			ResultSet rSet = fetchPostPst.executeQuery();
			
			while (rSet != null && rSet.next()) {
				BHTPost postDTO = getBHTPostFromCurrentResultSet(rSet);
				
				//Set thêm danh sách user đã like post.
//				postDTO.setLikedUsers(isUserLiked.get(postDTO.getPostID()));
				
				postsResult.add(postDTO);
			}
		}catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();
			return null;
		}

		return postsResult;
	}
	
	public ArrayList<BHTPost> fetchHighLightPosts (){
		return fetchHighLightPosts(DEFAULT_SPECIAL_TYPE_LIMIT);
	}
	
	public ArrayList<BHTPost> fetchHighLightPosts (Integer limit){
		
//		Map<Integer, Map<Integer, Boolean>> isUserLiked = 
//				userStarredPostMapper.getPostIDLikedByUserIDMapping();
//		
		ArrayList<BHTPost> postsResult = new ArrayList<>();
		try (PreparedStatement fetchPostPst = getConnection().prepareStatement(fetchHighlightStr)){
			fetchPostPst.setInt(1, limit);
			ResultSet rSet = fetchPostPst.executeQuery();
			
			while (rSet != null && rSet.next()) {
				BHTPost postDTO = getBHTPostFromCurrentResultSet(rSet);
				
				//Set thêm danh sách user đã like post.
//				postDTO.setLikedUsers(isUserLiked.get(postDTO.getPostID()));
				
				postsResult.add(postDTO);
			}
		}catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();
			return null;
		}

		return postsResult;
	}
	
	//Hàm fetch sẽ sử dụng pageLimit mặc định như đã được cấu hình ở trên.
	public ArrayList<BHTPost> fetchPost (Integer pageNo){
		return fetchPost(pageNo, DEFAULT_PAGE_LIMIT);
	}
	
	public ArrayList<BHTPost> fetchPost (Integer pageNo, Integer pageLimit){
		Integer startLimit = (pageNo - 1) * pageLimit;
		Integer offSet = pageLimit;
		
//		Map<Integer, Map<Integer, Boolean>> isUserLiked = 
//				userStarredPostMapper.getPostIDLikedByUserIDMapping();
		
		ArrayList<BHTPost> postsResult = new ArrayList<>();
		
		try (PreparedStatement fetchPostPst = getConnection().prepareStatement(fetchPostStr)) {
			System.out.println("AJLFJWEFIWOFJOWJIFOW");
			System.out.println("START LIMIT : " + startLimit.toString());
			System.out.println("OFFSET : " + offSet.toString());
			fetchPostPst.setLong(1, startLimit);
			fetchPostPst.setLong(2, offSet);
			
			ResultSet rSet = fetchPostPst.executeQuery();
			
			while (rSet != null && rSet.next()) {
				BHTPost postDTO = getBHTPostFromCurrentResultSet(rSet);
				
				//Set thêm danh sách user đã like post.
//				postDTO.setLikedUsers(isUserLiked.get(postDTO.getPostID()));
				
				postsResult.add(postDTO);
			}
			
		}catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();
			return null;
		}

		return postsResult;
	}
	
	public ArrayList<BHTPost> searchPost (BHTPost similarPost, Integer pageNo){
		return searchPost(similarPost, pageNo, DEFAULT_PAGE_LIMIT);
	}
	
	public ArrayList<BHTPost> searchPost (BHTPost similarPost, Integer pageNo, Integer pageLimit){
		
//		Map<Integer, Map<Integer, Boolean>> isUserLiked = 
//				userStarredPostMapper.getPostIDLikedByUserIDMapping();
		
		ArrayList<BHTPost> searchPostResult = new ArrayList<>();
		
		String searchSQLStr = getSearchSQLFromDTO(similarPost);
		
		System.out.println("SQL Generated String : " + searchSQLStr);
		
		try (PreparedStatement searchPostPst = getConnection().prepareStatement(searchSQLStr)){
			ResultSet rSet = searchPostPst.executeQuery();
			
			while (rSet != null && rSet.next()) {
				BHTPost postDTO = getBHTPostFromCurrentResultSet(rSet);
				
				//Set thêm danh sách user đã like post.
//				postDTO.setLikedUsers(isUserLiked.get(postDTO.getPostID()));
				
				searchPostResult.add(postDTO);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		return searchPostResult;
	}
	
	//Hàm lấy một DTO từ resultSet hiện tại.
	private BHTPost getBHTPostFromCurrentResultSet (ResultSet rSet) throws Exception {
		
		BHTPost postDTO = new BHTPost();
		try {
			postDTO.setNumView(rSet.getLong("NumView"));
			postDTO.setNumVote(rSet.getLong("NumVote"));
			postDTO.setPostApproved(rSet.getBoolean("PostApproved"));
			postDTO.setPostContent(rSet.getString("PostContent"));
			postDTO.setPostSummary(rSet.getString("PostSummary"));
			postDTO.setImageURL(rSet.getString("ImageURL"));
			postDTO.setPostHidden(rSet.getBoolean("PostHidden"));
			postDTO.setPostID(rSet.getInt("PostID"));
			postDTO.setPostPublishDtm(rSet.getTimestamp("PostPublishDtm"));
			postDTO.setPostReadTime(rSet.getLong("PostReadTime"));
			postDTO.setPostSoftDeleted(rSet.getBoolean("PostSoftDeleted"));
			postDTO.setPostSubmitDtm(rSet.getTimestamp("PostSubmitDtm"));
			postDTO.setPostTitle(rSet.getString("PostTitle"));
			
			BHTPostCategory category = new BHTPostCategory();
			category.setPostCategoryID(rSet.getInt("PostCategoryID"));
			category.setPostCategoryName(rSet.getString("PostCategoryName"));
			postDTO.setPostCategory(category);
			
			BHTUserAccount poster = new BHTUserAccount();
			poster.setUserID(rSet.getInt("UserID"));
			poster.setUserName(rSet.getString("UserName"));
			poster.setProfilePictureURL(rSet.getString("ProfilePictureURL"));
			postDTO.setPoster(poster);
			
		}catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
		return postDTO;
	}
	
	//Hàm trả về câu truy vấn SQL (chuỗi) để tìm kiếm theo các trường thông tin khác null của DTO truyền vào.
	private String getSearchSQLFromDTO (BHTPost similarPost) {
		//Tạo statement SQL từ đối tượng DTO truyền vào.
		String searchSQLStr = "SELECT * FROM POST JOIN UserAccount ON POST.PosterUserID = UserAccount.UserID JOIN PostCategory ON POST.PostCategoryID = PostCategory.PostCategoryID WHERE";
		if (similarPost.getPostApproved() != null)
			searchSQLStr += " PostApproved = " + DataTypeUtils.getStringFromBoolean(similarPost.getPostApproved()) + " AND";
		if (similarPost.getPostHidden() != null)
			searchSQLStr += " PostHidden = " + DataTypeUtils.getStringFromBoolean(similarPost.getPostHidden())  + " AND"; 
		if (similarPost.getPostSoftDeleted() != null)
			searchSQLStr += " PostSoftDeleted = " + DataTypeUtils.getStringFromBoolean(similarPost.getPostSoftDeleted()) + " AND";
		if (similarPost.getNumView() != null)
			searchSQLStr += " NumView = " + similarPost.getNumView().toString() + " AND";
		if (similarPost.getNumVote() != null)
			searchSQLStr += " NumVote = " + similarPost.getNumVote().toString() + " AND";
		if (similarPost.getPostCategory() != null && similarPost.getPostCategory().getPostCategoryID() != null)
			searchSQLStr += " PostCategory.PostCategoryID = " + similarPost.getPostCategory().getPostCategoryID().toString() + " AND";
		if (similarPost.getPostContent() != null)
			searchSQLStr += " PostContent = " + similarPost.getPostContent() + " AND";
		if (similarPost.getPoster() != null && similarPost.getPoster().getUserID() != null)
			searchSQLStr += " PosterUserID = " + similarPost.getPoster().getUserID().toString() + " AND";
		if (similarPost.getPostID() != null)
			searchSQLStr += " PostID = " + similarPost.getPostID().toString() + " AND";
		if (similarPost.getPostPublishDtm() != null)
			searchSQLStr += " PostPublishDtm = " + similarPost.getPostPublishDtm() + " AND"; 
		if (similarPost.getPostReadTime() != null)
			searchSQLStr += " PostReadTime = " + similarPost.getPostReadTime() + " AND";
		if (similarPost.getPostSubmitDtm() != null)
			searchSQLStr += " PostSubmitDtm = " + similarPost.getPostSubmitDtm() + " AND";
		if (similarPost.getPostTitle() != null)
			searchSQLStr += " PostTitle like \'%" + similarPost.getPostTitle() + "%\'" + " AND";
		//Cần thiết để câu lệnh SQL của chúng ta đúng cú pháp.
		searchSQLStr += " 1=1";
		return searchSQLStr;
	}
}
