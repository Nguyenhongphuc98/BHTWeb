package bhtweb.entities;

public class BHTUserGroup {
	
	int UserGroupID;
	
	String UserGroupName;
	
	public BHTUserGroup() {

	}

	public BHTUserGroup(int userGroupID, String userGroupName) {
		super();
		UserGroupID = userGroupID;
		UserGroupName = userGroupName;
	}

	public int getUserGroupID() {
		return UserGroupID;
	}

	public void setUserGroupID(int userGroupID) {
		UserGroupID = userGroupID;
	}

	public String getUserGroupName() {
		return UserGroupName;
	}

	public void setUserGroupName(String userGroupName) {
		UserGroupName = userGroupName;
	}
}
