package bhtweb.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import bhtweb.bo.PostBO;
import bhtweb.dto.PostDTO;
import bhtweb.entities.BHTPost;
import bhtweb.utils.ServletUtils;

@WebServlet(name = "PostServlet", urlPatterns = { "/posts" })
public class PostServlet extends HttpServlet {

	PostBO postBO;
	
	//Tên của các Query Parameter mà người dùng sẽ truyền đến.
	private final String PAGE_PARAM_NAME = "page";
	private final String AUTHORID_PARAM_NAME = "authorID";
	private final String CATEGORYID_PARAM_NAME = "categoryID";
	
	
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
			Integer authorID;
			Integer categoryID;
			
			//Lấy ra pageNo từ Param.
			pageNo = ServletUtils.getIntegerParam(request, PAGE_PARAM_NAME, 1);
			
			//Lấy ra AuthorID từ Param.
			authorID = ServletUtils.getIntegerParam(request, AUTHORID_PARAM_NAME, null);
			
			//Lấy ra categoryID từ Param.
			categoryID = ServletUtils.getIntegerParam(request, CATEGORYID_PARAM_NAME, null);
			
			List<PostDTO> postDTOs;
			
			System.out.println("AAA");
			
			//Lấy danh sách post không lọc theo gì hết.
			if (authorID == null && categoryID == null)
				postDTOs = postBO.getPosts(pageNo);
			else {
				System.out.println("Detected filter param !");
				BHTPost model = new BHTPost();
				System.out.println("authorID : " + authorID.toString());
				if (authorID != null)
					model.setPosterUserID(Long.valueOf(authorID));
				if (categoryID != null)
					model.setPostCategoryID(Long.valueOf(categoryID));
				postDTOs = postBO.searchPosts(model, pageNo);
			}
			
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
