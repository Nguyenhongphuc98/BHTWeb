/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bhtweb.dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import bhtweb.entities.BHTDocument;

/**
 *
 * @author NguyenHongPhuc
 */

public class ShortDocumentDTO {

	int id;
	String title;
	String summary;
	String authorName;
	int authorID;
	int categoryID;
	String categoryName;
	int subjectID;
	String subjectName;
	String publishDate;

	int viewCount;
	int downloadCount;
	
	public ShortDocumentDTO(BHTDocument entiry, String authorName, String subjectName, String categoryName) {
        
		this.id = entiry.getId();
        this.title = entiry.getTitle();
        this.summary = entiry.getDescription();
        this.authorID = entiry.getUploaderId();
        this.categoryID = entiry.getCategoryId();
        this.subjectID = entiry.getSubjectId();
        //this.publishDate = entiry.get;
        this.viewCount = entiry.getViewCount();
        this.downloadCount = entiry.getDownloadCount();
        
        this.authorName = authorName;
        this.categoryName = categoryName;
        this.subjectName = subjectName;
    }

    public ShortDocumentDTO(int id, String title, String summary, String authorName, int authorID, int categoryID, String categoryName, int subjectID, String subjectName, String publishDate, int viewCount, int downloadCount) {
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.authorName = authorName;
        this.authorID = authorID;
        this.categoryID = categoryID;
        this.categoryName = categoryName;
        this.subjectID = subjectID;
        this.subjectName = subjectName;
        this.publishDate = publishDate;
        this.viewCount = viewCount;
        this.downloadCount = downloadCount;
    }
    
    public ShortDocumentDTO() {
    	
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

	public String getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public int getDownloadCount() {
		return downloadCount;
	}

	public void setDownloadCount(int downloadCount) {
		this.downloadCount = downloadCount;
	}

}
