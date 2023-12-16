package com.test.controller; 

import com.test.LoginUser;
import com.test.dao.MenuDAO;
import com.test.services.EmployeeService;
import com.test.services.LoginService;
import com.test.services.MenuService;
import com.test.services.NavigationService;
import com.test.services.ServiceInterface;
import com.test.services.UserService;
import com.test.util.PassMan;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
//
//import srm.clib.common.util.Services;
//import srm.clib.common.util.TransServices;
//import srm.clib.cos.AdminMsgMasterCO;
//import srm.clib.cos.CertificateCO;
//import srm.clib.cos.ChangePassCO;
//import srm.clib.cos.CommunicatorCO;
//import srm.clib.cos.EPFApplicationCO;
//import srm.clib.cos.EmployeeMasterCO;
//import srm.clib.cos.EmployeeRegistrationCO;
//import srm.clib.cos.HomeCO;
//import srm.clib.cos.LoginCheckCO;
//import srm.clib.cos.LogoutCO;
//import srm.clib.cos.PhotoUploadCO;
//import srm.clib.cos.SessionOutCO;
//import srm.clib.cos.StudentMasterCO;
//import srm.clib.cos.UpdateBioAttendanceToTransactionCO;
//import srm.clib.cos.accesscontrol.AccessControlListCO;
//import srm.clib.cos.gate.LeaveCO;
//import srm.clib.db.custom.LogUserInfoBDO;
//import srm.clib.db.custom.PhotoUploadBDO;
//import srm.clib.initializer.Initializer;
//import srm.clib.model.CommandObject;

/**
 * Servlet implementation class for Servlet: AppController
 *
 */
