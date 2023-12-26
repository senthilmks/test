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
import com.test.dao.LoginDAO;

import net.sf.json.JSONObject;

/**
 *
 * @author Administrator
 */
public class LoginService implements ServiceInterface {

	@Override
	public void execute(HttpServlet servlet, HttpServletRequest request, HttpServletResponse response, DataSource datasrc, HttpSession session) throws IOException, ServletException {
		System.out.println("Login Service called");
		String  pageActionId= request.getParameter("hdnActionId");

		String strOperation = request.getParameter("action");
		if(strOperation==null)
		{
			strOperation="DEFAULT";
		}

		switch (strOperation) {
		case "DEFAULT":
			System.out.println("LoginService DEFAULT called");
			request.setAttribute("targetPage", "LoginView.jsp");
			break;
		case "AUTHENTICATE":
			System.out.println("LoginService View called");
			String uname=request.getParameter("hdnUserName");
			String password=request.getParameter("hdnPassword");

			LoginServiceValidation loginServiceValidation = new LoginServiceValidation();

			boolean isValidLogin = loginServiceValidation.isValidLogin(request, response, datasrc, session,1);
			if(isValidLogin) {
				LoginDAO loginDAO = new LoginDAO();		

				JSONObject objResultJSON = new  JSONObject();
				if(loginDAO.getIsAuthenticated(uname,password)) {
					objResultJSON.put("resultstatus", "success");
					objResultJSON.put("resultmessage", "Login Successful.");
					LoginUser loginUser= new LoginUser();
					loginUser.setUserId(uname);
					loginUser.setPassWord(password);
					session.setAttribute("logUser", loginUser); 
					//request.setAttribute("targetPage", "MyHome.jsp");
					request.setAttribute("targetPage", "Template.jsp");
				}
			}
			else {
				request.setAttribute("targetPage", "LoginView.jsp");
				request.setAttribute("errorMsg", loginServiceValidation.getErrorMsg());
			}

			break; 		   
		case default:
			System.out.println("LoginService default called");
			request.setAttribute("targetPage", "LoginView.jsp");
		}

		//        if(pageActionId!=null && pageActionId.trim().equalsIgnoreCase("1"))
		//        {
		//            String uname=request.getParameter("hdnUserName");
		//            String password=request.getParameter("hdnPassword");
		//            if(uname!=null && uname.trim().equalsIgnoreCase("test") && password!=null && password.trim().equalsIgnoreCase("test") ){
		//                LoginUser loginUser= new LoginUser();
		//                loginUser.setUserId(uname);
		//                loginUser.setPassWord(password);
		//                session.setAttribute("logUser", loginUser); 
		//                request.setAttribute("targetPage", "MyHome.jsp");
		//                //request.setAttribute("page", "LOGIN");
		//                 //response.sendRedirect("MyController.jsp");
		//                //response.sendRedirect(request.getContextPath()+"/AppController");
		//            }
		//            else
		//            { 
		//            	request.setAttribute("targetPage", "LoginView.jsp");
		//            }
		//        }
		//        else
		//        {
		//        	request.setAttribute("targetPage", "LoginView.jsp");
		//        }
		//request.setAttribute("targetPage", "MyLogin.jsp");
		// throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}    
}

