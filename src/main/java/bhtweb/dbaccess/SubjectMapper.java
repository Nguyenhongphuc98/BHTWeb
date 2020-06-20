package bhtweb.dbaccess;


import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import bhtweb.entities.BHTSubject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author NguyenHongPhuc
 */
public class SubjectMapper extends DBMapper {
    
    
    public SubjectMapper() throws Exception {
		super();
	}

	// get all subject exists in database
    public ArrayList<BHTSubject> getSubjects() {
        ArrayList<BHTSubject> subjects = new ArrayList<>();    
        try {     
            Statement stmt = getConnection().createStatement();
            String sqlStr = "SELECT * FROM subject";
            ResultSet rs = stmt.executeQuery(sqlStr); // Send the query to the server
            int count = 0;
            while (rs != null && rs.next()) {
                BHTSubject s = new BHTSubject();
                s.setSubjectId(rs.getInt("SubjectID"));
                s.setSubjectName(rs.getString("SubjectName"));
               
                subjects.add(s);
            }          
        } catch (Exception ex) {
            ex.printStackTrace();
        } 
        
        return subjects;
    }
    
    public BHTSubject getSubjectById(int subjectId) {
        BHTSubject s = null;
        try {     
            Statement stmt = getConnection().createStatement();
            String sqlStr = "SELECT * FROM subject where SubjectID = " + subjectId;
            ResultSet rs = stmt.executeQuery(sqlStr); // Send the query to the server
   
            if (rs != null && rs.next()){
                s = new BHTSubject();
                s.setSubjectId(rs.getInt("SubjectID"));
                s.setSubjectName(rs.getString("SubjectName"));
            }          
        } catch (Exception ex) {
            ex.printStackTrace();
        } 
        
        return s;
    }
}
