/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bhtweb.entities;
/**
 *
 * @author NguyenHongPhuc
 */
public class BHTProtected_commentaction {
    
    int commentActionID;
    
    String commentActionName;

    public BHTProtected_commentaction() {
    }

    public BHTProtected_commentaction(int commentActionID, String commentActionName) {
        this.commentActionID = commentActionID;
        this.commentActionName = commentActionName;
    }

    public int getCommentActionID() {
        return commentActionID;
    }

    public void setCommentActionID(int commentActionID) {
        this.commentActionID = commentActionID;
    }

    public String getCommentActionName() {
        return commentActionName;
    }

    public void setCommentActionName(String commentActionName) {
        this.commentActionName = commentActionName;
    }
    
}
