package bhtweb.dto;

import bhtweb.entities.BHTPostCategory;

public class PostCategoryDTO {

	private Integer postCategoryID;
	private String postCategoryName;
	
	public PostCategoryDTO () {
		
	}
	
	public PostCategoryDTO(BHTPostCategory postCategory) {
		this.postCategoryID = postCategory.getPostCategoryID();
		this.postCategoryName = postCategory.getPostCategoryName();
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
