package bhtweb.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bhtweb.utils.ServletUtils;

@WebServlet(name = "PostServlet", urlPatterns = {"/api/v1/docs"})
public class PostServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletUtils.addHeaderToResponse(resp);
		System.out.println("hehe");
	}

	private void doGetBHTPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			PrintWriter out = response.getWriter();
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			out.print("awesome !");
			out.flush();
		}catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
