package bhtweb.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import bhtweb.bo.DocumentBO;
import bhtweb.dto.DocumentDTO;
import bhtweb.utils.ServletUtils;

// docs/preview?id=n

// Chi preview duoc cai nao chua duyet thoi. cai nao da duyet la view detail

@WebServlet(name = "GetPreviewDocumentServlet", urlPatterns = { "/docs/preview" })
public class GetPreviewDocumentServlet extends HttpServlet {

	DocumentBO documentBO;
	private Gson gson;

	public void init() {

		documentBO = new DocumentBO();
		gson = new Gson();
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
				resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
				return;
			}
		} else {
			resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}


		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");

		// lay user tu session ra.
		HttpSession session = req.getSession(true);
		Integer uid = (Integer) session.getAttribute("uid");
//		if (uid == null) {
//			System.out.println("you have login to preview");
//			session.setAttribute("uid", 1);
//		} else {
			DocumentDTO doc = documentBO.previewDoc(id, 1);
			if (doc == null) {
				resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
			} else {
				String documentJsonString = this.gson.toJson(doc);
				out.print(documentJsonString);
				out.flush();
			}
		//}

	}
}