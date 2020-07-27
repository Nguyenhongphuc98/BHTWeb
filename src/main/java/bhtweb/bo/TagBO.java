package bhtweb.bo;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;

import bhtweb.dbaccess.TagMapper;
import bhtweb.dto.TagDTO;
import bhtweb.entities.BHTTag;

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
	
	public Boolean addTags (Integer postID, List<String> tags) {
		//Bước 1: Lưu trữ tất cả các tag này xuống DB (trùng tags thì khỏi lưu mới).
		tagMapper.addTags(tags);
		
		//Bước 2: Query danh sách tất cả các tag với ID thuộc về post.
		List<BHTTag> tag = tagMapper.getTagEntityFromName(tags);
		
		//Bước 3: Lưu mối liên kết giữa post và tag.
		Boolean result = tagMapper.addPostTag(postID, tag);
		
		return result;
	}
}
