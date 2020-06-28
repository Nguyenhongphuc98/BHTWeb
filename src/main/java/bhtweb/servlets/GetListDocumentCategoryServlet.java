package bhtweb.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bhtweb.bo.DocumentCategoryBO;
import bhtweb.bo.SubjectBO;
import bhtweb.entities.BHTDocumentCategory;
import bhtweb.entities.BHTSubject;


@WebServlet(name = "GetListDocumentCategory", urlPatterns = {"/GetListDocumentCategory"})
public class GetListDocumentCategoryServlet extends HttpServlet {
	
	DocumentCategoryBO categotyBO;

	public void init() {

		categotyBO = new DocumentCategoryBO();
	}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    	resp.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {
            out.println("GetListDocumentCategory ");
        }
        
        List<BHTDocumentCategory> categories = categotyBO.viewAllDocCategory();
        System.out.println("num of categories: " + categories.size());
    }
}