package bhtweb.dto;

import java.util.ArrayList;
import java.util.Date;

import bhtweb.entities.BHTPost;

public class PostDTO {

	private Integer id;
	private String title;
	
	private String imageURL;
	
	private String content;
	
	private Date submitDate;
	private Date publishDate;
	//Lưu trữ thời gian đọc tính theo phút.
	private Long readTime;
	
	private Long likeCount;
	
	private Boolean liked;
	
	private Long commentCount;
	
	private Long numView;
	
	private Boolean postSoftDeleted;
	private Boolean postHidden;
	private Boolean postApproved;
	
	//Khoá ngoại.
	private Integer authorID;
	
	private String authorName;
	
	//Khoá ngoại.
	private Integer categoryID;
	
	private String categoryName;
	
	private String authorAvatarURL;

	private Boolean saved;
	
	private String summary;
	
	private ArrayList<String> tags;
	
	public PostDTO() {
		
	}
	
	public PostDTO (BHTPost entity) {
		this.id = entity.getPostID();
		this.title = entity.getPostTitle();
		this.content = entity.getPostContent();
		this.summary = entity.getPostSummary();
		this.submitDate = entity.getPostSubmitDtm();
		this.publishDate = entity.getPostPublishDtm();
		this.readTime = entity.getPostReadTime();
		this.likeCount = entity.getNumVote();
		this.numView = entity.getNumView();
		this.postSoftDeleted = entity.getPostSoftDeleted();
		this.postHidden = entity.getPostHidden();
		this.postApproved = entity.getPostApproved();
		
		this.authorID = entity.getPoster().getUserID();
		this.categoryID = entity.getPostCategory().getPostCategoryID();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String contentURL) {
		this.content = contentURL;
	}

	public Date getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public Long getReadTime() {
		return readTime;
	}

	public void setReadTime(Long readTime) {
		this.readTime = readTime;
	}

	public Long getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(Long likeCount) {
		this.likeCount = likeCount;
	}

	public Boolean getLiked() {
		return liked;
	}

	public void setLiked(Boolean liked) {
		this.liked = liked;
	}

	public Long getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Long commentCount) {
		this.commentCount = commentCount;
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

	public Integer getAuthorID() {
		return authorID;
	}

	public void setAuthorID(Integer authorID) {
		this.authorID = authorID;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public Integer getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(Integer categoryID) {
		this.categoryID = categoryID;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getAuthorAvatarURL() {
		return authorAvatarURL;
	}

	public void setAuthorAvatarURL(String authorAvatarURL) {
		this.authorAvatarURL = authorAvatarURL;
	}

	public Boolean getSaved() {
		return saved;
	}

	public void setSaved(Boolean saved) {
		this.saved = saved;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public ArrayList<String> getTags() {
		return tags;
	}

	public void setTags(ArrayList<String> tags) {
		this.tags = tags;
	}
}
