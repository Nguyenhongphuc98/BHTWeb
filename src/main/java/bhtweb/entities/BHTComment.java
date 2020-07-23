package bhtweb.entities;

import java.util.Date;
import java.util.List;

import bhtweb.dto.CommentDTO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author NguyenHongPhuc
 */
public class BHTComment {
    
    private int commentID;
    
    private int userId;
    
    private int postId;
    
    private boolean commentSoftDeleted;
    
    private boolean commentHidden;
    
    private boolean commentApproved;
    
    private String commentContent;
    
    private int parentCommentID;
    
    private Date commentDtm;
       
    public BHTComment(CommentDTO commentDTO) {
    	this.setCommentID(commentDTO.getId());
    	this.setUserId(commentDTO.getUserID());
    	this.setPostId(commentDTO.getPostID());
    	this.setCommentSoftDeleted(commentDTO.getCommentSoftDeleted());
    	this.setCommentHidden(commentDTO.getCommentHidden());
    	this.setCommentApproved(commentDTO.getCommentApproved());
    	this.setCommentContent(commentDTO.getContent());
    	this.setParentCommentID(commentDTO.getParentCommentID());
    	this.setCommentDtm(commentDTO.getPostTimeStamp());
    }
    
    public BHTComment() {
    }

	public BHTComment(int commentID, int userId, int postId, boolean commentSoftDeleted, boolean commentHidden,
			boolean commentApproved, String commentContent, int parentCommentID, Date commentDtm) {
		super();
		this.commentID = commentID;
		this.userId = userId;
		this.postId = postId;
		this.commentSoftDeleted = commentSoftDeleted;
		this.commentHidden = commentHidden;
		this.commentApproved = commentApproved;
		this.commentContent = commentContent;
		this.parentCommentID = parentCommentID;
		this.commentDtm = commentDtm;
	}

	public int getCommentID() {
		return commentID;
	}

	public void setCommentID(int commentID) {
		this.commentID = commentID;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public boolean isCommentSoftDeleted() {
		return commentSoftDeleted;
	}

	public void setCommentSoftDeleted(boolean commentSoftDeleted) {
		this.commentSoftDeleted = commentSoftDeleted;
	}

	public boolean isCommentHidden() {
		return commentHidden;
	}

	public void setCommentHidden(boolean commentHidden) {
		this.commentHidden = commentHidden;
	}

	public boolean isCommentApproved() {
		return commentApproved;
	}

	public void setCommentApproved(boolean commentApproved) {
		this.commentApproved = commentApproved;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public int getParentCommentID() {
		return parentCommentID;
	}

	public void setParentCommentID(int parentCommentID) {
		this.parentCommentID = parentCommentID;
	}

	public Date getCommentDtm() {
		return commentDtm;
	}

	public void setCommentDtm(Date commentDtm) {
		this.commentDtm = commentDtm;
	}
}
