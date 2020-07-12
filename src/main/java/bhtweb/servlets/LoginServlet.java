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
import bhtweb.dto.LoginMessageDTO;
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

		// lay username and password. check ok thi luu vao session
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		 Enumeration paramNames = req.getParameterNames();
			while (paramNames.hasMoreElements()) {
				String paramName = (String) paramNames.nextElement();
				System.out.println("param: " + paramName);

			}

		AccountDTO accountDTO = userAccountBO.getAccountByUsername(username);

		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");

		LoginMessageDTO loginMessageDTO = new LoginMessageDTO();
		String accountJsonString = "";
		if (accountDTO != null) {
			if (accountDTO.getPassword().equals(password)) {

				loginMessageDTO.setLoginStatus(LoginMessageDTO.SUCCESS);
				loginMessageDTO.setAccount(accountDTO);

				// save to session
				HttpSession session = req.getSession();
				session.setAttribute("account", accountDTO);

			} else {
				loginMessageDTO.setLoginStatus(LoginMessageDTO.WRONGPASWORD);
			}

		} else {
			// user name not exits
			loginMessageDTO.setLoginStatus(LoginMessageDTO.USERNAMEINCORECT);
		}

		accountJsonString = this.gson.toJson(loginMessageDTO);
		out.print(accountJsonString);
		out.flush();

	}
}