<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/myTags.tld" prefix="apt"%>
<c:set var="isDataFound" value="false" />
<jsp:useBean id="objDao" class="com.test.dao.MenuDAO" scope="request" />
<jsp:useBean id="objPassMan" class="com.test.util.PassMan" 	scope="request" />
<c:set target="${objDao}" property="request" value="${pageContext.request}" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript">
            
             function funNavigateController(e,targetPage) {
                 var targetPage=targetPage;
                  e = e || window.event;
                  e.stopPropagation;     
                  var frmMain=document.getElementById("frmMain");
                   frmMain.resourceid.value=targetPage;
                 //  alert("Before submit");
                    frmMain.submit();
            }
</script>

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

	<input type="hidden" name="resourceid" id="resourceid" value="" />

	<div>
		<a href="" onclick="return false;"
			onmouseup="funNavigateController(event,'pP3GA+NETMJXLv9j7eE51Q=='); return false;">
			Logout Encryp</a>
	</div>

