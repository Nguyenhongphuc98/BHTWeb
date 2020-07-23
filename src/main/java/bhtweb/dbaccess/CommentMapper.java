package bhtweb.dbaccess;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import bhtweb.entities.BHTComment;

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

	private List<BHTComment> fetchListComments(String sqlStr) {
        List<BHTComment> comments = new ArrayList<>();

		try {
			Statement stmt = getConnection().createStatement();
			ResultSet rs;

			rs = stmt.executeQuery(sqlStr); // Send the query to the server
			while (rs != null && rs.next()) {
				BHTComment d = new BHTComment();
				d.setCommentID(rs.getInt("CommentID"));
				d.setUserId(rs.getInt("UserID"));
				d.setPostId(rs.getInt("PostID"));
				d.setCommentSoftDeleted(rs.getBoolean("CommentSoftDeleted"));
				d.setCommentHidden(rs.getBoolean("CommentHidden"));
				d.setCommentApproved(rs.getBoolean("CommentApproved"));
				d.setCommentContent(rs.getString("CommentContentURL"));
				d.setParentCommentID(rs.getInt("ParentCommentID"));
				d.setCommentDtm(rs.getDate("CommentDtm"));

				comments.add(d);
			}
		} catch (SQLException ex) {
			Logger.getLogger(DocumentMapper.class.getName()).log(Level.SEVERE, null, ex);
		}
		return comments;
    }
    
    public List<BHTComment> getCommentByParentId(int commentId, boolean isApproved) {
        String sqlStr = "SELECT * FROM comment WHERE ParentCommentID = " + commentId + " and CommentApproved = " + isApproved;
        List<BHTComment> ls = fetchListComments(sqlStr);
        return ls.size() > 0 ? ls : null;

    }
    
    public List<BHTComment> getCommentByParentId(int commentID){
    	String sqlStr = "SELECT * FROM comment WHERE ParentCommentID = " + commentID;
        List<BHTComment> ls = fetchListComments(sqlStr);
        return ls.size() > 0 ? ls : null;
    }
    
    public List<BHTComment> getCommentByPostId(int postId, boolean isApproved) {
        String sqlStr = "SELECT * FROM comment WHERE PostID = " + postId + " and CommentApproved = " + isApproved;
        List<BHTComment> ls = fetchListComments(sqlStr);
        return ls.size() > 0 ? ls : null;
    }
    
    public List<BHTComment> getCommentByPostId(int postId){
    	String sqlStr = "SELECT * FROM comment WHERE PostID = " + postId;
        List<BHTComment> ls = fetchListComments(sqlStr);
        return ls.size() > 0 ? ls : null;
    }
      
    public boolean saveComment(BHTComment comment) {
        
        // the mysql insert statement
        String query = " INSERT INTO comment (UserID, PostID,"
                + "CommentSoftDeleted, CommentHidden, CommentApproved, CommentContentURL,"
                + "ParentCommentID, CommentDtm)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try {
        	// create the mysql insert preparedstatement
            PreparedStatement preparedStmt = getConnection().prepareStatement(query);
            preparedStmt.setInt(1, comment.getUserId());
            preparedStmt.setInt(2, comment.getPostId());
            preparedStmt.setBoolean(3, comment.isCommentSoftDeleted());
            preparedStmt.setBoolean(4, comment.isCommentHidden());
            preparedStmt.setBoolean(5, comment.isCommentApproved());
            preparedStmt.setString(6, comment.getCommentContent());
            preparedStmt.setInt(7, comment.getPostId());
            preparedStmt.setDate(8, comment.getCommentDtm());
            
            // execute the preparedstatement
            return preparedStmt.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(DocumentMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
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
}
