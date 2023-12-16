/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.util.security;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;



import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;

import javax.servlet.ServletException;
 
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.test.util.security.RequestWrapper;



/**
 *
 * @author Administrator
 */
// @WebFilter(filterName = "Security_2_Delink_gzip", urlPatterns = {"/*"}, dispatcherTypes = {DispatcherType.FORWARD, DispatcherType.REQUEST})
public class Security_1_Delink_zip implements Filter { 
    
    private static final boolean debug = false;
    
    private FilterConfig filterConfig = null;
    
    Properties properties = null;
    
    
     public void log(String msg) {
        filterConfig.getServletContext().log(msg);        
    }
    
    private void doBeforeProcessing(RequestWrapper request, ResponseWrapper response)
            throws IOException, ServletException {
        if (debug) {
            log("Security_1_Delink_gzip:DoBeforeProcessing");
        }
        System.out.println("Security_1_Delink_gzip:DoBeforeProcessing");
    }    
    
    private void doAfterProcessing(RequestWrapper request, ResponseWrapper response)
            throws IOException, ServletException {
        if (debug) {
            log("Security_1_Delink_gzip:DoAfterProcessing");
        }  
        System.out.println("Security_1_Delink_gzip:DoAfterProcessing");
    }

   
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        if (debug) {
            log("Security_1_Delink_gzip:doFilter()");  
        } 
   
        
         if (request instanceof HttpServletRequest) {
             String Redirect="";
             // HttpServletRequest req = (HttpServletRequest) request;
             RequestWrapper reqWrapper = new RequestWrapper((HttpServletRequest) request);  
              
             try {Redirect = reqWrapper.getAttribute("Redirect").toString();} catch (Exception e) {  Redirect="";}   
              
              HttpServletResponse res = (HttpServletResponse) response;
              HttpSession session = reqWrapper.getSession(false);
              
              String ae = reqWrapper.getHeader("accept-encoding");
              if (ae != null && ae.indexOf("gzip") != -1) {        
                GZIPResponseWrapper wrappedResponse = new GZIPResponseWrapper(res);
                String URI= "";
                try {URI = reqWrapper.getRequestURI();} catch (Exception e) {  URI="";}
                
                 //reqWrapper.setAttribute("CUBres", reqWrapper.getQueryString());  // to check
                // reqWrapper.setParameter("CUBres", reqWrapper.getQueryString());
                 
                 if(URI.matches(properties.getProperty("excludeFiles")) ){
                      
                     chain.doFilter(request, wrappedResponse);
                     
                 }else if (session != null && !session.isNew()) {
                     if(URI.matches(properties.getProperty("excludeFiles")) || URI.matches(properties.getProperty("excludeJSPS")) ) {
                      //if( URI.matches(properties.getProperty("excludeFiles")) ||URI.endsWith(properties.getProperty("excludeCUB")) || URI.endsWith(properties.getProperty("excludeICICIReq")) || URI.endsWith(properties.getProperty("excludeICICIRes"))){ 
                             chain.doFilter(request, wrappedResponse);
                        }else{
                            chain.doFilter(reqWrapper, wrappedResponse);
                      }
                       
                  } else {
                             //request.getRequestDispatcher("/index.jsp").include(request, wrappedResponse);
                             request.getRequestDispatcher("/sm/usermanager/login/youLogin.jsp").forward(request, wrappedResponse);
                  }
         
                wrappedResponse.finishResponse();
                return;
              }
                /* gzip not supported */
              else{
                   if(session!=null){session.invalidate();}
                    log("gzip is not supported");  
                     request.getRequestDispatcher("/sm/error/smp.jsp").forward(request,response);
                     return;
              }
             //chain.doFilter(orgreq, res);
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
    
    
   /**
     * Destroy method for this filter 
     */
   @Override
    public void destroy() {  
       if (this.filterConfig != null) {
            this.filterConfig=null;
       }
       if (this.properties != null) {
            this.properties=null;
       }     
       if (debug) {                
                log("Security_2_Delink_gzip:destroy");
            }
    }

    /**
     * Init method for this filter 
     */
    @Override
    public void init(FilterConfig filterConfig) {        
        this.filterConfig = filterConfig;
        try{
            this.properties =loadProperties("../properties/SecurityFilter.properties");
            }catch(Exception e){
                System.out.println("Security_1_Delink_gzip::Property file.."+e.toString());
            }
        
        
        if (filterConfig != null) {
            if (debug) {                
                log("Security_1_Delink_gzip: Initializing filter");
            }
        }
    }


    
}// Class End 
