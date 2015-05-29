<%-- 
    Document   : AdministratorHomepage
    Created on : Apr 7, 2015, 4:24:24 PM
    Author     : zeb
--%>
<%--
    Document   : header
    Created on : Feb 26, 2015, 6:02:26 PM
    Author     : Andrew
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <jsp:include page="adminheader.jsp"></jsp:include>
    <c:choose>
        <c:when test="${userinfobean.getRole() != 'Admin'}">
        </c:when>
    <c:otherwise>    
        <div class="container">
            
             <div align="center">
        <h1 style="color:black">Hello Administrator ${userinfobean.firstName}</h1>
             <h3> Please select a link above to control movie or user information
    </h3>
          </div>
        </div>
</body>
</html>
</c:otherwise>
</c:choose>