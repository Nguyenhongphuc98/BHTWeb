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
import bhtweb.dto.ShortDocumentDTO;


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
        
        HttpSession session = req.getSession();
        Integer uid = (Integer) session.getAttribute("uid");
        
        // Neu null or role != admin -> denied
        
        // Nguoc lai thi cho lay
        
        List<ShortDocumentDTO> docs = documentBO.getListDocumentToBrowse();
        
        PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		
        if (docs != null) {
        	String documentJsonString = this.gson.toJson(docs);
			out.print(documentJsonString);
			out.flush();
		}
		
		
    }
}