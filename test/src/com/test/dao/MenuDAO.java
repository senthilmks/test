package com.test.dao;

import java.util.ArrayList;
import java.util.List;

import com.test.LoginUser;
import com.test.util.db.DataAccessManager;

public class MenuDAO extends DataAccessManager{
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
}
