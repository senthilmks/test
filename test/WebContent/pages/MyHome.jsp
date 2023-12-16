<%-- 
    Document   : index
    Created on : 8 Jul, 2022, 2:47:35 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>        
    </head>
    <body>
               <h1>Welcome to Hello World! 123</h1>
               
               <form id="frmMain" name="frmMain" method="post" autocomplete="off">
               <jsp:include page="common/frmMenuDisplay.jsp" flush="false" />
               </form>
               
               
    </body>
</html>
