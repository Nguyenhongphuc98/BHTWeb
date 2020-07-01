package bhtweb.entities;

import bhtweb.dto.PostCategoryDTO;

public class BHTPostCategory {

	private Long postCategoryID;
	private String postCategoryName;
	
	public BHTPostCategory() {
		
	}
	
	public BHTPostCategory(PostCategoryDTO postCategoryDTO) {
		this.postCategoryID = postCategoryDTO.getPostCategoryID();
		this.postCategoryName = postCategoryDTO.getPostCategoryName();
	}
	
	public Long getPostCategoryID() {
		return postCategoryID;
	}
	public void setPostCategoryID(Long postCategoryID) {
		this.postCategoryID = postCategoryID;
	}
	public String getPostCategoryName() {
		return postCategoryName;
	}
	public void setPostCategoryName(String postCategoryName) {
		this.postCategoryName = postCategoryName;
	}
}
