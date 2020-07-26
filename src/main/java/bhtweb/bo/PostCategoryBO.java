package bhtweb.bo;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;

import bhtweb.dbaccess.PostCategoryMapper;
import bhtweb.dbaccess.PostMapper;
import bhtweb.dto.PostCategoryDTO;
import bhtweb.entities.BHTPostCategory;

public class PostCategoryBO {
	private ServletContext context;
	private PostCategoryMapper postCategoryMapper;
	
	public PostCategoryBO() {
		this(null);
	}
	
	public PostCategoryBO(ServletContext servletContext) {
		this.context = servletContext;
		try {
			postCategoryMapper = new PostCategoryMapper();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public List<PostCategoryDTO> getAllPostCategories() {
		try {
			List<BHTPostCategory> categories = postCategoryMapper.fetchPostCategories();
			
			List<PostCategoryDTO> categoriesDTO = categories
					.stream()
					.map(PostCategoryDTO::new)
					.collect(Collectors.toList());
			return categoriesDTO;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	public PostCategoryDTO createPostCategory (PostCategoryDTO postCategoryDTO) {
		try {
			return new PostCategoryDTO(postCategoryMapper.insertPostCategory(new BHTPostCategory(postCategoryDTO)));
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	public Boolean updatePostCategory (PostCategoryDTO postCategoryDTO) {
		try {
			return postCategoryMapper.updatePostCategory(new BHTPostCategory(postCategoryDTO));
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
}