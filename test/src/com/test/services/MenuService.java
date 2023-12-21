/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.services;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.test.LoginUser;
import com.test.dao.LoginDAO;
import com.test.dao.MenuDAO;

import net.sf.json.JSONObject;

/**
 *
 * @author Administrator
 */
public class MenuService implements ServiceInterface {

	@Override
	public void execute(HttpServlet servlet, HttpServletRequest request, HttpServletResponse response, DataSource datasrc, HttpSession session) throws IOException, ServletException {
		System.out.println("MenuService called");
		//RequestDispatcher rd=null;
		String targetPage="LoginView.jsp";
		String strOperation = request.getParameter("action");
		if(strOperation==null)
		{
			strOperation="DISPATCH";
		}
		
		switch (strOperation.toUpperCase()) {
		case "DEFAULT":
			System.out.println("MenuService DEFAULT called");
			//request.setAttribute("targetPage", "MenuView.jsp");
			//request.getRequestDispatcher("/AppManager").forward(request,response);
			break;
		case "DISPATCH":
			System.out.println("MenuService View called");
			//JSONParser parser = new JSONParser();
			JSONObject objResultJSON =null;
			MenuDAO menuDAO = new MenuDAO();	
			String strMenuId = request.getParameter("resourceid");
			
			

			String menuDetails= menuDAO.getMenuDetails(strMenuId);
			
			if(menuDetails!=null && menuDetails.length()>0) {
				System.out.println("menuDetails>>"+menuDetails);
				objResultJSON = JSONObject.fromObject(menuDetails);				
				System.out.println("link >> "+objResultJSON.getString("menulink")+", resource >> "+objResultJSON.getString("resourcename"));
				request.setAttribute("resource", objResultJSON.getString("resourcename"));
				targetPage=objResultJSON.getString("menulink");
			}
			
			if(strMenuId.trim().equalsIgnoreCase("3")) {
				targetPage="LoginView.jsp";
			}
			else if(strMenuId.trim().equalsIgnoreCase("1")) {
				targetPage="Page1.jsp";
			}
			else if(strMenuId.trim().equalsIgnoreCase("2")) {
				targetPage="Page2.jsp";
			}
			
//			for(int j=0;j<menuDetails.size();j++) {
//				objResultJSON = JSONObject.fromObject((menuDetails.get(j).toString()));
//				System.out.println("menuDetails value >>"+menuDetails.get(j)+"objResultJSON string >> "+objResultJSON.toString()+", link >> "+objResultJSON.getString("menulink"));
//				
//			}
			
			if(menuDetails!=null && menuDetails.length()>0) {
				objResultJSON.put("resultstatus", "success");
				objResultJSON.put("resultmessage", "Login Successful.");
			}
			else {
				objResultJSON.put("resultstatus", "failure");
				objResultJSON.put("resultmessage", "Invalid User Name and/or password.");
				//request.setAttribute("targetPage", "LoginView.jsp");
			}
			
			//request.getRequestDispatcher("/AppManager").forward(request,response);
			break; 		   
		default:
			System.out.println("MenuService default called");
			//request.setAttribute("targetPage", "LoginView.jsp");
			//request.getRequestDispatcher("/AppManager").forward(request,response);
		}
		request.setAttribute("targetPage", targetPage);
	}    
}

