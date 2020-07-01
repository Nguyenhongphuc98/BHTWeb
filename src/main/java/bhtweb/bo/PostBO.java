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
	
	public PostBO (ServletContext context) {
		this.context = context;
		try {
			postMapper = new PostMapper();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<PostDTO> getPosts(Integer pageNo) {
		List<PostDTO> result;
		List<BHTPost> postsList = postMapper.fetchPost(pageNo);
		if (postsList == null)
			return null;
		result = postsList.stream().map(PostDTO::new).collect(Collectors.toList());
		return result;
	}
		
	public PostDTO createPost (PostDTO postDTO) {
		try {
			return new PostDTO(postMapper.insertPost(new BHTPost(postDTO)));
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
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
