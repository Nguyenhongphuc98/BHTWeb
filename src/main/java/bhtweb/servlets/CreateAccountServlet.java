package bhtweb.servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.google.gson.Gson;

import bhtweb.bo.UserAccountBO;
import bhtweb.dto.AccountDTO;
import bhtweb.dto.DocumentUploadDTO;
import bhtweb.dto.NewAccountDTO;
import bhtweb.dto.ResponseStatus;
import bhtweb.utils.ServletUtils;
import bhtweb.utils.Uploader;
import bhtweb.utils.Utilities;

@WebServlet(name = "CreateAccountServlet", urlPatterns = { "/register" })
public class CreateAccountServlet extends HttpServlet {

	private boolean isMultipart;
	private String savePath;
	private int maxFileSize = 2 * 1024 * 1024; // 2MB
	private int maxMemSize = 1024 * 1024;
	private File file;

	Uploader uploader;

	UserAccountBO userAccountBO;
	private Gson gson;

	public void init() {
		userAccountBO = new UserAccountBO();
		gson = new Gson();
		uploader = new Uploader();

		String path = getServletContext().getRealPath("/");
		savePath = path + "\\uploadDir\\";
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		PrintWriter out = ServletUtils.getJSONUnicodeWriterNoCORS(resp);
		// Check that we have a file upload request
		isMultipart = ServletFileUpload.isMultipartContent(req);

		if (!isMultipart) {
			resp.setStatus(HttpServletResponse.SC_PRECONDITION_FAILED);
			return;
		}

		DiskFileItemFactory factory = new DiskFileItemFactory();

		// maximum size that will be stored in memory
		factory.setSizeThreshold(maxMemSize);

		// Location to save data that is larger than maxMemSize.
		factory.setRepository(new File("c:\\mytemp"));

		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);

		// maximum file size to be uploaded.
		upload.setSizeMax(maxFileSize);

		Map<String, String> parameterMap = new HashMap<String, String>();

		ResponseStatus status = new ResponseStatus();
		status.setStatusCode(ResponseStatus.UNKNOWN);

		try {
			// Parse the request to get file items.
			List fileItems = upload.parseRequest(req);

			// Process the uploaded file items
			Iterator i = fileItems.iterator();

			String avatarId = "NULL";

			//String fileName = "";
			String contentType = "";
			FileItem avtFileItem = null;

			while (i.hasNext()) {
				
				FileItem fi = (FileItem) i.next();
				
				if (!fi.isFormField()) {
					
					//fileName = fi.getName();
					contentType = fi.getContentType();

					// Get path to save file on server
//					String fullPath = savePath + fileName.substring(fileName.lastIndexOf("\\") + 1);
//					if (fileName.lastIndexOf("\\") >= 0) {
//						fullPath = savePath + fileName.substring(fileName.lastIndexOf("\\"));
//					}
					
					avtFileItem = fi;
					
				} else {
					parameterMap.put(fi.getFieldName(), fi.getString());
				}
			}
			


			AccountDTO accountDTO = userAccountBO.getAccountByUsername(parameterMap.get("username"));
			
			if (accountDTO != null) {
				status.setStatusCode(ResponseStatus.ACCOUNT_EXISTED);
			} else {

				if (contentType.equals(Utilities.IMAGE_JPEG_TYPE) || contentType.equals(Utilities.IMAGE_PNG_TYPE)) {

					String fileName = parameterMap.get("username");
					if (contentType.contentEquals(Utilities.IMAGE_JPEG_TYPE)) {
						fileName += ".jpg";
					} else {
						fileName += ".png";
					}
					String fullPath = savePath + fileName;

					// Write file to server disk
					if (avtFileItem != null) {
						file = new File(fullPath);
						avtFileItem.write(file);
						
						// Save to driver
						avatarId = uploader.uploadFile(file, fileName, contentType);
					}

					NewAccountDTO newAccount = new NewAccountDTO();
					newAccount.setEmail(parameterMap.get("email"));
					newAccount.setProfilePictureURL(avatarId);
					newAccount.setUserGroupID(3); // user
					newAccount.setUserName(parameterMap.get("username"));
					newAccount.setUserPassword(parameterMap.get("password"));
					newAccount.setDisplayName(parameterMap.get("displayname"));

					// Save to database
					userAccountBO.CreateAccount(newAccount);

					status.setStatusCode(ResponseStatus.CREATE_ACCOUNT_SUCCESS);
					status.setNewAccount(newAccount);
				} else {
					status.setStatusCode(ResponseStatus.TYPE_NOT_SUPPORT);
				}
			}

		} catch (

		Exception ex) {
			System.out.println(ex);
		} finally {

			String statusJsonString = this.gson.toJson(status);
			out.print(statusJsonString);
			out.flush();
		}
	}
}