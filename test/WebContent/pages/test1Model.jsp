<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib  uri="/WEB-INF/myTags.tld" prefix="apt" %>  
<c:set var="isDataFound" value="false"/>
<jsp:useBean id="objDao" class="com.test.MyTestDaoJson" scope="request"/>
<c:set target="${objDao}" property="request" value="${pageContext.request}"/> 
<c:choose>
    <c:when test="${param.opr == 1}">  
        <table style="width:100%" id="tblTest" class="table border ui-corner-all ui-widget-content">
            <thead class="ui-widget-header">
                <tr>
                    <th >Title ID</th>
                    <th >Title Name</th>
                    <th><img src='../resources/images/close-4.jpg' onclick="funCloseLoaderDiv(this)"/></th>
                </tr>
            </thead>
            <tbody>
             <%--   <apt:process resultSet="<%=objDao.findUsersLike()%>"> 
                    <c:set var="isData" value="true" />
                    <tr style="cursor:pointer" id="<c:out value="${EMP_NO}" />" handle="<c:out value="${EMP_NAME}" />"  onclick="funSelectVal(this, 0);" >
                        <td><c:out value="${EMP_NO}" /> </td>  
                        <td colspan="2"><c:out value="${EMP_NAME}" /> </td> 
                    </tr>
                </apt:process>   --%>
             
            <%-- <c:set var="rowList" value="${objDao.users}" />
             <c:forEach var="row" items="${rowList}" varStatus="status" >                
             <c:set var="columnList" value="${row}" />
             <c:set var="isData" value="true" />
             
            <tr style="cursor:pointer" id="<c:out value="${columnList.employeecode}" />" handle="<c:out value="${columnList.employeefirstandmidname}" />"  onclick="funSelectVal(this, 0);" >
                        <td nowrap><c:out value="${columnList.employeecode}" /> </td>  
                        <td nowrap><c:out value="${columnList.employeefirstandmidname}" /> </td> 
            </tr>
            </c:forEach> --%>
            
            <c:set var="rowList" value="${objDao.users}" />
             <c:forEach var="row" items="${rowList}" varStatus="status" >                
             <c:set var="columnList" value="${row}" />
             <c:set var="isData" value="true" />
             
            <tr style="cursor:pointer" id="<c:out value="${columnList[0]}" />" handle="<c:out value="${columnList[1]}" />"  onclick="funSelectVal(this, 0);" >
                        <td nowrap><c:out value="${columnList[0]}" /> </td>  
                        <td nowrap><c:out value="${columnList[1]}" /> </td> 
            </tr>
            </c:forEach>
            
            <c:if test="isData eq false">
                 <tr><td colspan="2">No Data Found</td></tr>                
            </c:if>
            </tbody>
        </table>
    </c:when> 
    <c:when test="${param.opr == 2}">  
        <table style="width:100%" id="tblEmpData" class="ui-corner-all ui-widget-content">
            <thead class="ui-widget-header">
                 <tr>
                    <th colspan="2">Sample Data</th>
                </tr>
                <tr>
                    <th>Emp.No.</th>
                    <th>Emp.Name</th>
                </tr>
            </thead>
            <tbody>
                <apt:process resultSet="<%=objDao.findSampleUsers()%>"> 
                    <tr class="ui-widget-content" style="cursor:pointer" title="Click to view" onClick="funViewDetails(${EMP_NO})" handle="<c:out value="${EMP_NO}" />"   >
                        <td><c:out value="${EMP_NO}" /> </td>  
                        <td><c:out value="${EMP_NAME}" /> </td> 
                    </tr>
                </apt:process>
            </tbody>
        </table>
    </c:when> 
    <c:when test="${param.opr == 3}">  
        <%@ page contentType="application/json"%>    
        <%=objDao.saveUser()%>
    </c:when>    
    <c:when test="${param.opr == 4}">  
        <table style="width:100%" id="tblViewInfo" class="ui-corner-all ui-widget-content">
            <thead>
                <tr  class="ui-widget-header">
                    <th colspan="2">Details</th>
                </tr>
                <tr  class="ui-widget-header">
                    <th>Emp.No.</th>
                    <th>Emp.Name</th>
                </tr>
            </thead>
            <tbody class="ui-widget-content">
                <apt:process resultSet="<%=objDao.findUniqueUser()%>"> 
                    <tr handle="<c:out value="${EMP_NAME}" />"   >
                        <td><c:out value="${EMP_NO}" /> </td>  
                        <td><c:out value="${EMP_NAME}" /> </td> 
                    </tr>
                </apt:process>
            </tbody>
        </table>
    </c:when> 
</c:choose> 
