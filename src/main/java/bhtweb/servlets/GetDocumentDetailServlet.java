package bhtweb.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.imageio.metadata.IIOInvalidTreeException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.api.client.googleapis.auth.clientlogin.ClientLogin.Response;
import com.google.gson.Gson;

import bhtweb.bo.DocumentBO;
import bhtweb.dto.DocumentDTO;
import bhtweb.dto.ResponseStatus;
import bhtweb.dto.ShortDocumentDTO;
import bhtweb.utils.ServletUtils;

// docs/detail?id=n

@WebServlet(name = "GetDocumentDetailServlet", urlPatterns = { "/docs/detail" })
public class GetDocumentDetailServlet extends HttpServlet {

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
		
		String idString = ServletUtils.removeSessionID(req.getParameter("id"));
		int id = -1;
		if (idString != null) {
			try {
				id = Integer.parseInt(idString);

				DocumentDTO doc = documentBO.viewDocumentDetail(id);

				if (doc != null) {

					status.setStatusCode(ResponseStatus.GET_RESOURCE_SUCCESS);
					status.setDocumentDTO(doc);

					// System.out.println("got doc: " + doc.getSubjectName());
				} else {
					status.setStatusCode(ResponseStatus.RESOURCE_NOT_FOUND);
				}

			} catch (Exception e) {
				// resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
				// return;
				status.setStatusCode(ResponseStatus.RESOURCE_NOT_FOUND);
			}
		} else {
			status.setStatusCode(ResponseStatus.RESOURCE_NOT_FOUND);
		}

		String statusJsonString = this.gson.toJson(status);
		out.print(statusJsonString);
		out.flush();
	}
}