package bhtweb.dto;

import com.google.protobuf.GeneratedMessage;

public class LoginMessageDTO {

	public static int USERNAMEINCORECT = 0;
	public static int WRONGPASWORD = 1;
	public static int UNKNOWN = 2;
	public static int SUCCESS = 3;
	
	int loginStatus;
	
	String loginMessage;
	
	AccountDTO account;
	
	public LoginMessageDTO() {
		
	}
	
	public LoginMessageDTO(int loginStatus, AccountDTO account) {
		super();
		this.loginStatus = loginStatus;
		this.loginMessage = GeneratedMessage(loginStatus);
		this.account = account;
	}
	
	private static String GeneratedMessage(int code) {
		
		switch (code) {
		case 0:
			return "Incorrect username!";
			
		case 1:
			return "Incorrect password!";
			
		case 2:
			return "System Error!";
			
		case 3:
			return "Login success!";

		default:
			return "Unknow";
		}
	}
	
	public int getLoginStatus() {
		return loginStatus;
	}
	
	public void setLoginStatus(int loginStatus) {
		this.loginMessage = GeneratedMessage(loginStatus);
		this.loginStatus = loginStatus;
	}
	
	public AccountDTO getAccount() {
		return account;
	}
	
	public void setAccount(AccountDTO account) {
		this.account = account;
	}

	public String getLoginMessage() {
		return loginMessage;
	}

	public void setLoginMessage(String loginMessage) {
		this.loginMessage = loginMessage;
	}
}
