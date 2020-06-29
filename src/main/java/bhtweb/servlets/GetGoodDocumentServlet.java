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
import bhtweb.dto.ShortDocumentDTO;
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
    	
    	int limit = Integer.parseInt(req.getParameter("limit"));
    	
        List<ShortDocumentDTO> docs = documentBO.getMostDownloadDocumentList(limit);
        
        PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		
		if(docs != null) {
			String documentJsonString = this.gson.toJson(docs);
			out.print(documentJsonString);
			out.flush();
		}
		
    }
}