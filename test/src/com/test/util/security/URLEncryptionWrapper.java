/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */  
package com.test.util.security;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


import java.util.Base64;
//import srm.security.Security_1_Delink_zip.RequestWrapper;
/**
 *
 * @author Administrator
 */
public class URLEncryptionWrapper  implements URLEncryption, Encryption{
    private String sess=""; 
     
    private Charset CHARSET = Charset.forName("UTF-8"); 
     
    private static URLEncryptionWrapper encUrl = null;
    
   private  String ALGO = "AES/CBC/PKCS5Padding";
  // private  String ALGO = "AES/CBC/NoPadding";
    
    //private  String ALGO = "AES/ECB/PKCS7Padding";
    
    private  String SecretKeyString="AES";
    
    private  byte  m_BitFlag= BIT_256;
    
    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSSSS");
    
    public URLEncryptionWrapper(){
        
    }
    public URLEncryptionWrapper(String Sessn){
        this.sess=Sessn;
    }
   public static URLEncryptionWrapper getInstance( ) {
       if(encUrl==null){
           encUrl =new URLEncryptionWrapper( );
       }
      return encUrl;
   }
    
  
    @Override
    public byte getEncodeType() throws Exception {   
       return this.m_BitFlag;
    }
   
  
    @Override
    public byte[] getIvValue() throws UnsupportedEncodingException { 
        return ")ifuorr#a@l$i&*l".getBytes(CHARSET);
        //return ")for#@$stingm&*9".getBytes(CHARSET);
    }


    @Override
    public byte[] getKeyValue() throws UnsupportedEncodingException {
         return "O%2U$R5&A^L@I&Q4".getBytes("UTF-8"); // 128
        //return "H%2D$F5&C^B@K&N1".getBytes(CHARSET); //  running
       // return "M%2U$R5&A^L@I&N6M%2U$R5&A^L@I&U6".getBytes("UTF-8"); // 256
    }

    
    @Override
    public boolean isEncrypted(String sValue) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
  
   
     public String Encryt(String strVal) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(this.getKeyValue(), SecretKeyString);
            IvParameterSpec ivSpec = new IvParameterSpec(this.getIvValue());
            Cipher cipher = Cipher.getInstance(ALGO);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
            
