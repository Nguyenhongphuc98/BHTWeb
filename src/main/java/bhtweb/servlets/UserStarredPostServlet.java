package bhtweb.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.jdbc.interceptors.ResultSetScannerInterceptor;

import bhtweb.dbaccess.UserStarredPostMapper;
import bhtweb.entities.BHTUserStarredPost;
import bhtweb.utils.ServletUtils;

@WebServlet(name = "UserStarredPosts", urlPatterns = { "/likedPosts" })
public class UserStarredPostServlet extends HttpServlet {

	UserStarredPostMapper userStarredPostMapper;
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		try {
			userStarredPostMapper = new UserStarredPostMapper();
			
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPostUserStarredPost(req, resp);
	}
	
	private void doPostUserStarredPost (HttpServletRequest req, HttpServletResponse resp) {
		try {
			PrintWriter out = ServletUtils.getJSONUnicodeWriterNoCORS(resp);
			
			//Lấy ra userID từ query parameter.
			Integer userID = ServletUtils.getIntegerParam(req, "userID", null);
			Integer postID = ServletUtils.getIntegerParam(req, "postID", null);
			
			//Bắt buộc phải có userID parameter.
			//Bắt buộc phải có postID.
			if (userID == null || postID == null) {
				ServletUtils.printObjectJSON(out, resp, null, HttpURLConnection.HTTP_BAD_REQUEST);
				return;
			}
			boolean success = userStarredPostMapper.insertUserStarredPost(userID, postID);
			
			if (!success) {
				ServletUtils.printObjectJSON(out, resp, null, HttpURLConnection.HTTP_BAD_REQUEST);
				return;
			}
			
			ServletUtils.printObjectJSON(out, resp, null, HttpURLConnection.HTTP_OK);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doDeleteUserStarredPost(req, resp);
	}
	
	private void doDeleteUserStarredPost (HttpServletRequest req, HttpServletResponse resp) {
		try {
			PrintWriter out = ServletUtils.getJSONUnicodeWriterNoCORS(resp);
			
			//Lấy ra userID từ query parameter.
			Integer userID = ServletUtils.getIntegerParam(req, "userID", null);
			Integer postID = ServletUtils.getIntegerParam(req, "postID", null);
			
			//Bắt buộc phải có userID parameter.
			//Bắt buộc phải có postID.
			if (userID == null || postID == null) {
				ServletUtils.printObjectJSON(out, resp, null, HttpURLConnection.HTTP_BAD_REQUEST);
				return;
			}
			
			boolean success = userStarredPostMapper.deleteUserStarredPost(userID, postID);
			
			if (!success) {
				ServletUtils.printObjectJSON(out, resp, null, HttpURLConnection.HTTP_BAD_REQUEST);
				return;
			}
			
			ServletUtils.printObjectJSON(out, resp, null, HttpURLConnection.HTTP_OK);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGetUserStarredPost(req, resp);
	}
	private void doGetUserStarredPost (HttpServletRequest req, HttpServletResponse resp) {
		try {
			PrintWriter out = ServletUtils.getJSONUnicodeWriterNoCORS(resp);
			
			//Lấy ra userID từ query parameter.
			//Bắt buộc phải có userID parameter.
			Integer userID = ServletUtils.getIntegerParam(req, "userID", null);
			if (userID == null) {
				ServletUtils.printObjectJSON(out, resp, null, HttpURLConnection.HTTP_BAD_REQUEST);
				return;
			}
			
			List<BHTUserStarredPost> list = userStarredPostMapper.fetchByUserID(userID);
			
			if (list == null) {
				ServletUtils.printObjectJSON(out, resp, null, HttpURLConnection.HTTP_BAD_REQUEST);
				return;
			}
			
			List<Integer> results = new ArrayList<Integer>();
			for (BHTUserStarredPost entity : list) {
				results.add(entity.getPost().getPostID());
			}
			
			ServletUtils.printObjectJSON(out, resp, results, HttpURLConnection.HTTP_OK);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
