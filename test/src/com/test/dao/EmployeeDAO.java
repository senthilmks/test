package com.test.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.test.util.db.DataAccessManager;

public class EmployeeDAO extends DataAccessManager{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	StringBuilder strBuildSQLQuery=null;
	ArrayList<Object> queryParametersList = null;

	public ResultSet getEmployeesRs() {
		strBuildSQLQuery= new StringBuilder();
		strBuildSQLQuery.append("SELECT EMP_NO,EMP_NAME FROM EMPORG WHERE EMP_NO LIKE '2220%'");
		return getResultSet(strBuildSQLQuery.toString(),queryParametersList);
	}

	//	public List getEmployeesList() {
	//		strBufSQLQuery="SELECT EMP_NO,EMP_NAME FROM EMPORG WHERE EMP_NO=?";
	//		queryParametersList = new ArrayList();
	//		queryParametersList.add(0,"2220");
	//		return resultSetToList(strBufSQLQuery,queryParametersList);
	//	}

	public List getEmployeeList() {
		strBuildSQLQuery= new StringBuilder();
		strBuildSQLQuery.append("select userid,loginname,username from usermanager.tblusermaster");
		//		queryParametersList = new ArrayList();
		//		queryParametersList.add(0,"2220");
		return resultSetToList(strBuildSQLQuery.toString(),queryParametersList);
	}

	public List getEmployeesMap() {
		strBuildSQLQuery.append("SELECT EMP_NO,EMP_NAME FROM EMPORG WHERE EMP_NO like (?) ");
		queryParametersList = new ArrayList();
		queryParametersList.add(0,"2220%");
		return resultSetToListMap(strBuildSQLQuery.toString(),queryParametersList);
	}

	public String getEmployeesJSON() {
		strBuildSQLQuery= new StringBuilder();
		strBuildSQLQuery.append("select userid as id,loginname as text,username as desig from usermanager.tblusermaster where loginname like (?)");

		System.out.println("Search Parameter >> "+getRequest().getParameter("search"));
		if(getRequest().getParameter("search")!=null) {
			queryParametersList = new ArrayList();
			//queryParametersList.add(0,"22"+getRequest().getParameter("search")+"%");
			queryParametersList.add(0,getRequest().getParameter("search")+"%");
		}
		else {
			queryParametersList = new ArrayList();
			queryParametersList.add(0,"admin%");
		}
		return resultSetToJSONStr(strBuildSQLQuery.toString(),queryParametersList,1);
	}

}
