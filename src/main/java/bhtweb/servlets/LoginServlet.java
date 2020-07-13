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

import bhtweb.bo.DocumentBO;
import bhtweb.bo.UserAccountBO;
import bhtweb.dto.AccountDTO;
import bhtweb.dto.ResponseStatus;
import bhtweb.utils.ServletUtils;

@WebServlet(name = "LoginServlet", urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {

	UserAccountBO userAccountBO;
	private Gson gson;

	public void init() {
		userAccountBO = new UserAccountBO();
		gson = new Gson();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		ServletUtils.addHeaderToResponse(resp);
		
		// lay username and password. check ok thi luu vao session
		String username = req.getParameter("username");
		String password = req.getParameter("password");

		AccountDTO accountDTO = userAccountBO.getAccountByUsername(username);

		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");

		ResponseStatus loginMessageDTO = new ResponseStatus();
		String accountJsonString = "";
		if (accountDTO != null) {
			if (accountDTO.getPassword().equals(password)) {

				loginMessageDTO.setStatusCode(ResponseStatus.LOGIN_SUCCESS);
				loginMessageDTO.setAccount(accountDTO);

				// save to session
				HttpSession session = req.getSession();
				session.setAttribute("account", accountDTO);

			} else {
				loginMessageDTO.setStatusCode(ResponseStatus.WRONG_PASWORD);
			}

		} else {
			// user name not exits
			loginMessageDTO.setStatusCode(ResponseStatus.USERNAME_INCORECT);
		}

		accountJsonString = this.gson.toJson(loginMessageDTO);
		out.print(accountJsonString);
		out.flush();

	}
}