            byte [] results = cipher.doFinal(strVal.getBytes(CHARSET));
            Base64.Encoder encoder = Base64.getEncoder();
            //BASE64Encoder encoder = new BASE64Encoder();
            //return new BASE64Encoder().encode(results).replaceAll("\r\n", "");
            return byteArrayToHexString(results);
            
        } catch (Exception e) {
            strVal="error";
            System.err.println("URLEncryptionWrapper::Encryt::"+e.toString());
        }
        return strVal;
    }    
    

    public String Decrypt(String strVal) {
        String strParam="";
        try {
            SecretKeySpec keySpec = new SecretKeySpec(this.getKeyValue(), SecretKeyString);
            IvParameterSpec ivSpec = new IvParameterSpec(this.getIvValue());
            Cipher desCipher = Cipher.getInstance(ALGO);
            desCipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
            
            byte [] decbytes = hexStringToByteArray(strVal);
            //String str= new String(desCipher.doFinal(new BASE64Decoder().decodeBuffer(strParam)));
            byte [] results = desCipher.doFinal(decbytes);
            
            return new String(results, "UTF-8");
            
        } catch (Exception e) {
            strParam="error";
            System.err.println("URLEncryptionWrapper::Decrypt::"+e.toString());
        }
        return strParam;
    } 
    
    
   
     
  
    public String GenerateCheckSum(String strCheckSum){
       String stcheckSumdata = new String(strCheckSum);
        long[] chksum = new long[1];
        chksum[0] = 0;
        for (int j = 0; j < stcheckSumdata.length(); j++) {
            chksum[0] += ((int) stcheckSumdata.charAt(j)) * (j + 1);
        }
        return String.valueOf(chksum[0]);
    }
    
   
    
    public String EncrytTemplate(int isQueryURL, String Sess,String strURL){
           
        Calendar cal = Calendar.getInstance();

        String strSalt="dt="+ sdf.format(cal.getTime());

        strSalt=strSalt+"&PgSession="+Sess;

        strURL += (isQueryURL==FALSE)? "?"+strSalt: "&"+strSalt;

        String strCheckSum =GenerateCheckSum(strURL);

        strURL = strURL+"&urlchecksum=" + strCheckSum;

        return Encryt(strURL);
    }
       
       
   
    
  
    @Override
    public String EncrytTemplateByPass(String strURL){
        //Calendar cal = Calendar.getInstance();
        //String strSalt="dtByPass="+ sdf.format(cal.getTime());
        return Encryt(strURL);
    } 
    
   
    @Override
    public String EncrytTemplateSession(String Sess){
        throw new UnsupportedOperationException("Not supported yet.");
//        Calendar cal = Calendar.getInstance();
//        String strSalt="dtsess="+ sdf.format(cal.getTime());
//        String str= Encryt(Sess);
//         return str;
    }
    
     
    
    @Override
     public boolean IsValidCheckSum(String sChkSum,String sChkSum1){
        return sChkSum.equals(sChkSum1);
    }
      
   
    @Override
      public boolean IsValidURL_enc(String sChkURL,String sChkSum){
         try{   
            if(sChkURL.isEmpty() || sChkSum.isEmpty()) return false;
            sChkURL= Decrypt(sChkURL);
            sChkURL = sChkURL.substring(0, sChkURL.indexOf("&urlchecksum="));
            sChkURL=GenerateCheckSum(sChkURL);
            return IsValidCheckSum(sChkURL,sChkSum);
         } catch (Exception e) {}
        return false;   
    }    
            
          
     public String DecryptRequest(String strEnch, String strURL){
          if(strEnch.equalsIgnoreCase("enchid")){
             return DecrytURL(strURL);
         }else if( strEnch.equalsIgnoreCase("enchip")){
             return DecrytTemplateByPass(strURL);
         }else if( strEnch.equalsIgnoreCase("enchis")){
             return DecrytTemplateSession(strURL);
         }
         return "";      
     }
     
      public String DecrytTemplateSession(String strURL){
       
        String str= Decrypt(strURL);

         return str;
    } 
      
     public String DecrytTemplateByPass(String strURL){
        return Decrypt(strURL);
    } 
     
    public String DecrytURL(String strURL) {
        return Decrypt(strURL);
    }
    /* ****
     * Pass value like this SubmitTo=/package/filname.jsp
     * */
     
    public String EncrytURL(int isQueryURL, int bEncId, String Sess,String strURL) {
           
         strURL= "SubmitTo="+strURL;
         String str="",str1="" ;
        try {
            str=EncrytTemplate(isQueryURL,Sess,strURL);
            str = (bEncId==TRUE)? str="enchid="+str:str;
        } catch (Exception ex) {
            Logger.getLogger(URLEncryptionWrapper.class.getName()).log(Level.SEVERE, null, ex);
        }
         return str;
    }
     
     public String EncrytURLSession(int bEncId,String ses ) {
         return (bEncId==TRUE)?EncrytTemplateSession("enchis=PgSession="+ses):EncrytTemplateSession("PgSession="+ses);
     }
     
     public String EncrytURLParam_ByPass(int bEncId ) {
           
//          String str1="",str=(bEncId==TRUE)?"enchip=ByPassTo=ExcEptIOn":"ByPassTo=ExcEptIOn";
//          str1=EncrytTemplateByPass(str);
//          return str1;
         return (bEncId==TRUE)?EncrytTemplateByPass("enchip=ByPassTo=ExcEptIOn"):EncrytTemplateByPass("ByPassTo=ExcEptIOn");

     }    
     
     public static String byteArrayToHexString (byte buf[]) {  
          StringBuffer strbuf = new StringBuffer(buf.length * 2);  
          int i;  

          for (i = 0; i < buf.length; i++) {  
           if (((int) buf[i] & 0xff) < 0x10)  
            strbuf.append("0");  

           strbuf.append(Long.toString((int) buf[i] & 0xff, 16));  
          }  
  
      return strbuf.toString();  
     }  
     
     
    public static byte[] hexStringToByteArray(String s)   
     {       
         int len = s.length();       
         byte[] data = new byte[len / 2];       
         for (int i = 0; i < len; i += 2)   
         {           
         data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i+1), 16));       
         }       
         return data;   
     } 
   
    
    public static void main(String[] args) throws Exception 
    {
   
    	URLEncryptionWrapper passMan = new URLEncryptionWrapper();
	String inputPass="employee";
	String encryptedText="";
	//String encryptedPass=passMan.EncPass(inputPass);
	//String decryptedPass=passMan.DecPass(encryptedPass);
	//passMan.convertPass();
	encryptedText=passMan.Encryt(inputPass);
	System.out.println(" encryptedText >> "+encryptedText);
	System.out.println(" decryptedText >> "+passMan.Decrypt(encryptedText));
	//passMan.convertPass();
}
     
    
}
