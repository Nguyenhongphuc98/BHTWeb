package bhtweb.bo;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import bhtweb.dbaccess.DocumentCategoryMapper;
import bhtweb.dbaccess.SemesterMapper;
import bhtweb.entities.BHTDocumentCategory;
import bhtweb.entities.BHTSemester;

public class SemesterBO {
	
	public List<BHTSemester> viewAllSemester() {

		SemesterMapper mapper = null;
		List<BHTSemester> result = null;
		try {

			mapper = new SemesterMapper();
			result = mapper.getSemesters();

		} catch (Exception ex) {
			Logger.getLogger(SemesterBO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {

			try {
				mapper.closeConnection();
			} catch (Exception ex) {
				Logger.getLogger(SemesterBO.class.getName()).log(Level.SEVERE, null, ex);
			}
		}

		return result;
	}
}