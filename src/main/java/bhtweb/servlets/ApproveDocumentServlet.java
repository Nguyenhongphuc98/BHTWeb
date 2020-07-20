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
import bhtweb.dto.ResponseStatus;
import bhtweb.utils.BHTRole;
import bhtweb.utils.ServletUtils;

// docs/approved?id=n

@WebServlet(name = "ApproveDocumentServlet", urlPatterns = { "/admin/docs/approved" })
public class ApproveDocumentServlet extends HttpServlet {

	DocumentBO documentBO;
	private Gson gson;

	public void init() {

		documentBO = new DocumentBO();
		gson = new Gson();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter out = ServletUtils.getJSONUnicodeWriterNoCORS(resp);

		ResponseStatus status = new ResponseStatus();
		String statusString = "";

		String idString = req.getParameter("id");
		int id = 0;
		if (idString != null) {
			try {

				id = Integer.parseInt(idString);

				if (BHTRole.hasAdminPermission(req, -1)) {
					boolean result = documentBO.publishDocument(id);
					if (result) {
						status.setStatusCode(ResponseStatus.BROWSE_DOC_SUCCESS);
					} else {
						status.setStatusCode(ResponseStatus.BROWSE_DOC_FAIL);
					}

				} else {
					status.setStatusCode(ResponseStatus.PERMISSION_DENNED);
				}

			} catch (Exception e) {
				status.setStatusCode(ResponseStatus.RESOURCE_NOT_FOUND);
			}
		} else {
			status.setStatusCode(ResponseStatus.RESOURCE_NOT_FOUND);
		}

		statusString = this.gson.toJson(status);
		out.print(statusString);
		out.flush();
	}
}