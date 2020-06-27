package bhtweb.utils;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

import com.bht.core.DriverConnector;
import com.bht.core.Utilities;
import com.google.api.client.http.FileContent;
import com.google.api.services.drive.model.File;

public class Uploader {

	// Return `ERROR` if some thing not working
	public String uploadFile(java.io.File fileOnserver, String filename, String contentType) {

		String folderId = Utilities.ROOT_FOLDER;

		// JRE1.7
		switch (contentType) {
		case Utilities.TEXT_TYPE:
			folderId = Utilities.TEXT_FOLDER;
			break;

		case Utilities.PDF_TYPE:
			folderId = Utilities.PDF_FOLDER;
			break;

		case Utilities.POWERPOINT_TYPE:
			folderId = Utilities.POWERPOINT_FOLDER;
			break;

		default:
			System.out.println("content type: `" + contentType + "` not suport");
			return "ERROR";
		}

		BufferedOutputStream stream;
		try {
//			java.io.File fileSaveInServer = new java.io.File(savePath);
//			stream = new BufferedOutputStream(new FileOutputStream(fileSaveInServer));
//			stream.write(data);
//			stream.flush();
//			stream.close();
			
			/// Save to google driver

			File fileMetadata = new File();
			fileMetadata.setName(filename);
			fileMetadata.setParents(Collections.singletonList(folderId));

			FileContent mediaContent = new FileContent(contentType, fileOnserver);
			File fileUploadDriver = DriverConnector.getDriverService().files().create(fileMetadata, mediaContent)
					.setFields("id, parents").execute();

			String idString = fileUploadDriver.getId();
			System.out.println("File ID: " + idString);
			return idString;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return "ERROR";
		} catch (IOException e) {
			e.printStackTrace();
			return "ERROR";
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
			return "ERROR";
		}
	}
}
