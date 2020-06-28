package bhtweb.dbaccess;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import bhtweb.entities.BHTSemester;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author NguyenHongPhuc
 */
public class SemesterMapper extends DBMapper {
    
    public SemesterMapper() throws Exception {
		super();
	}

	// get all semester exists in database
    public ArrayList<BHTSemester> getSemesters() {
        ArrayList<BHTSemester> semesters = new ArrayList<>();    
        try {     
            Statement stmt = getConnection().createStatement();
            String sqlStr = "SELECT * FROM semester";
            ResultSet rs = stmt.executeQuery(sqlStr); // Send the query to the server
            int count = 0;
            while (rs != null && rs.next()) {
                BHTSemester s = new BHTSemester();
                s.setSemesterId(rs.getInt("SemesterID"));
                s.setAcademicYear(rs.getString("AcademicYear"));
                s.setSemesterNo(rs.getString("SemesterNo"));
               
                semesters.add(s);
            }          
        } catch (Exception ex) {
            ex.printStackTrace();
        } 
        
        return semesters;
    }
    
    public BHTSemester getSubjectById(int semesterId) {
        BHTSemester s = null;
        try {     
            Statement stmt = getConnection().createStatement();
            String sqlStr = "SELECT * FROM semester where SemesterID = " + semesterId;
            ResultSet rs = stmt.executeQuery(sqlStr); // Send the query to the server
   
            if (rs != null && rs.next()){
                s = new BHTSemester();
                s.setSemesterId(rs.getInt("SemesterID"));
                s.setAcademicYear(rs.getString("AcademicYear"));
                s.setSemesterNo(rs.getString("SemesterNo"));
            }          
        } catch (Exception ex) {
            ex.printStackTrace();
        } 
        
        return s;
    }
}