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

import bhtweb.bo.PostBO;
import bhtweb.dto.PostDTO;
import bhtweb.utils.ServletUtils;

@WebServlet(name = "PostServlet", urlPatterns = { "/posts" })
public class PostServlet extends HttpServlet {

	PostBO postBO;
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		postBO = new PostBO();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletUtils.addHeaderToResponse(resp);
		doGetBHTPost(req, resp);
	}

	private void doGetBHTPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			PrintWriter out = response.getWriter();
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			Integer pageNo;
			
			try {
				pageNo = Integer.parseInt(request.getParameter("page"));
			}catch(Exception ex) {
				pageNo = 1;
			}
			
			List<PostDTO> postDTOs = postBO.getPosts(pageNo);
			//Nếu trả về null thì có thể trang đang truy cập đến không tồn tại.
			if (postDTOs == null || postDTOs.size() == 0) {
				ServletUtils.printObjectJSON(out, response, null, 
						HttpURLConnection.HTTP_NOT_FOUND);
				return;
			}
			
			//Mọi thứ bình thường, ta sẽ chuyển dữ liệu sang JSON.
			ServletUtils.printObjectJSON(out, response, postDTOs, HttpURLConnection.HTTP_OK);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
