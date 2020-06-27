package bhtweb.dbaccess;


import java.io.FileFilter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.management.Query;

import bhtweb.entities.BHTDocument;
import bhtweb.utils.DocumentFilter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author NguyenHongPhuc
 */
public class DocumentMapper extends DBMapper {
    
    public DocumentMapper() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}

	private List<BHTDocument> fetchListDocs(String sqlStr) {
		List<BHTDocument> docs = new ArrayList<>();

		try {
			Statement stmt = getConnection().createStatement();
			ResultSet rs;

			rs = stmt.executeQuery(sqlStr); // Send the query to the server
			while (rs != null && rs.next()) {
				BHTDocument d = new BHTDocument();
				d.setId(rs.getInt("DocumentID"));
				d.setTitle(rs.getString("DocumentTitle"));
				d.setDescription(rs.getString("DocumentDescription"));
				d.setUploaderId(rs.getInt("DocumentUploaderUserID"));
				d.setContentUrl(rs.getString("DocumentContentURL"));
				d.setIsSoftDeleted(rs.getBoolean("DocumentSoftDeleted"));
				d.setIsHidden(rs.getBoolean("DocumentHidden"));
				d.setIsApproved(rs.getBoolean("DocumentApproved"));
				d.setViewCount(rs.getInt("DocumentViewCount"));
				d.setDownloadCount(rs.getInt("DocumentDownloadCount"));
				d.setSemesterId(rs.getInt("SemesterID"));
				d.setSubjectId(rs.getInt("SubjectID"));
				d.setCategoryId(rs.getInt("DocumentCategoryID"));
				d.setDocumentPublishDtm(rs.getDate("DocumentPublishDtm"));
				
				docs.add(d);
			}
		} catch (SQLException ex) {
			Logger.getLogger(DocumentMapper.class.getName()).log(Level.SEVERE, null, ex);
		}

        return docs;
    }
    
    public BHTDocument getDocsById(int docId, boolean isApproved) {

        String sqlStr = "SELECT * FROM document WHERE DocumentID = " + docId + " and DocumentApproved = " + isApproved;
        List<BHTDocument> ls = fetchListDocs(sqlStr);
        return ls.size() > 0 ? ls.get(0) : null;

    }
    
    public List<BHTDocument> getDocsBySubjectId(int subjectId) {
    
        String sqlStr = "SELECT * FROM document WHERE SubjectID = " + subjectId + " and DocumentApproved = 1";
        return fetchListDocs(sqlStr);

    }
    
    public List<BHTDocument> getDocsNotApprovedYet() {
         
        String sqlStr = "SELECT * FROM document WHERE DocumentApproved = 0";
        return fetchListDocs(sqlStr);
        
    }
    
    public List<BHTDocument> getDocsByCategoryId(int categoryId) {
         
        String sqlStr = "SELECT * FROM document WHERE DocumentCategoryID = " + categoryId + " and DocumentApproved = 1";
        return fetchListDocs(sqlStr);
        
    }
    
    public List<BHTDocument> getLatestDocs(int countLimit) {
         
        String sqlStr = "SELECT * FROM document WHERE DocumentApproved = 1 ORDER BY ... DESC LIMIT " + countLimit;
        return fetchListDocs(sqlStr);
        
    }
    
    public List<BHTDocument> getMostDownloadDocs(int countLimit) {
        
        String sqlStr = "SELECT * FROM document WHERE DocumentApproved = 1 ORDER BY DocumentDownloadCount DESC LIMIT " + countLimit;
        return fetchListDocs(sqlStr);
        
    }

	public List<BHTDocument> getDocsByAuthor(int uploaderId, boolean approved, int start, int count) {

		String sqlStr = "SELECT * FROM document WHERE DocumentApproved = " + (approved ? 1 : 0)
						+ " DocumentUploaderUserID = "
						+ uploaderId + " ORDER BY DocumentDownloadCount DESC LIMIT "
						+ start + ", " + count;
				
		return fetchListDocs(sqlStr);

	}
    
    /// get list doc by filter, limit base on publish date and pageIndex
    public List<BHTDocument> getDocsbyFilter(DocumentFilter filter, int start, int count) {
        
        String sqlStr = "SELECT * FROM document WHERE DocumentApproved = 1 "
        		+ " AND DocumentCategoryID " + filter.getCategoreId()
        		+ " AND SemesterID " + filter.getSemesterId()
        		//+ " AND " + filter.getYearNo()
        		+ " AND SubjectID " + filter.getSubjectId()
        		+ " ORDER BY DocumentPublishDtm DESC LIMIT " + start + " , " + count;
        
        System.out.println("excuting: " + sqlStr);
        return fetchListDocs(sqlStr);
        
    }
    
    public boolean saveDoc(BHTDocument doc) {
        
        // the mysql insert statement
        String query = " INSERT INTO document (DocumentTitle, DocumentDescription,"
                + "DocumentUploaderUserID, DocumentContentURL, DocumentSoftDeleted, DocumentHidden,"
                + "DocumentApproved, DocumentViewCount, DocumentDownloadCount, SemesterID, SubjectID, DocumentCategoryID, DocumentPublishDtm)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        
        try {
        	// create the mysql insert preparedstatement
            PreparedStatement preparedStmt = getConnection().prepareStatement(query);
            preparedStmt.setString(1, doc.getTitle());
            preparedStmt.setString(2, doc.getDescription());
            preparedStmt.setInt(3, doc.getUploaderId());
            preparedStmt.setString(4, doc.getContentUrl());
            preparedStmt.setBoolean(5, doc.isIsSoftDeleted());
            preparedStmt.setBoolean(6, doc.isIsHidden());
            preparedStmt.setBoolean(7, doc.isIsApproved());
            preparedStmt.setInt(8, doc.getViewCount());
            preparedStmt.setInt(9, doc.getDownloadCount());
            preparedStmt.setInt(10, doc.getSemesterId());
            preparedStmt.setInt(11, doc.getSubjectId());
            preparedStmt.setInt(12, doc.getCategoryId());
            preparedStmt.setDate(12, doc.getDocumentPublishDtm());
            
            // execute the preparedstatement
            return preparedStmt.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(DocumentMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public boolean updateDoc(BHTDocument doc, boolean delete, boolean hidden, boolean approved) {
        
        String sqlStr = "UPDATE document SET ";
        
        if (doc.getTitle() != null) {
            sqlStr += "DocumentTitle = '" + doc.getTitle() + "',";
        }
        if (doc.getDescription()!= null) {
            sqlStr += "DocumentDescription = '" + doc.getDescription() + "',";
        }
        if (doc.getContentUrl()!= null) {
            sqlStr += "DocumentContentURL = '" + doc.getContentUrl()+ "',";
        }
        if (delete) {
            sqlStr += "DocumentSoftDeleted = " + doc.isIsSoftDeleted() + ",";
        }
        if (hidden) {
            sqlStr += "DocumentHidden = " + doc.isIsHidden()+ ",";
        }
        if (approved) {
            sqlStr += "DocumentApproved = " + doc.isIsApproved()+ ",";
        }
        if (doc.getViewCount() != 0) {
            sqlStr += "DocumentViewCount = " + doc.getViewCount()+ ",";
        }
        if (doc.getDownloadCount()!= 0) {
            sqlStr += "DocumentDownloadCount = " + doc.getDownloadCount();
        }
       
        /// Khong cho update uploader semeter, subject, category
        
        //  Xoa dau --',-- cuoi cung neu co
        if (sqlStr.substring(sqlStr.length() - 2, sqlStr.length() - 1).equals("',")) {
            sqlStr = sqlStr.substring(0, sqlStr.length() - 3);
        } else if (sqlStr.charAt(sqlStr.length() - 1) == '\'') {
            sqlStr = sqlStr.substring(0, sqlStr.length() - 2);
        }
        
        sqlStr += " WHERE DocumentID = " + doc.getId();
        
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
