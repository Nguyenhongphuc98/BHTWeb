package bhtweb.servlets;

import java.awt.List;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bhtweb.bo.DocumentBO;
import bhtweb.dto.ShortDocumentDTO;
import bhtweb.utils.DocumentFilter;

// FilterDocumentServlet?subject=id1&category=id2&semester=id3&year=id4&page=id5

@WebServlet(name = "FilterDocumentServlet", urlPatterns = {"/FilterDocumentServlet"})
public class FilterDocumentServlet extends HttpServlet {
	
	DocumentBO documentBO;
	
	@Override
	public void init() throws ServletException {

		documentBO = new DocumentBO();
		
		super.init();
	}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    	String subjectId = req.getParameter("subject");
    	String categoryId = req.getParameter("category");
    	String semesterId = req.getParameter("semester");
    	String yearNoId = req.getParameter("year");
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
    	
    	if (yearNoId != null) {
    		try {
    			filter.setYearNo(Integer.parseInt(yearNoId));
			} catch (Exception e) { }
		}
    	
    	int pageIndex = 0;
    	if (page != null) {
    		try {
    			pageIndex = Integer.parseInt(page);
			} catch (Exception e) { }
		}
    	
    	java.util.List<ShortDocumentDTO> result = documentBO.searchDocument(filter, pageIndex);
    	for (int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i).toString());
		}
    	
    	resp.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {
            out.println("Filter docs...");
        }
        
        
    }
}