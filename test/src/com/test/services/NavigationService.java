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
public class NavigationService implements ServiceInterface {

    @Override
    public void execute(HttpServlet servlet, HttpServletRequest request, HttpServletResponse response, DataSource datasrc, HttpSession session) throws IOException, ServletException {
        System.out.println("NavigationService Service called");
        
        String opr = request.getParameter("opr");
        //String  pageActionId= request.getParameter("hdnActionId");
        if(opr==null)
        {
        	request.setAttribute("targetPage", "pages/MyView.jsp");
        }
        else
        {
        	request.setAttribute("targetPage", "pages/MyHome.jsp");
        }
        //request.setAttribute("targetPage", "MyLogin.jsp");
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }    
}

