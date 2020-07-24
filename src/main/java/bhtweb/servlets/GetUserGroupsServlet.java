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
import bhtweb.bo.UserGroupBO;
import bhtweb.dto.AccountDTO;
import bhtweb.dto.NewAccountDTO;
import bhtweb.dto.ResponseStatus;
import bhtweb.entities.BHTUserGroup;
import bhtweb.utils.BHTRole;
import bhtweb.utils.ServletUtils;

@WebServlet(name = "GetUserGroupsServlet", urlPatterns = { "/usergroups" })
public class GetUserGroupsServlet extends HttpServlet {

	UserGroupBO userGroupBO;
	private Gson gson;

	public void init() {
		userGroupBO = new UserGroupBO();
		gson = new Gson();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		PrintWriter out = ServletUtils.getJSONUnicodeWriterNoCORS(resp);

		String statusString = "";
		ResponseStatus status = new ResponseStatus();

		List<BHTUserGroup> userGroups = userGroupBO.getUserGroups();
		
		if (userGroups == null) {
			status.setStatusCode(ResponseStatus.RESOURCE_NOT_FOUND);
		} else {
			status.setStatusCode(ResponseStatus.GET_RESOURCE_SUCCESS);
			status.setBhtUserGroups(userGroups);
		}

		statusString = this.gson.toJson(status);
		out.print(statusString);
		out.flush();
	}
}