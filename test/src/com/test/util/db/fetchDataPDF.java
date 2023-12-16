/*
 * fetchDataPDF.java
 *
 * Created on August 30, 2018, 4:29 PM
 */
package com.test.util.db;

import java.util.Map;
import net.sf.jasperreports.engine.*;
 
/**
 *
 * @author Murali.S
 */
public class fetchDataPDF {
    byte[] getReportAsBytes(com.test.util.db.PooledConnection argConExt, Map Paramaters,String pdfNamewithPath){ 
         byte[] bytes =null;
        try {
              bytes=JasperRunManager.runReportToPdf(
                pdfNamewithPath,
                Paramaters,
                argConExt.econ);
        } catch (Exception e) {
             System.err.println("Error: Function getReportAsBytes " +e.toString()); 
        }finally{
            if(argConExt!=null){
                argConExt.releaseConnection();
            }
        } 
        return bytes;
    }
}//Class End
