package bhtweb.dbaccess;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import bhtweb.entities.BHTUserPostScoreRating;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author NguyenHongPhuc
 */
public class UserPostScoreRatingMapper extends DBMapper {
    
     public UserPostScoreRatingMapper() throws Exception {
		super();
	}

	private List<BHTUserPostScoreRating> fetchScoreRatings(String sqlStr) {
        List<BHTUserPostScoreRating> ratings = new ArrayList<>();
        
        try {
			Statement stmt = getConnection().createStatement();
			ResultSet rs;
			rs = stmt.executeQuery(sqlStr); // Send the query to the server
			while (rs != null && rs.next()) {
				BHTUserPostScoreRating d = new BHTUserPostScoreRating();
				d.setUserID(rs.getInt("UserID"));
				d.setUserName(rs.getString("UserName"));
				d.setUserPostScoreRatingID(rs.getInt("UserPostScoreRatingID"));
				d.setUserScore(rs.getInt("UserScore"));

				ratings.add(d);
			}
		} catch (SQLException ex) {
            Logger.getLogger(DocumentMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
   
        return ratings;
    }
    
    public BHTUserPostScoreRating getScoreRatingUserId(int uid) {

        String sqlStr = "SELECT * FROM userpostscorerating WHERE UserID = " + uid;
        List<BHTUserPostScoreRating> ls = fetchScoreRatings(sqlStr);
        return ls.size() > 0 ? ls.get(0) : null;

    }
      
     public List<BHTUserPostScoreRating> getScoreRatings(int topN) {

        String sqlStr = "SELECT * FROM userpostscorerating ORDER BY UserScore DESC limit " + topN;
        List<BHTUserPostScoreRating> ls = fetchScoreRatings(sqlStr);
        return ls;

    }
}
