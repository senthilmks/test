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
public class UserService implements ServiceInterface {

	@Override
	public void execute(HttpServlet servlet, HttpServletRequest request, HttpServletResponse response, DataSource datasrc, HttpSession session) throws IOException, ServletException {
		System.out.println("EmployeeService Service called");

		String strOperation = request.getParameter("action");

		if(strOperation==null)
		{
			request.setAttribute("targetPage", "UserView.jsp");
			return;
		}

		switch (strOperation) {
		case "viewrs":
			request.setAttribute("targetPage", "UserModel.jsp");
			break; 
		case "view":
			request.setAttribute("targetPage", "UserModel.jsp");
			break;   
		default:
			request.setAttribute("targetPage", "UserView.jsp");
		}

	}    
}

