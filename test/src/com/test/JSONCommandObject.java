/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
//import net.sf.json.JSONArray;
//import net.sf.json.JSONObject;

/**
 *
 * @author Administrator
 */
public class JSONCommandObject extends com.test.util.db.SQLTransEngine {

    private HttpServletRequest request;
    public String result;
    public String resultMessage;
    public final String SUCCESS = "<font>Your Request Completed successfully</font>";
    public final String FAIL = "<font>Your Request fail</font>";
    public final String UNIQUE = "<font>Duplicate request</font>";
    private String filter;
    private LoginUser logUser;
    private int resultStatus;
    public final String RESULTSUCCESS = "ui-state-highlight";
    public final String RESULTERROR = "ui-state-error";
    HashMap resultData = new HashMap();
    ArrayList<Object> queryParametersList = null;
    public JSONCommandObject() {
        result = "";
        resultMessage = "";
        filter = "";
        resultStatus = 0;
        //resultData= new HashMap();
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
        try {
            if (request.getParameter("filterval") != null) {
                this.filter = request.getParameter("filterval").trim().toUpperCase();
            }
        } catch (Exception e) {
            this.filter = "";
        }
        this.logUser = (LoginUser) request.getSession().getAttribute("logUser");
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = request.getParameter("filterval");
    }

    public int getResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(int resultStatus) {
        this.resultStatus = resultStatus;
    }

    public HashMap getResultData() {
        return resultData;
    }

    public void setResultData(HashMap resultData) {
        this.resultData = resultData;
    }

//    public static JSONArray convertToJSONArray(ResultSet resultSet)
//            throws Exception {
//        JSONArray jsonArray = new JSONArray();
//        while (resultSet.next()) {
//            JSONObject obj = new JSONObject();
//            int total_rows = resultSet.getMetaData().getColumnCount();
//            for (int i = 0; i < total_rows; i++) {
//                obj.put(resultSet.getMetaData().getColumnLabel(i + 1)
//                        .toLowerCase(), resultSet.getObject(i + 1));
//
//            }
//            jsonArray.put(obj);
//        }
//        return jsonArray;
//    }
    /**
     * Convert the ResultSet to a List of Maps, where each Map represents a row
     * with columnNames and columValues
     *
     * @param rs
     * @return
     * @throws SQLException
     */
//    private List<HashMap<String, Object>> resultSetToJson(ResultSet rs) throws SQLException {
//        ResultSetMetaData md = rs.getMetaData();
//        int columns = md.getColumnCount();
//        List<HashMap<String, Object>> rows = new ArrayList<HashMap<String, Object>>();
//        do {
//            HashMap<String, Object> row = new HashMap<String, Object>(columns);
//            for (int i = 1; i <= columns; ++i) {
//                row.put(md.getColumnName(i), (Object) rs.getObject(i));
//            }
//            rows.add(row);
//        } while (rs.next());
//        if (rs != null) {
//            rs.close();
//            rs = null;
//        }
//        return rows;
//    }
//
//    public HashMap<String, String> getRequestMaps() {
//        try {
//            HashMap params = (HashMap) this.request.getParameterNames();
//            System.out.println("getRequestMaps >> params >> " + params);
//            for (int i = 0; i <= params.size(); i++) {
//                System.out.println("getRequestMaps >> " + params.get(i));
//            }
//        } catch (Exception e) {
//
//        }
//        System.out.println("getRequestMaps >> getResultData >> " + getResultData());
//        return this.getResultData();
//    }
//public static JSONArray convertToJSONArray(ResultSet resultSet)
//            throws Exception {
//        JSONArray jsonArray = new JSONArray();
//        while (resultSet.next()) {
//            JSONObject obj = new JSONObject();
//            int total_rows = resultSet.getMetaData().getColumnCount();
//            for (int i = 0; i < total_rows; i++) {
//                obj.put(resultSet.getMetaData().getColumnLabel(i + 1)
//                        .toLowerCase(), resultSet.getObject(i + 1));
//
//            }
//            jsonArray.add(obj);
//        }
//        return jsonArray;
//    }
// public static JSONArray convertToJSONArray(ResultSet resultSet)
//            throws Exception {
//        JSONArray jsonArray = new JSONArray();
//        while (resultSet.next()) {
//            JSONObject obj = new JSONObject();
//            int total_rows = resultSet.getMetaData().getColumnCount();
//            for (int i = 0; i < total_rows; i++) {
//                obj.put(resultSet.getMetaData().getColumnLabel(i + 1)
//                        .toLowerCase(), resultSet.getObject(i + 1));
//
//            }
//            jsonArray.put(obj);
//        }
//        return jsonArray;
//    }
//public static String resultSetToJson(Connection connection, String query) {
//        List<Map<String, Object>> listOfMaps = null;
//        try {
//            QueryRunner queryRunner = new QueryRunner();
//            listOfMaps = queryRunner.query(connection, query, new MapListHandler());
//        } catch (SQLException se) {
//            throw new RuntimeException("Couldn't query the database.", se);
//        } finally {
//            DbUtils.closeQuietly(connection);
//        }
//        return new Gson().toJson(listOfMaps);
//    }
//public static String resultSetToJson(ResultSet rs) {
//        List<Map<String, Object>> listOfMaps = null;
//        try {
//           
//        } catch (Exception se) {
//            throw new RuntimeException("Couldn't query the database.", se);
//        } 
//        return new Gson().toJson(rs);
//    }
    public LoginUser getLogUser() {
        return logUser;
    }

