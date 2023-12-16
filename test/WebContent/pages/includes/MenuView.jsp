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
 	<jsp:include page="../common/frmMenuDispatcher.jsp" flush="false" />
	<!--  <div><a href="" onclick="return false;" onmouseup="funNavigateController(event,'HTvbTyJAjIy+5lKuvGfw8w=='); return false;"  > My Employee </a></div>
        <div><a href="" onclick="return false;" onmouseup="funNavigateController(event,'MyViewNew.jsp'); return false;"  > My View New </a></div>
        <div><a href="" onclick="return false;" onmouseup="funNavigateController(event,'MyDataTableSample.jsp'); return false;"  > My Data Table Sample </a></div>
        <div><a href="" onclick="return false;" onmouseup="funNavigateController(event,'MyDataTableSampleNew.jsp'); return false;"  > My Data Table</a></div>
        <div><a href="" onclick="return false;" onmouseup="funNavigateController(event,'MyDataTableColumnSearch.jsp'); return false;"  > My Data Table Column Search</a></div>
        <div><a href="" onclick="return false;" onmouseup="funNavigateController(event,'MyModalWindow.jsp'); return false;"  > My Modal Window</a></div>
        <div><a href="" onclick="return false;" onmouseup="funNavigateController(event,'MyLogin.jsp'); return false;"  > Logout</a></div>
        
         -->

	<li><a href="#">Categories</a>
		<ul class="droprightMenu">
			<li><a href="#">All</a>
				<ul class="droprightMenu">

					<!-- first loop, extracting categories level 1  -->

					<c:set var="rowList" value="${objDao.menuList}" />
					<c:forEach var="rows" items="${rowList}" varStatus="status">
						<c:set var="isDataFound" value="true" />
						<c:set var="columnList" value="${rows}" />
						<c:if test="${columnList.parentmenuid==0}">
							<li>
								<div>
									<a href="" onclick="return false;" onmouseup="funNavigateController(event,'${columnList.menuid}'); return false;">${columnList.menuname} </a>
								</div>
                                <!-- second loop, extracting categories level 2  -->
                                    <ul class="droprightMenu">  
                                    <c:set var="columnListSub" value="${rows}" />              										
                                            <c:if test="${columnListSub.parentmenuid==columnList.menuid }">
                                                <li>
                                                <div>
												<a href="" onclick="return false;" onmouseup="funNavigateController(event,'${columnList.menuid}'); return false;">${columnList.menuname} </a>
												</div>

                                                <%--  <!-- third loop, categories level 3  -->
                                                    <ul class="droprightMenu">
                                                        <c:forEach items="${menuList}" var="catLevel3">

                                                            <c:if test="${catLevel3.parentmenuid==catLevel2.menuid }">
                                                                <li><a href="#">${catLevel3.menuname }</a></li>
                                                            </c:if>

                                                        </c:forEach>
                                                    </ul>--%>
                                                    
                                                    </li>
                                            </c:if>
                                    </ul>  

							</li>
						</c:if>
					</c:forEach>
					<c:if test="${isDataAvailable == false}">
						<tr>
							<td colspan="2" align="center">No Record(s) found..!</td>
						</tr>
					</c:if>



				</ul></li>
		</ul></li>

	<%--  <c:set var="rowList" value="${objDao.menuList}"/>
                <c:forEach var="rows"  items="${rowList}" varStatus="status">
                    <c:set var="isDataFound" value="true"/>
                    <c:set var="columnList" value="${rows}"/>
                    <div><a href="" onclick="return false;" onmouseup="funNavigateController(event,'${columnList.resourcename}'); return false;"  >${columnList.menuname} </a></div>                    
                </c:forEach>  --%>

	<div>
		<a href="" onclick="return false;"
			onmouseup="funNavigateController(event,'pP3GA+NETMJXLv9j7eE51Q=='); return false;">
			Logout Encryp</a>
	</div>
</body>
</html>
