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
				d.setId(rs.getInt("CommentID"));
				d.setUserId(rs.getInt("UserID"));
				d.setPostId(rs.getInt("PostID"));
				d.setIsSoftDeleted(rs.getBoolean("CommentSoftDeleted"));
				d.setIsHidden(rs.getBoolean("CommentHidden"));
				d.setIsApproved(rs.getBoolean("CommentApproved"));
				d.setContentUrl(rs.getString("CommentContentURL"));
				d.setParentId(rs.getInt("ParentCommentID"));
				d.setCommentTime(rs.getDate("CommentDtm"));

				comments.add(d);
			}
		} catch (SQLException ex) {
			Logger.getLogger(DocumentMapper.class.getName()).log(Level.SEVERE, null, ex);
		}

		return comments;
    }
    
    public BHTComment getCommentByParentId(int commentId, boolean isApproved) {

        String sqlStr = "SELECT * FROM comment WHERE CommentID = " + commentId + " and DocumentApproved = " + isApproved;
        List<BHTComment> ls = fetchListComments(sqlStr);
        return ls.size() > 0 ? ls.get(0) : null;

    }
    
    public BHTComment getCommentByPostId(int postId, boolean isApproved) {

        String sqlStr = "SELECT * FROM comment WHERE PostID = " + postId + " and DocumentApproved = " + isApproved;
        List<BHTComment> ls = fetchListComments(sqlStr);
        return ls.size() > 0 ? ls.get(0) : null;

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
            preparedStmt.setBoolean(3, comment.isIsSoftDeleted());
            preparedStmt.setBoolean(4, comment.isIsHidden());
            preparedStmt.setBoolean(5, comment.isIsApproved());
            preparedStmt.setString(6, comment.getContentUrl());
            preparedStmt.setInt(7, comment.getPostId());
            preparedStmt.setDate(8, comment.getCommentTime());
            
            // execute the preparedstatement
            return preparedStmt.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(DocumentMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public boolean updateComment(BHTComment comment, boolean delete, boolean hidden, boolean approved) {
        
        String sqlStr = "UPDATE comment SET ";
        
        if (comment.getContentUrl()!= null) {
            sqlStr += "CommentContentURL = '" + comment.getContentUrl() + "',";
        }
        if (delete) {
            sqlStr += "CommentSoftDeleted = " + comment.isIsSoftDeleted() + ",";
        }
        if (hidden) {
            sqlStr += "CommentHidden = " + comment.isIsHidden()+ ",";
        }
        if (approved) {
            sqlStr += "CommentApproved = " + comment.isIsApproved()+ ",";
        }
       
        /// Khong cho update user, post, parent and time comment
        
        //  Xoa dau --',-- cuoi cung neu co
        if (sqlStr.substring(sqlStr.length() - 2, sqlStr.length() - 1).equals("',")) {
            sqlStr = sqlStr.substring(0, sqlStr.length() - 3);
        } else if (sqlStr.charAt(sqlStr.length() - 1) == '\'') {
            sqlStr = sqlStr.substring(0, sqlStr.length() - 2);
        }
        
        sqlStr += " WHERE CommentID = " + comment.getId();
        
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
