package bhtweb.dbaccess;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sound.midi.Soundbank;

import bhtweb.entities.BHTComment;
import bhtweb.entities.BHTUserAccount;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author NguyenHongPhuc
 */
public class CommentMapper extends DBMapper {
    
    public CommentMapper() throws Exception {
		super();
	}

	private List<BHTComment> fetchListCommentsParentOnly(String sqlStr) {
        List<BHTComment> rawCommentList = new ArrayList<>();

		try {
			Statement stmt = getConnection().createStatement();
			ResultSet rs;

			rs = stmt.executeQuery(sqlStr); // Send the query to the server
			while (rs != null && rs.next()) {
				BHTComment d = new BHTComment();
				
				d.setCommentID(rs.getInt("CommentID"));
				d.setPostId(rs.getInt("PostID"));
				d.setCommentSoftDeleted(rs.getBoolean("CommentSoftDeleted"));
				d.setCommentHidden(rs.getBoolean("CommentHidden"));
				d.setCommentApproved(rs.getBoolean("CommentApproved"));
				d.setCommentContent(rs.getString("CommentContent"));
				d.setParentComment(new BHTComment(rs.getInt("ParentCommentID")));
				d.setCommentDtm(rs.getDate("CommentDtm"));
				
				//Thông tin của người post bài.
				d.setUserAccount(new BHTUserAccount(rs.getInt("UserID")));
				d.getUserAccount().setProfilePictureURL(rs.getString("ProfilePictureURL"));
				d.getUserAccount().setUserName(rs.getString("UserName"));
				
				System.out.println("Nội dung comment : " + rs.getString("CommentContent"));
				System.out.println("Parent ID : " + d.getParentComment().getCommentID());

				rawCommentList.add(d);
			}
		} catch (SQLException ex) {
			Logger.getLogger(DocumentMapper.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		//Debug: Xuất thử xem rawCommentList lấy được bao nhiêu phần tử.
		System.out.println("Comment Count : " + rawCommentList.size());
		
		//Debug: Xuất thử giá trị của tất cả rawCommentList.
//		for (BHTComment comment : rawCommentList) {
//			
//		}
		
		//Hậu xử lý: Tiến hành thêm vào relationship cho cha và con.
		//Lấy ra các danh sách comment là cha.
		
		Map<Integer, BHTComment> parentComments = new HashMap<Integer, BHTComment>();

		for (BHTComment comment : rawCommentList) {
			if (comment.isParent()) {
				System.out.println(comment.getCommentContent() + " is parent !");
				parentComments.put(comment.getCommentID(), comment);
			}
		}
		
		//Duyệt qua tất cả comments và thêm mối liên kết vào.
		for (BHTComment comment : rawCommentList) {
			if (comment.isChild()) {
				System.out.println(comment.getCommentContent() + " is child");
				Integer parentID = comment.getParentComment().getCommentID();
				
				BHTComment parent = parentComments.get(parentID);
				//Thêm mối liên kết đến cha cho con.
				comment.setParentComment(parent);
				
				//Thêm mối liên kết đến con cho cha.
				if (parent != null)
					parent.getChildComments().add(comment);
			}
		}
		
		//Chuyển kết quả từ HashMap ra ArrayList.
		List<BHTComment> results = new ArrayList<>();
		for (BHTComment comment : parentComments.values()) {
			results.add(comment);
		}
		
		System.out.println("BHTComment Parent Only : " + results.size());
		
		return results;
    }
    
    public List<BHTComment> getCommentByParentId(int commentId, boolean isApproved) {
        String sqlStr = "SELECT * FROM comment JOIN UserAccount ON Comment.UserID = UserAccount.UserID WHERE ParentCommentID = " + commentId + " and CommentApproved = " + isApproved;
        List<BHTComment> ls = fetchListCommentsParentOnly(sqlStr);
        return ls.size() > 0 ? ls : null;

    }
    
    public List<BHTComment> getCommentByParentId(int commentID){
    	String sqlStr = "SELECT * FROM comment JOIN UserAccount ON Comment.UserID = UserAccount.UserID WHERE ParentCommentID = " + commentID;
        List<BHTComment> ls = fetchListCommentsParentOnly(sqlStr);
        return ls.size() > 0 ? ls : null;
    }
    
    public List<BHTComment> getCommentByPostId(int postId, boolean isApproved) {
        String sqlStr = "SELECT * FROM comment JOIN UserAccount ON Comment.UserID = UserAccount.UserID WHERE PostID = " + postId + " and CommentApproved = " + isApproved;
        List<BHTComment> ls = fetchListCommentsParentOnly(sqlStr);
        return ls.size() > 0 ? ls : null;
    }
    
    public List<BHTComment> getCommentByPostId(int postId){
    	String sqlStr = "SELECT * FROM comment JOIN UserAccount ON Comment.UserID = UserAccount.UserID WHERE PostID = " + postId;
        List<BHTComment> ls = fetchListCommentsParentOnly(sqlStr);
        return ls.size() > 0 ? ls : null;
    }
      
    public BHTComment saveComment(BHTComment comment) {
        
        // the mysql insert statement
        String query = " INSERT INTO comment (UserID, PostID,"
                + "CommentSoftDeleted, CommentHidden, CommentApproved, CommentContent,"
                + "ParentCommentID, CommentDtm)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try {
        	// create the mysql insert preparedstatement
            PreparedStatement preparedStmt = getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStmt.setInt(1, comment.getUserAccount().getUserID());
            preparedStmt.setInt(2, comment.getPostId());
            preparedStmt.setBoolean(3, comment.isCommentSoftDeleted());
            preparedStmt.setBoolean(4, comment.isCommentHidden());
            preparedStmt.setBoolean(5, comment.isCommentApproved());
            preparedStmt.setString(6, comment.getCommentContent());
            preparedStmt.setObject(7, comment.getParentComment().getCommentID(), java.sql.Types.INTEGER);
            preparedStmt.setTimestamp(8, new Timestamp(comment.getCommentDtm().getTime()));
            
            // execute the preparedstatement
            preparedStmt.executeUpdate();
            
            ResultSet resultSet = preparedStmt.getGeneratedKeys();
            resultSet.next();
            comment.setCommentID(resultSet.getInt(1));
            
            
        } catch (SQLException ex) {
            Logger.getLogger(DocumentMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return comment;
    }
    
    public boolean updateComment(BHTComment comment, boolean delete, boolean hidden, boolean approved) {
        
        String sqlStr = "UPDATE comment SET ";
        
        if (comment.getCommentContent()!= null) {
            sqlStr += "CommentContent = '" + comment.getCommentContent() + "',";
        }
        if (delete) {
            sqlStr += "CommentSoftDeleted = " + comment.isCommentSoftDeleted() + ",";
        }
        if (hidden) {
            sqlStr += "CommentHidden = " + comment.isCommentHidden()+ ",";
        }
        if (approved) {
            sqlStr += "CommentApproved = " + comment.isCommentApproved()+ ",";
        }
       
        /// Khong cho update user, post, parent and time comment
        
        //  Xoa dau --',-- cuoi cung neu co
        if (sqlStr.substring(sqlStr.length() - 2, sqlStr.length() - 1).equals("',")) {
            sqlStr = sqlStr.substring(0, sqlStr.length() - 3);
        } else if (sqlStr.charAt(sqlStr.length() - 1) == '\'') {
            sqlStr = sqlStr.substring(0, sqlStr.length() - 2);
        }
        
        sqlStr += " WHERE CommentID = " + comment.getCommentID();
        
        Statement stmt;
		try {
			stmt = getConnection().createStatement();
			 int r = stmt.executeUpdate(sqlStr);
		        return  r > 0 ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
       
    }
    
    public Boolean deleteComment (int commentID) {
    	String sql = "DELETE FROM Comment WHERE CommentID = ?";
    	
    	try(PreparedStatement pStatement = getConnection().prepareStatement(sql)){
    		
    		pStatement.setInt(1, commentID);
    		
    		int rows = pStatement.executeUpdate();
    		
    		if (rows > 0)
    			return true;
    		return false;
    		
    	}catch (Exception e) {
			// TODO: handle exception
    		e.printStackTrace();
		}
    	
    	return false;
    }
    
}
