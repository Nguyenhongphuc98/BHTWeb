package bhtweb.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import bhtweb.bo.CommentBO;
import bhtweb.dto.AccountDTO;
import bhtweb.dto.CommentDTO;
import bhtweb.dto.PostDTO;
import bhtweb.utils.BHTSession;
import bhtweb.utils.ServletUtils;

@WebServlet(name = "CommentServlet", urlPatterns = {"/postComments"})
public class CommentServlet extends HttpServlet {

	CommentBO commentBO;
	
	Gson gson;
	
	private final String POSTID_PARAM_NAME = "postID";
	private final String PARENTCOMMENTID_PARAM_NAME = "parentID";
	
	@Override
	public void init() throws ServletException {
		super.init();
		try {
			commentBO = new CommentBO();
			gson = new Gson();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//ServletUtils.addNoCORSHeader(resp);
		doPostComment(req, resp);
	}
	
	private void doPostComment (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//Lấy ra writer.
			PrintWriter out = ServletUtils.getJSONUnicodeWriterNoCORS(response);
			
			//Lấy ra user.
			AccountDTO accountDTO = BHTSession.currentUser(request);
			
			//Nếu không lấy được account thì thông báo người dùng không có quyền.
			if (accountDTO == null) {
				ServletUtils.printObjectJSON(out, response, null, HttpURLConnection.HTTP_UNAUTHORIZED);
				return;
			}
			
			//Lấy JSON body từ request.
			String body = ServletUtils.getJSONBody(request);
			
			//Deserialize từ JSON sang Object.
			CommentDTO commentDTO = gson.fromJson(body, CommentDTO.class);
			
			//Gán user id.
			commentDTO.setUserID(accountDTO.getId());
			
			//Insert comment vào DB.
			commentDTO = commentBO.postNewComment(commentDTO);
			
			//Nếu không insert được thì trả về null.
			if (commentDTO.getId() == null) {
				ServletUtils.printObjectJSON(out, response, null, HttpURLConnection.HTTP_INTERNAL_ERROR);
				return;
			}
			
			//In ra result.
			ServletUtils.printObjectJSON(out, response, commentDTO, HttpURLConnection.HTTP_OK);
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		// TODO Auto-generated method stub
		arg0.setCharacterEncoding("UTF-8");
		super.service(arg0, arg1);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//ServletUtils.addNoCORSHeader(resp);
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
			if (postID == null && parentCommentID == null) {
				ServletUtils.printObjectJSON(out, resp, 
						null, HttpURLConnection.HTTP_BAD_REQUEST);
				return;
			}

			List<CommentDTO> commentDTOs;
			
			System.out.println("Hello from CommentServlet");
			
			//Ưu tiên lọc theo postID, đã lọc bằng postID sẽ không lọc bằng parentCommentID.
			if (postID != null) {
				commentDTOs = commentBO.getCommentDTOsFromPostID(postID);
				System.out.println("PostID detected");
			}else if (parentCommentID != null) {
				commentDTOs = commentBO.getCommentDTOsFromParentCommentID(parentCommentID);
				System.out.println("CommentID detected");
			}else {
				commentDTOs = null;
			}
			
			if (commentDTOs == null || commentDTOs.size() == 0) {
				ServletUtils.printObjectJSON(out, resp, 
						commentDTOs, HttpURLConnection.HTTP_NOT_FOUND);
				return;
			}
			
			//Mọi thứ bình thường thì cứ in ra các comment thôi.
			ServletUtils.printObjectJSON(out, resp, commentDTOs, HttpURLConnection.HTTP_OK);
			
		}catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
}
