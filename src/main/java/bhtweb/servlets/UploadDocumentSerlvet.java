package bhtweb.servlets;

import java.io.File;
import java.io.PrintWriter;
import java.util.*;

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

import bhtweb.bo.DocumentBO;
import bhtweb.dto.AccountDTO;
import bhtweb.dto.DocumentDTO;
import bhtweb.dto.DocumentUploadDTO;
import bhtweb.utils.BHTSession;
import bhtweb.utils.ServletUtils;
import bhtweb.utils.Uploader;
import bhtweb.dto.ResponseStatus;

@WebServlet(name = "UploadDocumentSerlvet", urlPatterns = { "/docs/upload" })
public class UploadDocumentSerlvet extends HttpServlet {

	private boolean isMultipart;
	private String savePath;
	private int maxFileSize = 2 * 1024 * 1024; // 2MB
	private int maxMemSize = 1024 * 1024;
	private File file;

	Uploader uploader;
	DocumentBO documentBO;
	private Gson gson;

	public void init() {

		uploader = new Uploader();
		documentBO = new DocumentBO();

		String path = getServletContext().getRealPath("/");
		savePath = path + "uploadDir\\";
		
		//File directory = new File(path);
		//directory.mkdir();
		//System.out.println("created outsite: " + savePath);
//	    if (! directory.exists()){
//	        directory.mkdir();
//	        // If you require it to make the entire directory path including parents,
//	        // use directory.mkdirs(); here instead.
//	        System.out.println("created2: " + savePath);
//	    } else {
//	    	System.out.println("exist2: " + savePath);
//		}

		System.out.println("save to:" + path);

		gson = new Gson();
	}

	private boolean saveDocumentToDB(DocumentUploadDTO doc) {
		return documentBO.CreateDoc(doc);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {
		
		//ServletUtils.addNoCORSHeader(response);

		request.setCharacterEncoding("utf-8");

		// Check that we have a file upload request
		isMultipart = ServletFileUpload.isMultipartContent(request);

		PrintWriter out = ServletUtils.getJSONUnicodeWriterNoCORS(response);
		
		// get curent account
		//HttpSession session = request.getSession();
		//AccountDTO accountDTO = (AccountDTO) session.getAttribute("account");
		
		AccountDTO accountDTO = BHTSession.currentUser(request);
		
		ResponseStatus status = new ResponseStatus();
		String statusJsonString = "";
		
		if (accountDTO == null) {
			status.setStatusCode(ResponseStatus.PERMISSION_DENNED);
		} else {
			
			if (!isMultipart) {
				response.setStatus(HttpServletResponse.SC_PRECONDITION_FAILED);
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

			String url = "ERROR";
			Map<String, String> parameterMap = new HashMap<String, String>();

			String fileName ="No Name";
			try {
				// Parse the request to get file items.
				List fileItems = upload.parseRequest(request);

				// Process the uploaded file items
				Iterator i = fileItems.iterator();

				// duyet 1 file dau tien thoi, neu no dung post man hay gi do
				// co tinh day nhieu file

				while (i.hasNext()) {
					FileItem fi = (FileItem) i.next();
					if (!fi.isFormField()) {
						// Get the uploaded file parameters
						// String fieldName = fi.getFieldName();
						fileName = fi.getName();
						String contentType = fi.getContentType();

						// Write the file
						String fullPath = savePath + fileName.substring(fileName.lastIndexOf("\\") + 1);
						if (fileName.lastIndexOf("\\") >= 0) {
							fullPath = savePath + fileName.substring(fileName.lastIndexOf("\\"));
						}

						file = new File(fullPath);
						fi.write(file);

						// Save to driver
						url = uploader.uploadFile(file, fileName, contentType);

					} else {
						parameterMap.put(fi.getFieldName(), fi.getString());
					}
				}

			} catch (Exception ex) {
				System.out.println(ex);
			} finally {

				if (!url.equals("ERROR")) {

					// save to db

					// get current user via session.
					//int uploaderId = 1;
					DocumentUploadDTO doc = new DocumentUploadDTO(parameterMap.get("title"),
							parameterMap.get("summary"),
							accountDTO.getId(),
							url,
							parameterMap.get("semesterId"),
							parameterMap.get("subjectId"),
							parameterMap.get("categoryId"),
							fileName);

					if (saveDocumentToDB(doc)) {

						status.setStatusCode(ResponseStatus.CREATE_DOCUMENT_SUCCESS);
						status.setDocumentUploadDTO(doc);
					}

				} else {
					//response.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
					status.setStatusCode(ResponseStatus.CREATE_DOCUMENT_FAIL);
				}
			}
		}

		statusJsonString = this.gson.toJson(status);
		out.print(statusJsonString);
		out.flush();
	}

}
