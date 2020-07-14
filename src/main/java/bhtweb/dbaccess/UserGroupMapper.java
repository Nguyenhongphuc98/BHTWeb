package bhtweb.dbaccess;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import bhtweb.entities.BHTSubject;
import bhtweb.entities.BHTUserGroup;

public class UserGroupMapper extends DBMapper {

	public UserGroupMapper() throws Exception {
		super();
	}

	// get all user group exists in database
    public ArrayList<BHTUserGroup> getUserGroups() {
        ArrayList<BHTUserGroup> userGroups = new ArrayList<>();    
        try {     
            Statement stmt = getConnection().createStatement();
            String sqlStr = "SELECT * FROM usergroup";
            ResultSet rs = stmt.executeQuery(sqlStr); // Send the query to the server
            
            while (rs != null && rs.next()) {
            	BHTUserGroup s = new BHTUserGroup();
                s.setUserGroupID(rs.getInt("UserGroupID"));
                s.setUserGroupName(rs.getString("UserGroupName"));
               
                userGroups.add(s);
            }          
        } catch (Exception ex) {
            ex.printStackTrace();
        } 
        
        return userGroups;
    }
    
    public BHTUserGroup getUserGroupById(int id) {
        BHTUserGroup userGroup = null;
        try {     
            Statement stmt = getConnection().createStatement();
            String sqlStr = "SELECT * FROM usergroup where UserGroupID = " + id;
            ResultSet rs = stmt.executeQuery(sqlStr); // Send the query to the server
            
            if (rs != null && rs.next()) {
            	userGroup = new BHTUserGroup();
            	userGroup.setUserGroupID(rs.getInt("UserGroupID"));
            	userGroup.setUserGroupName(rs.getString("UserGroupName"));
               
            }          
        } catch (Exception ex) {
            ex.printStackTrace();
        } 
        
        return userGroup;
    }
}
