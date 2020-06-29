package bhtweb.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bhtweb.bo.DocumentBO;
import bhtweb.dto.DocumentDTO;

//ApproveDocumentServlet?id=n

@WebServlet(name = "ApproveDocumentServlet", urlPatterns = { "/ApproveDocumentServlet" })
public class ApproveDocumentServlet extends HttpServlet {

	DocumentBO documentBO;

	public void init() {

		documentBO = new DocumentBO();

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String idString = req.getParameter("id");
		int id = 0;
		if (idString != null) {
			try {
				id = Integer.parseInt(idString);
			} catch (Exception e) {
				return;
			}
		}

		if (id == 0) {
			return;
		}
		
		HttpSession session = req.getSession();
		Integer uid = (Integer) session.getAttribute("uid");

		// Neu null or role != admin -> denied

		// Nguoc lai thi update status to approved

		resp.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = resp.getWriter()) {
			out.println("GetDocumentDetailServlet, id: " + id);
		}

		boolean result = documentBO.publishDocument(id);

		if (result) {
			System.out.println("approved");
		} else {
			System.out.println("try later");
		}

	}
}