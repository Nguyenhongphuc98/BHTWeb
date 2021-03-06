package bhtweb.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import bhtweb.bo.UserAccountBO;
import bhtweb.dto.AccountDTO;
import bhtweb.dto.ResponseStatus;
import bhtweb.utils.BHTSession;
import bhtweb.utils.ServletUtils;


@WebServlet(name = "LogoutServlet", urlPatterns = { "/logout" })
public class LogoutServlet extends HttpServlet {

	UserAccountBO userAccountBO;
	private Gson gson;

	public void init() {
		userAccountBO = new UserAccountBO();
		gson = new Gson();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//ServletUtils.addNoCORSHeader(resp);
		
		// get accoun from session
		//HttpSession session = req.getSession();
		//AccountDTO accountDTO = (AccountDTO) session.getAttribute("account");
		
		AccountDTO accountDTO = BHTSession.currentUser(req);

		PrintWriter out = ServletUtils.getJSONUnicodeWriterNoCORS(resp);

		String accountJsonString = "";
		ResponseStatus loginMessageDTO = new ResponseStatus();
		
		if (accountDTO != null) {
			//session.removeAttribute("account");
			//session.invalidate();
			BHTSession.logout(req);
			loginMessageDTO.setStatusCode(ResponseStatus.LOGOUT_SUCCESS);
		} else {
			// no account in this session
			loginMessageDTO.setStatusCode(ResponseStatus.LOGOUT_FAIL);
		}

		accountJsonString = this.gson.toJson(loginMessageDTO);
		out.print(accountJsonString);
		out.flush();
	}
}