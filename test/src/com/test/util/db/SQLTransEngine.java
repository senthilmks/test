/* SQLEngine.java
 * Created on April 20, 2004, 12:20 PM
 * @author  Vinoth. S */
package com.test.util.db;

//import sm.dao.loginUserDao;
import com.test.util.db.*;
import java.sql.*;
import java.beans.*;
import java.util.Vector;
import com.test.util.db.PooledConnection; 

/*  Please Read This Carefully
 *  All database INSERT, UPDATE, DELETE Must be done using this SQLTransEngine
 *  class only
 *  This class takes care of Begin Transaction and End Transaction
 *  U must create object for this class whenever you whant to do a particular
 *  transaction (For set of sql comand execution) one time *
 *  Author : Vinoth. S
 *  Date : 18/April/2004
 **/
public class SQLTransEngine extends Object implements java.io.Serializable {
    private String DBPassword;
    private boolean blnConnectionStatus;
    private boolean blnLongTransCon = false;
    private String lstrErrorText = "";
    
    private java.util.Vector vectExecutedSQL;
    private com.test.util.db.PooledConnection econ;
    private java.sql.Connection tcon;
    private java.sql.Statement tstmt;
    //private admissiontransactionbean.LoginBean LoginUser = null;
    //private studenttransaction.studentLoginBean studentLoginUser=null; // By Praveen
     private com.test.LoginUser loginUser = null; 
    long lngStatementExecutedCount;
    private Exception eObj;

    public Vector getVectExecutedSQL() {
        return vectExecutedSQL; 
    }

    public void setVectExecutedSQL(Vector vectExecutedSQL) {
        this.vectExecutedSQL = vectExecutedSQL; 
    }
    
    //set the loginUser details - this is usaully set by transactions.transactions bean
    //public void setLoginUser(admissiontransactionbean.LoginBean lobj){
       // LoginUser = lobj;
   // }
    
    // Set the Student Login Details - By Praveen Jebathurai
   /** public void setStudentLoginUser(studenttransaction.studentLoginBean slu){
        this.studentLoginUser=slu;
        this.LoginUser=slu;
    }**/
    
     //set the loginUser details - this is usaully set by transactions.transactions bean
    public void setLoginUser(com.test.LoginUser lobj){ //by kanaga
        loginUser = lobj;
    }
    
    public String getErrorText(){return lstrErrorText;}
    
    //some time programmers may need to set the connection mannualy. this achieved by this function
    // modified by vinoth, S dt. 16-May-2005
    
    public void setConnection(java.sql.Connection c){
        tcon = c;
        blnConnectionStatus = true;
    }
    
    /*  Please read the following carefully
     *  All database INSERT, UPDATE, DELETE Must be done using this SQLTransEngine
     *  class only
     *  This class takes care of Begin Transaction and End Transaction
     *  U must create object for this class when you to do a database
     *  transaction (For set of sql statments execution) at a time
     *  Author : Vinoth. S
     *  Date : 18/April/2004
     **/
    public void SQLTransEngine(){
        try{
            blnConnectionStatus = false;                                                                  
            DBPassword = "myactualpassword";
            lngStatementExecutedCount = 0;
            //System.out.println("Inside of SQLTransEngine's Constructor!");
        }catch(Exception e){
            System.out.println("------------------------Error!! SQLTransEngine's Constructor!");
            blnConnectionStatus = false;
        }
    }
    
    public java.sql.Connection getConnection(){ return tcon; }
    
    /* Before starting then SQL trans (i.e any INSERT, UPDATE, DELETE) call
     * this method one time.
     * if some transaction is pending before then this method ends it
     * automatically!
     * Author Vinoth. s
     * Date : 18/May/2004
     */

    public void setLongTransaction(boolean value){
        blnLongTransCon = value;
    }
    
    public boolean getLongTransaction(){
        return blnLongTransCon;
    }
    
    public boolean beginTransaction(){
        if(blnConnectionStatus && lngStatementExecutedCount > 0){
            if(endTransaction() == false){return false;}
        }
        try{
            if (!blnLongTransCon){
                econ = com.test.util.db.DbConnectionManager.getConnectionForTransaction(this);
            }else{
                //System.out.println("calling connection for long Transactions");
                econ = com.test.util.db.DbConnectionManager.getConnectionForLongTransaction(this);
            }
            tcon = econ.econ;
            tstmt = tcon.createStatement();
            vectExecutedSQL = new Vector(5);
            blnConnectionStatus = true;
            tcon.setAutoCommit(false);
            lngStatementExecutedCount = 0;
            return true;
        }catch(Exception e){
            System.out.println("\n\n Error at Begin Trans "+e);
            return false;
        }
    }
    
