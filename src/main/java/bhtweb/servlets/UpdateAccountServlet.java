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
import bhtweb.dto.NewAccountDTO;
import bhtweb.dto.ResponseStatus;
import bhtweb.utils.ServletUtils;

@WebServlet(name = "UpdateAccountServlet", urlPatterns = { "/account/update" })
public class UpdateAccountServlet extends HttpServlet {

	UserAccountBO userAccountBO;
	private Gson gson;

	public void init() {
		userAccountBO = new UserAccountBO();
		gson = new Gson();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		ResponseStatus status = new ResponseStatus();
		
		//ServletUtils.addNoCORSHeader(resp);
		ServletUtils.addSessionToResponseStatus(req, status);
		
		NewAccountDTO newAccount = new NewAccountDTO();
		newAccount.setUserName(ServletUtils.removeSessionID(req.getParameter("username")));
		newAccount.setUserPassword(ServletUtils.removeSessionID(req.getParameter("password")));
		
		newAccount.setEmail(ServletUtils.removeSessionID(req.getParameter("email")));
		newAccount.setDisplayName(ServletUtils.removeSessionID(req.getParameter("displayname")));
		
		String oldPass = ServletUtils.removeSessionID(req.getParameter("oldPasword"));

		PrintWriter out = ServletUtils.getJSONUnicodeWriterNoCORS(resp);

		String statuString = "";
		if (userAccountBO.UpdateAccount(newAccount, oldPass)) {
			status.setStatusCode(ResponseStatus.UPDATE_ACCOUNT_SUCCESS);
			status.setNewAccount(newAccount);

		} else {
			// user name not exits
			status.setStatusCode(ResponseStatus.UPDATE_ACCOUNT_FAIL);
		}

		statuString = this.gson.toJson(status);
		out.print(statuString);
		out.flush();

	}
}