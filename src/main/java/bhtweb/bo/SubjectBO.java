package bhtweb.bo;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import bhtweb.dbaccess.DocumentMapper;
import bhtweb.dbaccess.SubjectMapper;
import bhtweb.dto.ShortDocumentDTO;
import bhtweb.entities.BHTDocument;
import bhtweb.entities.BHTSubject;
import bhtweb.utils.DocumentFilter;

public class SubjectBO {

	public List<BHTSubject> viewAllSubjects() {

		SubjectMapper mapper = null;
		List<BHTSubject> result = null;
		try {

			mapper = new SubjectMapper();
			result = mapper.getSubjects();

		} catch (Exception ex) {
			Logger.getLogger(SubjectBO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {

			try {
				mapper.closeConnection();
			} catch (Exception ex) {
				Logger.getLogger(SubjectBO.class.getName()).log(Level.SEVERE, null, ex);
			}
		}

		return result;
	}
}
