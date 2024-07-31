package com.example.Integration2.services;
import jakarta.annotation.PostConstruct;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.Cipher;
import java.util.ArrayList;
import java.util.Base64;
import java.security.Signature;
import java.util.List;

@Service
public class CryptoService {
//	key generate cmd bata command haney pachi huncha. command haneypachi .jks extension bha file generate huncha.tyo file bhaneyko hamro keystore file ho. tesma certificate, private key ra public key huncha.
//	tyo file lai resources folder bhitra rakhnu parcha.
//	first kaam hamile keystore load garney huncha

	private KeyStore keyStoreEswea;
	private KeyStore keyStoreKhalti;

	@PostConstruct
	public void init() throws Exception {
		// Load KeyStore if it is in another folder location not in resources folder on springboot

//		keyStoreEswea = loadKeyStore("C:\\Users\\ShreejwalKaphle\\OneDrive - compliancequest.com\\Documents\\WorkSpace\\keystoreforPKI\\esewakeystore\\esewakeystore.jks", "Shreejwal");
//
//		// Load Khalti KeyStore
//		keyStoreKhalti = loadKeyStore("C:\\Users\\ShreejwalKaphle\\OneDrive - compliancequest.com\\Documents\\WorkSpace\\keystoreforPKI\\khaltikeystore\\khaltikeystore.jks", "Shreejwal");

//		resource folder bhitra chan bhaney keystore teslai load garney tarika.
		keyStoreEswea = loadKeyStore("esewakeystore.jks", "Shreejwal");
		keyStoreKhalti = loadKeyStore("khaltikeystore.jks", "Shreejwal");

	}

	private KeyStore loadKeyStore(String path, String password) throws Exception {
		KeyStore keyStore = KeyStore.getInstance("JKS");
		try (InputStream is = new ClassPathResource(path).getInputStream()) {
			keyStore.load(is, password.toCharArray());
		}
		return keyStore;
	}

//	receiver ko public key le message lai encrypt garincha. encrypted message ko hash calculate garincha. hash calculate garepachi teslai sender ko private key le encrypt garincha.

	public String encryptAndSign(String data) throws Exception {
//      public key jaile pani certificate bata tanincha.
//		yaha hamile khalti hamro receiver ho bhaney , khaliti ko certificate bata khalti ko public key tancham.
		PublicKey khaltiPublicKey = keyStoreKhalti.getCertificate("khaltiKeyPair").getPublicKey();

//		cipher chai managed class ko java le diyeko, already encryption algorithm haru dincha esle jastai RSA ,AES etc.
//		yo cipher use nagarney ho bhaney afai le algorithm lekhna parcha jun le encrypt garney ho. tyo jhanjhat garna bhanda cipher use garney.

//		Rsa algorith lera aija encrypt garna lai bhanera command diyeko hamile yo line ma
		Cipher cipher = Cipher.getInstance("RSA");
//		cipher lai jun key le encrypt/decrypt garney ho tyo key feed garna parcha init() method ma. ra encrypt garney ho ki decrypt garney ho tyo command ni yei init() method ma dina parcha.
		cipher.init(Cipher.ENCRYPT_MODE, khaltiPublicKey);
//		cipher le sapai kura paisakeypachi dofinal method ma chai actual data lai liyera agadi feed gareko algorithm , key ra mode use garera actual data lai encrypt/decrypt gardincha.
		byte[] encryptedBytes = cipher.doFinal(data.getBytes());

		// encrypted data ko hash calculate garera tyo hash lai sender ko private key le feri encrypt garna parney huncha.
//		yo sign garney kaam chai Signature class le garcha.
//		signature class lai kun data ko hash calculate garna parney ho tyo data feed garna parney huncha.
//		signature clas lai kun hashing algorithm le encrypted data ko hash calculate gareney ho tyo feed garna parney huncha.
//		hash calculate bhaisakepachi kun key le tyo hash lai encrypt garna parney ho tyo feed garna parney huncha.

//		signature lai sender ko private key feed garnalai keystore bata directly private key taneko. yo key le encrypted data ko hash lai feri encrypt garcha jaba hami yo key feed garcham signature lai.
		PrivateKey esewaPrivateKey = (PrivateKey) keyStoreEswea.getKey("esewaKeyPair", "Shreejwal".toCharArray());
//		signature lai encrypted data ko hash calculate garna use garney algorithm feed gareko
		Signature signature = Signature.getInstance("SHA256withRSA");
//		signature lai encrypted data ko hash calculate garna use huney key feed gareko
		signature.initSign(esewaPrivateKey);
//		signature lai hash calculate garna parney data provide gareko.
		signature.update(encryptedBytes);
//		yo sign method le chai actual kaam garcha. 3 ota. feed gareko sapai inputs harulai liyera encrypted data ko hash calculate garera encrypt ni gardincha. rabyte ma return gardincha.
		byte[] signedBytes = signature.sign();
//		sender le receiver lai pathauda encrypted data ra signature lai chuta chutai pathaucha
		sendToReceiver(Base64.getEncoder().encodeToString(encryptedBytes),Base64.getEncoder().encodeToString(signedBytes));
		
		return Base64.getEncoder().encodeToString(encryptedBytes) + ";" + Base64.getEncoder().encodeToString(signedBytes);
	}

