<%-- 
    Document   : EmployeeView
    Created on : 27 Jun, 2022, 3:42:21 PM
    Author     : Administrator
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/myTags.tld" prefix="apt"%>
<c:set var="isDataFound" value="false" />
<jsp:useBean id="objDao" class="com.test.MyTestDaoJson" scope="request" />
<c:set target="${objDao}" property="request" value="${pageContext.request}" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- <link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" rel="stylesheet" />
<script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script> -->

<!-- Styles -->
<link rel="stylesheet" 	href="resources/css/bootstrap.min.css" />
<link rel="stylesheet" 	href="resources/css/select2.min.css" />
<link rel="stylesheet" 	href="resources/css/select2-bootstrap-5-theme.min.css" /> 
<!-- Or for RTL support -->
<link rel="stylesheet" href="resources/css/select2-bootstrap-5-theme.rtl.min.css" /> 

<!-- Scripts -->
<!--<script src="resources/scripts/jquery-3.6.1.min.js"></script> -->
<script	src="resources/scripts/jquery.slim.min.js"></script>
<script src="resources/scripts/bootstrap.bundle.min.js"></script>
<script src="resources/scripts/select2.min.js"></script> 


<jsp:include page="MyPageHeader.jsp" flush="false" />

<title>MyView1</title>
</head>
<body>
	<form id="frmTest" name="frmTest" method="post" autocomplete="off">
		<h1 align="center">Hello World! Select2 View</h1>
		<div>
			<select class="js-data-example-ajax" id="selectexample">
				<option value="3620194" >select2/select2</option>
			</select>
		</div>

		<div>
			<select class="js-example-basic-single">
				<option value="AL">Alabama</option>
				<option value="WY">Wyoming</option>
			</select>
		</div>
		<input type="hidden" id="resource" name="resource" value="${param.resource}" />
	</form>

	<script type="text/javascript">
	var mydata='[{"id":1,"trustName":"SRM Institute of Science & Technology","trustShortName":"SRMIST","activeStatus":1,"transactionId":1},{"id":2,"trustName":"Valliammai Society","trustShortName":"Valliammai","activeStatus":1,"transactionId":1}]'
	
$(document).ready(function() {
 
  
 
  
});
</script>

</body>
</html>
