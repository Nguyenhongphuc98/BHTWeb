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
import bhtweb.utils.ServletUtils;

// docs/approved?id=n

@WebServlet(name = "ApproveDocumentServlet", urlPatterns = { "/docs/approved" })
public class ApproveDocumentServlet extends HttpServlet {

	DocumentBO documentBO;

	public void init() {

		documentBO = new DocumentBO();

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		ServletUtils.addHeaderToResponse(resp);
		
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

		boolean result = documentBO.publishDocument(id);

		if (result) {
			resp.setStatus(HttpServletResponse.SC_OK);
			System.out.println("approved");
		} else {
			resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
			System.out.println("try later");
		}

	}
}