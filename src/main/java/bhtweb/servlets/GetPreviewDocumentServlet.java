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

//GetPreviewDocumentServlet?id=n

// Chi preview duoc cai nao chua duyet thoi. cai nao da duyet la view detail

@WebServlet(name = "GetPreviewDocumentServlet", urlPatterns = { "/GetPreviewDocumentServlet" })
public class GetPreviewDocumentServlet extends HttpServlet {

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

		resp.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = resp.getWriter()) {
			out.println("preview doc, id: " + id);

			// lay user tu session ra.
			System.out.println("hu");
			HttpSession session = req.getSession(true);
			System.out.println("huhu");
			Integer uid = (Integer) session.getAttribute("uid");
			if (uid == null) {
				System.out.println("you have login to preview");
				session.setAttribute("uid", 1);
			} else {
				DocumentDTO doc = documentBO.previewDoc(id, uid);
				if (doc == null) {
					System.out.println("you don't have permission");
				} else {
					System.out.println(doc.toString());
				}
			}
		}

	}
}