<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib  uri="/WEB-INF/myTags.tld" prefix="apt" %>
<c:set var="isDataFound" value="false"/>
<jsp:useBean id="objDao" class="com.test.dao.MenuGeneratorDAO" scope="request"/>
<c:set target="${objDao}" property="request" value="${pageContext.request}"/> 
<c:choose>

<c:when test="${param.action eq 'generatemenu11'}">  
        <%@page contentType="application/text; charset=UTF-8"%>
		<%=objDao.getUserMenuGenerated()%>
    </c:when> 
    
    <c:when test="${param.action eq 'generatemenu'}">  
        <table style="width:100%" id="tblTest" class="table border ui-corner-all ui-widget-content">
            <thead class="ui-widget-header">
                <tr>
                    <th >Menu ID</th>
                    <th >Menu Name</th>
                    <th >Menu Link</th>
                    <th><img src='resources/images/close-4.jpg' onclick="funCloseLoaderDiv(this)"/></th>
                </tr>
            </thead>
            <tbody>
                <c:set var="rowList" value="${objDao.getUserMenuGenerated()}"/>
                <c:forEach var="rows"  items="${rowList}" varStatus="status">
                    <c:set var="isDataFound" value="true"/>
                    <c:set var="columnList" value="${rows}"/>
                    <tr style="cursor: pointer" id="${columnList.mainmenuid}" handle="${columnList.mainmenuname}" onclick="selectValues(this, 0)">
                    <td><c:out value="${columnList.mainmenuid}" /> </td> 
                        <td colspan="2">${columnList.mainmenuname}</td>
                    </tr>
                </c:forEach>
                <c:if test="${isDataAvailable == false}">
                    <tr> <td colspan="2" align="center">No Record(s) found..!</td></tr>
                </c:if>
            </tbody>
        </table>
    </c:when> 
    
</c:choose> 
