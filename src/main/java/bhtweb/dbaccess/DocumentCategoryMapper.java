package bhtweb.dbaccess;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import bhtweb.entities.BHTDocumentCategory;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author NguyenHongPhuc
 */
public class DocumentCategoryMapper extends DBMapper {
    
    public DocumentCategoryMapper() throws Exception {
		super();
	}

	// get all docs category  exists in database
    public ArrayList<BHTDocumentCategory> getDocumentCategories() {
        ArrayList<BHTDocumentCategory> categories = new ArrayList<>();    
        try {     
            Statement stmt = getConnection().createStatement();
            String sqlStr = "SELECT * FROM documentcategory";
            ResultSet rs = stmt.executeQuery(sqlStr); // Send the query to the server
            int count = 0;
            while (rs != null && rs.next()) {
                BHTDocumentCategory s = new BHTDocumentCategory();
                s.setId(rs.getInt("DocumentCategoryID"));
                s.setName(rs.getString("DocumentCategoryName"));
                categories.add(s);
            }          
        } catch (Exception ex) {
            ex.printStackTrace();
        } 
        
        return categories;
    }
    
    public BHTDocumentCategory getBHTDocumentCategoryById(int docCategoryID) {
        BHTDocumentCategory s = null;
        try {     
            Statement stmt = getConnection().createStatement();
            String sqlStr = "SELECT * FROM documentcategory where DocumentCategoryID = " + docCategoryID;
            ResultSet rs = stmt.executeQuery(sqlStr); // Send the query to the server
   
            if (rs != null && rs.next()){
                s = new BHTDocumentCategory();
                s.setId(rs.getInt("DocumentCategoryID"));
                s.setName(rs.getString("DocumentCategoryName"));
            }          
        } catch (Exception ex) {
            ex.printStackTrace();
        } 
        
        return s;
    }
}
