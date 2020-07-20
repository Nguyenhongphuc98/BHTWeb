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
import bhtweb.bo.SubjectBO;
import bhtweb.entities.BHTSubject;
import bhtweb.utils.ServletUtils;
import bhtweb.utils.Uploader;


@WebServlet(name = "GetListSubjectServlet", urlPatterns = {"/subjects"})
public class GetListSubjectServlet extends HttpServlet {
	
	SubjectBO subjectBO;
	private Gson gson;

	public void init() {

		subjectBO = new SubjectBO();
		gson = new Gson();
	}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    	//ServletUtils.addNoCORSHeader(resp);
    	
        List<BHTSubject> subjects = subjectBO.viewAllSubjects();
        
        PrintWriter out = ServletUtils.getJSONUnicodeWriterNoCORS(resp);
		
        if (subjects != null) {
        	String subjectsJsonString = this.gson.toJson(subjects);
			out.print(subjectsJsonString);
			out.flush();
		}
    }
}