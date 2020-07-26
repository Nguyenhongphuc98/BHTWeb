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

import bhtweb.bo.PostBO;
import bhtweb.bo.PostCategoryBO;
import bhtweb.dto.PostCategoryDTO;
import bhtweb.utils.ServletUtils;

@WebServlet(name = "PostCategoryServlet", urlPatterns = { "/postCategories" })
public class PostCategoryServlet extends HttpServlet{

	PostCategoryBO postCategoryBO;
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		postCategoryBO = new PostCategoryBO();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ServletUtils.addNoCORSHeader(resp);
		doGetPostCategory(req, resp);
	}
	
	private void doGetPostCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = ServletUtils.getJSONUnicodeWriterNoCORS(response);
		
		List<PostCategoryDTO> categoryDTOs = postCategoryBO.getAllPostCategories();
		
		if (categoryDTOs == null) {
			ServletUtils.printObjectJSON(out, response, null, HttpURLConnection.HTTP_INTERNAL_ERROR);
			return;
		}
			
		ServletUtils.printObjectJSON(out, response, categoryDTOs, HttpURLConnection.HTTP_OK);
	}
	
}
