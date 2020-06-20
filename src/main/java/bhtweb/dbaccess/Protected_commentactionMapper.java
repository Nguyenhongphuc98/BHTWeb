package bhtweb.dbaccess;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import bhtweb.entities.BHTProtected_commentaction;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author NguyenHongPhuc
 */
public class Protected_commentactionMapper extends DBMapper {
    
    public Protected_commentactionMapper() throws Exception {
		super();
	}

	private List<BHTProtected_commentaction> fetchListActions(String sqlStr) {
        List<BHTProtected_commentaction> actions = new ArrayList<>();

		try {
			Statement stmt = getConnection().createStatement();
			ResultSet rs;
			rs = stmt.executeQuery(sqlStr); // Send the query to the server
			while (rs != null && rs.next()) {
				BHTProtected_commentaction d = new BHTProtected_commentaction();
				d.setCommentActionID(rs.getInt("CommentActionID"));
				d.setCommentActionName(rs.getString("CommentActionName"));

				actions.add(d);
			}
		} catch (SQLException ex) {
			Logger.getLogger(DocumentMapper.class.getName()).log(Level.SEVERE, null, ex);
		}

        return actions;
    }
    
    public List<BHTProtected_commentaction> getAllActions() {

        String sqlStr = "SELECT * FROM protected_commentaction";
        List<BHTProtected_commentaction> ls = fetchListActions(sqlStr);
        return ls;

    }
}
