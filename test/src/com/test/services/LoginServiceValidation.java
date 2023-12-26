/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.test.util.FieldValidation;

/**
 *
 * @author Administrator
 */


public class LoginServiceValidation
{
  boolean isAllValid;
  String errorMsg = "";
 // ArrayList<HashMap> paramList = null;
  java.util.HashMap paramList = null;
 
  
  public LoginServiceValidation() {}
  
  public boolean isValidLogin(HttpServletRequest request, HttpServletResponse response, DataSource datasrc, HttpSession session,int modeOfDisplay) throws IOException, ServletException {
    int counter = 0;
    
    try
    {
      InitilizeIndicators(request);
      FieldValidation fv = new FieldValidation();
      paramList = new HashMap();
      
      isAllValid = true;
      
      String field = request.getParameter("hdnUserName");
      paramList.put("uname", field);
      
      String fieldName = "hdnUserName";
      String fieldDesc = "User Name";
      System.out.println("CLIB : in LoginServiceValidation Date >> " + field);
      
      boolean isValidUserName = fv.validate(fv.REQUIRED, field, fieldName,fieldDesc, counter,modeOfDisplay);
      System.out.println("CLIB : in LoginServiceValidation isValidUserName >> " + isValidUserName);
      if (!isValidUserName)  {
        counter = fv.getCounter();
        isAllValid = false;
        if(modeOfDisplay==1) {
        	//request.setAttribute("errorMsg", fv.getJSONErrorObj());
        	errorMsg=fv.getJSONErrorObj();
        }
        else {
        	 errorMsg = fv.getErrorMessage();
             request.setAttribute(fieldName+"Ind", "*" + Integer.toString(fv.getCounter()));
        }
      }  
      
      
      field = request.getParameter("hdnPassword");
      paramList.put("password", field);
      
      fieldName = "hdnPassword";
      fieldDesc = "Password";
      boolean isValidPassword = fv.validate(fv.PASSWORD, field, fieldName,fieldDesc, counter,modeOfDisplay);
      System.out.println("CLIB : in LoginServiceValidation isValidUserName >> " + isValidUserName);
      if (!isValidPassword)  {
        counter = fv.getCounter();
        isAllValid = false;
        if(modeOfDisplay==1) {
        	//request.setAttribute("errorMsg", fv.getJSONErrorObj());
        	errorMsg=fv.getJSONErrorObj();
        }
        else {
        	 errorMsg = fv.getErrorMessage();
             request.setAttribute(fieldName+"Ind", "*" + Integer.toString(fv.getCounter()));
        }
      }  

      
    }
    catch (Exception e)
    {
      System.out.println("CLIB : in LoginServiceValidation Exception : " + e.toString());
    }
    

    return isAllValid;
  }
  
  public void InitilizeIndicators(HttpServletRequest request)
  {
    request.removeAttribute("unameInd");
   
  }
  
  public String getErrorMsg() {
    return errorMsg;
  }
  
  public void setErrorMsg(String errMsg) { errorMsg = errMsg; }
  
  public boolean isAllValid() {
    return isAllValid;
  }
  
  public void isAllValid(boolean isAllValid) { this.isAllValid = isAllValid; }
}

