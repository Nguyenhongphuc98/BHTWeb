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
import bhtweb.dto.DocumentDTO;
import bhtweb.dto.ShortDocumentDTO;


@WebServlet(name = "GetAllDocumentNotAprovedYetServlet", urlPatterns = { "/GetAllDocumentNotAprovedYetServlet" })
public class GetAllDocumentNotAprovedYetServlet extends HttpServlet {

	DocumentBO documentBO;

	public void init() {

		documentBO = new DocumentBO();

	}

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    	resp.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {
            out.println("GetAllDocumentNotAprovedYetServlet");
        }
        
        HttpSession session = req.getSession();
        Integer uid = (Integer) session.getAttribute("uid");
        
        // Neu null or role != admin -> denied
        
        // Nguoc lai thi cho lay
        
        List<ShortDocumentDTO> docs = documentBO.getListDocumentToBrowse();
        
        for (int i = 0; i < docs.size(); i++) {
        	System.out.println(docs.get(i).toString());
		}
		
		
    }
}