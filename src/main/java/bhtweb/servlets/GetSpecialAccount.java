package bhtweb.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import bhtweb.bo.UserAccountBO;
import bhtweb.dto.AccountDTO;
import bhtweb.dto.ResponseStatus;
import bhtweb.utils.ServletUtils;

///user?id=n

@WebServlet(name = "GetSpecialAccount", urlPatterns = { "/user" })
public class GetSpecialAccount extends HttpServlet {

	UserAccountBO userAccountBO;
	private Gson gson;

	public void init() {
		userAccountBO = new UserAccountBO();
		gson = new Gson();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//ServletUtils.addNoCORSHeader(resp);

		String uid = req.getParameter("id");

		PrintWriter out = ServletUtils.getJSONUnicodeWriterNoCORS(resp);

		String accountJsonString = "";
		ResponseStatus getUserMessageDTO = new ResponseStatus();

		if (uid == null) {
			getUserMessageDTO.setStatusCode(ResponseStatus.USER_NOT_FOUND);
		} else {
			try {

				int id = Integer.parseInt(uid);
				AccountDTO accountDTO = userAccountBO.getAccountById(id);
				if (accountDTO != null) {
					getUserMessageDTO.setStatusCode(ResponseStatus.GET_ACCOUNT_SUCCESS);
					getUserMessageDTO.setAccount(accountDTO);
				} else {
					getUserMessageDTO.setStatusCode(ResponseStatus.USER_NOT_FOUND);
				}

			} catch (Exception e) {
				getUserMessageDTO.setStatusCode(ResponseStatus.USER_NOT_FOUND);
			}

		}

		accountJsonString = this.gson.toJson(getUserMessageDTO);
		out.print(accountJsonString);
		out.flush();
	}
}