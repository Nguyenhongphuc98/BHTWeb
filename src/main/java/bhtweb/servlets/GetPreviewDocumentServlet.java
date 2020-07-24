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
import bhtweb.dto.AccountDTO;
import bhtweb.dto.DocumentDTO;
import bhtweb.dto.ResponseStatus;
import bhtweb.utils.BHTRole;
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

		//ServletUtils.addNoCORSHeader(resp);

		PrintWriter out = ServletUtils.getJSONUnicodeWriterNoCORS(resp);

		ResponseStatus status = new ResponseStatus();
		String documentJsonString = "";

		String idString = ServletUtils.removeSessionID(req.getParameter("id"));
		int id = 0;
		if (idString != null) {
			try {

				id = Integer.parseInt(idString);

				DocumentDTO doc = documentBO.previewDoc(id, 1);
				
				if (doc == null) {
					//resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
					status.setStatusCode(ResponseStatus.RESOURCE_NOT_FOUND);
				} else {
					
					if (BHTRole.hasAdminPermission(req, doc.getAuthorID())) {
						status.setStatusCode(ResponseStatus.GET_RESOURCE_SUCCESS);
						status.setDocumentDTO(doc);
					} else {
						status.setStatusCode(ResponseStatus.PERMISSION_DENNED);
					}
					
				}

			} catch (Exception e) {
				// resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
				// return;
				status.setStatusCode(ResponseStatus.RESOURCE_NOT_FOUND);
			}
		} else {
			// resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
			status.setStatusCode(ResponseStatus.RESOURCE_NOT_FOUND);
		}

		documentJsonString = this.gson.toJson(status);
		out.print(documentJsonString);
		out.flush();
	}
}