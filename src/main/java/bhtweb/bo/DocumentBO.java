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
import bhtweb.dto.DocumentUploadDTO;
import bhtweb.dto.ShortDocumentDTO;
import bhtweb.entities.BHTDocument;
import bhtweb.entities.BHTDocumentCategory;
import bhtweb.entities.BHTSubject;
import bhtweb.utils.DocumentFilter;

/**
 *
 * @author NguyenHongPhuc Xử lý nghiệp vụ của document trong hệ thống
 */
public class DocumentBO {

	public static int DOCS_PER_PAGE = 10;
	
	public static int GOOD_DOCS_LIMIT = 3;

	private DocumentDTO getDocumentById(int id, boolean isApproved) {

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
		} finally {
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

	private List<ShortDocumentDTO> createShortDocumentFor(List<BHTDocument> entitys) {
		List<ShortDocumentDTO> docs = new ArrayList<>();
		BHTDocumentCategory category = null;
		BHTSubject subject = null;

		DocumentMapper mapper = null;
		DocumentCategoryMapper categoryMapper = null;
		SubjectMapper subjectMapper = null;

		try {

			mapper = new DocumentMapper();
			categoryMapper = new DocumentCategoryMapper();
			subjectMapper = new SubjectMapper();

			for (BHTDocument item : entitys) {

				ShortDocumentDTO doc = null;

				category = categoryMapper.getBHTDocumentCategoryById(item.getCategoryId());
				subject = subjectMapper.getSubjectById(item.getSubjectId());
				// get user ...

				doc = new ShortDocumentDTO(
						item, "author name", 
						subject.getSubjectName(),
						category.getName(), item.getDocumentPublishDtm());
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

	
	public DocumentDTO viewDocumentDetail(int docId) {
		return getDocumentById(docId, true);
	}
	
	public DocumentDTO previewDoc(int docId, int requesterID) {
		
		// GET user by id , check group permission - admin, collaborator, author?
		// if ok then fetch else return null
		return getDocumentById(docId, false);
	}
	
	// Page index start from 0
	public List<ShortDocumentDTO> searchDocument(DocumentFilter filter, int pageIndex) {

		int startIndex = pageIndex * DOCS_PER_PAGE;

		DocumentMapper mapper = null;
		List<ShortDocumentDTO> result = null;
		try {

			mapper = new DocumentMapper();
			List<BHTDocument> entities = mapper.getDocsbyFilter(filter, startIndex, DOCS_PER_PAGE);
			result = createShortDocumentFor(entities);

		} catch (Exception ex) {
			Logger.getLogger(DocumentBO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {

			try {
				mapper.closeConnection();
			} catch (Exception ex) {
				Logger.getLogger(DocumentBO.class.getName()).log(Level.SEVERE, null, ex);
			}
		}

		return result;
	}

	// Page index start from 0
	public List<ShortDocumentDTO> getPersonalDocs(int uploader, int pageIndex, boolean approved) {
		
		int startIndex = pageIndex * DOCS_PER_PAGE;

		DocumentMapper mapper = null;
		List<ShortDocumentDTO> result = null;
		try {

			mapper = new DocumentMapper();
			List<BHTDocument> entities = mapper.getDocsByAuthor(uploader, approved, startIndex, DOCS_PER_PAGE);
			result = createShortDocumentFor(entities);

		} catch (Exception ex) {
			Logger.getLogger(DocumentBO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {

			try {
				mapper.closeConnection();
			} catch (Exception ex) {
				Logger.getLogger(DocumentBO.class.getName()).log(Level.SEVERE, null, ex);
			}
		}

		return result;
	}
	
	public List<ShortDocumentDTO> getMostDownloadDocumentList(int limit) {
		
		DocumentMapper mapper = null;
		List<ShortDocumentDTO> result = null;
		if (limit == 0) {
			limit = GOOD_DOCS_LIMIT;
		}
		
		try {

			mapper = new DocumentMapper();
			List<BHTDocument> entities = mapper.getMostDownloadDocs(limit);
			result = createShortDocumentFor(entities);

		} catch (Exception ex) {
			Logger.getLogger(DocumentBO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {

			try {
				mapper.closeConnection();
			} catch (Exception ex) {
				Logger.getLogger(DocumentBO.class.getName()).log(Level.SEVERE, null, ex);
			}
		}

		return result;
	}
	
	public List<ShortDocumentDTO> getListDocumentToBrowse() {

		DocumentMapper mapper = null;
		List<ShortDocumentDTO> result = null;
		try {

			mapper = new DocumentMapper();
			List<BHTDocument> entities = mapper.getDocsNotApprovedYet();
			result = createShortDocumentFor(entities);

		} catch (Exception ex) {
			Logger.getLogger(DocumentBO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {

			try {
				mapper.closeConnection();
			} catch (Exception ex) {
				Logger.getLogger(DocumentBO.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		return result;
	}

	public boolean publishDocument(int docId) {
		
		DocumentMapper mapper = null;
		boolean result = false;
		try {

			mapper = new DocumentMapper();
			
			BHTDocument doc = new BHTDocument();
			doc.setIsApproved(true);
			result = mapper.updateDoc(doc, false, false, true);

		} catch (Exception ex) {
			Logger.getLogger(DocumentBO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			
			try {
				mapper.closeConnection();
			} catch (Exception ex) {
				Logger.getLogger(DocumentBO.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		return result;
	}

	public boolean CreateDoc(DocumentUploadDTO document) {
		DocumentMapper mapper = null;

		BHTDocument bhtDocument = new BHTDocument();
		bhtDocument.setCategoryId(document.getCategoryId());
		bhtDocument.setContentUrl(document.getContentUrl());
		bhtDocument.setDescription(document.getDescription());
		bhtDocument.setSemesterId(document.getSemesterId());
		bhtDocument.setSubjectId(document.getSubjectId());
		bhtDocument.setTitle(document.getTitle());
		bhtDocument.setUploaderId(document.getUploaderId());
		
		boolean result = false;
		try {

			mapper = new DocumentMapper();
			result = mapper.saveDoc(bhtDocument);

		} catch (Exception ex) {
			Logger.getLogger(DocumentBO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {

			try {
				mapper.closeConnection();
			} catch (Exception ex) {
				Logger.getLogger(DocumentBO.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		return result;
	}
}
