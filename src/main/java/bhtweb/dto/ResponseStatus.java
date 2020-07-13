package bhtweb.dto;

import com.google.protobuf.GeneratedMessage;

public class ResponseStatus {

	// login
	public static int USERNAME_INCORECT = 0;
	public static int WRONG_PASWORD = 1;
	public static int UNKNOWN = 2;
	public static int LOGIN_SUCCESS = 3;
	
	// get current account
	public static int NOTFOUND = 4;
	public static int GET_ACCOUNT_SUCCESS = 5;
	
	// logout
	public static int LOGOUT_SUCCESS = 6;
	public static int LOGOUT_FAIL = 7;
	
	// get current user
	public static int GET_USER_SUCCESS = 8;
	public static int USER_NOT_FOUND = 9;
	
	// create account
	public static int ACCOUNT_EXISTED = 10;
	public static int CREATE_ACCOUNT_SUCCESS = 11;
	
	// upload file, image
	public static int TYPE_NOT_SUPPORT = 12;

	int statusCode;
	
	String statusMessage;
	
	// account when login, logout, current user
	AccountDTO account;
	
	// account when register success
	NewAccountDTO newAccount;
	
	public ResponseStatus() {
		
	}
	
	public ResponseStatus(int loginStatus, AccountDTO account) {
		super();
		this.statusCode = loginStatus;
		this.statusMessage = GeneratedMessage(loginStatus);
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
			
		case 4:
			return "Please login before do this action!";
			
		case 5:
			return "Get account success!";
			
		case 6:
			return "Logout success!";
			
		case 7:
			return "Logout fail, no account in this session!";
			
		case 8:
			return "Get user success!";
			
		case 9:
			return "User not found, try other identity!";
			
		case 10:
			return "Username is existed, try other username!";
			
		case 11:
			return "Register new account success!";

		case 12:
			return "This data type is not supported!";
			
		default:
			return "Unknow";
		}
	}
	
	public int getStatusCode() {
		return statusCode;
	}
	
	public void setStatusCode(int loginStatus) {
		this.statusMessage = GeneratedMessage(loginStatus);
		this.statusCode = loginStatus;
	}
	
	public AccountDTO getAccount() {
		return account;
	}
	
	public void setAccount(AccountDTO account) {
		this.account = account;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String loginMessage) {
		this.statusMessage = loginMessage;
	}

	public NewAccountDTO getNewAccount() {
		return newAccount;
	}

	public void setNewAccount(NewAccountDTO newAccount) {
		this.newAccount = newAccount;
	}
}
