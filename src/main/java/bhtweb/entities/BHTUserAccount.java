package bhtweb.entities;

import java.util.ArrayList;
import java.util.List;

public class BHTUserAccount {

	int userID;
	
	String userName;
	
	String profilePictureURL;
	
	String email;
	
	String userPassword;
	
	String facebookToken;
	
	String googleToken;
	
	Integer postScore;
	
	Integer userGroupID;
	
	String displayName;
	
	List<BHTPost> likedPosts = new ArrayList<>();
	
	List<BHTPost> savedPosts = new ArrayList<>();
	
	public BHTUserAccount() {
		facebookToken = "";
		googleToken = "";
		postScore = 0;
	}
	
	public BHTUserAccount(Integer userID) {
		super();
		
		facebookToken = "";
		googleToken = "";
		postScore = 0;
		
		this.userID = userID;
	}

	public BHTUserAccount(int userID, 
			String userName,
			String profilePictureURL, 
			String email,
			String userPassword,
			String facebookToken,
			String googleToken, 
			Integer postScore, 
			int userGroupID,
			String displayName) {
		super();
		this.userID = userID;
		this.userName = userName;
		this.profilePictureURL = profilePictureURL;
		this.email = email;
		this.userPassword = userPassword;
		this.facebookToken = facebookToken;
		this.googleToken = googleToken;
		this.postScore = postScore;
		this.userGroupID = userGroupID;
		this.displayName = displayName;
	}
	
	public List<BHTPost> getLikedPosts() {
		return likedPosts;
	}

	public void setLikedPosts(List<BHTPost> likedPosts) {
		this.likedPosts = likedPosts;
	}

	public List<BHTPost> getSavedPosts() {
		return savedPosts;
	}

	public void setSavedPosts(List<BHTPost> savedPosts) {
		this.savedPosts = savedPosts;
	}

	public Integer getUserID() {
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

	public String getProfilePictureURL() {
		return profilePictureURL;
	}

	public void setProfilePictureURL(String profilePictureURL) {
		this.profilePictureURL = profilePictureURL;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getFacebookToken() {
		return facebookToken;
	}

	public void setFacebookToken(String facebookToken) {
		this.facebookToken = facebookToken;
	}

	public String getGoogleToken() {
		return googleToken;
	}

	public void setGoogleToken(String googleToken) {
		this.googleToken = googleToken;
	}

	public Integer getPostScore() {
		return postScore;
	}

	public void setPostScore(Integer postScore) {
		this.postScore = postScore;
	}

	public Integer getUserGroupID() {
		return userGroupID;
	}

	public void setUserGroupID(int userGroupID) {
		this.userGroupID = userGroupID;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public void setUserGroupID(Integer userGroupID) {
		this.userGroupID = userGroupID;
	}
}
