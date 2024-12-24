package com.example.Integration2.Controllers;

import com.example.Integration2.Util.Util;
import com.example.Integration2.services.GoogleAuthService;
import com.example.Integration2.services.GoogleDriveService;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping ("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class GoogleCollaborationController {

	@Autowired
	GoogleAuthService googleAuthService;

	@Autowired
	GoogleDriveService googleDriveService;

	@GetMapping("/authenticate")
	public ResponseEntity < String > authenticate( HttpServletResponse response) throws IOException {
			// Generate the Google authorization URL
			GoogleAuthorizationCodeRequestUrl authorizationUrl = googleAuthService.authenticate();
			return ResponseEntity.ok(authorizationUrl.toString());
	}

	@GetMapping("/oauth2/callback")
	public void handleGoogleCallback(@RequestParam ("code") String authorizationCode,HttpServletResponse response) {
		googleAuthService.exchangeTokenWith(authorizationCode);
		// Generate a one-time token
		String oneTimeToken = UUID.randomUUID().toString();
		Util.setOtt( oneTimeToken );
		// Redirect user to the Angular app with OTT
		try {
			response.sendRedirect("http://localhost:4200/upload-to-google-drive?ott=" + oneTimeToken);
		} catch ( IOException e ) {
			throw new RuntimeException( e );
		}
	}

	@PostMapping("/exchange-ott")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> exchangeOtt(@RequestBody Map <String, String> request) {
		String oneTimeToken = request.get("oneTimeToken");

		if (oneTimeToken == null || oneTimeToken.isEmpty()) {
			return ResponseEntity.badRequest().body("One-Time Token (OTT) is missing");
		}

		// check if generated ott matches to what angular provides me.
		String ott = Util.getOtt();
		if(!oneTimeToken.equals( ott )){
			return ResponseEntity.badRequest().body("ott mismatch");
		}

		// Construct response with access and refresh tokens
		return ResponseEntity.ok(Map.of(
				"accessToken", Util.getAccessToken(),
				"refreshToken", Util.getRefreshToken()
		));
	}

	@PostMapping("/upload")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<String> uploadFile( @RequestParam("file") MultipartFile file, @RequestParam("accessToken") String accessToken, @RequestParam("refreshToken") String refreshToken) {
		// Call the method to upload the file to Google Drive
		googleDriveService.uploadFileInGoogleDriveFolder( "1tur-0SgnHwuCB6rZ5Ey3_DtLGbnRVMFn", accessToken, refreshToken, file );
		return ResponseEntity.ok( "File uploaded successfully" );
	}
}
