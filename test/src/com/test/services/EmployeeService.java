/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.services;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.test.LoginUser;

/**
 *
 * @author Administrator
 */
public class EmployeeService implements ServiceInterface {

	@Override
	public void execute(HttpServlet servlet, HttpServletRequest request, HttpServletResponse response, DataSource datasrc, HttpSession session) throws IOException, ServletException {
		System.out.println("EmployeeService Service called");

		String strOperation = request.getParameter("action");

		if(strOperation==null)
		{
			request.setAttribute("targetPage", "EmployeeView.jsp");
			return;
		}

		switch (strOperation) {
		case "select2search":
			System.out.println("EmployeeService View called");
			request.setAttribute("targetPage", "EmployeeModel.jsp");
			break; 
		case "viewrs":
			System.out.println("EmployeeService View called");
			request.setAttribute("targetPage", "EmployeeModel.jsp");
			break; 
		case "view":
			System.out.println("EmployeeService View called");
			request.setAttribute("targetPage", "EmployeeModel.jsp");
			break;   
		default:
			System.out.println("EmployeeService View called");
			request.setAttribute("targetPage", "EmployeeView.jsp");
		}

		//request.setAttribute("targetPage", "EmployeeView.jsp");

		//String  pageActionId= request.getParameter("hdnActionId");
		//        if(strOperation==null)
		//        {
		//        	request.setAttribute("targetPage", "pages/EmployeeView.jsp");
		//        }
		//        else
		//        {
		//        	request.setAttribute("targetPage", "pages/MyHome.jsp");
		//        }
		//request.setAttribute("targetPage", "MyLogin.jsp");
		// throw new UnsupportedstrOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}    
}

