/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bhtweb.entities;

import java.sql.Date;

/**
 *
 * @author NguyenHongPhuc
 */
public class BHTDocument {
    
    int id;
    
    String title;
    
    String description;
    
    int uploaderId;
    
    String contentUrl;
    
    boolean isSoftDeleted;
    
    boolean isHidden;
    
    boolean isApproved;
    
    Integer viewCount;
    
    Integer downloadCount;
    
    int semesterId;
    
    int subjectId;
    
    int categoryId;
    
    Date documentPublishDtm;

    public BHTDocument() {
    	
    	this.viewCount = 0;
    	this.downloadCount = 0;
    	
    	this.isApproved = false;
    	this.isSoftDeleted = false;
    	this.isHidden = false;
    	this.documentPublishDtm = new Date(new java.util.Date().getTime());
    }

    public BHTDocument(int id, String title, String description,
    		int uploaderId, String contentUrl, boolean isSoftDeleted,
    		boolean isHidden, boolean isApproved, Integer viewCount,
    		Integer downloadCount, int semesterId, int subjectId,
    		int categoryId, Date date) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.uploaderId = uploaderId;
        this.contentUrl = contentUrl;
        this.isSoftDeleted = isSoftDeleted;
        this.isHidden = isHidden;
        this.isApproved = isApproved;
        this.viewCount = viewCount;
        this.downloadCount = downloadCount;
        this.semesterId = semesterId;
        this.subjectId = subjectId;
        this.categoryId = categoryId;
        
        this.documentPublishDtm = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getUploaderId() {
        return uploaderId;
    }

    public void setUploaderId(int uploaderId) {
        this.uploaderId = uploaderId;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }

    public boolean isIsSoftDeleted() {
        return isSoftDeleted;
    }

    public void setIsSoftDeleted(boolean isSoftDeleted) {
        this.isSoftDeleted = isSoftDeleted;
    }

    public boolean isIsHidden() {
        return isHidden;
    }

    public void setIsHidden(boolean isHidden) {
        this.isHidden = isHidden;
    }

    public boolean isIsApproved() {
        return isApproved;
    }

    public void setIsApproved(boolean isApproved) {
        this.isApproved = isApproved;
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

    public int getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(int semesterId) {
        this.semesterId = semesterId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

	public boolean isSoftDeleted() {
		return isSoftDeleted;
	}

	public void setSoftDeleted(boolean isSoftDeleted) {
		this.isSoftDeleted = isSoftDeleted;
	}

	public boolean isHidden() {
		return isHidden;
	}

	public void setHidden(boolean isHidden) {
		this.isHidden = isHidden;
	}

	public boolean isApproved() {
		return isApproved;
	}

	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}

	public Date getDocumentPublishDtm() {
		return documentPublishDtm;
	}

	public void setDocumentPublishDtm(Date documentPublishDtm) {
		this.documentPublishDtm = documentPublishDtm;
	}
}
