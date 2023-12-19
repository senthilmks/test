package com.test.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.test.LoginUser;
import com.test.util.db.DataAccessManager;

public class MenuGeneratorDAO extends DataAccessManager{
	StringBuilder strBufSQLQuery=null;
	ArrayList<Object> queryParametersList = null;
	
	public List getMenuList() {
		strBufSQLQuery= new StringBuilder();
		strBufSQLQuery.append("select menumain.menuid,menumain.parentmenuid,menumain.moduleid,menumain.resourceid,menumain.menuname,menumain.menushortname,menumain.menulink,menumain.menutypeid,menumain.menulevel,menumain.sortorder,modulename,moduleshortname,menutypename,resourcename ");
//		try {
//			strBufSQLQuery.append(new com.test.util.PassMan().EncryStr("resourcename "));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		strBufSQLQuery.append("from (");
		strBufSQLQuery.append("select tmm.menuid,parentmenuid,moduleid,resourceid,menuname,menushortname,menulink,menutypeid,menulevel,sortorder ");
		strBufSQLQuery.append("from usermanager.tblusermaster tum ");
		strBufSQLQuery.append("left join admin.tblofficewisemenus tom on tum.officeid=tom.officeid and ispublicaccess=1 and tom.activestatus=1 ");
		strBufSQLQuery.append("join admin.tblmenumaster tmm on tmm.menuid=tom.menuid and tmm.activestatus=1 ");
		//strBufSQLQuery.append("where tum.userid=? ");
		strBufSQLQuery.append(" union all ");
		strBufSQLQuery.append("select tmm.menuid,parentmenuid,moduleid,resourceid,menuname,menushortname,menulink,menutypeid,menulevel,sortorder  ");
		strBufSQLQuery.append("from usermanager.tblusermaster tum ");
		strBufSQLQuery.append("left join usermanager.tblusergroupwiseusers tugu on tugu.userid=tum.userid and tugu.activestatus=1 ");
		strBufSQLQuery.append("left join usermanager.tblusergroupwisemenus tuga on tugu.groupid=tuga.groupid and tuga.activestatus=1 ");
		strBufSQLQuery.append("left join admin.tblmenumaster tmm on tmm.menuid=tuga.menuid and tmm.activestatus=1 ");
		//strBufSQLQuery.append("where tum.userid=? ");
		strBufSQLQuery.append(" union all ");
		strBufSQLQuery.append("select tmm.menuid,parentmenuid,moduleid,resourceid,menuname,menushortname,menulink,menutypeid,menulevel,sortorder  ");
		strBufSQLQuery.append("from usermanager.tblusermaster tum ");
		strBufSQLQuery.append("left join admin.tbluserwisemenus tuwm on tuwm.userid=tum.userid and tuwm.activestatus=1  ");
		strBufSQLQuery.append("join admin.tblmenumaster tmm on tmm.menuid=tuwm.menuid and tmm.activestatus=1 ");		
		//strBufSQLQuery.append("where tum.userid=? ");
		strBufSQLQuery.append(") menumain ");
		strBufSQLQuery.append("join system.tblappmodulemaster tmm on tmm.moduleid=menumain.moduleid ");
		strBufSQLQuery.append("join system.tblmenutypemaster tmtm on tmtm.menutypeid=menumain.menutypeid ");
		strBufSQLQuery.append(" join system.tblappresourcemaster trm on trm.resourceid=menumain.resourceid ");
//		queryParametersList = new ArrayList();
//		queryParametersList.add(0,"2220");
		return resultSetToList(strBufSQLQuery.toString(),queryParametersList);
	}	
	
	
	public String getMenuDetails(String strMenuId) {
		strBufSQLQuery= new StringBuilder();
		strBufSQLQuery.append("select menumain.menuid,menumain.parentmenuid,menumain.moduleid,menumain.resourceid,menumain.menuname,menumain.menushortname,menumain.menulink,menumain.menutypeid,menumain.menulevel,menumain.sortorder,resourcename ");
		strBufSQLQuery.append("from admin.tblmenumaster menumain ");		
		strBufSQLQuery.append(" join system.tblappresourcemaster trm on trm.resourceid=menumain.resourceid ");
		strBufSQLQuery.append("where menumain.menuid=? ");
		queryParametersList = new ArrayList<Object>();
		queryParametersList.add(0,Integer.parseInt(strMenuId));
		return resultSetToJsonObj(strBufSQLQuery.toString(),queryParametersList);
	}
	
