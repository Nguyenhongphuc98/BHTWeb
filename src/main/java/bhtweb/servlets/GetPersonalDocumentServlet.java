package bhtweb.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import bhtweb.bo.DocumentBO;
import bhtweb.dto.ShortDocumentDTO;
import bhtweb.utils.ServletUtils;

// user/docs? uid = 1 & approved = 0& page =0
@WebServlet(name = "GetPersonalDocumentServlet", urlPatterns = { "/user/docs" })
public class GetPersonalDocumentServlet extends HttpServlet {

	private DocumentBO documentBO;
	private Gson gson;

	public void init() {

		documentBO = new DocumentBO();
		gson = new Gson();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		ServletUtils.addHeaderToResponse(resp);
		
		String uidString = req.getParameter("uid");
		String approvedString = req.getParameter("approved");
		String pageIndexString = req.getParameter("page");
		
		int uid = -1;
		int approved = 0;
		int pageIndex = 0;
		boolean approvedBool = true;
		
		if (uidString != null && approvedString != null && pageIndexString != null) {
			try {
				uid = Integer.parseInt(uidString);
				approved = Integer.parseInt(approvedString);
				approvedBool = approved == 0 ? false : true;
				pageIndex = Integer.parseInt(pageIndexString);
			} catch (Exception e) {
				System.out.println("err" + e.toString());
				return;
			}
		}

		if (uid == -1) {
			return;
		}
		
//		HttpSession session = req.getSession();
//		Integer uid = (Integer) session.getAttribute("uid");
//
//		// Neu null or role != admin , author -> denied
//
//		// Nguoc lai thi cho lay

		List<ShortDocumentDTO> docs = documentBO.getPersonalDocs(uid, pageIndex, approvedBool);

		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		
		if (docs!= null) {
			
			String documentJsonString = this.gson.toJson(docs);
			out.print(documentJsonString);
			out.flush();
			
		} else {
			resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
		}

	}
}