package bhtweb.servlets;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import bhtweb.bo.DocumentBO;
import bhtweb.dto.DocumentUploadDTO;
import bhtweb.utils.Uploader;


@WebServlet(name = "UploadDocumentSerlvet", urlPatterns = {"/UploadDocumentSerlvet"})
public class UploadDocumentSerlvet extends HttpServlet{
	
	private boolean isMultipart;
	private String savePath;
	private int maxFileSize = 50 * 1024;
	private int maxMemSize = 4 * 1024;
	private File file;
	
	Uploader uploader;
	DocumentBO documentBO;
	
	private String encodeValue(String value) {
	    try {
			return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "NULL";
		}
	}

	public void init() {
		
		uploader = new Uploader();
		documentBO = new DocumentBO();

		String path = getServletContext().getRealPath("/");
		savePath = path + "\\uploadDir\\";
		
		System.out.println("save to:" + path);
	}
	
	private boolean saveDocumentToDB(DocumentUploadDTO doc) {
		return documentBO.CreateDoc(doc);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {
		
		request.setCharacterEncoding("UTF-8");

		// Check that we have a file upload request
		isMultipart = ServletFileUpload.isMultipartContent(request);
		response.setContentType("text/html");
		java.io.PrintWriter out = response.getWriter();

		if (!isMultipart) {
			out.println("No file uploaded");
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
		
		try {
			// Parse the request to get file items.
			List fileItems =  upload.parseRequest(request);

			// Process the uploaded file items
			Iterator i =  fileItems.iterator();

			out.println("<title>Servlet upload</title>");

			// duyet 1 file dau tien thoi, neu no dung post man hay gi do
			// co tinh day nhieu file
			
			while (i.hasNext()) {
				FileItem fi = (FileItem) i.next();
				if (!fi.isFormField()) {
					// Get the uploaded file parameters
					// String fieldName = fi.getFieldName();
					String fileName = fi.getName();
					String contentType = fi.getContentType();
					
					// Write the file
					String fullPath = savePath + fileName.substring(fileName.lastIndexOf("\\") + 1);
					if (fileName.lastIndexOf("\\") >= 0) {
						fullPath = savePath + fileName.substring(fileName.lastIndexOf("\\"));
					}
					
					//file = new File(fullPath);
					//fi.write(file);
					
					//Save to driver
					//url = uploader.uploadFile(file, fileName, contentType);
					
					url ="hehe";
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
		        int uploaderId = 1;
		        DocumentUploadDTO doc = new DocumentUploadDTO(
		        		encodeValue(parameterMap.get("title")),
		        		parameterMap.get("summary"),
		        		uploaderId,
		        		url,
		        		parameterMap.get("semesterId"),
		        		parameterMap.get("subjectId"),
		        		parameterMap.get("categoryId"));
//				if (saveDocumentToDB(doc)) {
//					out.println("Uploaded Filename: " + url + "<br>");
//				}
		        System.out.println(parameterMap.get("title"));
				out.println("title: " + parameterMap.get("title"));
				out.println("summary: " + parameterMap.get("summary"));
				out.println("categoryId: " + parameterMap.get("categoryId"));
				out.println("subjectId: " + parameterMap.get("subjectId"));
				out.println("semesterId: " + parameterMap.get("semesterId"));
			} else {
				out.println("Upload fail.");
			}
		}
	}

}
