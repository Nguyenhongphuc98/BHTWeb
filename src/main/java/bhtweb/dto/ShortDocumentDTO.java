/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bhtweb.dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author NguyenHongPhuc
 */
public class ShortDocumentDTO {
    
    	String id;
	String url;
	String title;
	String summary;
	String authorName;
	String authorID;
	String categoryID;
	String categoryName;
	String subjectID;
	String subjectName;
	String publishDate;
	String imageURL;
        
        int viewCount;
	int downloadCount;

    public ShortDocumentDTO(String id, String url, String title, String summary, String authorName, String authorID, String categoryID, String categoryName, String subjectID, String subjectName, String publishDate, String imageURL, int viewCount, int downloadCount) {
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
        this.publishDate = publishDate;
        this.imageURL = imageURL;
        this.viewCount = viewCount;
        this.downloadCount = downloadCount;
    }
    
    public static List<ShortDocumentDTO> makeDocs() {
        List<ShortDocumentDTO> docs = new ArrayList<>();
        docs.add(new ShortDocumentDTO("1",
                "docs1-url", "bai do so 1",
                "sumaru aiig", "Nguyen Hong Phuc",
                "1", "1", "Chuyen tam linh", "1",
                "Tam ly hoc", "22/15/2030",
                "image",999, 23));
        docs.add(new ShortDocumentDTO("2",
                "docs1-url", "bai do so 3",
                "sumaru aiig", "Nguyen Hong Pink",
                "1", "1", "Chuyen tam linh", "1",
                "Tam ly hoc", "22/15/2030",
                "image",999, 23));
        docs.add(new ShortDocumentDTO("3",
                "docs1-url", "bai do so 3",
                "sumaru aiig", "Nguyen Hong Blue",
                "1", "1", "Chuyen tam linh", "1",
                "Tam ly hoc", "22/15/2030",
                "image",999, 23));
        return docs;
    }

    public static ShortDocumentDTO makeDoc(String id) {
        return new ShortDocumentDTO(id,
                "docs1-url", "bai do so 1",
                "sumaru aiig", "Nguyen Hong Phuc",
                "1", "1", "Chuyen tam linh", "1",
                "Tam ly hoc", "22/15/2030",
                "image",999, 23);
    }
    
    public ShortDocumentDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getAuthorID() {
        return authorID;
    }

    public void setAuthorID(String authorID) {
        this.authorID = authorID;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
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

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
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
