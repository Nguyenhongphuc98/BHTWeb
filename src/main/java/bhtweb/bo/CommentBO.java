package bhtweb.bo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;

import bhtweb.dbaccess.CommentMapper;
import bhtweb.dto.CommentDTO;
import bhtweb.dto.PostDTO;
import bhtweb.entities.BHTComment;

public class CommentBO{
	
	private ServletContext context;
	
	private CommentMapper commentMapper;
	
	public CommentBO () throws Exception {
		this(null);
	}
	
	public CommentBO (ServletContext context) throws Exception {
		this.context = context;
		this.commentMapper = new CommentMapper();
	}
	
	public List<CommentDTO> getCommentDTOsFromPostID (Integer postID){
		List<CommentDTO> comments = commentMapper.getCommentByPostId(postID, true)
				.stream()
				.map(CommentDTO::new)
				.collect(Collectors.toList());
		return comments;
	}
	
	public List<CommentDTO> getCommentDTOsFromParentCommentID (Integer parentID){
		List<CommentDTO> comments = commentMapper.getCommentByParentId(parentID)
				.stream()
				.map(CommentDTO::new)
				.collect(Collectors.toList());
		
		return comments;
	}
	
	public CommentDTO postNewComment (CommentDTO commentDTO) {
		BHTComment entity = new BHTComment(commentDTO);
		
		entity = commentMapper.saveComment(entity);
		
		CommentDTO result = new CommentDTO(entity);
		
		return result;
		
	}
	
	public Boolean deleteComment (int commentID) {
		return commentMapper.deleteComment(commentID);
	}
	
}