    /* this procedure retunr the ready status of SQLTransEngine
     * By checking this you can come to an idea that you can execute your
     * Statement or Not.
     * Author : Vinoth. S
     * Date : 18/April/2004
     **/
    public boolean getConnectionStatus(){return blnConnectionStatus;}
    
    public boolean executeSQL(String argSQL){
        if (blnConnectionStatus){
            try{
                //System.out.println("SQL to be executed:" + argSQL);
                econ.setObjectInfo(this.getClass() + " " + this.toString());
                econ.setSQLString(argSQL);
                tstmt.executeUpdate(argSQL);
                //System.out.println(".............STATEMENT ");
                lngStatementExecutedCount = lngStatementExecutedCount + 1;
                //System.out.println(".............COUNT ");
                vectExecutedSQL.addElement(argSQL);
                //System.out.println(".............Add Element ");
                //System.out.println("Success:-- " + argSQL);
                return true;
            }
            catch(SQLException e){
                eObj =e;
                System.out.println("//%%% SQL Trans Engine Report: Exception occurred, & Transaction Rolled Back! %%%\n" + e);
                System.out.println("//%  "+ argSQL + "%//");
                lstrErrorText = ""+e;
                SQLException iose = e;
                while(iose != null){
                    if (iose.getErrorCode() == 0 ) {
                        return false;
                    }
//                    System.out.println("//%SQLState: "+iose.getSQLState());
//                    System.out.println("//%Error Cd: "+iose.getErrorCode());
//                    System.out.println("//%Message : "+iose.getMessage());
//                    System.out.println("//%Exce  : "+e);
                    lstrErrorText += "\n" + iose.getSQLState() + " " + iose.getErrorCode() + " " + iose.getMessage();
                    iose = e.getNextException();
                }
//                System.out.println("//%%% End of Report.");
                return false;
            }catch(Exception ge){
                System.out.println(ge);
                return false;
            }
        }else{
            System.out.println("Error at execution of trans Reason: beginTransaction method not called!"+" SIGAMANI");
            return false;
        }
    }
    public int executeRowsSQL(String argSQL){ 
           int rowcount=0;
        if (blnConnectionStatus){
            try{ 
                //System.out.println("SQL to be executed:" + argSQL);
                 econ.setSQLString(argSQL);
              rowcount=  tstmt.executeUpdate(argSQL);
                //System.out.println(".............STATEMENT ");
                lngStatementExecutedCount = lngStatementExecutedCount + 1;
                //System.out.println(".............COUNT ");
                vectExecutedSQL.addElement(argSQL);
                //System.out.println(".............Add Element ");
                //System.out.println("Success:-- " + argSQL);
                return rowcount;
            }
            catch(SQLException e){
                eObj =e;
//                System.out.println("//%%% SQL Trans Engine Report: Exception occurred, & Transaction Rolled Back! %%%\n" + e);
//                System.out.println("//%  "+ argSQL + "%//");
                lstrErrorText = ""+e;
                SQLException iose = e;
                while(iose != null){
                    if (iose.getErrorCode() == 0 ) {
                        return rowcount;
                    }
//                    System.out.println("//%SQLState: "+iose.getSQLState());
//                    System.out.println("//%Error Cd: "+iose.getErrorCode());
//                    System.out.println("//%Message : "+iose.getMessage());
//                    System.out.println("//%Exce  : "+e);
                    lstrErrorText += "\n" + iose.getSQLState() + " " + iose.getErrorCode() + " " + iose.getMessage();
                    iose = e.getNextException();
                }
//                System.out.println("//%%% End of Report.");
                return rowcount;
            }catch(Exception ge){
                System.out.println(ge);
                return rowcount;
            }
        }else{
            System.out.println("Error at execution of trans Reason: beginTransaction method not called!");
            return rowcount;
        }
    }
    
    /*   call this method once you copleted the SQL trans on database
     *   so that this method ends your current transaction, to start another
     *   transaction call beginTransaction method
     */
    public boolean endTransaction(){
        if(blnConnectionStatus){
            try{
                //Ok, This is time to store data actually!
                //sm.srm.transactions.DBScripter.pleaseStoreMe(vectExecutedSQL, 1, "");
                tcon.commit();
                blnConnectionStatus = false;
                econ.releaseConnection();
                return true;
            }catch(Exception e){
                System.out.println("System ROLL BACKING the transaction Place I!");
                try{tcon.rollback();}catch(Exception re){}
                return false;
            }
        }else{
            System.out.println("System ROLL BACKING the transaction Place II!");
            try{tcon.rollback();}catch(Exception re){}
            return false;
        }
    }
    
