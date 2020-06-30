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

import com.google.gson.Gson;

import bhtweb.bo.DocumentBO;
import bhtweb.dto.DocumentDTO;
import bhtweb.dto.ShortDocumentDTO;

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
    	
		String idString = req.getParameter("id");
		int id = -1;
		if (idString != null) {
			try {
				id = Integer.parseInt(idString);
			} catch (Exception  e) {
				resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
				return;
			}
		} else {
			resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
    	
		if (id == -1) { return; }
    	
        DocumentDTO doc = documentBO.viewDocumentDetail(id);
        
        PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
        
        if (doc != null) {
        	String documentJsonString = this.gson.toJson(doc);
			out.print(documentJsonString);
			out.flush();
		}
		
        resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
    }
}