	public String getUserMenuDetails(String strMenuId) {
		strBufSQLQuery= new StringBuilder();
		strBufSQLQuery.append("SELECT MM.menuid AS mainmenuid,COALESCE(SM.menuid,0) AS submenuid, COALESCE(PM.menuid,0) AS popupmenuid,MM.menudisplayname AS MainMenuName, SM.menudisplayname AS SubMenuName,PM.menudisplayname AS PopupMenuName, ");
		strBufSQLQuery.append("SM.menulinkname AS submenupage,PM.menulinkname AS popupumenupage , MM.mainmenulink   FROM swi.menus MM ");		
		strBufSQLQuery.append("LEFT JOIN swi.menus SM ON MM.menuid = SM.parentmenuid AND SM.menulevel = 2  LEFT JOIN swi.menus PM ON SM.menuid = PM.parentmenuid AND PM.menulevel = 3 ");
		strBufSQLQuery.append("WHERE MM.menulevel = 1  and MM.activestatus=1 menumain.menuid=? ");
		strBufSQLQuery.append("WHERE MM.menulevel = 1  and MM.activestatus=1 menumain.menuid=?ORDER BY MM.menuid,MM.menusortnumber, MM.menudisplayname, SM.menusortnumber,SM.menudisplayname, PM.menusortnumber, pm.menudisplayname ");
		queryParametersList = new ArrayList<Object>();
		queryParametersList.add(0,Integer.parseInt(strMenuId));
		return resultSetToJsonObj(strBufSQLQuery.toString(),queryParametersList);
	}
	
//  String strQuery=" SELECT MM.menuid AS mainmenuid,COALESCE(SM.menuid,0) AS submenuid, COALESCE(PM.menuid,0) AS popupmenuid,MM.menudisplayname AS MainMenuName, SM.menudisplayname AS SubMenuName,PM.menudisplayname AS PopupMenuName,"
//  + " SM.menulinkname AS submenupage,PM.menulinkname AS popupumenupage , MM.mainmenulink   FROM swi.menus MM   "
//  +" LEFT JOIN swi.menus SM ON MM.menuid = SM.parentmenuid AND SM.menulevel = 2  LEFT JOIN swi.menus PM ON SM.menuid = PM.parentmenuid AND PM.menulevel = 3  "
//  +" WHERE MM.menulevel = 1  and MM.logintypeid in(3,"+ loginType +") and MM.activestatus=1 "
////  +" WHERE MM.menulevel = 1  and MM.logintypeid in(3,1) and MM.activestatus=1 "
//  +"ORDER BY MM.menuid,MM.menusortnumber, MM.menudisplayname, SM.menusortnumber,SM.menudisplayname, PM.menusortnumber, pm.menudisplayname";
	
	
	/*public ResultSet getEmployeeMenus(int loginType, long stdid, long officeid) {
        ResultSet rs = null;
        String strQuery, sque;

        try {

            String[] srs = getservice(stdid);
            String[] spl = getsplservice(stdid);
//            System.out.println("srs in ludao--" + srs[0] + srs[1]);
//            System.out.println("spl in ludao--" + spl[0] + spl[1]);
            // srs[0] -- serviceid   srs[1] -- menulevel
            String menulevel = srs[1];
            String sval[] = menulevel.split(",");
            List<String> list = Arrays.asList(sval);
            int counter = list.size();
            //special service
            String smenulevel = spl[1];
            String spval[] = smenulevel.split(",");
            List<String> slist = Arrays.asList(spval);
            int scounter = slist.size();

            strQuery = " (SELECT  COALESCE(SM.showmenu,1)as showmenu,COALESCE(SM.menuid,0) as uniqid,COALESCE(SMM.forcemode,0)as forcemode,COALESCE(SMM.executeorder,0) as executeorder,MM.classfunction as classname,MM.displayorder,SM.menusortnumber,MM.menuid AS mainmenuid,COALESCE(SM.menuid,0) AS submenuid, COALESCE(PM.menuid,0) AS popupmenuid,MM.menudisplayname AS MainMenuName, MM.propertyname as propertyMenuname,SM.menudisplayname AS SubMenuName,SM.propertyname AS SubpropertyMenuname,PM.menudisplayname AS PopupMenuName,PM.menudisplayname AS PopupPropertyMenuName,"
                    + " SM.menulinkname AS submenupage,PM.menulinkname AS popupumenupage , MM.mainmenulink   FROM swi.menus MM   "
                    + " LEFT JOIN swi.menus SM ON MM.menuid = SM.parentmenuid AND SM.menulevel = 2   and SM.activestatus=1 and SM.logintypeid in(3," + loginType + ")"
                    + " LEFT JOIN swi.menus PM ON SM.menuid = PM.parentmenuid AND PM.menulevel = 3 and PM.logintypeid in(3," + loginType + ")   LEFT JOIN academy.servicemappingmaster SMM on SMM.serviceid::numeric=SM.menuid and SMM.officeid=" + officeid
                    + " WHERE MM.menulevel = 1  and MM.logintypeid in(3," + loginType + ") and MM.activestatus=1 )";
            //  + " ORDER BY MM.displayorder,MM.menuid,MM.menusortnumber, MM.menudisplayname, SM.menusortnumber,SM.menudisplayname, PM.menusortnumber, pm.menudisplayname)";

            String squery = " (SELECT SM.showmenu,COALESCE(SM.menuid,0) as uniqid ,COALESCE(SM.forcemode,0)as forcemode,COALESCE(SM.executeorder,0) as executeorder,SM.classfunction as classname,MM.displayorder,SM.menusortnumber,MM.menuid AS mainmenuid,COALESCE(SM.menuid,0) AS submenuid, COALESCE(PM.menuid,0) AS popupmenuid,MM.menudisplayname AS MainMenuName, MM.propertyname as propertyMenuname,SM.menudisplayname AS SubMenuName,SM.propertyname AS SubpropertyMenuname,PM.menudisplayname AS PopupMenuName,PM.menudisplayname AS PopupPropertyMenuName,"
                    + " SM.menulinkname AS submenupage,PM.menulinkname AS popupumenupage , MM.mainmenulink   FROM swi.menus MM   "
                    + " LEFT JOIN swi.menus SM ON MM.menuid = SM.parentmenuid AND SM.menulevel = 2   and SM.activestatus = 3 and SM.logintypeid in(3," + loginType + ")"
                    + " LEFT JOIN swi.menus PM ON SM.menuid = PM.parentmenuid AND PM.menulevel = 3 and PM.logintypeid in(3," + loginType + ")  LEFT JOIN academy.servicemappingmaster SMM on SMM.serviceid::numeric=SM.menuid and  SMM.officeid=" + officeid
                    + " WHERE MM.menulevel = 1  and MM.logintypeid in(3," + loginType + ") and MM.activestatus in (1,3) and SM.menuid in (" + srs[0] + "," + spl[0] + ")) ";
            // + " ORDER BY MM.displayorder,MM.menuid,MM.menusortnumber, MM.menudisplayname, SM.menusortnumber,SM.menudisplayname, PM.menusortnumber, pm.menudisplayname)";

            String squery1 = " (SELECT PM.showmenu,COALESCE(PM.menuid,0)as uniqid ,COALESCE(SMM.forcemode,0)as forcemode,COALESCE(SMM.executeorder,0) as executeorder, MM.classfunction as classname,MM.displayorder,SM.menusortnumber,MM.menuid AS mainmenuid,COALESCE(SM.menuid,0) AS submenuid, COALESCE(PM.menuid,0) AS popupmenuid,MM.menudisplayname AS MainMenuName, MM.propertyname as propertyMenuname,SM.menudisplayname AS SubMenuName,SM.propertyname AS SubpropertyMenuname,PM.menudisplayname AS PopupMenuName,PM.menudisplayname AS PopupPropertyMenuName,"
                    + " SM.menulinkname AS submenupage,PM.menulinkname AS popupumenupage , MM.mainmenulink   FROM swi.menus MM   "
                    + " LEFT JOIN swi.menus SM ON MM.menuid = SM.parentmenuid AND SM.menulevel = 2   and SM.activestatus = 3 LEFT JOIN swi.menus PM ON SM.menuid = PM.parentmenuid AND PM.menulevel = 3     LEFT JOIN academy.servicemappingmaster SMM on MM.menuid=SMM.serviceid::numeric and SMM.officeid=" + officeid
                    + " WHERE MM.menulevel = 1  and MM.logintypeid in(3," + loginType + ") and MM.activestatus in (1,3) and PM.menuid in (" + srs[0] + "," + spl[0] + ")) ";
            //  + " ORDER BY MM.displayorder, MM.menuid,MM.menusortnumber, MM.menudisplayname, SM.menusortnumber,SM.menudisplayname, PM.menusortnumber, pm.menudisplayname)";

//            String strQuery=" SELECT MM.menuid AS mainmenuid,COALESCE(SM.menuid,0) AS submenuid, COALESCE(PM.menuid,0) AS popupmenuid,MM.menudisplayname AS MainMenuName, SM.menudisplayname AS SubMenuName,PM.menudisplayname AS PopupMenuName,"
//                            + " SM.menulinkname AS submenupage,PM.menulinkname AS popupumenupage , MM.mainmenulink   FROM swi.menus MM   "
//                            +" LEFT JOIN swi.menus SM ON MM.menuid = SM.parentmenuid AND SM.menulevel = 2  LEFT JOIN swi.menus PM ON SM.menuid = PM.parentmenuid AND PM.menulevel = 3  "
//                            +" WHERE MM.menulevel = 1  and MM.logintypeid in(3,"+ loginType +") and MM.activestatus=1 "
////                            +" WHERE MM.menulevel = 1  and MM.logintypeid in(3,1) and MM.activestatus=1 "
//                            +"ORDER BY MM.menuid,MM.menusortnumber, MM.menudisplayname, SM.menusortnumber,SM.menudisplayname, PM.menusortnumber, pm.menudisplayname";
            String sTmp = "";
            if (srs[0].equalsIgnoreCase("0") && spl[0].equalsIgnoreCase("0")) {
                sTmp = strQuery;
//                System.out.println("when serviceid is nul  " + strQuery);
            } else if (srs[0].equalsIgnoreCase("0") && slist.contains("2")) {
                sTmp = strQuery + " union " + squery;
//                System.out.println("serviceid nt nul = ");
            } else if (srs[0].equalsIgnoreCase("0") && slist.contains("3")) {
                sTmp = strQuery + " union " + squery1;
//                System.out.println("serviceid nt nul = ");
            } else if (counter > 1) {
                sTmp = strQuery + " union " + squery + " union " + squery1;
//                System.out.println("serviceid nt nul = ");
            } else if (list.contains("2") && spl[0].equalsIgnoreCase("0")) {
                sTmp = strQuery + " union " + squery;
//                System.out.println("serviceid nt nul = ");
            } else if (list.contains("2") && slist.contains("2")) {
                sTmp = strQuery + " union " + squery;
//                System.out.println("serviceid nt nul = ");
            } else if (list.contains("2") && slist.contains("3")) {
                sTmp = strQuery + " union " + squery + " union " + squery1;
//                System.out.println("serviceid nt nul = ");
            } else if (list.contains("3") && spl[0].equalsIgnoreCase("0")) {
                sTmp = strQuery + " union " + squery1;
//                System.out.println("serviceid nt nul = ");
            } else if (list.contains("3") && slist.contains("2")) {
                sTmp = strQuery + " union " + squery + " union " + squery1;
//                System.out.println("serviceid nt nul = ");
            } else if (list.contains("3") && slist.contains("3")) {
                sTmp = strQuery + " union " + squery1;
//                System.out.println("serviceid nt nul = ");
            }

            sTmp = "Select * from ( " + sTmp + " ) as main order by main.displayorder,main.menusortnumber asc ";
            System.out.println("sTmp = " + sTmp);
            rs = sm.srm.dbo.fetchData.getResultSet(sTmp);

        } catch (Exception e) {
        }
        return rs;
    }*/
	

	
	
