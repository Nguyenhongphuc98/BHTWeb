package bhtweb.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bhtweb.bo.PostBO;
import bhtweb.utils.ServletUtils;

@WebServlet(name = "GetPostPagesServlet", urlPatterns = { "/getPostPages" })
public class GetPostPagesServlet extends HttpServlet {

	PostBO postBO;
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		postBO = new PostBO();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGetPageNumber(req, resp);
	}
	
	private void doGetPageNumber(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		PrintWriter out = ServletUtils.getJSONUnicodeWriterNoCORS(response);
		
		Integer pageNumber = postBO.getPostPages();
		
		if (pageNumber == null) {
			ServletUtils.printObjectJSON(out, response, null, HttpURLConnection.HTTP_INTERNAL_ERROR);
			return;
		}
		
		out.print(pageNumber);
	}
}
