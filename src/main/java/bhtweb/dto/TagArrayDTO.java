package bhtweb.dto;

import java.util.ArrayList;
import java.util.List;

public class TagArrayDTO {

	List<String> tags = new ArrayList<String>();

	public TagArrayDTO(List<String> tags) {
		super();
		this.tags = tags;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}
}
