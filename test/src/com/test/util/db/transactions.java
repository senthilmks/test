/*
 * Transactions.java
 * Created on April 20, 2004, 12:17 PM
 */    
package com.test.util.db;

import java.sql.*;
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import com.test.LoginUser;

/**
 * Systems <B>unique transactionid</B> generator
 **/
public class transactions extends com.test.util.db.SQLTransEngine
implements java.io.Serializable {
    private Connection con;
    private long llngTransactionid;
    private long TransType;
    private long DigitalSign;
    private long OfficeId;
    private LoginUser loginUser=null;
    //private admissiontransactionbean.AdmissionLoginBean studentLoginUser=null; // By Praveen
    private long llngFMtoEmployeeid;
    private transactionStatus curStatus;
    protected String transactionIDs;
    private String lstrRejectionReason="";
    private String lstrInterimRemarks="";
    private String lstrCCEmployeeid="";
    private String MultipleTransactionIDs;
    
    DateFormat dtFmt = new SimpleDateFormat("yyyy-MM-dd");
    
    
    
    public final void setLoginUser(LoginUser tlu){
        this.loginUser = tlu;
        super.setLoginUser(this.loginUser);
        //DigitalSign =tlu.getDigitalSignature();
       // OfficeId = tlu.getOfficeId();
    } 
//    
//    // Set the Student Login Detail - Done By Praveen Jebathurai
//    /**public final void setStudentLoginUser(admissiontransactionbean.LoginBean tlu) {
//        this.LoginUser = tlu;
//        super.setLoginUser(this.LoginUser);
//        DigitalSign = 3711634;
//        OfficeId = tlu.getLlngOfficeId();
//    }**/
//    
//    // Get the Student Login Detail - Done By Praveen Jebathurai
//    public admissiontransactionbean.AdmissionLoginBean getStudentLoginUser(){
//        return studentLoginUser;
//    }
//    
    public LoginUser getLoginUser(){
        return loginUser;
    }
    
    //Setting the Database Transaction Type
    public final void setTranstype(long typeval){
        this.TransType=typeval;
        //System.out.println("\n\n TRANS TYPE :"+TransType);
    }
    
    public final long getTranstype(){
        return TransType;
    }
    
    //Setting the Transaction Id from the JSP file.
    public final void setTransactionid(long tranid){
        this.llngTransactionid=tranid;
        //System.out.println("\n\n TRANS ID:"+llngTransactionid);
    }
    
    public void setFileMovementToEmployeeid(long empid){
        llngFMtoEmployeeid = empid;
        //System.out.println("FileMovement To Employee id at this stage is"+ llngFMtoEmployeeid);
    }
    
    // Method to get File to employees By Praveen Jebathurai
    public long getFileMovementToEmployeeid(){
        return llngFMtoEmployeeid;
    }
    public void setCopyToEmployees(String val){
        lstrCCEmployeeid = val;
    }
    public void setInterimRemarks(String val){
        lstrInterimRemarks = val;
    }
    
    public void setRejectionReason(String val){
        lstrRejectionReason = val;
        //System.out.println("Rejection Reason at this stage is"+ lstrRejectionReason);
    }
    //This will contain multiple transactionid with employeeid and transferid delimted by "," and "$" SYMBOLS.
    public void setMultipleTransactionIDs(String argStrTransIDs){
        MultipleTransactionIDs = argStrTransIDs;
    }
    
    //This will contain multiple transactionid with employeeid and transferid delimted by "," and "$" SYMBOLS.
    public String  getMultipleTransactionIDs(){
        return MultipleTransactionIDs;
    }
    
    //Getting the Transaction Status for a given TransactionId.
    /**
     * This method is used to know the status of transaction id set.
     * To set <I>transactionId</I> use <B>setTransactionId</B> method
     */
    public final int getTransStatus(){
        try{
            java.sql.Statement stmt=null;
            String que="SELECT status FROM hrd.transactions WHERE transactionid = " + this.llngTransactionid;
            java.sql.ResultSet rs = this.getResultSet(que);
            if (rs.next()){
                return (rs.getInt("status"));
                
            }
            else return 0;
        }
        catch(Exception e){
            return 0;
        }
    }
    
    private final boolean closeTransaction(java.util.Date close_date, transactionStatus ts) throws Exception{
        //System.out.println("present trans id is"+llngTransactionid);
        int ra=ts.id();
        //System.out.println("present Status id is"+ra);
        
        if(this.llngTransactionid > 0 && this.OfficeId > 0){
            try{
                String lstrSql = "UPDATE hrd.transactions SET status = " +
                ts.id() + ",closedate = CURRENT_TIMESTAMP,digitalsignclose=" +
                this.DigitalSign + " WHERE transactionid = " +
                this.llngTransactionid + " AND officeid = " + this.OfficeId;
                this.executeSQL(lstrSql);
                
                /*String lstrSql = "UPDATE hrd.transactions SET status = " +
                    ts.id() + ",closedate = CURRENT TIMESTAMP,digitalsignclose=" +
                    this.DigitalSign + " WHERE transactionid = "+ this.llngTransactionid;
                this.executeSQL(lstrSql);*/
                
               
               /** if (!"".equals(lstrRejectionReason)){
                    lstrSql = "INSERT INTO hrd.rejectionReason(officeid,transactionid," +
                    "employeeid,rejectionReason) VALUES(" + LoginUser.getLlngOfficeId() + "," +
                    llngTransactionid + "," + LoginUser.getStudentId() + ",'" +
                    lstrRejectionReason + "')";
                    this.executeSQL(lstrSql);
                    addDigitalStamp(" hrd.interimCheckRemarks ", llngTransactionid);
                }**/
                return true;
            }
            catch(Exception e){
                return false;
            }
        }else{
            java.sql.SQLException se = new java.sql.SQLException("llngTransactionid OR " +
            " OFFICEID NOT SET, To Update a transaction status u must pass this");
            throw se;
        }
    }
    
   public final long getTransactionId(){
        if (this.getLongTransaction()){
            return this.getLongTransTransactionId();
        }
        try{
           // this.beginTransaction();
            String lstrIpAddress=null;
            String lstrSQL = "SELECT nextval('hrd.seq_transactions') AS NewTranID FROM hrd.seq_transactions";
          //  ResultSet lrs = this.getResultSet(lstrSQL);
            ResultSet lrs = com.test.util.db.fetchData.getResultSet(lstrSQL);
            if (lrs.next()){
                this.llngTransactionid = lrs.getInt("NewTranID");
                System.out.println("New Transactionid is"+llngTransactionid);
//                if (studentLoginUser != null){
//                    if (studentLoginUser.getStudentId() >0){
//                        //System.out.println("\nIN STUDENT TRANSACTION START");
//                        lstrSQL = "INSERT INTO deemucty.studenttransaction (studentid, transactionid) values ("+
//                        " " + studentLoginUser.getStudentId() + "," + this.llngTransactionid + ")";
//                        this.executeSQL(lstrSQL);
//                        lstrIpAddress = studentLoginUser.getIPAddress();
//                    }
//                } else {
//                    lstrIpAddress = LoginUser.getIpAddress();
//                }
                lstrSQL = "INSERT INTO hrd.transactions(transactionId,officeid," +
                "digitalsignopen,transtypeid,opendate,status,ipaddress) VALUES(" +
                this.llngTransactionid + "," + this.OfficeId + "," +
                this.DigitalSign + "," + this.TransType + ",current_timestamp," +
                transactionStatus.DataEntered.id() + ",'" + lstrIpAddress + "')";
                System.out.println("New Transactionid genaration query is"+lstrSQL);
                this.executeSQL(lstrSQL);
               
              
            }
           
        }catch(Exception te){
            System.out.println("Sequence TransactionId Generation ID:" + te);
           // this.rollbackTransaction();
            this.llngTransactionid = 0;
        }
        return(this.llngTransactionid);
    }
   
   
   public final long getTransactionId(long officeId){
       
       
       OfficeId = officeId ;
        if (this.getLongTransaction()){
            return this.getLongTransTransactionId();
        }
        try{
            String lstrIpAddress=null;
            String lstrSQL = "SELECT nextval('hrd.seq_transactions') AS NewTranID FROM hrd.seq_transactions";
            ResultSet lrs = this.getResultSet(lstrSQL);
            if (lrs.next()){
                this.llngTransactionid = lrs.getInt("NewTranID");
                //System.out.println("New Transactionid is"+llngTransactionid);
//                if (studentLoginUser != null){
//                    if (studentLoginUser.getStudentId() >0){
//                        //System.out.println("\nIN STUDENT TRANSACTION START");
//                        lstrSQL = "INSERT INTO deemucty.studenttransaction (studentid, transactionid) values ("+
//                        " " + studentLoginUser.getStudentId() + "," + this.llngTransactionid + ")";
//                        this.executeSQL(lstrSQL);
//                        lstrIpAddress = studentLoginUser.getIPAddress();
//                    }
//                } else {
//                    lstrIpAddress = LoginUser.getIpAddress();
//                }
                lstrSQL = "INSERT INTO hrd.transactions(transactionId,officeid," +
                "digitalsignopen,transtypeid,opendate,status,ipaddress) VALUES(" +
                this.llngTransactionid + "," + officeId + "," +
                this.DigitalSign + "," + this.TransType + ",current_timestamp," +
                transactionStatus.DataEntered.id() + ",'" + lstrIpAddress + "')";
                //System.out.println("New Transactionid genaration query is"+lstrSQL);
                this.executeSQL(lstrSQL);
              
            }
        }catch(Exception te){
            System.out.println("Sequence TransactionId Generation ID:" + te);
            this.rollbackTransaction();
            this.llngTransactionid = 0;
        }
        return(this.llngTransactionid);
    }
   
    
    //TRANSACTION ID FOR LONG TIME RUNNING TRANSACTIONS
    public final long getLongTransTransactionId(){
        try{
            String lstrIpAddress=null;
            String lstrSQL = "SELECT nextval('hrd.seq_transactions') AS NewTranID FROM hrd.seq_transactions";
            ResultSet lrs = this.getResultSet(lstrSQL);
            if (lrs.next()){
                this.llngTransactionid = lrs.getInt("NewTranID");
                //lstrIpAddress = LoginUser.getIpAddress();
                lstrSQL = "INSERT INTO hrd.longtransactions(transactionId,officeid," +
                "digitalsignopen,transtypeid,opendate,status,ipaddress) VALUES(" +
                this.llngTransactionid + "," + this.OfficeId + "," +
                this.DigitalSign + "," + this.TransType + ",current_timestamp," +
                transactionStatus.DataEntered.id() + ",'" + lstrIpAddress + "')";
                //System.out.println("New Transactionid genaration query is"+lstrSQL);
                this.executeSQL(lstrSQL);
               
            }
            
        }catch(Exception te){
            System.out.println("Sequence TransactionId Generation ID:" + te);
            this.rollbackTransaction();
            this.llngTransactionid = 0;
        }
        return(this.llngTransactionid);
    }
    
   
    
   
    
   
    
    /* Current supported values are 1 and 9 respectively approval and rejection
     */
    public void setApprovalStatus(int argIntStatusValue){
        //System.out.println("\n\n Approval Status:"+argIntStatusValue);
        if (argIntStatusValue == transactionStatus.Approved.id()){
            curStatus = transactionStatus.Approved;
            //System.out.println("\n\n Approval Status:"+argIntStatusValue);
        }else{
            if (argIntStatusValue == transactionStatus.Rejected.id()){
                curStatus = transactionStatus.Rejected;
            }
        }
    }
    
    
    /** Getting the current Status is 1 or 9 respect to approval or reject
     * By Senthil 1-07-04 */
    public int getApprovalStatus(){
        return curStatus.id();
    }
    
    /* Pass the transactionIDs in a string with the comoa delimeted string
     */
    public void setApprovalTransIDs(String argStrTransIDs){
        transactionIDs = argStrTransIDs;
    }
    
    public String getApprovalTransIds(){
        return transactionIDs;
    }
    /**
     * This function called to <B>close</B> the transactions,
     * for example the opened transaction <I>accepted or rejected</I>.
     * Before calling this function you must set the <U>SlidingloginUser</U> object lu
     * and <U>transactionId</U> using <B>setLoginUser</B> and <B>setTransactionId</B> methods.
     * Trans table name must be supplied with respective schema mame
     * transactionId are comoa delimited values
     */
    public boolean closeTransactions(String transTableName){
        StringTokenizer st = new StringTokenizer(transactionIDs, ",");
        String strCurrentId = "";
        java.util.Date dt = new java.util.Date();
        long lngZero = 0;
        long lngCurrentTransId=0;
        boolean blnResult = false;
        try{
            while(st.hasMoreTokens()){
                strCurrentId = st.nextToken();
                lngCurrentTransId = Long.parseLong(strCurrentId);
                //System.out.println("current trans id is"+lngCurrentTransId);
                if (lngCurrentTransId > lngZero){
                    setTransactionid(lngCurrentTransId);
                    blnResult = closeTransaction(dt, curStatus);
                    //System.out.println("successfully updated filemovement table in close transaction"+blnResult);
                    if (blnResult){
                        blnResult = closeTransTableStatus(transTableName);
                    }
                }
                if(!blnResult){
                    return false;
                }
            }
        }catch(Exception e){
            System.out.println(e.toString());
            return false;
        }
        return blnResult;
    }
    
    public boolean closeTransTableStatus(String transTableName) throws Exception{
        if(this.llngTransactionid > 0 && this.OfficeId >= 0){
            try{
/*                String lstrSql = "UPDATE " + transTableName + " SET activestatus = " +
                    curStatus.id() + " WHERE transactionid = " +
                    this.llngTransactionid + " AND officeid = " + this.OfficeId;
 */
                String lstrSql = "UPDATE " + transTableName + " SET activestatus = " +
                curStatus.id() + " WHERE transactionid = "+this.llngTransactionid;
                
                this.executeSQL(lstrSql);
                return true;
            }
            catch(Exception e){
                return false;
            }
        }else{
            java.sql.SQLException se = new java.sql.SQLException("llngTransactionid OR " +
            " OFFICEID NOT SET, To Update a transaction status u must pass this");
            throw se;
        }
    }
    
    public boolean closeTransactionsHavingSameTransactionId(String transTableName){
        StringTokenizer st = new StringTokenizer(MultipleTransactionIDs, ",");
        String strCurrentId = "";
        String oneSetRecord="";
        String EmployeeId="";
        String TransferId="";
        java.util.Date dt = new java.util.Date();
        long lngZero = 0;
        long lngCurrentTransId=0;
        boolean blnResult = false;
        try{
            while(st.hasMoreTokens()){
                oneSetRecord=st.nextToken();
                StringTokenizer st2 = new StringTokenizer(oneSetRecord, "$");
                while(st2.hasMoreTokens()){
                    strCurrentId = st2.nextToken();
                    EmployeeId=st2.nextToken();
                    TransferId=st2.nextToken();
                    st2.nextToken();
                    lngCurrentTransId = Long.parseLong(strCurrentId);
                    if (lngCurrentTransId > lngZero){
                        setTransactionid(lngCurrentTransId);
                        blnResult = closeTransaction(dt, curStatus);
                        if (blnResult){
                            blnResult = closeTransTableStatusHavingSameTransactionId(transTableName,EmployeeId,TransferId);
                        }
                    }
                    if(!blnResult){
                        return false;
                    }
                }//Sub While
            } //Main While
        }catch(Exception e){
            System.out.println(e.toString());
            return false;
        }
        return blnResult;
    }
    
    //This is used for multiple record having same transactionid eg, in Transfer table it save multiple record with same transactionid
    public boolean closeTransTableStatusHavingSameTransactionId(String transTableName,String EmployeeId,String TransferId) throws Exception{
        if(this.llngTransactionid > 0 && this.OfficeId > 0){
            try{
                String lstrSql = "UPDATE " + transTableName + " SET activestatus = " +
                    curStatus.id() + " WHERE transactionid = " +
                    this.llngTransactionid + " AND officeid = " + this.OfficeId +
                    " AND transferid="+Long.parseLong(TransferId) + " AND Employeeid=" + 
                    Long.parseLong(EmployeeId);
                //System.out.println(lstrSql);
                this.executeSQL(lstrSql);
                return true;
            }
            catch(Exception e){
                return false;
            }
        }else{
            java.sql.SQLException se = new java.sql.SQLException("llngTransactionid OR " +
            " OFFICEID NOT SET, To Update a transaction status u must pass this");
            throw se;
        }
    }
   
   
}