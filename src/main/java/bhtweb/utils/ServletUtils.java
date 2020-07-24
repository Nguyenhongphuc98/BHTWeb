package bhtweb.utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.output.ThresholdingOutputStream;

import com.google.api.client.repackaged.com.google.common.base.Throwables;
import com.google.gson.Gson;

import bhtweb.dto.AccountDTO;
import bhtweb.dto.ResponseStatus;

public class ServletUtils {
	
	private static final Gson gson = new Gson();
	
	public static void printObjectJSON (PrintWriter out, HttpServletResponse response,
			Object obj, Integer statusCode) {
		
		String jsonResponse = "";
		
		if (obj != null) {
			jsonResponse = gson.toJson(obj);
		}
		response.setStatus(statusCode);
		out.print(jsonResponse);
		out.flush();
	}
	
	public static String getStringParam(HttpServletRequest req, 
			String paramName, String defaultValue) {
		String paramValue = req.getParameter(paramName);
		if (paramValue == null || paramValue.length() == 0)
			return defaultValue;
		return paramValue;
	}
	
	public static Integer getIntegerParam (HttpServletRequest req, 
			String paramName, Integer defaultValue) {
		
		String paramValue = req.getParameter(paramName);
		Integer integerResult = null;
		System.out.println("Something called getIntegerParam !");
		try {
			integerResult = Integer.parseInt(paramValue);
			System.out.println("Integer Result : " + integerResult);
		}catch (Exception ex) {
			return defaultValue;
		}finally {
			if (integerResult == null)
				return defaultValue;
		}
		
		return integerResult;
	}
	
	public static void addNoCORSHeader (HttpServletResponse resp) {
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setHeader("Access-Control-Allow-Credentials", "true");
		resp.setHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
		resp.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept");
	}
	
	//Lưu ý cần apply noCORSHeader trước khi getWriter.
	public static PrintWriter getJSONUnicodeWriter (HttpServletResponse response) throws IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		return writer;
	}
	
	public static PrintWriter getJSONUnicodeWriterNoCORS (HttpServletResponse response) throws IOException{
		addNoCORSHeader(response);
		return getJSONUnicodeWriter(response);
	}
	
	public static AccountDTO getCurrentAccountDTO(HttpServletRequest req) {
		HttpSession session = req.getSession();

		AccountDTO accountDTO = (AccountDTO) session.getAttribute("account");
		
		return accountDTO;
	}
	
	public static void addSessionToResponseStatus (HttpServletRequest req, ResponseStatus status) {
		
		HttpSession session = req.getSession();
		status.setSessionID(session.getId());
	}
	
	public static String removeSessionID(String parameter) {
		if (parameter == null) {
			return null;
		}
		return parameter.split(";")[0];
	}
}
