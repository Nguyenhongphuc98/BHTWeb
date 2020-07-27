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

import bhtweb.bo.TagBO;
import bhtweb.dto.TagDTO;
import bhtweb.utils.ServletUtils;

@WebServlet(name = "TagServlet", urlPatterns = {"/postTags"})
public class TagServlet extends HttpServlet {

	TagBO tagBO;
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		tagBO = new TagBO();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//ServletUtils.addNoCORSHeader(resp);
		doGetTagByPostID(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//ServletUtils.addNoCORSHeader(resp);
		
	}
	
	private void doGetTagByPostID (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = ServletUtils.getJSONUnicodeWriterNoCORS(response);
		
		Integer postID = ServletUtils.getIntegerParam(request, "postID", null);
		
		if (postID == null) {
			ServletUtils.printObjectJSON(out, response, null, HttpURLConnection.HTTP_BAD_REQUEST);
			return;
		}
		
		List<TagDTO> tagDTOs = tagBO.getTagByPost(postID);
		
		if (tagDTOs == null) {
			ServletUtils.printObjectJSON(out, response, null, HttpURLConnection.HTTP_BAD_REQUEST);
			return;
		}
		ServletUtils.printObjectJSON(out, response, tagDTOs, HttpURLConnection.HTTP_OK);
	}
	
	private void doPostTagByPostAndTag (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = ServletUtils.getJSONUnicodeWriterNoCORS(response);
		
	}
}
