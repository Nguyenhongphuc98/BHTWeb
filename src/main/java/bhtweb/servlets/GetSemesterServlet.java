package bhtweb.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bhtweb.bo.SemesterBO;
import bhtweb.bo.SubjectBO;
import bhtweb.entities.BHTSemester;
import bhtweb.entities.BHTSubject;

@WebServlet(name = "GetSemesterServlet", urlPatterns = {"/GetSemesterServlet"})
public class GetSemesterServlet extends HttpServlet {
	
	SemesterBO semesterBO;

	public void init() {

		semesterBO = new SemesterBO();
	}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    	resp.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {
            out.println("GetSemesterServlet ");
        }
        
        List<BHTSemester> semesters = semesterBO.viewAllSemester();
        System.out.println("num of semester: " + semesters.size());
    }
}