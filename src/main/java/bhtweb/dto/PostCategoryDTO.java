package bhtweb.dto;

import bhtweb.entities.BHTPostCategory;

public class PostCategoryDTO {

	private Integer id;
	private String title;
	
	public PostCategoryDTO () {
		
	}
	
	public PostCategoryDTO(BHTPostCategory postCategory) {
		this.id = postCategory.getPostCategoryID();
		this.title = postCategory.getPostCategoryName();
	}
	
	public Integer getPostCategoryID() {
		return id;
	}
	public void setPostCategoryID(Integer postCategoryID) {
		this.id = postCategoryID;
	}
	public String getPostCategoryName() {
		return title;
	}
	public void setPostCategoryName(String postCategoryName) {
		this.title = postCategoryName;
	}
}
