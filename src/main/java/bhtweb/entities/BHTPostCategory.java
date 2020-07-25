package bhtweb.entities;

import bhtweb.dto.PostCategoryDTO;

public class BHTPostCategory {

	private Integer postCategoryID;
	private String postCategoryName;
	
	public BHTPostCategory() {
		
	}
	
	public BHTPostCategory (Integer postCategoryID) {
		this.postCategoryID = postCategoryID;
	}
	
	public BHTPostCategory(PostCategoryDTO postCategoryDTO) {
		this.postCategoryID = postCategoryDTO.getPostCategoryID();
		this.postCategoryName = postCategoryDTO.getPostCategoryName();
	}
	
	public Integer getPostCategoryID() {
		return postCategoryID;
	}
	public void setPostCategoryID(Integer postCategoryID) {
		this.postCategoryID = postCategoryID;
	}
	public String getPostCategoryName() {
		return postCategoryName;
	}
	public void setPostCategoryName(String postCategoryName) {
		this.postCategoryName = postCategoryName;
	}
}
