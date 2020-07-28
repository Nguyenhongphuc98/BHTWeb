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
import bhtweb.utils.BHTRole;
import bhtweb.utils.BHTSession;
import bhtweb.utils.ServletUtils;


@WebServlet(name = "ChangeRuleUserServlet", urlPatterns = { "/admin/updaterole" })
public class ChangeRuleUserServlet extends HttpServlet {

	UserAccountBO userAccountBO;
	private Gson gson;

	public void init() {
		userAccountBO = new UserAccountBO();
		gson = new Gson();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		ResponseStatus status = new ResponseStatus();
	
		//ServletUtils.addSessionToResponseStatus(req, status);
		
		NewAccountDTO newAccount = new NewAccountDTO();
		newAccount.setUserName(ServletUtils.removeSessionID(req.getParameter("username")));
		
		String ruleId = ServletUtils.removeSessionID(req.getParameter("ruleId"));
		if (ruleId != null) {
			try {
				int id = Integer.parseInt(ruleId);
				newAccount.setUserGroupID(id);
			} catch (Exception e) { }
		}

		PrintWriter out = ServletUtils.getJSONUnicodeWriterNoCORS(resp);
		String statuString = "";
		
		//HttpSession session = req.getSession();
		//AccountDTO accountDTO = (AccountDTO) session.getAttribute("account");
		AccountDTO accountDTO = BHTSession.currentUser(req);
		
		if (accountDTO != null && accountDTO.getRoleId() == BHTRole.ADMIN) {
			if (userAccountBO.UpdateAccount(newAccount, "noneeded", true)) {
				status.setStatusCode(ResponseStatus.UPDATE_ACCOUNT_SUCCESS);
				status.setNewAccount(newAccount);

			} else {
				// user name not exits
				status.setStatusCode(ResponseStatus.UPDATE_ACCOUNT_FAIL);
			}
		} else {
			status.setStatusCode(ResponseStatus.PERMISSION_DENNED);
		}
		
		statuString = this.gson.toJson(status);
		out.print(statuString);
		out.flush();

	}
}