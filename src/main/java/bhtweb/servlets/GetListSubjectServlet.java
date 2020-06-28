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
import bhtweb.bo.SubjectBO;
import bhtweb.entities.BHTSubject;
import bhtweb.utils.Uploader;


@WebServlet(name = "GetListSubjectServlet", urlPatterns = {"/GetListSubjectServlet"})
public class GetListSubjectServlet extends HttpServlet {
	
	SubjectBO subjectBO;

	public void init() {

		subjectBO = new SubjectBO();
	}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    	resp.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {
            out.println("GetListSubjectServlet ");
        }
        
        List<BHTSubject> subjects = subjectBO.viewAllSubjects();
        System.out.println("num of subject: " + subjects.size());
    }
}