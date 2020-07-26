package bhtweb.dto;

import bhtweb.entities.BHTTag;

public class TagDTO {

	private Integer id;
	private String tagdetail;
	
	public TagDTO() {
		
	}
	
	public TagDTO (BHTTag entity) {
		this.id = entity.getTagID();
		this.tagdetail = entity.getTagContent();
	}
	
	public TagDTO(Integer id, String tagdetail) {
		super();
		this.id = id;
		this.tagdetail = tagdetail;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTagdetail() {
		return tagdetail;
	}
	public void setTagdetail(String tagdetail) {
		this.tagdetail = tagdetail;
	}
}
