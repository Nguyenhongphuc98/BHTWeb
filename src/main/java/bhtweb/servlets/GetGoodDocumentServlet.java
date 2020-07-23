package bhtweb.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import bhtweb.bo.DocumentBO;
import bhtweb.dto.DocumentUploadDTO;
import bhtweb.dto.ResponseStatus;
import bhtweb.dto.ShortDocumentDTO;
import bhtweb.utils.ServletUtils;
import bhtweb.utils.Uploader;

// docs/goodDoc?limit=n

@WebServlet(name = "GetGoodDocumentServlet", urlPatterns = { "/docs/goodDoc" })
public class GetGoodDocumentServlet extends HttpServlet {

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

		try {
			int limit = Integer.parseInt(ServletUtils.removeSessionID(req.getParameter("limit")));

			List<ShortDocumentDTO> docs = documentBO.getMostDownloadDocumentList(limit);

			if (docs != null) {
				status.setStatusCode(ResponseStatus.GET_RESOURCE_SUCCESS);
				status.setShortDocs(docs);
			} else {
				status.setStatusCode(ResponseStatus.RESOURCE_NOT_FOUND);
			}
		} catch (Exception e) {
			status.setStatusCode(ResponseStatus.RESOURCE_NOT_FOUND);
		}

		String statusJsonString = this.gson.toJson(status);
		out.print(statusJsonString);
		out.flush();

	}
}