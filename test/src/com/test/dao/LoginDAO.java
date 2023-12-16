package com.test.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.test.util.db.DataAccessManager;

import net.sf.json.JSONObject;

public class LoginDAO extends DataAccessManager{
	StringBuilder strBuildSQLQuery=null;
	ArrayList<Object> queryParametersList = null;
	ResultSet rs = null;

//	public ResultSet getEmployeesRs() {
//		strSQLQuery="SELECT EMP_NO,EMP_NAME FROM EMPORG WHERE EMP_NO LIKE '2220%'";
//		return getResultSet(strSQLQuery,queryParametersList);
//	}
	
	public boolean getIsAuthenticated(String userName,String password) {
		strBuildSQLQuery= new StringBuilder();
		boolean isAuthenticated = false;
		strBuildSQLQuery.append("select userid,loginname,username from usermanager.tblusermaster WHERE loginname=? and esignature=? ");
		queryParametersList = new ArrayList();
		queryParametersList.add(0,userName);
		queryParametersList.add(1,password);
		rs=getResultSet(strBuildSQLQuery.toString(),queryParametersList);
		try {
			if(rs.next()) {
				//objResultJSON.put("result", "success");	
				isAuthenticated=true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("isAuthenticated >> "+isAuthenticated);
		return isAuthenticated;
	}
	
//	public List getEmployeesMap() {
//		strSQLQuery="SELECT EMP_NO,EMP_NAME FROM EMPORG WHERE EMP_NO like (?) ";
//		queryParametersList = new ArrayList();
//		queryParametersList.add(0,"2220%");
//		return resultSetToListMap(strSQLQuery,queryParametersList);
//	}
	
	public List getEmployeesMap() {
		strBuildSQLQuery= new StringBuilder();
		strBuildSQLQuery.append("select userid,loginname,username from usermanager.tblusermaster WHERE loginname like (?) ");
		queryParametersList = new ArrayList();
		queryParametersList.add(0,"a%");
		return resultSetToListMap(strBuildSQLQuery.toString(),queryParametersList);
	}

}
