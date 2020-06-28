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

import bhtweb.bo.DocumentBO;
import bhtweb.dto.DocumentUploadDTO;
import bhtweb.dto.ShortDocumentDTO;
import bhtweb.utils.Uploader;

// /GetGoodDocumentServlet?limit=n

@WebServlet(name = "GetGoodDocumentServlet", urlPatterns = { "/GetGoodDocumentServlet" })
public class GetGoodDocumentServlet extends HttpServlet {

	DocumentBO documentBO;

	public void init() {

		documentBO = new DocumentBO();

	}

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    	int limit = Integer.parseInt(req.getParameter("limit"));
    	
    	resp.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {
            out.println("GetGoodDocumentServlet, limit: " + limit);
        }
        
        List<ShortDocumentDTO> docs = documentBO.getMostDownloadDocumentList(limit);
        
        for (int i = 0; i < docs.size(); i++) {
			System.out.println(docs.get(i).toString());
		}
    }
}