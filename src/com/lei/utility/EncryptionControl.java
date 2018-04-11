package com.lei.utility;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.dcm4che2.base64.Base64Decoder;
import org.dcm4che2.base64.Base64Encoder;

import com.lei.utility.constants.ApplicationConstants;

/**
 * 
 * @author Shrikant Kushwaha
 *
 */
public class EncryptionControl {
	private static final String algorithm = PropertyUtility.getValueString(ApplicationConstants.CONFIGURATIONSBUNDLE.getValue(), ApplicationConstants.ENCRIPTIONALGO.getValue(),"AES");
	private static final int keyLength = PropertyUtility.getValueInteger(ApplicationConstants.CONFIGURATIONSBUNDLE.getValue(), ApplicationConstants.ENCRIPTKEYLENGTH.getValue(),256);;
	private static final boolean enabled =   PropertyUtility.getValueBoolean(ApplicationConstants.CONFIGURATIONSBUNDLE.getValue(), ApplicationConstants.ENCRIPTIONENABLED.getValue(),false);;
	private static final String serverKey = PropertyUtility.getValueString(ApplicationConstants.CONFIGURATIONSBUNDLE.getValue(), ApplicationConstants.SERVERKEY.getValue(),null);;
//	private static final String serverKey = "ONEHnT9cfxChdSr+IUb1sipywA2ztHTDc6OIkEQ7GpY=";

	public String getNewKey(){
		try{
			KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);
			keyGenerator.init(keyLength);
			SecretKey secretKey = keyGenerator.generateKey();
			byte data[]=secretKey.getEncoded();
			String key = new String( Base64Encoder.encode(data));
			return key;
		}catch(Exception e){
			return serverKey;
		}
	}

	public String encrypt(String plainText){
		try{
			return encrypt(plainText,serverKey);
		}catch(Exception e){
			return plainText;
		}
	}
	public String decrypt(String encryptedText){
		try{
			return decrypt(encryptedText, serverKey);
		}catch(Exception e){
			return encryptedText;
		}
	}
	
	public String encrypt(String plainText, String secretKey){
		try{
			if(enabled){
				return encrypt(plainText, getKey(secretKey));
			}
			return plainText;
		}catch(Exception e){
			return plainText;
		}
	}
	public String decrypt(String encryptedText, String secretKey){
		try{
			if(enabled){
				return decrypt(encryptedText, getKey(secretKey));
			}
			return encryptedText;
		}catch(Exception e){
			return encryptedText;
		}
	}

	private SecretKey getKey(String key){
		return new SecretKeySpec(Base64Decoder.decode(key.getBytes()), algorithm);
	}
	
	private Cipher getCipher() throws NoSuchAlgorithmException, NoSuchPaddingException{
		return Cipher.getInstance(algorithm);
	}

	private String encrypt(String plainText, SecretKey secretKey) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException{
		Cipher cipher = getCipher();
		byte[] plainTextByte = plainText.getBytes();
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		byte[] encryptedByte = cipher.doFinal(plainTextByte);
		byte[] encryptedText = Base64Encoder.encode(encryptedByte);
		return new String(encryptedText);
	}

	private String decrypt(String encryptedText, SecretKey secretKey) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException{
		Cipher cipher = getCipher();
		byte[] encryptedTextByte = Base64Decoder.decode(encryptedText);
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		byte[] decryptedByte = cipher.doFinal(encryptedTextByte);
		String decryptedText = new String(decryptedByte);
		return decryptedText;
	}
}