public class AppController extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Hashtable appServices = new Hashtable();
	private DataSource datasrc;
	//	TransServices transServices=null;
	//private DataSource attendDataSrc;

	public AppController() {
		super();
	}   	

	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
		// TODO Auto-generated method stub
	}  	

	//	public void initDB(ServletConfig config)
	//	{
	//		try{ 
	//			datasrc = new Initializer().createDBCon(config);
	//			//attendDataSrc = (DataSource) config.getServletContext().getAttribute("attendDS");
	//			
	//			System.out.println("CLIB : Data source created in AppController >> initDB >> "+datasrc);
	//			//System.out.println("CLIB : Data source created in AppController >> initDB >> Attendance Data Source >> "+attendDataSrc);
	//		}
	//		catch(Exception e)
	//		{
	//			System.out.println("CLIB : Database Connection Error : "+e.toString());
	////			String errMsg ="Exception occured in Class: "+this.getClass().getName()+", Method: The error message is "+e.toString();
	////			request.setAttribute("errMsg",errMsg);
	////			request.setAttribute("targetJSP","ErrorPage.jsp");
	//		}
	//	}

	public void initServices()
	{
		//		System.out.println("CLIB : in InitCmds in AppController");
		appServices.put("NAVIGATION",new NavigationService());
		appServices.put("LOGIN",new LoginService());		
		appServices.put("EMPLOYEE",new EmployeeService());
		appServices.put("USER",new UserService());
		appServices.put("MENU",new MenuService());
		//		appServices.put("SESSIONOUT",new SessionOutCO());		
		//		appServices.put("CHANGEPASS",new ChangePassCO());
		//		appServices.put("HOME",new HomeCO());
		//		appServices.put("EPFAPPLICATION",new EPFApplicationCO());
		//		appServices.put("EMPREGISTRATION",new EmployeeRegistrationCO());
		//		appServices.put("ACCESSCONTROLLIST",new AccessControlListCO());
		//		appServices.put("EMPLOYEEMASTER",new EmployeeMasterCO());
		//		appServices.put("COMMUNICATOR",new CommunicatorCO()); 
		//		appServices.put("CERTIFICATE",new CertificateCO());
		//		appServices.put("LEAVE",new LeaveCO());
		//		appServices.put("STUDENTMASTER",new StudentMasterCO());
		//		appServices.put("ADMINMSGMASTER",new AdminMsgMasterCO());
		//		appServices.put("SCHEDULE",new UpdateBioAttendanceToTransactionCO());
		//		appServices.put("PHOTOUPLOAD",new PhotoUploadCO());
		//		transServices=new TransServices(); 
	}  

	public void init(ServletConfig config) throws ServletException
	{
		super.init(config);
		//		initDB(config);
		initServices();
	}
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try
		{
			MenuDAO menuDAO=null;
			String strRequestIP = request.getRemoteHost();		
			String strMenuId = request.getParameter("resourceid");
			String strResource =request.getParameter("resource");
			String strModuleName="pages";

			System.out.println("CLIB : in pageName of AppController >> "+strResource);
			boolean sessionExpired = false;

			HttpSession session = request.getSession();	

			String strTargetPage="";
			//MenuService menuService = new MenuService();

			//			if(strMenuId!=null && strMenuId.trim().length()>0){
			//				menuDAO = new MenuDAO();
			//				ArrayList menuDetails=(ArrayList) menuDAO.getMenuDetails(strMenuId);
			//				System.out.println("menuDetails>>"+menuDetails.size());
			//			}


			
			if(strMenuId!=null && strMenuId.trim().length()>0){
				strResource="MENU";
			}
			else if(strResource==null){
				strResource="LOGIN";
			}
			//			else
			//			{
			//				//request.setAttribute("action", "GETMENUDETAILS");
			//				//menuService.execute(this, request, response, datasrc, session);
			////				//strResource=new PassMan().DecryStr(strResource);
			////				strModuleName="pages/includes";
			//				
			//			}

			// Enable the following lines to session time out
			//			if(strResource==null || ((session.getAttribute("logUserInfoBDO")==null)&& (!(strResource.equals("LOGIN") || strResource.equals("EPFAPPLICATION") || strResource.equals("EMPREGISTRATION")))) )
			//			{
			//				sessionExpired=true;
			//				targetJSP ="SessionOut.jsp";
			//				RequestDispatcher rd1 = request.getRequestDispatcher(targetJSP);
			//				rd1.forward(request,response);
			//				return;
			//			}
			//			else if(strResource.equals("LOGIN"))
			//			{
			//				String sessionVal="";
			//				System.out.println("CLIB : AppController >> LOGIN by default");
			//				Enumeration e= session.getAttributeNames();
			//				while (e.hasMoreElements())
			//				{
			//					sessionVal= (String) e.nextElement();
			//					//session.removeAttribute(sessionVal);//commented by Mahesh J for capcha code
			//					if(!opr.equalsIgnoreCase("checkLogin"))
			//					{
			//						session.removeAttribute(sessionVal);
			//					}
			//				}
			//			}
			// End of Session Time out


			//			strTargetPage = (String) request.getParameter("strTargetPage");
			//			System.out.println("Decrypted strTargetPage >> "+strTargetPage);


			ServiceInterface service;
			service = (ServiceInterface) appServices.get(strResource.toUpperCase());

			if(service==null) {
				strTargetPage="ResourceNotFoundView.jsp";
			}
			//			else if(datasrc==null) {
			//				strTargetPage="strResourceNotFoundView.jsp";
			//			}
			//			else if (session.getAttribute("logUser")==null || (LoginUser)session.getAttribute("logUser") ==null  || (strTargetPage==null || strTargetPage.trim().length()==0) ) 
			//			{
			//				strTargetPage="LoginView.jsp";
			//			}
			else {
				service.execute(this,request,response,datasrc,session);	
				strTargetPage = (String) request.getAttribute("targetPage"); 
				if(strResource.equalsIgnoreCase("menu")) {				
					strResource=(String) request.getAttribute("resource");
					request.setAttribute("resource",strResource);
				}
			}			
			System.out.println("CLIB : strTargetPage in AppController >> "+strTargetPage+", resource >> "+strResource);

			strTargetPage=strModuleName+"/"+strTargetPage;

			RequestDispatcher rd = request.getRequestDispatcher(strTargetPage);
			rd.forward(request,response);
		}
		catch(Exception e)
		{
			String errMsg ="Exception occured in Class: "+this.getClass().getName()+", Method: The error message is "+e.getMessage();
			System.out.println("AppController >> errMsg >> "+errMsg);
			request.setAttribute("errMsg",errMsg);
			request.setAttribute("targetJSP","LoginView.jsp");
		}
	}   	  	    
}