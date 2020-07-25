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
import bhtweb.dto.DocumentDTO;
import bhtweb.dto.ResponseStatus;
import bhtweb.dto.ShortDocumentDTO;
import bhtweb.utils.BHTRole;
import bhtweb.utils.ServletUtils;


@WebServlet(name = "GetAllDocumentNotAprovedYetServlet", urlPatterns = { "/admin/docs/notApproved" })
public class GetAllDocumentNotAprovedYetServlet extends HttpServlet {

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
		
//        HttpSession session = req.getSession();
//        Integer uid = (Integer) session.getAttribute("uid");
        
		String documentJsonString = "";
		ResponseStatus status = new ResponseStatus();
		
		// -1 mean just check admin, because no user have id -1
		if (BHTRole.hasCollaboratorPermission(req, -1)) {
			List<ShortDocumentDTO> docs = documentBO.getListDocumentToBrowse();
			if (docs != null) {
				status.setStatusCode(ResponseStatus.GET_RESOURCE_SUCCESS);
				status.setShortDocs(docs);
			} else {
				status.setStatusCode(ResponseStatus.RESOURCE_NOT_FOUND);
			}
		} else {
			status.setStatusCode(ResponseStatus.PERMISSION_DENNED);
		}
		
        documentJsonString = this.gson.toJson(status);
		out.print(documentJsonString);
		out.flush();
    }
}