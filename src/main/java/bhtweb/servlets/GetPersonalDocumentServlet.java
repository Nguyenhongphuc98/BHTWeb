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

import bhtweb.bo.DocumentBO;
import bhtweb.dto.ShortDocumentDTO;

// GetPersonalDocumentServlet? uid = 1 & approved = 0& page =0
@WebServlet(name = "GetPersonalDocumentServlet", urlPatterns = { "/GetPersonalDocumentServlet" })
public class GetPersonalDocumentServlet extends HttpServlet {

	DocumentBO documentBO;

	public void init() {

		documentBO = new DocumentBO();

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("in get personal docs");
		
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
//		// Neu null or role != admin -> denied
//
//		// Nguoc lai thi update status to approved

		resp.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = resp.getWriter()) {
			out.println("get personal servlet, uid: " + uid);
		}

		List<ShortDocumentDTO> docs = documentBO.getPersonalDocs(uid, pageIndex, approvedBool);

		if (docs!= null) {
			for (int i = 0; i < docs.size(); i++) {
				System.out.println(docs.get(i).toString());
			}
			
		} else {
			System.out.println("dont have doc of this user");
		}

	}
}