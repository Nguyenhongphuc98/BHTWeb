package bhtweb.bo;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import bhtweb.dbaccess.DocumentCategoryMapper;
import bhtweb.entities.BHTDocumentCategory;

public class DocumentCategoryBO {

	public List<BHTDocumentCategory> viewAllDocCategory() {

		DocumentCategoryMapper mapper = null;
		List<BHTDocumentCategory> result = null;
		try {

			mapper = new DocumentCategoryMapper();
			result = mapper.getDocumentCategories();

		} catch (Exception ex) {
			Logger.getLogger(DocumentCategoryBO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {

			try {
				mapper.closeConnection();
			} catch (Exception ex) {
				Logger.getLogger(DocumentCategoryBO.class.getName()).log(Level.SEVERE, null, ex);
			}
		}

		return result;
	}
}
