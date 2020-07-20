package bhtweb.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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
import bhtweb.utils.ServletUtils;

// Just admin can view all user !!!
@WebServlet(name = "GetAllUserServlet", urlPatterns = { "/users" })
public class GetAllUserServlet extends HttpServlet {

	UserAccountBO userAccountBO;
	private Gson gson;

	public void init() {
		userAccountBO = new UserAccountBO();
		gson = new Gson();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//ServletUtils.addNoCORSHeader(resp);

		PrintWriter out = ServletUtils.getJSONUnicodeWriterNoCORS(resp);

		ResponseStatus loginMessageDTO = new ResponseStatus();
		String accountJsonString = "";
		
		HttpSession session = req.getSession();
		AccountDTO currentUser = (AccountDTO) session.getAttribute("account");
		
		// role = 1 mean this user is admin
		if (currentUser != null && currentUser.getRoleId() == 1) {
			
				List<AccountDTO> accountDTOs = userAccountBO.viewAllUser();
				
				loginMessageDTO.setStatusCode(ResponseStatus.GET_ACCOUNT_SUCCESS);
				loginMessageDTO.setAccounts(accountDTOs);

			} else {
				loginMessageDTO.setStatusCode(ResponseStatus.PERMISSION_DENNED);
			}


		accountJsonString = this.gson.toJson(loginMessageDTO);
		out.print(accountJsonString);
		out.flush();
	}
}
