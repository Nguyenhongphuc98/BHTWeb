package bhtweb.entities;

public class BHTTag {
	
	private Integer tagID;
	private String tagContent;
	
	public BHTTag() {
		
	}
	
	public BHTTag(Integer tagID, String tagContent) {
		super();
		this.tagID = tagID;
		this.tagContent = tagContent;
	}
	public Integer getTagID() {
		return tagID;
	}
	public void setTagID(Integer tagID) {
		this.tagID = tagID;
	}
	public String getTagContent() {
		return tagContent;
	}
	public void setTagContent(String tagContent) {
		this.tagContent = tagContent;
	}
}