	 public String getMenuIteration(ListIterator<menuListBean> itr, HttpServletRequest request) {
	        String strMenuName = "";
	        String strPageName = "";
	        String strMenulink = "";
	        long mmid = 0, smid = 0, pmid = 0;
	        int ipopOpen = 0, itype = 0, ievencount = 0;
	        int isubmenuUL = 0;
	        StringBuffer sBody = new StringBuffer();
	        sBody.append("<ul id=\"blueMenu\">");
	        try {
//	            while(rsNext){
	            while (itr.hasNext()) {
	                strMenuName = "";
	                menuListBean mlb = (menuListBean) itr.next();

	                if (mmid != mlb.getMainmenuid() && mlb.getShowmenu() > 0) {
	                    try {
	                        strMenuName = mlb.getPropertymenuname();
	                    } catch (Exception e) {
	                    }
	                    try {
	                        strMenulink = mlb.getMainmenulink();
	                    } catch (Exception e) {
	                        strMenulink = "";
	                    }
	                    if (strMenulink == null) {
	                        strMenulink = "";
	                    }
	                    strMenulink = strMenulink.trim();
//	                                System.out.println("strMenulink = " + strMenulink); 

	                    if (ipopOpen == 1) {
	                        ipopOpen = 0;
	                        sBody.append("</ul> </li>");
	                    }
	                    if (mmid != 0 && mmid != mlb.getMainmenuid() && isubmenuUL == 0) {
	                        sBody.append("</li>");
	                    } else if (mmid != 0 && mmid != mlb.getMainmenuid() && isubmenuUL == 1) {
	                        isubmenuUL = 0;
	                        if (itype == 1) {
	                            itype = 0;
	                            int iTemp = ievencount % 2;
	                            if (iTemp != 0) {
	                                sBody.append("<li> </li>");
	                            }
	                            sBody.append("<li class=\"istylei1\"><a href=\"#\">&nbsp</a></li>");

	                        }
	                        sBody.append("<li class=\"istylei1\"><a href=\"#\">&nbsp</a></li>");
	                        sBody.append("</ul> </li>");
	                    }
	                    sBody.append(" <li  class=\"istylei0\">");

	                    if (strMenulink.isEmpty() == false) {
	                        sBody.append("<a class=\"istylei0\" onclick=\"AjaxPostMethod('" + SMService.getMenu(request, strMenulink) + "')\">" + (SMService.getResourceValue(strMenuName, request) == null ? mlb.getMainmenuname() : SMService.getResourceValue(strMenuName, request)) + "</a>");
//	                                    sBody.append("<a  onclick=\"AjaxPostMethod('test1.jsp')\">" + strMenuName + "</a>");
	                    } else {
	                        sBody.append("<a >" + (SMService.getResourceValue(strMenuName, request) == null ? mlb.getMainmenuname() : SMService.getResourceValue(strMenuName, request)) + "</a>");

	                    }
	                } // Top menu End

	                if (smid != mlb.getSubmenuid()) {
	                    try {
	                        strMenuName = mlb.getSubpropertymenuname();
	                        strPageName = mlb.getSubmenupage();
	                    } catch (Exception e) {
	                    }
	                    if (strPageName == null) {
	                        strPageName = "javascript:undefined";
	                    } else {
	                        strPageName = strPageName;
	                    }

	                    if (mlb.getSubmenuid() > 0 && mlb.getShowmenu() > 0) {
	                        if (ipopOpen == 1) {
	                            ipopOpen = 0;
	                            sBody.append("</ul> </li>");
	                        }
	                        if (mmid != mlb.getMainmenuid()) {
	                            isubmenuUL = 1;
	                            if (mlb.getMainmenuname().equals("Academic")) {
	                                itype = 1;
	                                sBody.append("<ul class=\"istylem0\">");
	                            } else {
	                                sBody.append("<ul class=\"istylem01\">");
	                            }
	                        }
	                        // sTmp=strPageName;
	                        if (strPageName.equals("1") || strPageName.equals("2") || strPageName.equals("3")) {
	                            strPageName = strPageName;
	                        } else {
	                            strPageName = SMService.getMenu(request, strPageName);
	                        }
	                        if (mlb.getPopupmenuid() > 0) {
	                            sBody.append("<li><a style=\"cursor: pointer;width: 160px\">" + (SMService.getResourceValue(strMenuName, request) == null ? mlb.getSubmenuname() : SMService.getResourceValue(strMenuName, request)) + "</a>");
//	                                            sBody.append("<li><a onclick=\"AjaxPostMethod('" + strPageName + "')\" style=\"cursor: pointer;width: 160px\">" + (SMService.getResourceValue(strMenuName)==null ?  mlb.getSubmenuname() : SMService.getResourceValue(strMenuName))  + "</a>");
	                            if (itype == 1) {
	                                ievencount++;
	                            }
	                        } else {
	                            sBody.append("<li><a onclick=\"AjaxPostMethod('" + strPageName + "')\" style=\"cursor: pointer;width: 160px\">" + (SMService.getResourceValue(strMenuName, request) == null ? mlb.getSubmenuname() : SMService.getResourceValue(strMenuName, request)) + "</a></li>");
	                            if (itype == 1) {
	                                ievencount++;
	                            }
	                        }

	                    }
	                }

	                if (pmid != mlb.getPopupmenuid() && mlb.getShowmenu() > 0) {
	                    try {
	                        strMenuName = mlb.getPopuppropertymenuname();
	                        strPageName = mlb.getPopupumenupage();
	                    } catch (Exception e) {
	                    }
	                    if (strPageName == null) {
	                        strPageName = "javascript:undefined";
	                    } else {
	                        strPageName = strPageName;
	                    }
	                    if (mlb.getPopupmenuid() > 0) {
	                        if (smid != mlb.getSubmenuid()) {
	                            ipopOpen = 1;
	                            sBody.append("<ul  style=\"width: 160px\">");
	                        }
//	                        System.out.println("showmenu>>>>>>>>>" + mlb.getShowmenu());
	                        if (mlb.getShowmenu() == 1) {
	                            sBody.append("<li><a onclick=\"AjaxPostMethod('" + SMService.getMenu(request, strPageName) + "')\" style=\"cursor: pointer;width: 160px\">" + (SMService.getResourceValue(strMenuName, request) == null ? mlb.getPopupmenuname() : SMService.getResourceValue(strMenuName, request)) + "</a></li>");
	                            pmid = mlb.getPopupmenuid();
	                        }
	                    }
	                }

	                mmid = mlb.getMainmenuid();
	                smid = mlb.getSubmenuid();
	            }// While End
	            if (isubmenuUL > 0) {
	                sBody.append("<li class=\"istylei1\"><a href=\"#\">&nbsp</a></li>");
	                sBody.append("</ul></li>");
	            }
	            sBody.append("</ul>");
//	            System.out.println("sBody.."+sBody.toString());

	        } catch (Exception e) {
	            System.out.println("Menu Iteration Error = " + e.toString());
	        }
	        return sBody.toString();
	    }
	 
	 
	 // JSP
	 
