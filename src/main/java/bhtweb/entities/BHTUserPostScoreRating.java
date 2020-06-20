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
public class BHTUserPostScoreRating {
    
    int userPostScoreRatingID;
    
    int userID;
    
    String userName;
    
    Integer userScore;

    public BHTUserPostScoreRating() {
    }

    public BHTUserPostScoreRating(int userPostScoreRatingID, int userID, String userName, Integer userScore) {
        this.userPostScoreRatingID = userPostScoreRatingID;
        this.userID = userID;
        this.userName = userName;
        this.userScore = userScore;
    }

    public int getUserPostScoreRatingID() {
        return userPostScoreRatingID;
    }

    public void setUserPostScoreRatingID(int userPostScoreRatingID) {
        this.userPostScoreRatingID = userPostScoreRatingID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserScore() {
        return userScore;
    }

    public void setUserScore(Integer userScore) {
        this.userScore = userScore;
    }
    
}
