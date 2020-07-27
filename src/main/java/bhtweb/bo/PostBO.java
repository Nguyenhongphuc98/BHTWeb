package bhtweb.bo;


import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;

import com.sun.mail.iap.Literal;

import bhtweb.dbaccess.PostMapper;
import bhtweb.dto.PostDTO;
import bhtweb.entities.BHTPost;

public class PostBO {

	private ServletContext context;
	private PostMapper postMapper;
	
	private TagBO tagBO;
	
	public PostBO (ServletContext context) {
		this.context = context;
		try {
			postMapper = new PostMapper();
			tagBO = new TagBO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public PostBO() {
		this(null);
	}
	
	public PostDTO createNewPost (PostDTO postDTO) {
		
		//Tạo ra một entity từ DTO.
		BHTPost entity = new BHTPost(postDTO);
		
		//Gán entity bằng entity thực tế sau khi insert.
		entity = postMapper.insertPost(entity);
		
		//Sau khi đã thêm post thành công, ta tiến hành thêm tag.
		Boolean tagAddSuccess = tagBO.addTags(entity.getPostID(), postDTO.getTags());
		
		//Chuyển entity thành DTO kết quả.
		PostDTO result = new PostDTO(entity);
		
		return result;
	}
	
	public List<PostDTO> getPosts(Integer pageNo) {
		List<PostDTO> result;
		List<BHTPost> postsList = postMapper.fetchPost(pageNo);
		if (postsList == null)
			return null;
		result = postsList.stream().map(PostDTO::new).collect(Collectors.toList());
		return result;
	}
	
	public List<PostDTO> getHighlights (){
		List<PostDTO> result;
		List<BHTPost> postsList = postMapper.fetchHighLightPosts();
		if (postsList == null)
			return null;
		result = postsList.stream().map(PostDTO::new).collect(Collectors.toList());
		return result;
	}
	
	public List<PostDTO> getNewest(){
		List<PostDTO> result;
		List<BHTPost> postsList = postMapper.fetchNewestPosts();
		if (postsList == null)
			return null;
		result = postsList.stream().map(PostDTO::new).collect(Collectors.toList());
		return result;
	}
	
	public List<PostDTO> getNewActivities(){
		List<PostDTO> result;
		List<BHTPost> postsList = postMapper.fetchNewActivities();
		if (postsList == null)
			return null;
		result = postsList.stream().map(PostDTO::new).collect(Collectors.toList());
		return result;
	}
	
	public List<PostDTO> searchPosts(BHTPost similarPost, Integer pageNo){
		List<PostDTO> result;
		List<BHTPost> postsList = postMapper.searchPost(similarPost, pageNo);
		if (postsList == null)
			return null;
		result = postsList.stream().map(PostDTO::new).collect(Collectors.toList());
		return result;
	}
	
	public Boolean updatePost (PostDTO postDTO) {
		try {
			return postMapper.updatePost(new BHTPost(postDTO));
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
}