	 <% 
     List<menuListBean> iMenuList = null;
       boolean rsNext=false;
       try {
           iMenuList = (List<menuListBean>) session.getAttribute("rsmenu");
//           if ((iMenuList.isEmpty() || iMenuList == null)||(strurl.equals("usermanagerUserHomePage.jsp"))) {
               if ((iMenuList.isEmpty() || iMenuList == null)) {
                   rsNext = false;
               } else {
                   rsNext = true;
               }
           } catch (Exception e) {
          System.out.println("exception" + e);
          rsNext = false;
       }
       if (!rsNext) {
           
         ResultSet menuRs = lu.getEmployeeMenus(lu.getLoginType(),lu.getStudentId(),lu.getOfficeId());  
           ResultSetMapper<menuListBean> resultSetMapper = new ResultSetMapper<menuListBean>();
           iMenuList = resultSetMapper.mapRersultSetToObject(menuRs, menuListBean.class, true);
           session.setAttribute("rsmenu", iMenuList);
           menuRs.close();
           lu.getService().Initialize(iMenuList);
           menuRs =  lu.getEmployeeMenusDetails(lu.getLoginType(),lu.getStudentId(),lu.getOfficeId()); 
           lu.getService().UpdateRelativePage(menuRs);
           menuRs.close();
       }
       String strMenuName = "";
       String strPageName = "";
       long mmid=0, smid=0, pmid = 0;
       int ipopOpen=0;
       int isubmenuUL=0;
       String strMenulink = "";
       try{
         String sBody = lu.getMenuIteration(iMenuList.listIterator(), request);
         out.print(sBody);
       }catch(Exception e){
           System.out.println("Menu Iteration Error = " + e.toString());
       }
       %>
}
