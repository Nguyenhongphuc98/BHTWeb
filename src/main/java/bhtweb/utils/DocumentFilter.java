package bhtweb.utils;

public class DocumentFilter {
	
	String subjectId;
	
	String categoreId;
	
	String semesterId;
	
	String yearNo;

	public DocumentFilter() {
		this.subjectId = " != null";
		this.categoreId = " != null";
		this.semesterId = " != null";
		this.yearNo = " != null";
	}

	public DocumentFilter(int subjectId, int categoreId, int semesterId, int yearNo) {
		super();
		this.subjectId = "= " + subjectId;
		this.categoreId = "= " + categoreId;
		this.semesterId = "= " + semesterId;
		this.yearNo = "= " + yearNo;
	}

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = "= " + subjectId;
	}

	public String getCategoreId() {
		return categoreId;
	}

	public void setCategoreId(int categoreId) {
		this.categoreId = "= " + categoreId;
	}

	public String getSemesterId() {
		return semesterId;
	}

	public void setSemesterId(int semesterId) {
		this.semesterId = "= " + semesterId;
	}

	public String getYearNo() {
		return yearNo;
	}

	public void setYearNo(int yearNo) {
		this.yearNo = "= " + yearNo;
	}
}
