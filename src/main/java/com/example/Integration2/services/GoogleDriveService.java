package com.example.Integration2.services;

import com.google.api.client.http.FileContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import com.google.auth.oauth2.AccessToken;
import com.google.auth.oauth2.GoogleCredentials;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;

@Service
public class GoogleDriveService {

	public Drive DriveBuilder(String accessToken,String refreshToken){
		AccessToken token = new AccessToken(accessToken,null); // 1 hour validity
		GoogleCredentials googleCredentials = GoogleCredentials
				.newBuilder()
				.setAccessToken(token)
				.build();


		// Initialize Drive service
		Drive.Builder builder = new Drive.Builder(
				new NetHttpTransport( ),
				JacksonFactory.getDefaultInstance( ),
				request -> {
					googleCredentials.refreshIfExpired();
					request.getHeaders().setAuthorization("Bearer " + googleCredentials.getAccessToken().getTokenValue());
				}		);
		builder.setApplicationName( "shreejwal drive collab app" );// Ensure tokens are refreshed
		Drive driveService = builder
				.build();
		return driveService;
	}

	public void listGoogleDriveFiles(String accessToken,String refreshToken) throws IOException {
		Drive driveService = DriveBuilder(accessToken,refreshToken);

		// Make a request to list the files in Google Drive
		Drive.Files.List request = driveService.files().list();
		FileList fileList = request.execute();

		// Print the file names
		for ( File file : fileList.getFiles()) {
			System.out.println("File name: " + file.getName());
		}
	}

//	this method will create a folder in your google drive from this application.
//	tyo folder ko euta id huncha. timile folder banauda folder ko naam dinchau tara google le tyo folder lai as an ID rakhcha.
//	tyo id ma pachi file upload garna google lai request pathauchau, tyo request payesi google le timro folder ma rakhdincha.
	public void CreateFolderInGoogleDrive(String folderName,String accessToken, String refreshToken ){
		Drive driveService = DriveBuilder(accessToken,refreshToken);

		File folderMetadata = new File();
		folderMetadata.setName(folderName);
		folderMetadata.setMimeType("application/vnd.google-apps.folder");

		File folder = null;
		try {
			folder = driveService.files().create(folderMetadata)
					.setFields("id")
					.execute();
		} catch ( IOException e ) {
			throw new RuntimeException( e );
		}
		String folderId = folder.getId();
		System.out.println("Folder ID: " + folderId);
	}

	public void uploadFileInGoogleDriveFolder( String folderId, String accessToken, String refreshToken, MultipartFile multipartFile ){
		final String UPLOAD_DIR = "C:\\personal workspace\\java side integration\\google collaboration\\filestobesavedintoserver";

		// Save the file to the upload directory
		Path filePath = Paths.get( UPLOAD_DIR, multipartFile.getOriginalFilename( ) );
		try {
			Files.write( filePath, multipartFile.getBytes( ) );
		} catch ( IOException e ) {
			throw new RuntimeException( e );
		}
		Drive driveService = DriveBuilder(accessToken,refreshToken);

		File fileMetadata = new File();
		fileMetadata.setName(filePath.toFile().getName());
		fileMetadata.setParents( Collections.singletonList(folderId));

		FileContent mediaContent = new FileContent("text/plain", filePath.toFile());

		File uploadedFile = null;
		try {
			uploadedFile = driveService.files().create(fileMetadata, mediaContent)
					.setFields("id")
					.execute();
		} catch ( IOException e ) {
			throw new RuntimeException( e );
		}

		System.out.println("File ID: " + uploadedFile.getId());
	}
}

