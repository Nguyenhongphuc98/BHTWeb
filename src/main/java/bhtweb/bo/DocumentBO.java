/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bhtweb.bo;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sound.sampled.LineListener;

import bhtweb.dbaccess.DocumentCategoryMapper;
import bhtweb.dbaccess.DocumentMapper;
import bhtweb.dbaccess.SubjectMapper;
import bhtweb.dto.DocumentDTO;
import bhtweb.dto.ShortDocumentDTO;
import bhtweb.entities.BHTDocument;
import bhtweb.entities.BHTDocumentCategory;
import bhtweb.entities.BHTSubject;
import bhtweb.utils.DocumentFilter;

/**
 *
 * @author NguyenHongPhuc
 * Xử lý nghiệp vụ của document trong hệ thống
 */
public class DocumentBO {
	
	public static int DOCS_PER_PAGE = 10;
    
	public DocumentDTO getDocumentById(int id, boolean isApproved) {
		
		DocumentDTO doc = null;
		BHTDocument entity = null;
		BHTDocumentCategory category = null;
		BHTSubject subject = null;
		
        DocumentMapper mapper = null;
        DocumentCategoryMapper categoryMapper = null;
        SubjectMapper subjectMapper = null;
        
        try {
        	
            mapper = new DocumentMapper();
            categoryMapper = new DocumentCategoryMapper();
            subjectMapper = new SubjectMapper();
            
            entity = mapper.getDocsById(id, isApproved);
            category = categoryMapper.getBHTDocumentCategoryById(entity.getCategoryId());
            subject = subjectMapper.getSubjectById(entity.getSubjectId());
            
            // get user ...
            
            doc = new DocumentDTO(entity, "authorName", "authorAvatar", category.getName(), subject.getSubjectName());
            
        } catch (Exception ex) {
            Logger.getLogger(DocumentBO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
            	
                mapper.closeConnection();
                categoryMapper.closeConnection();
                subjectMapper.closeConnection();
                
                
            } catch (Exception ex) {
                Logger.getLogger(DocumentBO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return doc;
    }

	// Page index start from 0
	public List<ShortDocumentDTO> getListDocument(DocumentFilter filter, int pageIndex) {

		int startIndex = pageIndex * DOCS_PER_PAGE;

		List<ShortDocumentDTO> docs = new ArrayList<>();
		List<BHTDocument> entitys = null;
		BHTDocumentCategory category = null;
		BHTSubject subject = null;

		DocumentMapper mapper = null;
		DocumentCategoryMapper categoryMapper = null;
		SubjectMapper subjectMapper = null;

		try {

			mapper = new DocumentMapper();
			categoryMapper = new DocumentCategoryMapper();
			subjectMapper = new SubjectMapper();

			entitys = mapper.getDocsbyFilter(filter, startIndex, DOCS_PER_PAGE);
			for (BHTDocument item : entitys) {

				ShortDocumentDTO doc = null;

				category = categoryMapper.getBHTDocumentCategoryById(item.getCategoryId());
				subject = subjectMapper.getSubjectById(item.getSubjectId());
				// get user ...

				doc = new ShortDocumentDTO(item, "author name", subject.getSubjectName(), category.getName());
				docs.add(doc);
			}

		} catch (Exception ex) {
			Logger.getLogger(DocumentBO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			try {

				mapper.closeConnection();
				categoryMapper.closeConnection();
				subjectMapper.closeConnection();

			} catch (Exception ex) {
				Logger.getLogger(DocumentBO.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		return docs;
	}
}
