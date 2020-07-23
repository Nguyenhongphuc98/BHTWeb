package bhtweb.entities;

import java.util.Date;
import java.util.List;

import javax.swing.text.AbstractDocument.DefaultDocumentEvent;

import bhtweb.dto.PostDTO;

public class BHTPost {
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
	
	//Những comment thuộc bài post.
	private List<BHTComment> postComments;
	
	public BHTPost() {
		
	}
	
	public BHTPost(PostDTO dto) {
		this.postID = dto.getId();
		this.postTitle = dto.getTitle();
		this.postContentURL = dto.getContentURL();
		this.postSubmitDtm = dto.getSubmitDate();
		this.postPublishDtm = dto.getPublishDate();
		this.postReadTime = dto.getReadTime();
		this.numVote = dto.getLikeCount();
		this.numView = dto.getNumView();
		this.postSoftDeleted = dto.getPostSoftDeleted();
		this.postHidden = dto.getPostHidden();
		this.postApproved = dto.getPostApproved();
		
		this.posterUserID = dto.getAuthorID();
		this.postCategoryID = dto.getCategoryID();
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

	public List<BHTComment> getPostComments() {
		return postComments;
	}

	public void setPostComments(List<BHTComment> postComments) {
		this.postComments = postComments;
	}
}
