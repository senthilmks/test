/*
 * DbConnectionManager.java
 *
 * Created on August 30, 2018, 4:29 PM
 */
package com.test.util.db;

import java.util.Vector;
 
/**
 *
 * @author Murali.S
 */ 
public class DbConnectionManager {
    private static com.test.util.db.DbConnection queryCon = 
            new com.test.util.db.DbConnection("TestingQuery", 
            0, /* Start up Connection*/
            0, /* Min conn*/
            600, /* Max Conn*/
            30000, /* Connection to close in millsec if it is not in use  */
            45000 /* Monitor Time to close DB  not in use connections*/
            ); 
    private static com.test.util.db.DbConnection transCon = new com.test.util.db.DbConnection("TestingTrans Query",0,0,600,30000,45000);
    private static com.test.util.db.DbConnection reportCon = new com.test.util.db.DbConnection("TestingReport Query",0,0,280,30000,45000);
     
    
     /* Query */
    public static  synchronized  PooledConnection getConnectionForQuery(String arg) {
        return queryCon.getConnection(arg);        
    }
    
     /* Transaction */
    public static synchronized PooledConnection getConnectionForTransaction(java.lang.Object obj){        
        return transCon.getConnection(obj);        
    }
    
    /* Long Transactions */    
    public static synchronized PooledConnection getConnectionForLongTransaction(java.lang.Object obj){        
        return transCon.getConnection(obj);
        //return longTransCon.getConnection(obj);
    }

      /* Report */
    public static synchronized PooledConnection getConnectionForReport(String arg){
        return reportCon.getConnection(arg);        
    }
    
    public static Vector getAllQueryConnections(){return queryCon.getAllConnections();}  
    public static Vector getAllTransactionConnections(){return transCon.getAllConnections(); }    
    public static Vector getAllReportConnections(){return reportCon.getAllConnections();}
     
    public static long getWaitingQueryCount(){return queryCon.getWaitCount();}    
    public static long getWaitingTranscationQueryCount(){return transCon.getWaitCount();}    
    public static long getWaitingReportQueryCount(){return reportCon.getWaitCount();}    
    
//    public static Vector getWaitingQueryList(){return queryCon.getWaitListQuery();}    
//    public static Vector getWaitingTranscationQueryList(){return transCon.getWaitListQuery();}    
//    public static Vector getWaitingReportQueryList(){return reportCon.getWaitListQuery();}   
    
    
    public static com.test.util.db.DbConnection getQueryObject(){return queryCon;}    
    public static com.test.util.db.DbConnection getTranscationObject(){return transCon;}    
    public static com.test.util.db.DbConnection getReportObject(){return reportCon;}     
    
    
    
}//Class End
