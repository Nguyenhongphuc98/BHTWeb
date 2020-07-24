package bhtweb.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.util.ArrayList;
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
	private final String POSTID_PARAM_NAME = "id";
	private final String FILTERTYPE_PARAM_NAME = "type";
	
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		postBO = new PostBO();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletUtils.addNoCORSHeader(resp);
		doGetBHTPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPostBHTPost(req, resp);
	}
	
	private void doPostBHTPost (HttpServletRequest request, HttpServletResponse response) {
		
	}

	private void doGetBHTPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			PrintWriter out = ServletUtils.getJSONUnicodeWriterNoCORS(response);

			Integer pageNo;
			Integer authorID;
			Integer categoryID;
			Integer postID;
			String type;
			
			List<Object> filters = new ArrayList<Object>();
			
			//Lấy ra pageNo từ Param.
			pageNo = ServletUtils.getIntegerParam(request, PAGE_PARAM_NAME, 1);
			
			//Lấy ra AuthorID từ Param.
			authorID = ServletUtils.getIntegerParam(request, AUTHORID_PARAM_NAME, null);
			if (authorID != null)
				filters.add(authorID);
			
			//Lấy ra categoryID từ Param.
			categoryID = ServletUtils.getIntegerParam(request, CATEGORYID_PARAM_NAME, null);
			if (categoryID != null)
				filters.add(categoryID);
			
			//Lấy ra ID bài Post từ Param.
			postID = ServletUtils.getIntegerParam(request, POSTID_PARAM_NAME, null);
			if (postID != null)
				filters.add(postID);
			
			//Lấy ra loại post người dùng đang mong muốn từ param.
			type = ServletUtils.getStringParam(request, FILTERTYPE_PARAM_NAME, null);
			if (type != null)
				filters.add(type);
			
			List<PostDTO> postDTOs;
			
			//Lấy danh sách post không lọc theo gì hết.
			if (filters.size() == 0)
				postDTOs = postBO.getPosts(pageNo);
			//Sau đó ưu tiên lấy danh sách theo loại.
			else if (type != null){
				//Highlights tức là những bài viết được sort theo số view giảm dần.
				if (type.equals("highlights")) {
					postDTOs = postBO.getHighlights();
				}
				//Newest tức là những bài viết được sort theo ngày publish giảm dần.
				else if (type.equals("newest")) {
					postDTOs = postBO.getNewest();
				}
				//newActivities là những bài viết liên quan đến hoạt động.
				//Được sort theo ngày publish giảm dần.
				else if (type.equals("newActivities")) {
					postDTOs = postBO.getNewActivities();
				}else {
					postDTOs = null;
					ServletUtils.printObjectJSON(out, response, postDTOs, HttpURLConnection.HTTP_BAD_REQUEST);
				}
			}else{
				System.out.println("Detected filter param !");
				BHTPost model = new BHTPost();
				if (authorID != null)
					model.setPosterUserID(Long.valueOf(authorID));
				if (categoryID != null)
					model.setPostCategoryID(Long.valueOf(categoryID));
				if (postID != null)
					model.setPostID(Long.valueOf(postID));
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
