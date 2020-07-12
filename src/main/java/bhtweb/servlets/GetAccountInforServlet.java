package bhtweb.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import bhtweb.bo.UserAccountBO;
import bhtweb.dto.AccountDTO;
import bhtweb.dto.LoginMessageDTO;

@WebServlet(name = "GetAccountInforServlet", urlPatterns = { "/users/current" })
public class GetAccountInforServlet extends HttpServlet {

	UserAccountBO userAccountBO;
	private Gson gson;

	public void init() {
		userAccountBO = new UserAccountBO();
		gson = new Gson();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// get accoun from session
		HttpSession session = req.getSession();

		AccountDTO accountDTO = (AccountDTO) session.getAttribute("account");

		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");

		String accountJsonString = "";
		LoginMessageDTO loginMessageDTO = new LoginMessageDTO();
		if (accountDTO != null) {

			loginMessageDTO.setLoginStatus(LoginMessageDTO.GET_ACCOUNT_SUCCESS);
			loginMessageDTO.setAccount(accountDTO);

		} else {
			// no account in this session
			loginMessageDTO.setLoginStatus(LoginMessageDTO.NOTFOUND);
		}

		accountJsonString = this.gson.toJson(loginMessageDTO);
		out.print(accountJsonString);
		out.flush();
	}
}