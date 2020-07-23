/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bhtweb.dto;

import bhtweb.entities.BHTDocument;

/**
 *
 * @author NguyenHongPhuc
 * Chứa thông tin của document detail
 */

public class DocumentDTO {
	
	int id;
	
    String url;
    
    String title;
    
    String summary;
    
    String authorName;
    
    int authorID;
    
    String authorAvatar;
    
    int categoryID;
    
    String categoryName;
    
    int subjectID;
    
    String subjectName;
    
    Integer viewCount;
    
    Integer downloadCount;
    
    String publishDate;
    
    String imageURL;
    
    String fileName;
    
    public DocumentDTO() {
    	
    }
    
	public DocumentDTO(BHTDocument entity,
			String authorName,
			String authorAvatar,
			String categoryName,
			String subjectName) {
		
		this.categoryID = entity.getCategoryId();
		this.url = entity.getContentUrl();
		this.summary = entity.getDescription();
		this.downloadCount = entity.getDownloadCount();
		this.id = entity.getId();
		//this. = entity.getSemesterId();
		this.subjectID = entity.getSubjectId();
		this.title = entity.getTitle();
		this.authorID = entity.getUploaderId();
		this.viewCount = entity.getViewCount();
		this.fileName = entity.getDocumentFileName();
		
		this.authorName = authorName;
		this.authorAvatar = authorAvatar;
		this.categoryName = categoryName;
		this.subjectName = subjectName;
	}

	public DocumentDTO(int id, String url, String title, String summary, String authorName, int authorID,
			int categoryID, String categoryName, int subjectID, String subjectName, Integer viewCount,
			Integer downloadCount, String publishDate, String imageURL, String authorAvatar) {
	
		this.id = id;
		this.url = url;
		this.title = title;
		this.summary = summary;
		this.authorName = authorName;
		this.authorID = authorID;
		this.categoryID = categoryID;
		this.categoryName = categoryName;
		this.subjectID = subjectID;
		this.subjectName = subjectName;
		this.viewCount = viewCount;
		this.downloadCount = downloadCount;
		this.publishDate = publishDate;
		this.imageURL = imageURL;
		this.authorAvatar = authorAvatar;
	}
	
	@Override
	public String toString() {
		return "id: "
				+ this.id
				+ "url: " + this.url
				+ "title: " + this.title
				+ "category: " + this.categoryName
				+ "subject: " + this.subjectName
				+ "views: " + this.viewCount
				+ "downloads: " + this.downloadCount
				+ "date: " + this.publishDate
				+ "useravt: " + this.authorAvatar;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public int getAuthorID() {
		return authorID;
	}

	public void setAuthorID(int authorID) {
		this.authorID = authorID;
	}

	public int getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public int getSubjectID() {
		return subjectID;
	}

	public void setSubjectID(int subjectID) {
		this.subjectID = subjectID;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public Integer getViewCount() {
		return viewCount;
	}

	public void setViewCount(Integer viewCount) {
		this.viewCount = viewCount;
	}

	public Integer getDownloadCount() {
		return downloadCount;
	}

	public void setDownloadCount(Integer downloadCount) {
		this.downloadCount = downloadCount;
	}

	public String getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public String getAuthorAvatar() {
		return authorAvatar;
	}

	public void setAuthorAvatar(String authorAvatar) {
		this.authorAvatar = authorAvatar;
	}
}
