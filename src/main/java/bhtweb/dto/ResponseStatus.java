package bhtweb.dto;

import java.util.List;

import com.google.protobuf.GeneratedMessage;

public class ResponseStatus {

	// Login
	public static int USERNAME_INCORECT = 0;
	public static int WRONG_PASWORD = 1;
	public static int UNKNOWN = 2;
	public static int LOGIN_SUCCESS = 3;
	
	// Get current account
	public static int NOTFOUND = 4;
	public static int GET_ACCOUNT_SUCCESS = 5;
	
	// Logout
	public static int LOGOUT_SUCCESS = 6;
	public static int LOGOUT_FAIL = 7;
	
	// Get current user
	public static int GET_USER_SUCCESS = 8;
	public static int USER_NOT_FOUND = 9;
	
	// Create account
	public static int ACCOUNT_EXISTED = 10;
	public static int CREATE_ACCOUNT_SUCCESS = 11;
	
	// Upload file, image
	public static int TYPE_NOT_SUPPORT = 12;
	
	// Make some thing need admin permission
	public static int PERMISSION_DENNED = 13;
	
	// Get resource: docs,...
	public static int RESOURCE_NOT_FOUND = 14;
	
	public static int GET_RESOURCE_SUCCESS = 15;
	
	// Duyet tai lieu
	public static int BROWSE_DOC_SUCCESS = 16;
	
	public static int BROWSE_DOC_FAIL = 17;

	int statusCode;
	
	String statusMessage;
	
	// Account when login, logout, current user
	AccountDTO account;
	
	// List accounts when get all user for admin
	List<AccountDTO> accounts;
	
	// Account when register success
	NewAccountDTO newAccount;
	
	// Document when get detail
	DocumentDTO documentDTO;
	
	// List short doc when get to browse for admin
	List<ShortDocumentDTO> shortDocs;
	
	public ResponseStatus() {
		
	}
	
	public ResponseStatus(int loginStatus, AccountDTO account, List<AccountDTO> accounts) {
		super();
		this.statusCode = loginStatus;
		this.statusMessage = GeneratedMessage(loginStatus);
		this.account = account;
		this.accounts = accounts;
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
			
		case 13:
			return "Permission denied, contact admin to do this action!";
			
		case 14:
			return "Resource not found!";
			
		case 15:
			return "Get resource success!";
			
		case 16:
			return "Browse document success!";
			
		case 17:
			return "Browse document fail!";
			
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

	public List<AccountDTO> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<AccountDTO> accounts) {
		this.accounts = accounts;
	}

	public DocumentDTO getDocumentDTO() {
		return documentDTO;
	}

	public void setDocumentDTO(DocumentDTO documentDTO) {
		this.documentDTO = documentDTO;
	}

	public List<ShortDocumentDTO> getShortDocs() {
		return shortDocs;
	}

	public void setShortDocs(List<ShortDocumentDTO> shortDocs) {
		this.shortDocs = shortDocs;
	}
}
