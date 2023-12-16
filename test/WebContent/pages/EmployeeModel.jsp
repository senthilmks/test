<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib  uri="/WEB-INF/myTags.tld" prefix="apt" %>
<c:set var="isDataFound" value="false"/>
<jsp:useBean id="objDao" class="com.test.dao.EmployeeDAO" scope="request"/>
<c:set target="${objDao}" property="request" value="${pageContext.request}"/> 
<c:choose>
    <c:when test="${param.action eq 'viewrs'}">  
        <table style="width:100%" id="tblTest" class="table border ui-corner-all ui-widget-content">
            <thead class="ui-widget-header">
                <tr>
                    <th >Emp.No.</th>
                    <th >Emp.Name</th>
                    <th><img src='resources/images/close-4.jpg' onclick="funCloseLoaderDiv(this)"/></th>
                </tr>
            </thead>
            <tbody>
                <apt:process resultSet="<%=objDao.getEmployeesRs()%>"> 
                    <tr style="cursor:pointer" id="<c:out value="${EMP_NO}" />" handle="<c:out value="${EMP_NAME}" />"  onclick="funSelectVal(this, 0);" >
                        <td><c:out value="${EMP_NO}" /> </td>  
                        <td><c:out value="${EMP_NAME}" /> </td> 
                    </tr>
                </apt:process>
            </tbody>
        </table>
    </c:when> 
    
     <c:when test="${param.action eq 'view'}"> 
     
     <div class="table-responsive">
        <table id="tblEmpData" class="table table-primary table-hover table-sm caption-top">
        <caption>List of users</caption>
            <thead >
                <tr>
                    <th scope="col">Emp.No.</th>
                    <th scope="col">Emp.Name</th>
                   <!--  <th scope="col"><img src='resources/images/close-4.jpg' onclick="funCloseLoaderDiv(this)"/></th>  -->
                </tr>
            </thead>
            <tbody>
                <c:set var="rowList" value="${objDao.employeeList}"/>
                <c:forEach var="rows"  items="${rowList}" varStatus="status">
                    <c:set var="isDataFound" value="true"/>
                    <c:set var="columnList" value="${rows}"/>
                    <tr style="cursor: pointer" id="${columnList.userid}" handle="${columnList.username}" onclick="selectValues(this, 0)">
                   
                    <td>${columnList.userid}</td> 
                        <td>${columnList.username}</td>
                        
                    </tr>
                </c:forEach>
                <c:if test="${isDataAvailable == false}">
                    <tr> <td colspan="2" align="center">No Record(s) found..!</td></tr>
                </c:if>                
            </tbody>
        </table>
        </div>
        
    </c:when> 
    
    <c:when test="${param.action eq 'viewlist'}">  
        <table style="width:100%" id="tblTest" class="table border ui-corner-all ui-widget-content">
            <thead class="ui-widget-header">
                <tr>
                    <th >Emp.No.</th>
                    <th >Emp.Name</th>
                    <th><img src='resources/images/close-4.jpg' onclick="funCloseLoaderDiv(this)"/></th>
                </tr>
            </thead>
            <tbody>
                <c:set var="rowlistAcademic" value="${objDao.employeesList}"/>
                <c:forEach var="rows"  items="${rowlistAcademic}" varStatus="status">
                    <c:set var="isDataFound" value="true"/>
                    <c:set var="columnList" value="${rows}"/>
                    <tr style="cursor: pointer" id="${columnList.EMP_NO}" handle="${columnList.EMP_NAME}" onclick="selectValues(this, 0)">
                    <td><c:out value="${columnList.EMP_NO}" /> </td> 
                        <td colspan="2">${columnList.EMP_NAME}</td>
                    </tr>
                </c:forEach>
                <c:if test="${isDataAvailable == false}">
                    <tr> <td colspan="2" align="center">No Record(s) found..!</td></tr>
                </c:if>
            </tbody>
        </table>
    </c:when> 
    
    <c:when test="${param.action eq 'select2search'}">  
        <%@page contentType="application/json; charset=UTF-8"%>
		<%=objDao.getEmployeesJSON()%>
    </c:when> 
    
</c:choose> 
