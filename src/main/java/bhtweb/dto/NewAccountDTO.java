package bhtweb.dto;

// when create account wwe use this account.
public class NewAccountDTO {

	String userName;
	
	String profilePictureURL;
	
	String email;
	
	String userPassword;
	
	Integer userGroupID;

	public NewAccountDTO() {

	}

	public NewAccountDTO(String userName, String profilePictureURL, String email, String userPassword,
			Integer userGroupID) {
		super();
		this.userName = userName;
		this.profilePictureURL = profilePictureURL;
		this.email = email;
		this.userPassword = userPassword;
		this.userGroupID = userGroupID;
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

	public Integer getUserGroupID() {
		return userGroupID;
	}

	public void setUserGroupID(Integer userGroupID) {
		this.userGroupID = userGroupID;
	}
}
