/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.util.security;
import java.io.InputStream;
import java.io.FileInputStream;
import com.test.util.security.URLEncryptionWrapper;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.test.util.security.RequestWrapper;


public class Security_2_Checksum implements Filter
{
   private FilterConfig filterConfig=null;
   Properties properties=null;

 
  public void doFilter (ServletRequest request,
              ServletResponse response,
              FilterChain chain)
  {
      
    try
    {
         RequestWrapper reqWrapper = (RequestWrapper) (HttpServletRequest) request;


        {
            URLEncryptionWrapper encClass = URLEncryptionWrapper.getInstance();
            boolean bRedirect = false;
            try {
                 String URI= "";
                try {URI = reqWrapper.getRequestURI();} catch (Exception e) { URI=""; }
                HttpServletResponse res = (HttpServletResponse) response;
                HttpSession session = reqWrapper.getSession(false);
                String sess="",str = "", str1 = "";
                
                 try{
                     sess=reqWrapper.getParameter("PgSession");
                 }catch(Exception e){ sess="";/*e.printStackTrace(); */}
                 
                 if(URI.matches(properties.getProperty("excludeFiles")) || URI.matches(properties.getProperty("excludeJSPS")) ) {
                //if(URI.matches(properties.getProperty("excludeFiles")) || URI.contains(properties.getProperty("excludeCUB"))|| URI.contains(properties.getProperty("excludeICICIReq")) || URI.contains(properties.getProperty("excludeICICIRes")) ){
                    bRedirect = false;
                }
                else if (URI.endsWith("/LoadImage")) {
                    chain.doFilter(reqWrapper, response);
                }
                else if(session.getId().equals(sess)==false){ 
                        bRedirect = true;
                        System.out.println("Redirect to sessionexpired page 1");
                        try{
                            System.out.println("Redirect 123");
                            System.out.println("str.."+str);
                            System.out.println("str1.."+str1);
                            System.out.println("URI.."+URI);
                            System.out.println("sess.."+sess);
                            System.out.println("session.."+session.getId());
                            
                            //request.getRequestDispatcher("/sm/error/sessionexpired.jsp").forward(request, response);
                            ((HttpServletResponse)response).sendRedirect(reqWrapper.getContextPath() +"/sm/error/sessexpired.jsp");
                        }catch(Exception e){
                            System.out.println("error while redirect" +e.toString());
                            e.printStackTrace();
                        }
                 }else{  
                        try {str = reqWrapper.getParameter("enchid");} catch (Exception e) {}
                        try {str1 = reqWrapper.getParameter("urlchecksum");} catch (Exception e) {}

                        if (encClass.IsValidURL_enc(str, str1) == false) {
                            bRedirect = true;
                            System.out.println("Redirect to sessionexpired page 2");
                            System.out.println("str.."+str);
                            System.out.println("str1.."+str1);
                            //request.getRequestDispatcher("/sm/error/sessionexpired.jsp").forward(request, response);
                            ((HttpServletResponse)response).sendRedirect(reqWrapper.getContextPath() +"/sm/error/sessexpired.jsp");
                        }
                }
            } catch (Exception e) {}
             if (bRedirect == false) {chain.doFilter(reqWrapper, response);}
        }
    }catch (Exception io) {
      System.out.println ("IOException raised in Filter2"+io.toString());
    } 
    
  }

    /**
     * To load the property file values.
     * @param strFileName
     * @return Properties
     */
    public  Properties loadProperties(String strFileName){
        
         Properties objProperties = new Properties();
         URL url = getClass().getResource(strFileName);
         InputStream is =null;
         URI uri = null;
         
         try {
             uri = new java.net.URI(url.getFile());
             is = new FileInputStream(uri.getPath());
              objProperties.load(is);
         } catch (IOException ex) {
             System.out.println("ex = " + ex);
        } catch (URISyntaxException ex) {
            System.out.println("ex = " + ex);
         }
        

        return objProperties;
    }      
  
  public FilterConfig getFilterConfig()
  {
   return (this.filterConfig);
  }

  public void setFilterConfig (FilterConfig filterConfig)
  {
    this.filterConfig = filterConfig;
  }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
       try{
        this.properties =loadProperties("../properties/SecurityFilter.properties");
        }catch(Exception e){
                System.out.println("Security_1_Delink_gzip::Property file.."+e.toString());
            }
    }

    @Override
    public void destroy() {
       // throw new UnsupportedOperationException("Not supported yet.");
        if (this.filterConfig != null) {
            this.filterConfig=null;
       }
       if (this.properties != null) {
            this.properties=null;
       } 
       
    }

    
   
}
