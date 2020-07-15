package bhtweb.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bhtweb.dto.ResponseStatus;

public class ServletUtils {

	public static void addHeaderToResponse (HttpServletResponse resp) {
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setHeader("Access-Control-Allow-Credentials", "true");
		resp.setHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
		resp.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept");
	}
	
	public static void addSessionToResponseStatus (HttpServletRequest req, ResponseStatus status) {
		
		HttpSession session = req.getSession();
		status.setSessionID(session.getId());
	}
}
