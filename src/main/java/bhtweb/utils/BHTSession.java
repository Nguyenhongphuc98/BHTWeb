package bhtweb.utils;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import bhtweb.dto.AccountDTO;
import bhtweb.dto.NewAccountDTO;

public class BHTSession {
	
	public static HashMap<String, AccountDTO> currentAccounts = new HashMap<String, AccountDTO>();

	public static String login(HttpServletRequest request, AccountDTO account) {
		
		HttpSession session = request.getSession();
		String sessionID = session.getId();
		currentAccounts.put(sessionID, account);
		
		return sessionID;
	}
	
	public static AccountDTO currentUser(HttpServletRequest req) {
		String sessionID = req.getParameter("sessionID");
		return currentAccounts.get(sessionID);
	}
	
	public static void logout(HttpServletRequest request) {
		String sessionID = request.getParameter("sessionID");
		currentAccounts.put(sessionID, null);
	}
	
	public static void updateAccount(HttpServletRequest request, NewAccountDTO accountDTO) {
		
		String sessionID = request.getParameter("sessionID");
		AccountDTO account = currentAccounts.get(sessionID);
		
		if (account == null) {
			return;
		}
		if (accountDTO.getDisplayName() != null) {
			account.setDisplayName(accountDTO.getDisplayName());
		}
		
		if (accountDTO.getUserPassword() != null) {
			account.setPassword(accountDTO.getUserPassword());
		}
		
		if (accountDTO.getEmail() != null) {
			account.setEmail(accountDTO.getEmail());
		}
	}
}
