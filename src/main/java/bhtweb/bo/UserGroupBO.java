package bhtweb.bo;

import java.util.List;

import bhtweb.dbaccess.UserGroupMapper;
import bhtweb.entities.BHTUserGroup;

public class UserGroupBO {

	public List<BHTUserGroup> getUserGroups() {
		
		UserGroupMapper mapper = null;
		List<BHTUserGroup> userGroups = null;
		
		try {
			mapper = new UserGroupMapper();
			userGroups = mapper.getUserGroups();
		} catch (Exception e) {
			
		} finally {
			mapper.closeConnection();
		}
		
		return userGroups;
	}
}
