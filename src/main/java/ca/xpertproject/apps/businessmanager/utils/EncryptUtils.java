package ca.xpertproject.apps.businessmanager.utils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class EncryptUtils {
	
	
	public static String encryptAES(String password, String inputToEncrypt) throws NoSuchAlgorithmException, UnsupportedEncodingException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		
		MessageDigest sha = MessageDigest.getInstance("SHA-256");
	    byte[] key = sha.digest(password.getBytes("UTF-8"));
	    SecretKey secretKey = new SecretKeySpec(key, "AES");
		
	    byte[] keyBytes = secretKey.getEncoded();
	    SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");

	    Cipher cipher = Cipher.getInstance("AES");
	    cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
	    byte[] encryptedBytes = cipher.doFinal(inputToEncrypt.getBytes());
	    String encryptedText = Base64.getEncoder().encodeToString(encryptedBytes);
	    
	    return encryptedText;
	}
	
	public static String decryptAES(String password, String inputToDecrypt) throws NoSuchAlgorithmException, UnsupportedEncodingException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
		
		MessageDigest sha = MessageDigest.getInstance("SHA-256");
	    byte[] key = sha.digest(password.getBytes("UTF-8"));
	    SecretKey secretKey = new SecretKeySpec(key, "AES");
		
	    byte[] keyBytes = secretKey.getEncoded();
	    SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");
		
	    Cipher cipher = Cipher.getInstance("AES");
	    
		cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
	    byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(inputToDecrypt));
	    String decryptedText = new String(decryptedBytes);
	    
	    return decryptedText;
	}
	
}
