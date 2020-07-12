package bhtweb.bo;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import bhtweb.dbaccess.DocumentCategoryMapper;
import bhtweb.dbaccess.DocumentMapper;
import bhtweb.dbaccess.SubjectMapper;
import bhtweb.dbaccess.UserAccountMapper;
import bhtweb.dbaccess.UserGroupMapper;
import bhtweb.dto.AccountDTO;
import bhtweb.dto.DocumentDTO;
import bhtweb.dto.DocumentUploadDTO;
import bhtweb.dto.NewAccountDTO;
import bhtweb.dto.ShortDocumentDTO;
import bhtweb.entities.BHTDocument;
import bhtweb.entities.BHTDocumentCategory;
import bhtweb.entities.BHTSubject;
import bhtweb.entities.BHTUserAccount;
import bhtweb.entities.BHTUserGroup;
import bhtweb.utils.DocumentFilter;

public class UserAccountBO {

	private List<AccountDTO> createAccountDTOFor(List<BHTUserAccount> entities) {

		List<AccountDTO> accounts = new ArrayList<>();

		BHTUserGroup userGroup = null;
		int documentCount = 0;

		UserGroupMapper userGroupMapper = null;
		DocumentMapper documentMapper = null;

		try {

			userGroupMapper = new UserGroupMapper();
			documentMapper = new DocumentMapper();

			for (BHTUserAccount item : entities) {

				AccountDTO account = null;

				userGroup = userGroupMapper.getUserGroupById(item.getUserGroupID());
				documentCount = documentMapper.getDocumentCountOfUserId(item.getUserID());

				account = new AccountDTO(item.getUserID(), item.getUserName(), item.getUserPassword(),
						item.getProfilePictureURL(), item.getEmail(), item.getPostScore(), 0, documentCount,
						userGroup.getUserGroupID(), userGroup.getUserGroupName());

				accounts.add(account);
			}

		} catch (Exception ex) {
			Logger.getLogger(DocumentBO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			try {

				userGroupMapper.closeConnection();
				documentMapper.closeConnection();

			} catch (Exception ex) {
				Logger.getLogger(DocumentBO.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		return accounts;
	}

	public List<AccountDTO> viewAllUser() {

		UserAccountMapper mapper = null;
		List<AccountDTO> result = null;
		try {

			mapper = new UserAccountMapper();
			List<BHTUserAccount> entities = mapper.getAllAccount();
			result = createAccountDTOFor(entities);

		} catch (Exception ex) {
			Logger.getLogger(DocumentBO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {

			try {
				mapper.closeConnection();
			} catch (Exception ex) {
				Logger.getLogger(DocumentBO.class.getName()).log(Level.SEVERE, null, ex);
			}
		}

		return result;
	}

	public boolean UpdateAccount(NewAccountDTO account, String oldPassWord) {

		UserAccountMapper mapper = null;

		BHTUserAccount bhtUserAccount = new BHTUserAccount();

		bhtUserAccount.setUserName(account.getUserName());
		bhtUserAccount.setEmail(account.getEmail());
		bhtUserAccount.setProfilePictureURL(account.getProfilePictureURL());
		bhtUserAccount.setUserPassword(account.getUserPassword());
		bhtUserAccount.setUserGroupID(account.getUserGroupID());

		boolean result = false;
		try {

			mapper = new UserAccountMapper();
			BHTUserAccount oldAccount = mapper.getAccountByUsername(account.getUserName());
			if (oldAccount == null || !oldAccount.getUserPassword().equals(oldPassWord)) {
				return false;
			}
			result = mapper.updateAccount(bhtUserAccount);

		} catch (Exception ex) {
			Logger.getLogger(DocumentBO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {

			try {
				mapper.closeConnection();
			} catch (Exception ex) {
				Logger.getLogger(DocumentBO.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		return result;
	}

	public boolean CreateAccount(NewAccountDTO account) {

		UserAccountMapper mapper = null;

		BHTUserAccount bhtUserAccount = new BHTUserAccount();

		bhtUserAccount.setUserName(account.getUserName());
		bhtUserAccount.setEmail(account.getEmail());
		bhtUserAccount.setProfilePictureURL(account.getProfilePictureURL());
		bhtUserAccount.setUserPassword(account.getUserPassword());
		bhtUserAccount.setUserGroupID(account.getUserGroupID());

		boolean result = false;
		try {

			mapper = new UserAccountMapper();

			result = mapper.saveAccount(bhtUserAccount);

		} catch (Exception ex) {
			Logger.getLogger(DocumentBO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {

			try {
				mapper.closeConnection();
			} catch (Exception ex) {
				Logger.getLogger(DocumentBO.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		return result;
	}

	public AccountDTO getAccountByUsername(String username) {

		UserAccountMapper mapper = null;

		AccountDTO accountDTO = null;

		boolean result = false;
		try {

			mapper = new UserAccountMapper();
			BHTUserAccount bhtUserAccount = mapper.getAccountByUsername(username);
			if (bhtUserAccount != null) {
				List<BHTUserAccount> bhtUserAccounts = new ArrayList<BHTUserAccount>();
				bhtUserAccounts.add(bhtUserAccount);
				accountDTO = createAccountDTOFor(bhtUserAccounts).get(0);
			}

		} catch (Exception ex) {
			Logger.getLogger(DocumentBO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {

			try {
				mapper.closeConnection();
			} catch (Exception ex) {
				Logger.getLogger(DocumentBO.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		return accountDTO;
	}

	public AccountDTO getAccountById(Integer uid) {
		UserAccountMapper mapper = null;

		AccountDTO accountDTO = null;

		boolean result = false;
		try {

			mapper = new UserAccountMapper();
			BHTUserAccount bhtUserAccount = mapper.getAccountById(uid);
			if (bhtUserAccount != null) {
				List<BHTUserAccount> bhtUserAccounts = new ArrayList<BHTUserAccount>();
				bhtUserAccounts.add(bhtUserAccount);
				accountDTO = createAccountDTOFor(bhtUserAccounts).get(0);
			}

		} catch (Exception ex) {
			Logger.getLogger(DocumentBO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {

			try {
				mapper.closeConnection();
			} catch (Exception ex) {
				Logger.getLogger(DocumentBO.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		return accountDTO;
	}
}
