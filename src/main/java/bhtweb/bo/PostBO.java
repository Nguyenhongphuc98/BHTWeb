package bhtweb.bo;

import javax.servlet.ServletContext;

import bhtweb.dbaccess.PostMapper;
import bhtweb.dto.PostDTO;

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
			return postMapper.insertPost(postDTO);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	public Boolean updatePost (PostDTO postDTO) {
		try {
			return postMapper.updatePost(postDTO);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
}
