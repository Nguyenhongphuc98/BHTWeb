package bhtweb.bo;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;

import bhtweb.dbaccess.TagMapper;
import bhtweb.dto.TagDTO;

public class TagBO {

	private ServletContext context;
	
	private TagMapper tagMapper;
	
	public TagBO () {
		this(null);
	}
	
	public TagBO (ServletContext context) {
		try {
			this.context = context;
			this.tagMapper = new TagMapper();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public List<TagDTO> getTagByPost (Integer postID){
		List<TagDTO> tags = tagMapper.getTagsOfPostID(postID)
				.stream()
				.map(TagDTO::new)
				.collect(Collectors.toList());
		return tags;
	}
	
}
