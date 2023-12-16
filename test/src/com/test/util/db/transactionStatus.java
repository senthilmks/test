/*
 * transactionStatusEnum.java
 *
 * Created on June 2, 2004, 2:45 PM
 */

package com.test.util.db;

/**
 * @author  root
 */
public final class transactionStatus {
    
    public static int upperBound = 0; // counter variable for no. of instancess
    
    // this final variable stores the constant value for the status that we assigns
    public int statusValue;
    public String statusDescription;
    public final int NumberOfStatus;
    
    /** Important this is private constructro No one else this class can create 
        object for this class*/
    private transactionStatus(int argStatusId, String argStatusDesc) {
        this.statusValue = argStatusId;
        this.statusDescription = argStatusDesc;
        this.NumberOfStatus = upperBound++;
    }
    public String toString() {return this.statusDescription;}
    public int id() {return this.statusValue;}    
    public static int statusCount() { return upperBound; }    
    //Whenever you are making changes in this list please look the 
    //Database Function TransactionStatus need to be modified or not
    public static final transactionStatus DataEntered = new transactionStatus(0, "DataEntered");
    public static final transactionStatus Approved = new transactionStatus(1, "Approved");
    public static final transactionStatus Rejected = new transactionStatus(9, "Rejected");    
}