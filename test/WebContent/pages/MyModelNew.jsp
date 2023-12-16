<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib  uri="/WEB-INF/myTags.tld" prefix="apt" %>
<c:set var="isDataFound" value="false"/>
<jsp:useBean id="objDao" class="com.test.dao.CommonMethodsDAO" scope="request"/>
<c:set target="${objDao}" property="request" value="${pageContext.request}"/> 
<c:choose>
    <c:when test="${param.opr == 1}">  
        <table style="width:100%" id="tblTest" class="table border ui-corner-all ui-widget-content">
            <thead class="ui-widget-header">
                <tr>
                    <th >Office Id</th>
                    <th >Office Name</th>
                    <th><img src='resources/images/close-4.jpg' onclick="funCloseLoaderDiv(this)"/></th>
                </tr>
            </thead>
            <tbody>
                <apt:process resultSet="<%=objDao.getOffices()%>"> 
                    <tr style="cursor:pointer" id="<c:out value="${OFFICEID}" />" handle="<c:out value="${OFFICENAME}" />"  onclick="funSelectVal(this, 0);" >
                        <td><c:out value="${OFFICEID}" /> </td>  
                        <td><c:out value="${OFFICENAME}" /> </td> 
                    </tr>
                </apt:process>
            </tbody>
        </table>
    </c:when> 
    <c:when test="${param.opr == 2}">  
        <table style="width:98%" cellpadding="1" id="tblEmpData" class="ui-corner-all ui-widget-content">
            <thead class="ui-widget-header">
<!--                 <tr>
                     <th colspan="3"> Sample Data</th>
                </tr>-->
                <tr>
                    <th><input type="checkbox" id="chkAllOff" name="chkAllOff" onclick="funCheckAll(this);"/></th>
                    <th>Office ID</th>
                    <th>Office Name</th>
                </tr>
            </thead>
<!--            <tfoot class="ui-state-highlight">
                  <th>Office ID</th>
                    <th>Office Name</th>
            </tfoot>-->
            <tbody>
                <apt:process resultSet="<%=objDao.getOffices()%>"> 
                    <c:set var="isDataFound" value="true"/>
                    <tr class="ui-widget-content" style="cursor:pointer" title="Click to view" onClick="funViewDetails(${OFFICEID})" handle="<c:out value="${OFFICEID}" />"   >
                        <td><input type="checkbox" id="chkOff${OFFICEID}" name="chkOff${OFFICEID}" class="clsChkOff" value="${OFFICEID}" /> </td>  
                        <td><c:out value="${OFFICEID}" /> </td>  
                        <td><c:out value="${OFFICENAME}" /> </td> 
                    </tr>
                </apt:process>
                    <c:if test="${isDataFound == false}"> 
                        <tr class="ui-widget-content" style="cursor:pointer" title="Click to view" onClick="funViewDetails(${OFFICEID})" handle="<c:out value="${OFFICEID}" />"   >
                        <td>No Records found </td>  
                    </tr>
                </c:if>
            </tbody>
        </table>
    </c:when> 
    <c:when test="${param.opr == 3}">  
        <%@ page contentType="application/json"%>    
        <%=objDao.saveHostel()%>
    </c:when> 
</c:choose> 
