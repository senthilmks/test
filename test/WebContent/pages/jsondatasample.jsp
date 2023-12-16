<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib  uri="/WEB-INF/myTags.tld" prefix="apt" %>
<c:set var="isDataFound" value="false"/>
<jsp:useBean id="objDao" class="com.test.dao.EmployeeDAO" scope="request"/>
<c:set target="${objDao}" property="request" value="${pageContext.request}"/> 
<%@page contentType="application/json; charset=UTF-8"%>
<%=objDao.getEmployeesJSON()%>
