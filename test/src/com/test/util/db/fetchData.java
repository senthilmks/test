/*
 * fetchData.java
 *
 * Created on August 30, 2018, 4:29 PM
 */
package com.test.util.db;

import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/** 
 * @author  Murali.S
 */
public class fetchData  {     

    public fetchData() {
    }        

     public static byte[] getPDFReportAsBytes(String sJasperNamewithPath,Map Paramaters,String ReportName)throws Exception {
        com.test.util.db.fetchDataPDF pdf= new com.test.util.db.fetchDataPDF();
        return pdf.getReportAsBytes(DbConnectionManager.getConnectionForReport(ReportName),Paramaters,sJasperNamewithPath); 
    } 

    public static java.sql.ResultSet getResultSet(String argSql ,ArrayList<Object> queryParametersList)  
        throws java.sql.SQLException
    {
        com.test.util.db.ResultSetExtented ers = new com.test.util.db.ResultSetExtented();
        return ers.getResultSet(com.test.util.db.DbConnectionManager.getConnectionForQuery(argSql), argSql,queryParametersList);
    }
    public static  PooledConnection getConnectionForQuery(String argSql) {
        return com.test.util.db.DbConnectionManager.getConnectionForQuery(argSql);
    }
//     public static  PooledConnection getConnectionForQuery(String argSql) {
//        return com.test.dao.DbConnectionManager.getConnectionForTransaction(argSql);
////        return null;
//    }
    public static long getRecordCount(java.sql.ResultSet argRs){
        long recCount = 0;
        try{
            argRs.last();
            recCount = argRs.getRow();  
            argRs.beforeFirst();
            return recCount;
        }catch(Exception E){
            System.err.println(E);
            return 0;
        }
    }
}//Class End