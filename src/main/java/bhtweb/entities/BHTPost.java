package bhtweb.entities;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.text.AbstractDocument.DefaultDocumentEvent;

import bhtweb.dto.PostDTO;

public class BHTPost {
	private Integer postID;
	private String postTitle;
	private String postContent;
	private String postSummary;
	private String imageURL;
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
	private BHTUserAccount poster;
	//Khoá ngoại.
	private BHTPostCategory postCategory;
	
	//Những comment thuộc bài post.
	private List<BHTComment> postComments;
	
	//Những userID đã like bài post.
	private Map<Integer, Boolean> likedUsers = new HashMap<>();
	
	//Những userID đã saved bài post.
	private Map<Integer, Boolean> savedUsers = new HashMap<>();
	
	public BHTPost() {
		
	}
	
	public BHTPost (Integer postID) {
		this.postID = postID;
	}
	
	public BHTPost(PostDTO dto) {
		this.postID = dto.getId();
		this.postTitle = dto.getTitle();
		this.postContent = dto.getContent();
		this.postSummary = dto.getSummary();
		this.imageURL = dto.getImageURL();
		this.postSubmitDtm = dto.getSubmitDate();
		this.postPublishDtm = dto.getPublishDate();
		this.postReadTime = dto.getReadTime();
		this.numVote = dto.getLikeCount();
		this.numView = dto.getNumView();
		this.postSoftDeleted = dto.getPostSoftDeleted();
		this.postHidden = dto.getPostHidden();
		this.postApproved = dto.getPostApproved();
		
		this.poster = new BHTUserAccount(dto.getAuthorID());
		this.postCategory = new BHTPostCategory(dto.getCategoryID());
	}
	
	public String getPostSummary() {
		return postSummary;
	}

	public void setPostSummary(String postSummary) {
		this.postSummary = postSummary;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public String getPostContent() {
		return postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}

	public Integer getPostID() {
		return postID;
	}

	public void setPostID(Integer postID) {
		this.postID = postID;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
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

	public BHTUserAccount getPoster() {
		return poster;
	}

	public void setPoster(BHTUserAccount poster) {
		this.poster = poster;
	}

	public BHTPostCategory getPostCategory() {
		return postCategory;
	}

	public void setPostCategory(BHTPostCategory postCategory) {
		this.postCategory = postCategory;
	}

	public List<BHTComment> getPostComments() {
		return postComments;
	}

	public void setPostComments(List<BHTComment> postComments) {
		this.postComments = postComments;
	}

	public Map<Integer, Boolean> getLikedUsers() {
		return likedUsers;
	}

	public void setLikedUsers(Map<Integer, Boolean> likedUsers) {
		this.likedUsers = likedUsers;
	}

	public Map<Integer, Boolean> getSavedUsers() {
		return savedUsers;
	}

	public void setSavedUsers(Map<Integer, Boolean> savedUsers) {
		this.savedUsers = savedUsers;
	}
}
