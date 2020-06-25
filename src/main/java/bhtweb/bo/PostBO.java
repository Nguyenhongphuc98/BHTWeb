package bhtweb.bo;

import javax.servlet.ServletContext;

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
