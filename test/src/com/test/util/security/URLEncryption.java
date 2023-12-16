
package com.test.util.security;

/**
 *
 * @author Murali.S
 */
public interface URLEncryption {
     
    public String EncrytURL(int isQueryURL,int bEncId,String Sess,String strURL);
    public String EncrytURLParam_ByPass(int i);
    
    public String EncrytTemplate(int isQueryURL,String Sess,String strURL);
    public String EncrytTemplateByPass(String strURL);
    public String EncrytTemplateSession(String Sess);


    public String GenerateCheckSum(String strCheckSum);
    public boolean IsValidCheckSum(String sChkURL, String sChkSum);
    public boolean IsValidURL_enc(String sChkSum,String sChkSum1);
}
