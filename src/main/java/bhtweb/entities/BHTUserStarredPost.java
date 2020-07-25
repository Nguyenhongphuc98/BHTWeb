package bhtweb.entities;

import java.util.HashMap;
import java.util.Map;

public class BHTUserStarredPost {
	
	private BHTUserAccount userAccount;
	private BHTPost post;
	
	public BHTUserStarredPost () {
		
	}
	
	public BHTUserStarredPost (Integer userID, Integer postID) {
		userAccount = new BHTUserAccount(userID);
		post = new BHTPost(postID);
	}
	
	public BHTUserAccount getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(BHTUserAccount userAccount) {
		this.userAccount = userAccount;
	}
	public BHTPost getPost() {
		return post;
	}
	public void setPost(BHTPost post) {
		this.post = post;
	}
}
