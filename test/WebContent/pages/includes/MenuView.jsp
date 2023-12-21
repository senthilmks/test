<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/myTags.tld" prefix="apt"%>
<c:set var="isDataFound" value="false" />
<jsp:useBean id="objDao" class="com.test.dao.MenuDAO" scope="request" />
<jsp:useBean id="objPassMan" class="com.test.util.PassMan" 	scope="request" />
<c:set target="${objDao}" property="request" value="${pageContext.request}" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP Page</title>
</head>
<body>
		<div><a href="" onclick="return false;" onmouseup="funLoadPage(event,'3'); return false;"  > Login </a></div>
	  <div><a href="" onclick="return false;" onmouseup="funLoadPage(event,'1'); return false;"  > Page1 </a></div>
        <div><a href="" onclick="return false;" onmouseup="funLoadPage(event,'2'); return false;"  > Page2 </a></div>
        <div><a href="" onclick="return false;" onmouseup="funRequestDispatcher(event,'1'); return false;"  > Employee </a></div>
  	
</body>
</html>
