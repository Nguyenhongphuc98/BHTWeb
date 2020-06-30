package bhtweb.dto;

public class DocumentUploadDTO {

	String title;

	String description;

	int uploaderId;

	String contentUrl;

	int semesterId;

	int subjectId;

	int categoryId;

	public DocumentUploadDTO() {

	}

	public DocumentUploadDTO(String title, String description, int uploaderId, String contentUrl, String semesterId,
			String subjectId, String categoryId) {
		super();
		this.title = title;
		this.description = description;
		this.uploaderId = uploaderId;
		this.contentUrl = contentUrl;
		this.semesterId = Integer.parseInt(semesterId);
		this.subjectId = Integer.parseInt(subjectId);
		this.categoryId = Integer.parseInt(categoryId);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getUploaderId() {
		return uploaderId;
	}

	public void setUploaderId(int uploaderId) {
		this.uploaderId = uploaderId;
	}

	public String getContentUrl() {
		return contentUrl;
	}

	public void setContentUrl(String contentUrl) {
		this.contentUrl = contentUrl;
	}

	public int getSemesterId() {
		return semesterId;
	}

	public void setSemesterId(int semesterId) {
		this.semesterId = semesterId;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
}
