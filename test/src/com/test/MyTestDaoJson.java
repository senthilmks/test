/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 *
 * @author Administrator
 */

public class MyTestDaoJson extends JSONCommandObject{

    PreparedStatement pst = null;
    ResultSet rs = null;
    Connection dbcon = null;
    SQLDBConnection sQLDBConnection = new SQLDBConnection();
    
//    HashMap resultData = new HashMap();
//    private HttpServletRequest request;
//    String filter="";    
//     public void setRequest(HttpServletRequest request) {
//        this.request = request;
//        try{
//            if(request.getParameter("filterval")!=null){
//                this.filter = request.getParameter("filterval").trim().toUpperCase();
//            }
//        }
//        catch (Exception e)
//        {
//            this.filter="";
//        }       
//    }

    public synchronized ResultSet findSampleUsers() throws Exception {
        String sqlQry = "";
        try {
            dbcon = sQLDBConnection.getConnection();
            //sqlQry="SELECT EMPID,NAME,PW,EMPTYPE,DEPARTMENT,FACULTYID,ISACTIVE,ISDELETED,EMPROLE FROM TBLEMPLOYEEMASTER WHERE TO_NUMBER(EMPID)=TO_NUMBER(?)";
            //sqlQry = "SELECT EMP_NO FROM EMPORG WHERE upper(EMP_NAME) like UPPER('%"+getRequest().getParameter("filterval")+"%')";                  
            sqlQry = "SELECT EMP_NO,EMP_NAME FROM EMPORG WHERE CAST(EMP_NO AS INT)in (222023,222024,222025,222026,222027,222028,222029,222030) ";
//            System.out.println("MyTestDao Uname >> " + request.getParameter("uname"));
//            System.out.println("MyTestDao Uname >> " + request.getParameter("filterval"));
            //System.out.println("CLIB : in LoginCDAO loginID >> "+loginID);
            pst = dbcon.prepareStatement(sqlQry);
            //	pst.setString(1,tarTable);

            rs = pst.executeQuery();

//            if (rs.next()) {
//                logUserInfoBDO.setLogUserID(Services.setEmptyIfNull(rs.getString(1)));
//                //	System.out.println("CLIB : in LoginCDAO getLogUserID >> "+logUserInfoBDO.getLogUserID());
//                logUserInfoBDO.setLogUserName(Services.setEmptyIfNull(rs.getString(2)));
//                logUserInfoBDO.setLogPass(Services.setEmptyIfNull(rs.getString(3)));
//                logUserInfoBDO.setLogType(Services.setEmptyIfNull(rs.getString(4)));
//                logUserInfoBDO.setLogDeptID(Services.setEmptyIfNull(rs.getString(5)));
//                logUserInfoBDO.setFacultyID(Services.setEmptyIfNull(rs.getString(6)));
//                logUserInfoBDO.setIsActive(Services.setEmptyIfNull(rs.getString(7)));
//                //System.out.println("CLIB : in LoginCDAO getIsActiven >> "+logUserInfoBDO.getIsActive());
//                logUserInfoBDO.setIsDeleted(Services.setEmptyIfNull(rs.getString(8)));
//                logUserInfoBDO.setRole(Services.setEmptyIfNull(rs.getString(9)));
//                logUserInfoBDO.setDeptName(Services.setEmptyIfNull(rs.getString(10)));
//            } else {
//                // System.out.println("CLIB : in LoginCDAO sqlQry >> "+sqlQry);
//                logUserInfoBDO = null;
//            }
//            rs.close();
//            pst.close();
//            dbcon.close();
//            rs = null;
//            pst = null;
//            dbcon = null;
        } catch (SQLException e) {
//            //System.out.println("CLIB : Exception in LoginCDAO >> "+e.getMessage());
//            errMsg = "Exception occured in <br> Class: " + this.getClass().getName() + " <br> Method: " + Services.getCurrentMethodName() + "The error message is " + e;
//            throw new CLibUserException(errMsg);
        } catch (Exception e) {
            
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (dbcon != null) {
                dbcon.close();
            }
            rs = null;
            pst = null;
            dbcon = null;
        } finally {
//            try {
//                if (rs != null) {
//                    rs.close();
//                }
//                if (pst != null) {
//                    pst.close();
//                }
//                if (dbcon != null) {
//                    dbcon.close();
//                }
//            } catch (SQLException e) {
//                //System.out.println("CLIB : Exception in LoginCDAO >> "+e.getMessage());
//                errMsg = "Exception occured in <br> Class: " + this.getClass().getName() + " <br> Method: " + Services.getCurrentMethodName() + "The error message is " + e;
//                throw new CLibUserException(errMsg);
//            } catch (Exception e) {
//                //System.out.println("CLIB : Exception in LoginCDAO >> "+e.getMessage());
//                errMsg = "Exception occured in <br> Class: " + this.getClass().getName() + " <br> Method: " + Services.getCurrentMethodName() + "The error message is " + e;
//                throw new CLibUserException(errMsg);
//            }

        }
        return rs;
    }

