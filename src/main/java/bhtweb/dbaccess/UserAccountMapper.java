package bhtweb.dbaccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import bhtweb.entities.BHTUserAccount;

public class UserAccountMapper extends DBMapper {

	public UserAccountMapper() throws Exception {
		super();
	}

	private List<BHTUserAccount> fetchListAccount(String sqlStr) {
		
		List<BHTUserAccount> accounts = new ArrayList<>();

		try {
			Statement stmt = getConnection().createStatement();
			ResultSet rs;

			rs = stmt.executeQuery(sqlStr); 
			while (rs != null && rs.next()) {
				BHTUserAccount d = new BHTUserAccount();
				d.setUserID(rs.getInt("UserID"));
				d.setUserName(rs.getString("UserName"));
				d.setProfilePictureURL(rs.getString("ProfilePictureURL"));
				d.setEmail(rs.getString("Email"));
				d.setUserPassword(rs.getString("UserPassword"));
				d.setFacebookToken(rs.getString("FacebookToken"));
				d.setGoogleToken(rs.getString("GoogleToken"));
				d.setPostScore(rs.getInt("PostScore"));
				d.setUserGroupID(rs.getInt("UserGroupID"));
				
				accounts.add(d);
			}
		} catch (SQLException ex) {
			Logger.getLogger(DocumentMapper.class.getName()).log(Level.SEVERE, null, ex);
		}

        return accounts;
    }
    
    public BHTUserAccount getAccountById(int accountID) {

        String sqlStr = "SELECT * FROM useraccount WHERE UserID = " + accountID;
        List<BHTUserAccount> ls = fetchListAccount(sqlStr);
        return ls.size() > 0 ? ls.get(0) : null;

    }
    
    public BHTUserAccount getAccountByUsername(String username) {

        String sqlStr = "SELECT * FROM useraccount WHERE UserName = " + username;
        List<BHTUserAccount> ls = fetchListAccount(sqlStr);
        return ls.size() > 0 ? ls.get(0) : null;

    }
    
    public List<BHTUserAccount> getAccountsByRole(int roleId) {
    
        String sqlStr = "SELECT * FROM useraccount WHERE UserGroupID = " + roleId;
        return fetchListAccount(sqlStr);

    }
    
    public List<BHTUserAccount> getAllAccount() {
        
        String sqlStr = "SELECT * FROM useraccount";
        return fetchListAccount(sqlStr);

    }
    
    public boolean saveAccount(BHTUserAccount account) {
        
    	String query = "INSERT INTO `bhtcnpm_db`.`useraccount` (`UserName`, `ProfilePictureURL`, `Email`, `UserPassword`, `FacebookToken`, `GoogleToken`, `PostScore`, `UserGroupID`) "
    			+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try {
        	// create the mysql insert preparedstatement
            PreparedStatement preparedStmt = getConnection().prepareStatement(query);
            preparedStmt.setString(1, account.getUserName());
            preparedStmt.setString(2, account.getProfilePictureURL());
            preparedStmt.setString(3, account.getEmail());
            preparedStmt.setString(4, account.getUserPassword());
            preparedStmt.setString(5, account.getFacebookToken());
            preparedStmt.setString(6, account.getGoogleToken());
            preparedStmt.setInt(7, account.getPostScore());
            preparedStmt.setInt(8, account.getUserGroupID());

            preparedStmt.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(DocumentMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return true;
    }
    
    public boolean updateAccount(BHTUserAccount account) {
        
        String sqlStr = "UPDATE useraccount SET ";
        
        if (account.getEmail() != null) {
            sqlStr += "Email = '" + account.getEmail() + "',";
        }
        if (account.getProfilePictureURL()!= null) {
            sqlStr += "ProfilePictureURL = '" + account.getProfilePictureURL() + "',";
        }
        if (account.getPostScore()!= null) {
            sqlStr += "PostScore = '" + account.getPostScore()+ "',";
        }
        if (account.getUserGroupID() != null) {
            sqlStr += "UserGroupID = " + account.getUserGroupID() + ",";
        }
       
        //  Xoa dau --',-- cuoi cung neu co
        if (sqlStr.substring(sqlStr.length() - 2, sqlStr.length() - 1).equals("',")) {
            sqlStr = sqlStr.substring(0, sqlStr.length() - 3);
        } else if (sqlStr.charAt(sqlStr.length() - 1) == ',') {
            sqlStr = sqlStr.substring(0, sqlStr.length() - 1);
        }
        
        sqlStr += " WHERE UserID = " + account.getUserID();
       
        Statement stmt;
		try {
			stmt = getConnection().createStatement();
			int r = stmt.executeUpdate(sqlStr);
			return r > 0 ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
       
    }
}
