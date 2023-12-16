/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */    
package com.test.util.security;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

  
/**
     * This request wrapper class extends the support class HttpServletRequestWrapper,
     * which implements all the methods in the HttpServletRequest interface, as
     * delegations to the wrapped request. 
     * You only need to override the methods that you need to change.
     * You can get access to the wrapped request using the method getRequest()
     */
    public class RequestWrapper extends MultiReadHttpServletRequest{
        
        public RequestWrapper(HttpServletRequest request) {
            super(request);
            
        }
      
    }
     class MultiReadHttpServletRequest extends HttpServletRequestWrapper {  
         private static final boolean debug = false;
        private URLEncryptionWrapper encClass=null;
        protected HashMap<String,String[]> parameterMap;  
     
        private HttpServletRequest req;
        private byte[] contentData;
        private byte bContentRead=0;
              
        
        private List<FileItem> fileItemList=new ArrayList<FileItem>(); 
        
        private int filecount=0;
//        protected String boundary;
//        protected ServletInputStream inputStream;
//        protected int bufferSize = 4 * 1024;
//        protected boolean contentRead = false;
        
       
        public MultiReadHttpServletRequest(HttpServletRequest request) {
            
            super(request);
            encClass= URLEncryptionWrapper.getInstance();
            req=request;
            
            if(ServletFileUpload.isMultipartContent(request)==false){
            try {  
                parsePostRequest();
            } catch (Exception ex) {
                //Logger.getLogger(Security_2_Delink_gzip.class.getName()).log(Level.SEVERE, null, ex);
                //System.out.println("function in  Constructor::parsePostRequest::MultiReadHttpServletRequest "+ex.toString());
            }
              
            try {
                
                if (contentData == null) {parseGetRequest();}
                
            } catch (Exception ex) {
               // Logger.getLogger(Security_2_Delink_gzip.class.getName()).log(Level.SEVERE, null, ex);
                //System.out.println("function in  Constructor::parseGetRequest::MultiReadHttpServletRequest "+ex.toString());
            }
            }else{  
                // Multi part
                try{
                   // parsePostRequest();
                        getParamsFromMultipartForm(req);
                }catch(Exception ex){
                    System.out.println("function in  Constructor::parseRequest::MultiReadHttpServletRequest "+ex.toString());
                }
            }
              
        }       
    public  void getParamsFromMultipartForm(HttpServletRequest req) throws FileUploadException {
       
       try {
                       
                        String fieldname="",fieldvalue="",strEnch="";
                        FileItemFactory factory = new DiskFileItemFactory();
                        ServletFileUpload upload = new ServletFileUpload(factory);
                        List items = upload.parseRequest(req);
                        //List items = upload.parseRequest(new ServletRequestContext(req));
                        //List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(new ServletRequestContext(req));
                        String enc = req.getCharacterEncoding();
                        if (enc == null) {
                        enc = "UTF-8";
                        }
                        boolean decode = req.getContentType() != null && req.getContentType().equals("application/x-www-form-urlencoded");
                        Iterator iterator = items.iterator();
                       
                         byte[] data = new byte[req.getContentLength()];
                       int len = 0, totalLen = 0;
                       InputStream is = req.getInputStream();
                       HashMap<String, LinkedList<String>> mapA = new HashMap<String, LinkedList<String> >();//(data.length * 2);
                       LinkedList<String> list;
                       
                        while (iterator.hasNext()) {
                            FileItem item = (FileItem) iterator.next();
                            if (item.isFormField()) {
                                
                                 fieldname = item.getFieldName();
                                 fieldvalue = item.getString();
                            } else {
                                // Process form file field (input type="file").
                                fieldname = item.getFieldName();
                                fieldvalue = item.getName();
                                filecount=filecount+1;
                              //  new filecontent[filecount]= (item);
                                fileItemList.add(item);
                                
                                // ... (do your job here)
                            }
                            list = mapA.get(fieldname);
                            if (list == null) {
                                list = new LinkedList<String>();
                                mapA.put(fieldname, list);
                            }
                            list.add(fieldvalue);
                             try{
                            strEnch="";
                            strEnch=fieldname.substring(0, 6);//enchid_
                            }catch(Exception e){}
                            if(strEnch.equalsIgnoreCase("enchid") || strEnch.equalsIgnoreCase("enchip")|| strEnch.equalsIgnoreCase("enchis")){
                                String str1 = encClass.Decrypt(fieldvalue);
                                byte[] data1 = new byte[str1.length()];
                                String s1 = new String(str1.getBytes("UTF-8"), enc), name1, value1;
                                int ii;
                                StringTokenizer st1 = new StringTokenizer(str1, "&");

                                while (st1.hasMoreTokens()) {
                                    s1 = st1.nextToken();
                                    ii = s1.indexOf("=");
                                    if (ii > 0 && s1.length() > ii + 1) {
                                        name1 = s1.substring(0, ii);
                                        value1 = s1.substring(ii + 1);
                                        if (decode) {
                                            try {  
                                                name1 = URLDecoder.decode(name1, "UTF-8");
                                            } catch (Exception e) {
                                            }
                                            try {
                                                value1 = URLDecoder.decode(value1, "UTF-8");
                                            } catch (Exception e) {
                                            }
                                        }
                                        list = mapA.get(name1);
                                        if (list == null) {
                                            list = new LinkedList<String>();
                                            mapA.put(name1, list);
                                        }
                                        list.add(value1);
                                        System.out.println("name.."+name1 + " value is.."+value1);

                                    }
                                }
                            }// Decrypt values

                        }

                           HashMap<String, String[]> map = new HashMap<String, String[]>(mapA.size() * 2);
                           for (String key : mapA.keySet()) {
                               list = mapA.get(key);
                               map.put(key, list.toArray(new String[list.size()]));
                           }
                           parameterMap = map;

                        
                    } catch (Exception e) {
                        System.out.println(e.toString());
                    }
       
          
    }
    
                
                
 @Override
        public String getHeader(String name) {
            String header = req.getHeader(name);
            return header == null ? (String) req.getAttribute(name) : header;
        }

        @Override
        @SuppressWarnings("unchecked")
        public Enumeration<String> getHeaderNames() {
            List<String> headerNames = Collections.list(req.getHeaderNames());
            headerNames.addAll(Collections.list(req.getAttributeNames()));
                return Collections.enumeration(headerNames);
        }
          
        
     
    
    
          private void parsePostRequest() throws IOException {
            if (contentData != null) {
                return; //already parsed
            }
            int ilength=req.getContentLength();
            if(req.getContentLength()<=0) 
                return;
            byte[] data = new byte[req.getContentLength()];
            int len = 0, totalLen = 0;
            InputStream is = req.getInputStream();
            while (totalLen < data.length) {
                totalLen += (len = is.read(data, totalLen, data.length - totalLen));
                if (len < 1) {
                    throw new IOException("Cannot read more than " + totalLen + (totalLen == 1 ? " byte!" : " bytes!"));
                }
            }
            contentData = data;
            System.out.println("data=="+data);
            String enc = req.getCharacterEncoding();
            if (enc == null) {
                enc = "UTF-8";
            }
            String strEnch="";
            String s = new String(data, enc), name, value;
            StringTokenizer st = new StringTokenizer(s, "&");
            int i;
            HashMap<String, LinkedList<String>> mapA = new HashMap<String, LinkedList<String>>(data.length * 2);
            LinkedList<String> list;
            boolean decode = req.getContentType() != null && req.getContentType().equals("application/x-www-form-urlencoded");
            while (st.hasMoreTokens()) {
                s = st.nextToken();
                i = s.indexOf("=");
                if (i > 0 && s.length() > i + 1) {
                    name = s.substring(0, i);
                    value = s.substring(i + 1);
                    if (decode) {
                        try {
                            name = URLDecoder.decode(name, "UTF-8");
                        } catch (Exception e) {
                        }
                        try {   
                            value = URLDecoder.decode(value, "UTF-8");
                        } catch (Exception e) {
                        }
                    }
                    list = mapA.get(name);
                    if (list == null) {
                        list = new LinkedList<String>();
                        mapA.put(name, list);
                    }   
                    list.add(value);
                   // String str1= encClass.Decrypt(value);   
                    try{
                    strEnch="";
                    strEnch=name.substring(0, 6);//enchid_
                    }catch(Exception e){}
                    if(strEnch.equalsIgnoreCase("enchid") || strEnch.equalsIgnoreCase("enchip")|| strEnch.equalsIgnoreCase("enchis")){
                        String str1 = encClass.DecryptRequest(strEnch,value);
                        byte[] data1 = new byte[str1.length()];
                        String s1 = new String(str1.getBytes("UTF-8"), enc), name1, value1;   
                        int ii;
                        StringTokenizer st1 = new StringTokenizer(str1, "&");
         
                        while (st1.hasMoreTokens()) {
                            s1 = st1.nextToken();
                            ii = s1.indexOf("=");
                            if (ii > 0 && s1.length() > ii + 1) {
                                name1 = s1.substring(0, ii);
                                value1 = s1.substring(ii + 1);
                                if (decode) {
                                    try {  
                                        name1 = URLDecoder.decode(name1, "UTF-8");
                                    } catch (Exception e) {
                                    }
                                    try {
                                        value1 = URLDecoder.decode(value1, "UTF-8");
                                    } catch (Exception e) {
                                    }
                                }
                                list = mapA.get(name1);
                                if (list == null) {
                                    list = new LinkedList<String>();
                                    mapA.put(name1, list);
                                }
                                list.add(value1);
                    System.out.println("name.."+name1 + " value is.."+value1);
                            }
                        }
                    }// Decrypt values
                    
                    
                } // for for st stoken  
            }   
            HashMap<String, String[]> map = new HashMap<String, String[]>(mapA.size() * 2);
            for (String key : mapA.keySet()) {
                list = mapA.get(key);
                map.put(key, list.toArray(new String[list.size()]));
            }
            parameterMap = map;
        }  
                
    private void parseGetRequest() throws IOException {
        
            String str2= getQueryString();
            if(str2==null)
                return;
            byte[] data = new byte[str2.length()];
            int len = 0, totalLen = 0;
            InputStream is = req.getInputStream();
           /* while (totalLen < data.length) {
                totalLen += (len = is.read(data, totalLen, data.length - totalLen));
                if (len < 1) {
                    throw new IOException("Cannot read more than " + totalLen + (totalLen == 1 ? " byte!" : " bytes!"));
                }
            }*/   
            contentData = data;
            String enc = req.getCharacterEncoding();
            if (enc == null) {
                enc = "UTF-8";
            }
            String strEnch="";
            String s = new String(str2.getBytes("UTF-8"), enc), name, value;
            StringTokenizer st = new StringTokenizer(s, "&");
            int i;
            HashMap<String, LinkedList<String>> mapA = new HashMap<String, LinkedList<String>>(data.length * 2);
            LinkedList<String> list;
            boolean decode = req.getContentType() != null && req.getContentType().equals("application/x-www-form-urlencoded");
            while (st.hasMoreTokens()) {
                s = st.nextToken();
                i = s.indexOf("=");
                if (i > 0 && s.length() > i + 1) {
                    name = s.substring(0, i);
                    value = s.substring(i + 1);
                    if (decode) {
                        try {   
                            name = URLDecoder.decode(name, "UTF-8");
                        } catch (Exception e) {
                        }
                        try {   
                            value = URLDecoder.decode(value, "UTF-8");
                        } catch (Exception e) {
                        }
                    }
                    list = mapA.get(name);
                    if (list == null) {
                        list = new LinkedList<String>();
                        mapA.put(name, list);
                    }
                    list.add(value);
                   try{
                    strEnch="";
                    strEnch=name.substring(0, 6);//enchid_
                    }catch(Exception e){}
                    if(strEnch.equalsIgnoreCase("enchid") || strEnch.equalsIgnoreCase("enchip")|| strEnch.equalsIgnoreCase("enchis")){
                        String str1 = encClass.DecryptRequest(strEnch,value);
                        byte[] data1 = new byte[str1.length()];
                        String s1 = new String(str1.getBytes("UTF-8"), enc), name1, value1;
                        int ii;
                        StringTokenizer st1 = new StringTokenizer(str1, "&");
         
                        while (st1.hasMoreTokens()) {
                            s1 = st1.nextToken();
                            ii = s1.indexOf("=");
                            if (ii > 0 && s1.length() > ii + 1) {
                                name1 = s1.substring(0, ii);
                                value1 = s1.substring(ii + 1);
                                if (decode) {
                                    try {
                                        name1 = URLDecoder.decode(name1, "UTF-8");
                                    } catch (Exception e) {
                                    }
                                    try {
                                        value1 = URLDecoder.decode(value1, "UTF-8");
                                    } catch (Exception e) {
                                    }
                                }
                                list = mapA.get(name1);
                                if (list == null) {
                                    list = new LinkedList<String>();
                                    mapA.put(name1, list);
                                }
                                list.add(value1);
                    
                            }
                        }
                    }// Decrypt values
                    
                    
                } // for for st stoken  
            }
            HashMap<String, String[]> map = new HashMap<String, String[]>(mapA.size() * 2);
            for (String key : mapA.keySet()) {
                list = mapA.get(key);
                map.put(key, list.toArray(new String[list.size()]));
            }
            parameterMap = map;
        }
    
     
         public int getFileCount() {
             int count = 0;
             count = this.filecount;
             return count;
         }
     
         public List<FileItem> getFileContent() {
             List<FileItem> content= null;
             content = this.fileItemList;
             return content;
         }
        
    
        /** 
         * Only used internally to set the Map for the parameters. 
         * @param HashMap<String,String[]> parameterMap 
         */
        private void setParameterMap(HashMap<String, String[]> parameterMap) {
            this.parameterMap = parameterMap;
        }

        /** 
         * Set values for a parameter. 
         * This method allows parameters to be added at runtime. 
         * @param String name 
         * @param String[] value 
         */   
        public void setParameterValues(String name, String[] value) {
           // HashMap<String, String[]> params = HashMap<String, String[]>parameterMap;
            HashMap<String, String[]> params = parameterMap;
            params.put(name, value);
        }  
        
       
        
         public void setParameter(String name, String[] values) {
          //   protected HashMap<String,String[]> parameterMap; 
          HashMap <String, String[]> params = parameterMap;
            params.put(name, values);
        }  
         
          public void setParameter(String name, String value) {
          String[] values= new String[1];
            values[0]=value;
          HashMap <String, String[]> params = parameterMap;
            params.put(name, values);
        }  
          
         
        /** 
         * Returns an immutable java.util.Map of the parameters in this request. 
         * This method overrides the default behaviour since parameters may have been injected inside a filter. 
         * @return HashMap<String,String[]> 
         */
    @Override
        public HashMap<String, String[]> getParameterMap() {
            final HashMap<String, String[]> parameterMap = this.parameterMap;
            return parameterMap;
        }

        /** 
         * Returns an Enumeration of all parameter names in the request. 
         * The same data can be obtained by retrieving the keys from getParameterMap(). 
         * @return Enumeration<String> 
         */
        public Enumeration getParameterNames() {
            HashMap<String, String[]> params = getParameterMap();
                Set<String> names = params.keySet();
            final Iterator<String> it = names.iterator();
            Enumeration<String> namesEnum = new Enumeration<String>() {

                public boolean hasMoreElements() {
                    return it.hasNext();
                }

                public String nextElement() {
                    String next = it.next();
                    return next;
                }
            };
            return namesEnum;
        }
        
        /** 
         * Get a collection of values for the given parameter. 
         * If no such parameter exists, null will be returned. 
         * @param String name 
         * @return String[] 
         */
        public String[] getParameterValues(String name) {
           // String[] str ;//=getParameterMap().get(name);
               HashMap<String, String[]>  wrappedParams =getParameterMap();
              Object str1= wrappedParams.get(name);
            Set<String> names = wrappedParams.keySet();
            for (Iterator it = names.iterator(); it.hasNext();) {
                    Object key = it.next();
                     Object str = wrappedParams.get(key);
                     if(key.equals(name))
                         return (String[]) str;
               }
            
            return null;
        }

        /** 
         * Get the value of the given parameter. 
         * If more than one parameter exists for this name, only the first is returned. 
         * If the requested parameter doesn't exist, null is returned. 
         * @param String name 
         * @return String 
         */
        public String getParameter(String name) {
            String value = null;
            String[] values = getParameterValues(name);
            if (values != null) {
                value = values[0];
            }
            return value;
        }
           
              
          @Override
    public String getQueryString() {
              if (debug) {
                  System.out.println("Security_1_Delink_gzip::MultiReadHttpServletRequest getQueryString() localParams = ");
            }
                  return req.getQueryString();

        }
            
          
        @Override
  public StringBuffer getRequestURL()
	    {
    StringBuffer str=req.getRequestURL();
    return str;
  }
 
    
  
  /**
   * Returns the part of the URI corresponding to the application's
   * prefix.  The first part of the URI selects applications
   * (ServletContexts).
   *
   * <p><code>getContextPath()</code> is /myapp for the uri
   * /myapp/servlet/Hello,
   */
        @Override
  public String getContextPath()
	    {
    String str= req.getContextPath();
    return str;
  }
  /**
   * Returns the URI part corresponding to the selected servlet.
   * The URI is relative to the application.
   *
   * <p/>Corresponds to CGI's <code>SCRIPT_NAME</code>
   *
   * <code>getServletPath()</code> is /servlet/Hello for the uri
   * /myapp/servlet/Hello/foo.
   *
   * <code>getServletPath()</code> is /dir/hello.jsp
   * for the uri /myapp/dir/hello.jsp/foo,
   */          
        @Override
  public String getServletPath()
	       {  
    String strPath= req.getServletPath();
    
   /*  if (strPath.endsWith("/SessionID")) {
            String toReplace = strPath.substring(strPath.indexOf("/SessionID"), strPath.length());
            strPath = strPath.replace(toReplace, "/WebController");
        }*/
    
   /* if(str.startsWith("/WebController")){
        str=str.replace(str, "/AppController");
    }*/
    /*if(str.startsWith("/usermanager/")){
        String strChk = "/";
            int iChkIndex = str.indexOf(strChk,1);
            
        
        String toReplace = str.substring(iChkIndex+1, str.length());
        String newURI =str.substring(0,iChkIndex+1);
       String dec =enc.Decrypt(toReplace);
       str=dec;
       System.out.println("getServletPath"+str);
    }*/  
    return strPath;
  }
  /**
   * Returns the URI part after the selected servlet and null if there
   * is no suffix.
   *
   * <p/>Corresponds to CGI's <code>PATH_INFO</code>
   *
   * <p><code>getPathInfo()</code> is /foo for
   * the uri /myapp/servlet/Hello/foo.
   *
   * <code>getPathInfo()</code> is /hello.jsp for for the uri
   * /myapp/dir/hello.jsp/foo.
   */
        @Override   
  public String getPathInfo()
	    {
    String str= req.getPathInfo();
    return str;
  }
  /**
   * Returns the physical path name for the path info.
   *
   * <p/>Corresponds to CGI's <code>PATH_TRANSLATED</code>
   *
   * @return null if there is no path info.
   */    
        @Override
  public String getPathTranslated()
	    {
    String str= req.getPathTranslated();
    return str;
  }      
                 
         @Override
        public String getRequestURI() {
           // String originalURI = super.getRequestURI();
            //StringBuffer newURI = new StringBuffer();
            
            String newURI = req.getRequestURI();
            
          /*  if (newURI.endsWith("/SessionID")) {
            String toReplace = newURI.substring(newURI.indexOf("/SessionID"), newURI.length());
            newURI = newURI.replace(toReplace, "/WebController");
            }*/
            
           
            
            return newURI;
        }
         
          
    }



