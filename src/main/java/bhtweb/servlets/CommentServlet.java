package bhtweb.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bhtweb.bo.CommentBO;
import bhtweb.dto.CommentDTO;
import bhtweb.utils.ServletUtils;

@WebServlet(name = "CommentServlet", urlPatterns = {"/postComments"})
public class CommentServlet extends HttpServlet {

	CommentBO commentBO;
	
	private final String POSTID_PARAM_NAME = "postID";
	private final String PARENTCOMMENTID_PARAM_NAME = "parentID";
	
	@Override
	public void init() throws ServletException {
		super.init();
		try {
			commentBO = new CommentBO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ServletUtils.addNoCORSHeader(resp);
		doGetComment(req, resp);
	}
	
	private void doGetComment(HttpServletRequest req, HttpServletResponse resp) {
		try {
			
			PrintWriter out = ServletUtils.getJSONUnicodeWriterNoCORS(resp);
			
			Integer postID;
			Integer parentCommentID;
			
			postID = ServletUtils.getIntegerParam(req, POSTID_PARAM_NAME, null);
			parentCommentID = ServletUtils.getIntegerParam(req, PARENTCOMMENTID_PARAM_NAME, null);
			
			//Nếu cả 2 trường đều rỗng thì làm sao biết Query comment theo cách nào ?
			if (postID == null && parentCommentID == null)
				return;
			
			List<CommentDTO> commentDTOs;
			
			//Ưu tiên lọc theo postID, đã lọc bằng postID sẽ không lọc bằng parentCommentID.
			if (postID != null) {
				commentDTOs = commentBO.getCommentDTOsFromPostID(postID);
			}else if (parentCommentID != null) {
				commentDTOs = commentBO.getCommentDTOsFrom
			}
			
		}catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
}