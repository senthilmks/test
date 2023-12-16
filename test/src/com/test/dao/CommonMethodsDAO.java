/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.dao;

import com.test.JSONCommandObject;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.StringTokenizer;
import net.sf.json.JSONObject;

/**
 *
 * @author admin
 */
//public class CommonMethodsDAO extends com.test.util.db.transactions  {
public class CommonMethodsDAO extends JSONCommandObject {

    boolean resultStatus = true;

    public ResultSet getOffices() {
        ResultSet rs = null;
        ArrayList<Object> queryParametersList = null;
        String query = "";
        try {
            query = "select officeid, officename from hrd.offices";
            rs = com.test.util.db.fetchData.getResultSet(query,queryParametersList);
        } catch (Exception e) {
            System.out.println("UserMenuRightsDao = " + e);
        }
        return rs;
    }

    public String saveHostel() {
        JSONObject objResultJSON = new JSONObject();
        boolean blnStatus = false;
        try {

            blnStatus = this.beginTransaction();

            System.out.println("hdnEmpId >>" + getRequest().getParameter("hdnEmpId") );
            String query = "insert into hostel.hostelwisestaffrights (hostelid,employeeid) values (1," + getRequest().getParameter("hdnEmpId") + ")";
//                    System.out.println("query2 = " + query);
            blnStatus = this.executeSQL(query);
            if (blnStatus) {
                this.endTransaction();
                objResultJSON.put("resultstatus", "1");
                result = "Saved successfully";
                objResultJSON.put("result", result);
                objResultJSON.put("resultclass", RESULTSUCCESS);
            } else {
                this.rollbackTransaction();
                objResultJSON.put("resultstatus", "0");
                result = "Save failed ";
                objResultJSON.put("result", result);
                objResultJSON.put("resultclass", RESULTERROR);
            }

//            blnStatus = this.beginTransaction();
//            String query = "delete from hostel.hostelwisestaffrights where employeeid = " + emp;
//
////            System.out.println("query1 = " + query);
//            blnStatus = this.executeSQL(query);
//            if (!blnStatus) {
//                this.rollbackTransaction();
//                return false;
//            }
//            if (s == null) {
//                if (blnStatus) {
//                    this.endTransaction();
//                } else {
//                    this.rollbackTransaction();
//                }
//            } else {
//                StringTokenizer st = new StringTokenizer(s, ",");
//                while (st.hasMoreElements()) {
//                    blnStatus = this.beginTransaction();
//                    String hostelid = st.nextToken();
////                    System.out.println("menu=" + hostelid);
//                    query = "insert into hostel.hostelwisestaffrights (hostelid,employeeid) values (" + hostelid + "," + emp + ")";
////                    System.out.println("query2 = " + query);
//                    blnStatus = this.executeSQL(query);
//                    if (blnStatus) {
//                        this.endTransaction();
//                    } else {
//                        this.rollbackTransaction();
//                    }
//                }
//            }
        } catch (Exception e) {
            this.rollbackTransaction();
            System.out.println("" + e);
             objResultJSON.put("resultstatus", "0");
                result = "Save failed ";
                objResultJSON.put("result", result);
                objResultJSON.put("resultclass", RESULTERROR);
        }
        return objResultJSON.toString().trim();
    }

}
