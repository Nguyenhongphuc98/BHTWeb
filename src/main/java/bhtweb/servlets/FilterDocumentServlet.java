package bhtweb.servlets;

import java.awt.List;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import bhtweb.bo.DocumentBO;
import bhtweb.dto.ResponseStatus;
import bhtweb.dto.ShortDocumentDTO;
import bhtweb.utils.DocumentFilter;
import bhtweb.utils.ServletUtils;

// docs/search?subject=id1&category=id2&semester=id3&year=id4&page=id5

@WebServlet(name = "FilterDocumentServlet", urlPatterns = {"/docs/search"})
public class FilterDocumentServlet extends HttpServlet {
	
	DocumentBO documentBO;
	private Gson gson;
	
	@Override
	public void init() throws ServletException {

		documentBO = new DocumentBO();
		gson = new Gson();
		
	}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    	PrintWriter out = ServletUtils.getJSONUnicodeWriterNoCORS(resp);
    	
    	String subjectId = req.getParameter("subject");
    	String categoryId = req.getParameter("category");
    	String semesterId = req.getParameter("semester");
    	//String yearNoId = req.getParameter("year");
    	String page = req.getParameter("page");
    	
    	DocumentFilter filter = new DocumentFilter();
    	if (subjectId != null) {
    		try {
    			filter.setSubjectId(Integer.parseInt(subjectId));
			} catch (Exception e) { }
		}
    	
    	if (categoryId != null) {
    		try {
    			filter.setCategoreId(Integer.parseInt(categoryId));
			} catch (Exception e) { }
		}
    	
    	if (semesterId != null) {
    		try {
    			filter.setSemesterId(Integer.parseInt(semesterId));
			} catch (Exception e) { }
		}
    	
//    	if (yearNoId != null) {
//    		try {
//    			filter.setYearNo(Integer.parseInt(yearNoId));
//			} catch (Exception e) { }
//		}
    	
    	int pageIndex = 0;
    	if (page != null) {
    		try {
    			pageIndex = Integer.parseInt(page);
			} catch (Exception e) { }
		}
    	
    	ResponseStatus status = new ResponseStatus();
		
		java.util.List<ShortDocumentDTO> result = documentBO.searchDocument(filter, pageIndex);
		
    	if (result != null) {
    		status.setStatusCode(ResponseStatus.GET_RESOURCE_SUCCESS);
    		status.setShortDocs(result);
		} else {
			status.setStatusCode(ResponseStatus.RESOURCE_NOT_FOUND);
		}
    	
    	String statusJsonString = this.gson.toJson(status);
		out.print(statusJsonString);
		out.flush();
    }
}