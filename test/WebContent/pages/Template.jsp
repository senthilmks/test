<%-- 
    Document   : EmployeeView
    Created on : 27 Jun, 2022, 3:42:21 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <script src="resources/js/common.js"></script>
        
        <script src="resources/scripts/jquery-3.6.0.min.js" type="text/javascript"></script>
		<link href="resources/css/select2.min.css" rel="stylesheet" />
		<link href="resources/css/select2-bootstrap.min.css" rel="stylesheet" />
		<link href="resources/css/bootstrap.min.css" rel="stylesheet" />
		<script src="resources/scripts/select2.min.js"></script>
		
		<link rel="stylesheet" href="resources/css/dataTables.bootstrap5.min.css"/> 
		<script src="resources/js/dataTables.bootstrap5.min.js" type="text/javascript"></script> 
		<script src="resources/js/jquery.dataTables.min.js" type="text/javascript"></script> 
   
        <link rel="stylesheet" href="resources/css/validationEngine.jquery.css"/>
        <script src="resources/scripts/jquery-ui.min.js" type="text/javascript"></script> 
        <link rel="stylesheet" href="resources/css/jquery-ui.min_cupertino.css"/>  
        
        
        <link rel="stylesheet" href="resources/css/jquery.dataTables.min.css"/> 
       <!--  <script src="resources/scripts/jquery.dataTables.min.js" type="text/javascript"></script> --> 
         <script src="resources/scripts/datatables.min.js" type="text/javascript"></script>
         
          
        <script src="resources/scripts/jquery.validationEngine.min.js" type="text/javascript"></script> 
        <script src="resources/scripts/jquery.validationEngine-en.min.js" type="text/javascript"></script> 
        <script src="resources/scripts/jquery-ui-timepicker-addon.min.js" type="text/javascript"></script> 
        <link rel="stylesheet" href="resources/css/jquery-ui-timepicker-addon.min.css"/> 
        <link rel="stylesheet" href="resources/css/jquery.dataTables.yadcf.css"/> 
        <script src="resources/scripts/jquery.dataTables.yadcf.min.js" type="text/javascript"></script>
        
        
        

        <title>MyView</title>
    </head>
    <body onload="funLoadPage(event,'3'); return false;">
        <form id="frmMain" name="frmMain" method="post" autocomplete="off">
            <h1 align="center">Hello World! Template</h1>
            

            <table style="width:80%" border="0" align="center" >
            <tr>                    
                    <td colspan="2" align="center">                               
                        Header   
                    </td>
                </tr>
                <tr>     
                <td>                               
                        <div><jsp:include page="includes/MenuView.jsp" flush="false" /></div>   
                    </td>               
                    <td>                               
                        <div id="divPageContainer" name="divPageContainer" style="width: 100%; height: auto;overflow-x: none; overflow-y: none; display: none;" >Default Content</div>   
                    </td>
                </tr>
            </table>
           
			<%--<input type="hidden" id="resource" name="resource" value='${requestScope.resource}' /> --%>
			<input type="hidden" id="resource" name="resource" value='employee' />
			<input type="hidden" name="resourceid" id="resourceid" value="" /> 
			
        </form>
        


    </body>
</html>
