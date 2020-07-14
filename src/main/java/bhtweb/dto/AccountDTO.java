package bhtweb.dto;

import javax.persistence.criteria.CriteriaBuilder.In;

// contain detail info to show a account
public class AccountDTO {

	int id;
	
	String username;
	
	String password;
	
	String avatar;
	
	String email;
	
	Integer score;
	
	Integer postCount;
	
	Integer documentCount;
	
	Integer roleId;
	
	String roleName;

	public AccountDTO() {
	}

	public AccountDTO(int id, String username,String pasword, String avatar, String email, Integer score, Integer postCount,
			Integer documentCount, Integer roleId, String roleName) {
		super();
		this.id = id;
		this.username = username;
		this.password = pasword;
		this.avatar = avatar;
		this.email = email;
		this.score = score;
		this.postCount = postCount;
		this.documentCount = documentCount;
		this.roleId = roleId;
		this.roleName = roleName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getPostCount() {
		return postCount;
	}

	public void setPostCount(Integer postCount) {
		this.postCount = postCount;
	}

	public Integer getDocumentCount() {
		return documentCount;
	}

	public void setDocumentCount(Integer documentCount) {
		this.documentCount = documentCount;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
}
