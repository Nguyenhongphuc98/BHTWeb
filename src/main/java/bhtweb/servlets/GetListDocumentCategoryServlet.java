package bhtweb.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import bhtweb.bo.DocumentCategoryBO;
import bhtweb.bo.SubjectBO;
import bhtweb.entities.BHTDocumentCategory;
import bhtweb.entities.BHTSubject;
import bhtweb.utils.ServletUtils;


@WebServlet(name = "GetListDocumentCategory", urlPatterns = {"/docs/categories"})
public class GetListDocumentCategoryServlet extends HttpServlet {
	
	DocumentCategoryBO categotyBO;
	private Gson gson;

	public void init() {

		categotyBO = new DocumentCategoryBO();
		gson = new Gson();
	}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
        List<BHTDocumentCategory> categories = categotyBO.viewAllDocCategory();
        
        PrintWriter out = ServletUtils.getJSONUnicodeWriterNoCORS(resp);
        
        if (categories != null) {
        	String categoriesJsonString = this.gson.toJson(categories);
			out.print(categoriesJsonString);
			out.flush();
		}
        
    }
}