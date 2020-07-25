package bhtweb.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import bhtweb.dto.AccountDTO;

public class BHTRole {

	public static int ADMIN = 1;

	public static int COLLABORATOR = 2;

	public static int USER = 3;

	public static Boolean hasAdminPermission(HttpServletRequest req, int resourceAuthorID) {

		// get accoun from session
		HttpSession session = req.getSession();
		AccountDTO accountDTO = (AccountDTO) session.getAttribute("account");

		if (accountDTO != null && 
				(accountDTO.getRoleId() == BHTRole.ADMIN || accountDTO.getId() == resourceAuthorID)) {
			return true;
		}
		
		return false;
	}
	
	public static Boolean hasCollaboratorPermission(HttpServletRequest req, int resourceAuthorID) {

		// get accoun from session
		HttpSession session = req.getSession();
		AccountDTO accountDTO = (AccountDTO) session.getAttribute("account");

		if (accountDTO != null && 
				(accountDTO.getRoleId() == BHTRole.ADMIN 
				||accountDTO.getRoleId() == BHTRole.COLLABORATOR 
				|| accountDTO.getId() == resourceAuthorID)
				) {
			return true;
		}
		
		return false;
	}
}