    public boolean rollbackTransaction(){
        if(blnConnectionStatus){
            try{
                blnConnectionStatus = false;
                System.out.println("System ROLL BACKING the transaction Place I!");
                //sm.srm.transactions.DBScripter.pleaseStoreMe(vectExecutedSQL, 2, lstrErrorText);
                try{tcon.rollback();}catch(Exception re){}
                econ.releaseConnection();
                return true;
            }catch(Exception e){
                return false;
            }
        }else{
            System.out.println("System ROLL BACKING the transaction Place II!");
            try{tcon.rollback();}catch(Exception re){}
            return false;
        }
    }
    
    public java.sql.ResultSet getResultSet(String argSql)
        throws java.sql.SQLException{
        try{
            java.sql.Statement lst;
            java.sql.ResultSet lrs;
            lst = tcon.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            lrs = lst.executeQuery(argSql);
            return lrs;
        }catch(java.sql.SQLException se){
            //Process is catching the error and
            //throw it again.
            System.out.println("Error: Function getResultSet " +argSql);
            SQLException iose = se;
            while(iose != null){
                System.out.println(iose.getSQLState());
                System.out.println(iose.getErrorCode());
                System.out.println(iose.getMessage());
                iose = se.getNextException();
            }
            throw se;
        }
    }
    
    public final boolean addDigitalStamp(String tablenameWithSchema, long transactionsid){
        return true;
        //DigitalStamping
        //System.out.println("Digital Stamp called " + tablenameWithSchema + " " + transactionsid);
        /*boolean blnResult=false;
        try{
            String lstrDSable = "";
            java.sql.ResultSet lrs = getResultSet("SELECT t.* FROM " + tablenameWithSchema +
            " AS T WHERE transactionid = " + transactionsid);
            java.sql.ResultSetMetaData rsmd = lrs.getMetaData();
            while (lrs.next()){
                lstrDSable = "" + this.LoginUser.getOfficeSample();
                for (int col=1; col < rsmd.getColumnCount(); col++){
                    if (!"DIGITALSTAMP".equals(rsmd.getCatalogName(col).toUpperCase())){
                        lstrDSable = lstrDSable + lrs.getString(col);
                    }
                }
                //System.out.print("FROM BEAN : "+transactionsid);
                // By Praveen*/
                /* For DB2
                 blnResult=executeSQL("UPDATE " + tablenameWithSchema + " SET digitalstamp = left('" +
                    lstrDSable +"',32) WHERE transactionid = " + transactionsid);*/
                // For Postgres SQL
                /*blnResult=executeSQL("UPDATE " + tablenameWithSchema + " SET digitalstamp = MD5(MD5(MD5('" +
                lstrDSable +"'))) WHERE transactionid = " + transactionsid);
                // End By Praveen Jebathurai
                lstrDSable = "";
            }
            //if (blnResult)
                //System.out.println("DIGITAL Add Successfully");
            return blnResult;
        }catch(Exception e){
            System.out.println("Digital Stamping Error" + e);
            return false;
        }*/
    }
    
    
    //Method to make digitalstamp by using orgtransactionid
    // Feb25 2005 Mahamood Ahmed
    public final boolean addDigitalStampByOrgTransactionId(String tablenameWithSchema, long transactionsid){        
        //DigitalStamping        
        return true;
        /*
        try{
            String lstrDSable = "";
            String lstrOid = "";
            java.sql.ResultSet lrs = getResultSet("SELECT t.*, oid FROM " + tablenameWithSchema +
            " AS T WHERE officeid = " + this.LoginUser.getOfficeId() +
            " AND orgtransactionid = " + transactionsid);
            java.sql.ResultSetMetaData rsmd = lrs.getMetaData();
            while (lrs.next()){
                lstrDSable = "" + this.LoginUser.getOfficeSample();
                for (int col=1; col < rsmd.getColumnCount(); col++){
                    if (!"DIGITALSTAMP".equals(rsmd.getCatalogName(col).toUpperCase())){
                        lstrDSable = lstrDSable + lrs.getString(col);
                    }
                }
                lstrOid = lrs.getString("oid");
                //System.out.println(lstrDSable);
                executeSQL("UPDATE " + tablenameWithSchema + " SET digitalstamp = md5(md5('" +
                lstrDSable +"')) WHERE oid = " + lstrOid + " AND officeid = " +
                this.LoginUser.getOfficeId() + " AND orgtransactionid = " + transactionsid);
                lstrDSable = "";
            }
            return true;
        }catch(Exception e){
            System.out.println("Digital Stamping Error" + e);
            return false;
        }*/
    }
    
    
    protected void finalize(){
        //if connection not closed then closing it
        try{
            if(blnConnectionStatus){
                try{tcon.rollback();}catch(Exception re){}
                tstmt.close();
                econ.releaseConnection();
            }
        }catch(Exception e){}
    }
    
    public Exception getSQLException(){
        return eObj;
    }

//    void setLoginUser(loginUserDao loginUserDao) {
//        throw new UnsupportedOperationException("Not yet implemented");
//    }
}//Class End