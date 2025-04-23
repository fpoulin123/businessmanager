package ca.xpertproject.apps.businessmanager.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EncryptUtilsTest {
	

	@Test
	public void testEncryption() {
		
		String password = "sup3rCleMaJ1k";
		
		String inputToEncrypt = "Texte à chiffrer";
		
		String inputToDecrypt = "M83LHf+Pmak0VScC5MjvQiCn3MKtwCJ3z0U0uPbVqzw=";
		
		try {
			String encrypted = EncryptUtils.encryptAES(password, inputToEncrypt);
			
			assertEquals(inputToDecrypt, encrypted);
				
			
		} catch (InvalidKeyException | NoSuchAlgorithmException | UnsupportedEncodingException | NoSuchPaddingException
				| IllegalBlockSizeException | BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testDecryption() {
		String password = "sup3rCleMaJ1k";
		
		String inputToEncrypt = "Texte à chiffrer";
		
		String inputToDecrypt = "M83LHf+Pmak0VScC5MjvQiCn3MKtwCJ3z0U0uPbVqzw=";
		
		try {
			
			String decrypted = EncryptUtils.decryptAES(password, inputToDecrypt);
			
			assertEquals(inputToEncrypt, decrypted);
			
		} catch (InvalidKeyException | NoSuchAlgorithmException | UnsupportedEncodingException | NoSuchPaddingException
				| IllegalBlockSizeException | BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