    public String findUniqueUserJson() throws Exception {
        String sqlQry = "";
        JSONObject objResultJSON = new JSONObject();
        JSONArray arrResultDataJson = new JSONArray();
        try {
            dbcon = sQLDBConnection.getConnection();            
            sqlQry = "SELECT EMP_NO,EMP_NAME FROM EMPORG WHERE CAST(EMP_NO AS INT)in (222023,222024,222025,222026) ";
            pst = dbcon.prepareStatement(sqlQry);
            rs = pst.executeQuery();
            
            int slno = 1; 
            ResultSetMetaData md = rs.getMetaData();
            int columns = md.getColumnCount();
            try {
                while (rs.next()) {
                    JSONArray tempDataJsonArr = new JSONArray();
                    tempDataJsonArr.add(String.valueOf(slno));
                    int i = 1;
                    while (i <= columns) {
                        tempDataJsonArr.add(rs.getString(i));
                        i++;
                    }
                    arrResultDataJson.add(tempDataJsonArr);
                    slno++;
                }
            } catch (Exception e) {
                System.out.println("Exception occured");
            }

        } catch (SQLException e) {
            System.out.println("CLIB : Exception in MyTestDAOJson "+e.getMessage());

        } catch (Exception e) {
            System.out.println("CLIB : Exception in MyTestDAOJson >> " + e.getMessage());
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (dbcon != null) {
                dbcon.close();
            }
            rs = null;
            pst = null;
            dbcon = null;
        } finally {
//            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (dbcon != null) {
                    dbcon.close();
                }
        }
        objResultJSON.put("result", arrResultDataJson);
        return objResultJSON.toString();
    }
    
   public String saveUser() throws Exception {
       System.out.println("saveUser called");
        String sqlQry = "";
        boolean resultStatus=true;        
        JSONObject objResultJSON = new JSONObject();
        if(resultStatus)
        {
            objResultJSON.put("resultstatus", "1");
            result="Saved successfully";
            objResultJSON.put("result", result);
            objResultJSON.put("resultclass", RESULTSUCCESS);
        }
        return objResultJSON.toString().trim();
        //return objResultJSON;
    }
   
   
   public synchronized ResultSet findUniqueUser() throws Exception {
        String sqlQry = "";
        try {
            dbcon = sQLDBConnection.getConnection();
            //sqlQry="SELECT EMPID,NAME,PW,EMPTYPE,DEPARTMENT,FACULTYID,ISACTIVE,ISDELETED,EMPROLE FROM TBLEMPLOYEEMASTER WHERE TO_NUMBER(EMPID)=TO_NUMBER(?)";
            //sqlQry = "SELECT EMP_NO FROM EMPORG WHERE upper(EMP_NAME) like UPPER('%"+getRequest().getParameter("filterval")+"%')";                  
            sqlQry = "SELECT EMP_NO,EMP_NAME FROM EMPORG WHERE CAST(EMP_NO AS INT)= ? ";
//            System.out.println("MyTestDao Uname >> " + request.getParameter("uname"));
//            System.out.println("MyTestDao Uname >> " + request.getParameter("filterval"));
            //System.out.println("CLIB : in LoginCDAO loginID >> "+loginID);
            pst = dbcon.prepareStatement(sqlQry);
            System.out.println(" findUniqueUser >> filter >. " + getRequest().getParameter("filterval"));
            pst.setString(1,getRequest().getParameter("filterval"));

            rs = pst.executeQuery();

//            if (rs.next()) {
//                logUserInfoBDO.setLogUserID(Services.setEmptyIfNull(rs.getString(1)));
//                //	System.out.println("CLIB : in LoginCDAO getLogUserID >> "+logUserInfoBDO.getLogUserID());
//                logUserInfoBDO.setLogUserName(Services.setEmptyIfNull(rs.getString(2)));
//                logUserInfoBDO.setLogPass(Services.setEmptyIfNull(rs.getString(3)));
//                logUserInfoBDO.setLogType(Services.setEmptyIfNull(rs.getString(4)));
//                logUserInfoBDO.setLogDeptID(Services.setEmptyIfNull(rs.getString(5)));
//                logUserInfoBDO.setFacultyID(Services.setEmptyIfNull(rs.getString(6)));
//                logUserInfoBDO.setIsActive(Services.setEmptyIfNull(rs.getString(7)));
//                //System.out.println("CLIB : in LoginCDAO getIsActiven >> "+logUserInfoBDO.getIsActive());
//                logUserInfoBDO.setIsDeleted(Services.setEmptyIfNull(rs.getString(8)));
//                logUserInfoBDO.setRole(Services.setEmptyIfNull(rs.getString(9)));
//                logUserInfoBDO.setDeptName(Services.setEmptyIfNull(rs.getString(10)));
//            } else {
//                // System.out.println("CLIB : in LoginCDAO sqlQry >> "+sqlQry);
//                logUserInfoBDO = null;
//            }
//            rs.close();
//            pst.close();
//            dbcon.close();
//            rs = null;
//            pst = null;
//            dbcon = null;
        } catch (SQLException e) {
//            //System.out.println("CLIB : Exception in LoginCDAO >> "+e.getMessage());
//            errMsg = "Exception occured in <br> Class: " + this.getClass().getName() + " <br> Method: " + Services.getCurrentMethodName() + "The error message is " + e;
//            throw new CLibUserException(errMsg);
        } catch (Exception e) {
            
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (dbcon != null) {
                dbcon.close();
            }
            rs = null;
            pst = null;
            dbcon = null;
        } finally {
//            try {
//                if (rs != null) {
//                    rs.close();
//                }
//                if (pst != null) {
//                    pst.close();
//                }
//                if (dbcon != null) {
//                    dbcon.close();
//                }
//            } catch (SQLException e) {
//                //System.out.println("CLIB : Exception in LoginCDAO >> "+e.getMessage());
//                errMsg = "Exception occured in <br> Class: " + this.getClass().getName() + " <br> Method: " + Services.getCurrentMethodName() + "The error message is " + e;
//                throw new CLibUserException(errMsg);
//            } catch (Exception e) {
//                //System.out.println("CLIB : Exception in LoginCDAO >> "+e.getMessage());
//                errMsg = "Exception occured in <br> Class: " + this.getClass().getName() + " <br> Method: " + Services.getCurrentMethodName() + "The error message is " + e;
//                throw new CLibUserException(errMsg);
//            }

        }
        return rs;
    }
   
   
    public synchronized ResultSet findUsersLike() throws Exception {
        String sqlQry = "";
        try {
            dbcon = sQLDBConnection.getConnection();
            //sqlQry="SELECT EMPID,NAME,PW,EMPTYPE,DEPARTMENT,FACULTYID,ISACTIVE,ISDELETED,EMPROLE FROM TBLEMPLOYEEMASTER WHERE TO_NUMBER(EMPID)=TO_NUMBER(?)";
            //sqlQry = "SELECT EMP_NO FROM EMPORG WHERE upper(EMP_NAME) like UPPER('%"+getRequest().getParameter("filterval")+"%')";                  
            //if(getRequest().getParameter("filterval").trim().length()>2)
            //{
            //sqlQry = "SELECT EMP_NO,EMP_NAME FROM EMPORG WHERE  REPLACE(REPLACE(UPPER(EMP_NAME),'-',''),'-','') LIKE  REPLACE(REPLACE('"+ getRequest().getParameter("filterval").trim().toUpperCase() +"%','-',''),'-','') ";
            	sqlQry = "SELECT EMP_NO,EMP_NAME FROM EMPORG WHERE EMP_NO LIKE '2220%'";
            
            System.out.println("findUsersLike >> sqlQry >> "+sqlQry);
            
            pst = dbcon.prepareStatement(sqlQry);
            //	pst.setString(1,tarTable);

            rs = pst.executeQuery();
           // }

        } catch (SQLException e) {
//            //System.out.println("CLIB : Exception in LoginCDAO >> "+e.getMessage());
//            errMsg = "Exception occured in <br> Class: " + this.getClass().getName() + " <br> Method: " + Services.getCurrentMethodName() + "The error message is " + e;
//            throw new CLibUserException(errMsg);
        } catch (Exception e) {
            
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (dbcon != null) {
                dbcon.close();
            }
            rs = null;
            pst = null;
            dbcon = null;
        } finally {
//            try {
//                if (rs != null) {
//                    rs.close();
//                }
//                if (pst != null) {
//                    pst.close();
//                }
//                if (dbcon != null) {
//                    dbcon.close();
//                }
//            } catch (SQLException e) {
//                //System.out.println("CLIB : Exception in LoginCDAO >> "+e.getMessage());
//                errMsg = "Exception occured in <br> Class: " + this.getClass().getName() + " <br> Method: " + Services.getCurrentMethodName() + "The error message is " + e;
//                throw new CLibUserException(errMsg);
//            } catch (Exception e) {
//                //System.out.println("CLIB : Exception in LoginCDAO >> "+e.getMessage());
//                errMsg = "Exception occured in <br> Class: " + this.getClass().getName() + " <br> Method: " + Services.getCurrentMethodName() + "The error message is " + e;
//                throw new CLibUserException(errMsg);
//            }

        }
        return rs;
    }
    
    
     public synchronized List getUsers() throws Exception {
         String sqlQry = "SELECT employeecode,employeefirstandmidname FROM workforce.employees WHERE  REPLACE(REPLACE(UPPER(employeefirstandmidname),'-',''),'-','') LIKE  REPLACE(REPLACE('"+ getRequest().getParameter("filterval").trim().toUpperCase() +"%','-',''),'-','')  limit 25";
         return resultSetToList(sqlQry);
         //return resultSetToListMap(sqlQry);
     }
     

     public String resultSetToJsonTest() {
         String jsonResult = null;
         ResultSet rs = null;
         ArrayList<String> resultList = null;
         java.util.HashMap<String, String> hashMapRecord = null;
         try {
             hashMapRecord = new java.util.HashMap();
             resultList = new ArrayList();
             String columnName = "";
             boolean blnRecordFound = false;
             int i = 1;
             String argQuery="select groupname from usermanager.tblusergroups";
             rs = com.test.util.db.fetchData.getResultSet(argQuery,queryParametersList);
             java.sql.ResultSetMetaData objMetaData = rs.getMetaData();
             long columnCount = objMetaData.getColumnCount();
             while (rs.next()) {
                 blnRecordFound = true;
                 hashMapRecord = new java.util.HashMap();
                 for (i = 1; i <= columnCount; i++) {
                     columnName = (String) objMetaData.getColumnName(i);
                     hashMapRecord.put(columnName, rs.getString(columnName));
                 }
                 resultList.add(JSONObject.fromObject(hashMapRecord).toString());
             }
             rs.close();
             rs = null;
//             if (blnRecordFound) {
//                 hashMapRecord.put("recordFound", "YES");
//             } else {
//                 hashMapRecord.put("recordFound", "NO");
//             }
//
//             hashMapRecord.put("resultStatus", "1");
//             hashMapRecord.put("resultClass", RESULTSUCCESS);
//             hashMapRecord.put("result", "Success");

             JSONObject jsonObj = JSONObject.fromObject(hashMapRecord);
             jsonResult = jsonObj.toString();

         } catch (Exception e) {
             hashMapRecord.put("resultStatus", "0");
             hashMapRecord.put("resultClass", RESULTERROR);
             hashMapRecord.put("result", "Could not load data.");
         } finally {
             try {
                 rs.close();
             } catch (SQLException e) {

             } catch (Exception e) {

             }
         }

         return resultList.toString();
     }
}
