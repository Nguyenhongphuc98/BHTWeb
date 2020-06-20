package bhtweb.entities;

import java.sql.Date;

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
    
    int id;
    
    int userId;
    
    int postId;
    
    boolean isSoftDeleted;
    
    boolean isHidden;
    
    boolean isApproved;
    
    String contenttUrl;
    
    int parentId;
    
    Date commentTime;

    public BHTComment() {
    }

    public BHTComment(int id, int userId, int postId, boolean isSoftDeleted, boolean isHidden, boolean isApproved, String commentUrl, int parentId, Date commentTime) {
        this.id = id;
        this.userId = userId;
        this.postId = postId;
        this.isSoftDeleted = isSoftDeleted;
        this.isHidden = isHidden;
        this.isApproved = isApproved;
        this.contenttUrl = commentUrl;
        this.parentId = parentId;
        this.commentTime = commentTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getContentUrl() {
        return contenttUrl;
    }

    public void setContentUrl(String commentUrl) {
        this.contenttUrl = commentUrl;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }
}
