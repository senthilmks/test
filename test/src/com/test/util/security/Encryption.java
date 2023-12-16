package com.test.util.security;



/** 
 *
 * @author Murali.S
 */
public interface Encryption {
     public byte[] getIvValue()throws Exception;
     public byte[] getKeyValue()throws Exception;
     public boolean isEncrypted(String sValue) throws Exception ;
     public  String Encryt(String sValue) throws Exception ;
     public String Decrypt(String sValue) throws Exception ;
     public byte getEncodeType() throws Exception ;
     
    public  final int BIT_128 = 1;
    public  final int  BIT_256 = 2;
    
    public  final int  TRUE = 1;
    public  final int  FALSE = 0;
    
    
}
