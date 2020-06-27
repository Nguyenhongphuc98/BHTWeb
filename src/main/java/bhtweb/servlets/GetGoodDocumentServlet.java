package bhtweb.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// /GetGoodDocumentServlet?limit=n

@WebServlet(name = "GetGoodDocumentServlet", urlPatterns = {"/GetGoodDocumentServlet"})
public class GetGoodDocumentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    	int limit = Integer.parseInt(req.getParameter("limit"));
    	
    	resp.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {
            out.println("GetGoodDocumentServlet, limit: " + limit);
        }
        
        
    }
}