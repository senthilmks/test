package com.test.util;

import java.security.Key;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;


public class PassMan 
{
    
	private static final String ALGO = "AES";
	//private static final byte[] keyValue = new byte[] { 'T', 'h', 'e', 'B', 'e', 's', 't','S', 'e', 'c', 'r','e', 't', 'K', 'e', 'y' };
	private static final byte[] keyValue = new byte[] { 'M', 'y', '#', 'U', 'n', 'i', 'v','e', 'r', 's', 'i','t', 'y', 'K', 'e', 'y' };

	public static String EncryStr(String Data) throws Exception 
	{
		Key key = generateKey();
		Cipher c = Cipher.getInstance(ALGO);
		c.init(Cipher.ENCRYPT_MODE, key);
		byte[] encVal = c.doFinal(Data.getBytes());
		 Base64.Encoder encoder = Base64.getEncoder();  
		String encryptedValue = encoder.encodeToString(encVal);
		return encryptedValue;
	}

	public static String DecryStr(String encryptedData) throws Exception 
	{
		Key key = generateKey();
		Cipher c = Cipher.getInstance(ALGO);
		c.init(Cipher.DECRYPT_MODE, key);
		Base64.Decoder decoder = Base64.getDecoder();
		//byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedData);
		byte[] decordedValue =decoder.decode(encryptedData);
		byte[] decValue = c.doFinal(decordedValue);
		String decryptedValue = new String(decValue);
		return decryptedValue;
	}
	private static Key generateKey() throws Exception 
	{
		Key key = new SecretKeySpec(keyValue, ALGO);
		return key;
	}
	public static void main(String[] args) throws Exception 
        {
       
    	PassMan passMan = new PassMan();
    	String inputPass="employee";
    	String encryptedText="";
    	//String encryptedPass=passMan.EncPass(inputPass);
    	//String decryptedPass=passMan.DecPass(encryptedPass);
    	//passMan.convertPass();
    	encryptedText=passMan.EncryStr(inputPass);
    	System.out.println(" encryptedText >> "+encryptedText);
    	System.out.println(" decryptedText >> "+passMan.DecryStr(encryptedText));
    	//passMan.convertPass();
    }
}

