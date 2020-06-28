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

import bhtweb.bo.DocumentBO;
import bhtweb.dto.DocumentDTO;
import bhtweb.dto.ShortDocumentDTO;

//GetDocumentDetailServlet?id=n

@WebServlet(name = "GetDocumentDetailServlet", urlPatterns = { "/GetDocumentDetailServlet" })
public class GetDocumentDetailServlet extends HttpServlet {

	DocumentBO documentBO;

	public void init() {

		documentBO = new DocumentBO();

	}

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
		String idString = req.getParameter("id");
		int id = 0;
		if (idString != null) {
			try {
				id = Integer.parseInt(idString);
			} catch (Exception  e) {
				return;
			}
		}
    	
		if (id == 0) { return; }
    	
    	resp.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {
            out.println("GetDocumentDetailServlet, id: " + id);
        }
        
        DocumentDTO doc = documentBO.viewDocumentDetail(id);
        
        System.out.println("vcvc");
		System.out.println(doc.toString());
		
    }
}