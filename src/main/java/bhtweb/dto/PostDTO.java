package bhtweb.dto;

import java.util.Date;

import bhtweb.entities.BHTPost;

public class PostDTO {

	private Long postID;
	private String postTitle;
	private String postContentURL;
	private Date postSubmitDtm;
	private Date postPublishDtm;
	//Lưu trữ thời gian đọc tính theo phút.
	private Long postReadTime;
	
	private Long numVote;
	private Long numView;
	
	private Boolean postSoftDeleted;
	private Boolean postHidden;
	private Boolean postApproved;
	
	//Khoá ngoại.
	private Long posterUserID;
	//Khoá ngoại.
	private Long postCategoryID;
	
	public PostDTO() {
		
	}
	
	public PostDTO (BHTPost entity) {
		this.postID = entity.getPostID();
		this.postTitle = entity.getPostTitle();
		this.postContentURL = entity.getPostContentURL();
		this.postSubmitDtm = entity.getPostSubmitDtm();
		this.postPublishDtm = entity.getPostPublishDtm();
		this.postReadTime = entity.getPostReadTime();
		this.numVote = entity.getNumVote();
		this.numView = entity.getNumView();
		this.postSoftDeleted = entity.getPostSoftDeleted();
		this.postHidden = entity.getPostHidden();
		this.postApproved = entity.getPostApproved();
		
		this.posterUserID = entity.getPosterUserID();
		this.postCategoryID = entity.getPostCategoryID();
	}
	
	public Long getPostID() {
		return postID;
	}
	public void setPostID(Long postID) {
		this.postID = postID;
	}
	public String getPostTitle() {
		return postTitle;
	}
	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}
	public String getPostContentURL() {
		return postContentURL;
	}
	public void setPostContentURL(String postContentURL) {
		this.postContentURL = postContentURL;
	}
	public Date getPostSubmitDtm() {
		return postSubmitDtm;
	}
	public void setPostSubmitDtm(Date postSubmitDtm) {
		this.postSubmitDtm = postSubmitDtm;
	}
	public Date getPostPublishDtm() {
		return postPublishDtm;
	}
	public void setPostPublishDtm(Date postPublishDtm) {
		this.postPublishDtm = postPublishDtm;
	}
	public Long getPostReadTime() {
		return postReadTime;
	}
	public void setPostReadTime(Long postReadTime) {
		this.postReadTime = postReadTime;
	}
	public Long getNumVote() {
		return numVote;
	}
	public void setNumVote(Long numVote) {
		this.numVote = numVote;
	}
	public Long getNumView() {
		return numView;
	}
	public void setNumView(Long numView) {
		this.numView = numView;
	}
	public Boolean getPostSoftDeleted() {
		return postSoftDeleted;
	}
	public void setPostSoftDeleted(Boolean postSoftDeleted) {
		this.postSoftDeleted = postSoftDeleted;
	}
	public Boolean getPostHidden() {
		return postHidden;
	}
	public void setPostHidden(Boolean postHidden) {
		this.postHidden = postHidden;
	}
	public Boolean getPostApproved() {
		return postApproved;
	}
	public void setPostApproved(Boolean postApproved) {
		this.postApproved = postApproved;
	}
	public Long getPosterUserID() {
		return posterUserID;
	}
	public void setPosterUserID(Long posterUserID) {
		this.posterUserID = posterUserID;
	}
	public Long getPostCategoryID() {
		return postCategoryID;
	}
	public void setPostCategoryID(Long postCategoryID) {
		this.postCategoryID = postCategoryID;
	}
}