	public String verifyAndDecrypt(String encryptedData , String sign) throws Exception {
//		receiver ko side ma encrypted data ra signature ayo.
		byte[] encryptedBytes = Base64.getDecoder().decode(encryptedData);
		byte[] receivedSignature = Base64.getDecoder().decode(sign);

//		paile ta signature lai sender ko public key le decrypt garna parney huncha. tesbata encrypted message ko calculate bhayeko hash niskincha.

//		signature lai public key feed garna lai certificate bata sender ko public key fetch gareko. public key chai receivedsignature lai decrypt garna use huncha pachi.
		PublicKey esewaPublicKey = keyStoreEswea.getCertificate("esewaKeyPair").getPublicKey();
//		sender le jun hasing algorithm lagayera encrypted message ko hash calculate gareko huncha, tyei algorithm signature lai feed gareko.
//		kina bhanaye signature lai feri encrypted message feed garna parney huncha ra tesko hash feri calculate garna parney hucnha
//		feri hash kina calculate garna pacha bahney , received signature lai decrypte garepachi pani hash nai niskincha, encrypted message ko hash calculate garepachi pani hash nai niskncha,
//		ra tyo duita hash compare garda milnai parcha verify huna lai.
		Signature signature = Signature.getInstance("SHA256withRSA");
//		signture lai sender ko public key feed gareko. yesbata pachi received signature decrypt huncha ra hash niskincha tesbata.
		signature.initVerify(esewaPublicKey);
//		signture lai encrypted byte pani feed gareko. kin bhaney yei encrypted byte lai hash calculate garney ho ra compare garney ho receivedsignature decrypt bhayeko hash sanga.
		signature.update(encryptedBytes);
//		verify method le chai sapai kaam garcha. feed gareko input sapai liyera,
//		first ma encrypted message lincha. teslai provided hasing algorithm le hashcode calculate garcha
//		receivedsignature lai sender ko public key le decrypt garera hashcode output ma nikalcha. kina decrypt garda hashcode niskincha bhaney sender le hashcode lai encrypt garera pathako huncha.
//		receivedsignature bata decrypt bhayeko hashcode lai ra encryptedmessage bata calculate bhayeko hashcode lai compare garcha. milyo bhaney verified huncha sender ko identity.
		if (!signature.verify(receivedSignature)) {
			throw new SecurityException("Signature verification failed.");
		}

//		sender ko identity verify bhaisakepachi, aba encrypted message lai decrypt garna parney huncha. tesko lagi reveicer ko private key chaincha. tyo taneyko cha yaha keystore bata directly
		PrivateKey khaltiPrivateKey = (PrivateKey) keyStoreKhalti.getKey("khaltiKeyPair", "Shreejwal".toCharArray());
//      encrypt garda jun algorithm cipher lai feed gareko cha tei algorithm cipher lai feed garna parney huncha decrypt garna lai pani. tei algorithm to decrypt feed gareko.
		Cipher cipher = Cipher.getInstance("RSA");
//		decrypt garna lai command diyeko plus kun key le decrupt huncha tei key pani feed gareko
		cipher.init(Cipher.DECRYPT_MODE, khaltiPrivateKey);
//		dofinal ma encrypted message lai rsa algorith le receiver ko private key lagayera decrypt garcha ra actula message ko byte return garcha.
		byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
//		byte ko string convert garda actual message aucha.
		return new String(decryptedBytes);
	}
	
	private void sendToReceiver(String encrypteddata,String signature){
		try ( CloseableHttpClient httpClient = HttpClients.createDefault()) {
			HttpPost postRequest = new HttpPost("http://localhost:8080/khalti");

			List < NameValuePair > urlParameters = new ArrayList <>();
			urlParameters.add(new BasicNameValuePair("encryptedData", encrypteddata));
			urlParameters.add(new BasicNameValuePair("signature", signature));

			// Set form parameters as the entity
			postRequest.setEntity(new UrlEncodedFormEntity(urlParameters));
			postRequest.setHeader("Content-Type", "application/x-www-form-urlencoded");

			// Execute the request
			HttpResponse response = httpClient.execute(postRequest);
		} catch ( IOException e ) {
			throw new RuntimeException( e );
		}
	} 
}
