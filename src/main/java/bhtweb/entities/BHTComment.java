package bhtweb.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.output.ThresholdingOutputStream;

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
    
    private Integer commentID;
    
    private BHTUserAccount userAccount;
    
    private Integer postId;
    
    private Boolean commentSoftDeleted;
    	
    private Boolean commentHidden;
    
    private Boolean commentApproved;
    
    private String commentContent;
    
    private BHTComment parentComment;
    
    private Date commentDtm;
       
    private List<BHTComment> childComments = new ArrayList<>();
    
    public BHTComment(CommentDTO commentDTO) {
    	this.setCommentID(commentDTO.getId());
    	this.setUserAccount(new BHTUserAccount(commentDTO.getUserID()));
    	this.setPostId(commentDTO.getPostID());
    	this.setCommentSoftDeleted((commentDTO.getCommentSoftDeleted()) == null ? (false) : (commentDTO.getCommentSoftDeleted()));
    	this.setCommentHidden(commentDTO.getCommentHidden() == null ? (false) : (commentDTO.getCommentHidden()));
    	this.setCommentApproved(commentDTO.getCommentApproved() == null ? (false) : (commentDTO.getCommentApproved()));
    	this.setCommentContent(commentDTO.getContent());
    	this.setParentComment(new BHTComment(commentDTO.getId()));
    	this.setCommentDtm(commentDTO.getPostTimeStamp() == null ? (new Date()) : (commentDTO.getPostTimeStamp()));
    }
    
    public BHTComment() {
    }
    
    public BHTComment(Integer commentID) {
    	super();
    	this.commentID = commentID;
    }

	public BHTComment(Integer commentID, Integer userId, Integer postId, Boolean commentSoftDeleted, Boolean commentHidden,
			Boolean commentApproved, String commentContent, BHTComment parentComment, Date commentDtm) {
		super();
		this.commentID = commentID;
		this.userAccount = new BHTUserAccount();
		userAccount.setUserID(userId);
		this.postId = postId;
		this.commentSoftDeleted = commentSoftDeleted;
		this.commentHidden = commentHidden;
		this.commentApproved = commentApproved;
		this.commentContent = commentContent;
		this.parentComment = parentComment;
		this.commentDtm = commentDtm;
	}
	
	//Một comment là cha khi nó không có cha.
	public Boolean isParent() {
		return getParentComment() == null || getParentComment().getCommentID() == null || getParentComment().getCommentID() == 0;
	}
	
	//Một comment là con khi nó có cha.
	public Boolean isChild() {
		return !isParent();
	}
	
	public BHTComment getParentComment() {
		return parentComment;
	}

	public void setParentComment(BHTComment parentComment) {
		this.parentComment = parentComment;
	}

	public Integer getCommentID() {
		return commentID;
	}

	public void setCommentID(Integer commentID) {
		this.commentID = commentID;
	}

	public BHTUserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(BHTUserAccount userAccount) {
		this.userAccount = userAccount;
	}

	public Integer getPostId() {
		return postId;
	}

	public void setPostId(Integer postId) {
		this.postId = postId;
	}

	public Boolean isCommentSoftDeleted() {
		return commentSoftDeleted;
	}

	public void setCommentSoftDeleted(Boolean commentSoftDeleted) {
		this.commentSoftDeleted = commentSoftDeleted;
	}

	public Boolean isCommentHidden() {
		return commentHidden;
	}

	public void setCommentHidden(Boolean commentHidden) {
		this.commentHidden = commentHidden;
	}

	public Boolean isCommentApproved() {
		return commentApproved;
	}

	public void setCommentApproved(Boolean commentApproved) {
		this.commentApproved = commentApproved;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public Date getCommentDtm() {
		return commentDtm;
	}

	public void setCommentDtm(Date commentDtm) {
		this.commentDtm = commentDtm;
	}

	public List<BHTComment> getChildComments() {
		return childComments;
	}

	public void setChildComments(List<BHTComment> childComments) {
		this.childComments = childComments;
	}
}