    public void setLogUser(LoginUser logUser) {
        this.logUser = logUser;
    }

    public String resultSetToJson(String argQuery) {
        String jsonResult = null;
        ResultSet rs = null;
        
        java.util.HashMap<String, String> hashMapRecord = null;
        try {
            hashMapRecord = new java.util.HashMap();
            String columnName = "";
            boolean blnRecordFound = false;
            int i = 1;
            argQuery="select groupname from usermanager.tblusergroups";
            rs = com.test.util.db.fetchData.getResultSet(argQuery,queryParametersList);
            java.sql.ResultSetMetaData objMetaData = rs.getMetaData();
            long columnCount = objMetaData.getColumnCount();
            while (rs.next()) {
                blnRecordFound = true;
                for (i = 1; i <= columnCount; i++) {
                    columnName = (String) objMetaData.getColumnName(i);
                    hashMapRecord.put(columnName, rs.getString(columnName));
                }
            }
            rs.close();
            rs = null;
            if (blnRecordFound) {
                hashMapRecord.put("recordFound", "YES");
            } else {
                hashMapRecord.put("recordFound", "NO");
            }

            hashMapRecord.put("resultStatus", "1");
            hashMapRecord.put("resultClass", RESULTSUCCESS);
            hashMapRecord.put("result", "Success");

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

        return jsonResult;
    }
    
    
    public List resultSetToList(String argQuery) {
        String jsonResult = null;
        ResultSet rs = null;
        ArrayList<String> resultList = null;
        java.util.HashMap hashMapRecord = null;
        try {
            resultList = new ArrayList();
            String columnName = "";
            boolean blnRecordFound = false;
            
            int recCount=0;
            
            int i = 1;
            rs = com.test.util.db.fetchData.getResultSet(argQuery,queryParametersList);
            java.sql.ResultSetMetaData objMetaData = rs.getMetaData();
            long columnCount = objMetaData.getColumnCount();
            while (rs.next()) {
                blnRecordFound = true;
                recCount++;
                for (i = 0; i <= columnCount-1; i++) {
                    columnName = (String) objMetaData.getColumnName(i+1);
                    //hashMapRecord.put(columnName, rs.getString(columnName));
                    resultList.add(i,rs.getString(columnName));
                }
            }
            for (i = 0; i <= resultList.size()-1; i++) {
                  System.out.println(" i "+i+" val = >> "+resultList.get(i));
                   
                }
            
            rs.close();
            rs = null;
//            if (blnRecordFound) {
//                hashMapRecord.put("recordFound", "YES");
//            } else {
//                hashMapRecord.put("recordFound", "NO");
//            }
//
//            hashMapRecord.put("resultStatus", "1");
//            hashMapRecord.put("resultClass", RESULTSUCCESS);
//            hashMapRecord.put("result", "Success");

//            JSONObject jsonObj = JSONObject.fromObject(hashMapRecord);
//            jsonResult = jsonObj.toString();

        } catch (Exception e) {
//            hashMapRecord.put("resultStatus", "0");
//            hashMapRecord.put("resultClass", RESULTERROR);
//            hashMapRecord.put("result", "Could not load data.");
            System.out.println(" exception >> "+e.getMessage());
                resultList.add("EXCEPTION");
        } finally {
            try {
                rs.close();
                rs=null;
            } catch (SQLException e) {

            } catch (Exception e) {

            }
        }

        return resultList;
    }
    
    
    public List resultSetToListMap(String argQuery) {
        String jsonResult = null;
        ResultSet rs = null;
        ArrayList<HashMap> resultList = null;
        HashMap hashMapRecord = null;
        try {
            resultList = new ArrayList();
            hashMapRecord = new java.util.HashMap();
            String columnName = "";
            boolean blnRecordFound = false;
            int i = 1;
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
                resultList.add(hashMapRecord);
            }
            rs.close();
            rs = null;
            if (blnRecordFound) {
                hashMapRecord.put("recordFound", "YES");
            } else {
                hashMapRecord.put("recordFound", "NO");
            }

            hashMapRecord.put("resultStatus", "1");
            hashMapRecord.put("resultClass", RESULTSUCCESS);
            hashMapRecord.put("result", "Success");

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

        return resultList;
    }
    
    public String findUniqueUserJson() throws Exception {
        String sqlQry = "";
        JSONObject objResultJSON = new JSONObject();
        JSONArray arrResultDataJson = new JSONArray();
        ResultSet rs = null;
        String argQuery=null;
        try {
//            dbcon = sQLDBConnection.getConnection();            
//            sqlQry = "SELECT EMP_NO,EMP_NAME FROM EMPORG WHERE CAST(EMP_NO AS INT)in (222023,222024,222025,222026) ";
//            pst = dbcon.prepareStatement(sqlQry);
//            rs = pst.executeQuery();
        	rs = com.test.util.db.fetchData.getResultSet(argQuery,queryParametersList);
            
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
           
        } finally {
//            try {
               
        }
        objResultJSON.put("result", arrResultDataJson);
        return objResultJSON.toString();
    }
}
