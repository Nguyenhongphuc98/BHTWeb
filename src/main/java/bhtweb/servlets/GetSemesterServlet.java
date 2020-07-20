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

import bhtweb.bo.SemesterBO;
import bhtweb.bo.SubjectBO;
import bhtweb.entities.BHTSemester;
import bhtweb.entities.BHTSubject;
import bhtweb.utils.ServletUtils;

@WebServlet(name = "GetSemesterServlet", urlPatterns = {"/semesters"})
public class GetSemesterServlet extends HttpServlet {
	
	SemesterBO semesterBO;
	private Gson gson;

	public void init() {

		semesterBO = new SemesterBO();
		gson = new Gson();
	}

    @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    	//ServletUtils.addNoCORSHeader(resp);
    	
    	PrintWriter out = ServletUtils.getJSONUnicodeWriterNoCORS(resp);

		List<BHTSemester> semesters = semesterBO.viewAllSemester();

		if (semesters != null) {
			String semestersJsonString = this.gson.toJson(semesters);
			out.print(semestersJsonString);
			out.flush();
		}
	}
}