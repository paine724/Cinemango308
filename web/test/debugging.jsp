<%-- 
    Document   : testingjsp
    Created on : May 4, 2014, 3:50:36 PM
    Author     : Andrew
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Debugging JSP</title>
    </head>
    <body>
        <h1>Debugging</h1>
        ${ErrorMsg.getErrorMsg()}<BR>
        <table>
            <tr>
                <th>pageContext.session Name</th>
                <th>pageContext.session Value</th>
            </tr>
            <tr>
                <td>creationTime</td>
                <td><c:out value="${pageContext.session.creationTime}"/></td>
            </tr>
            <tr>
                <td>id</td>
                <td><c:out value="${pageContext.session.id}"/></td>
            </tr>
            <tr>
                <td>lastAccessedTime</td>
                <td><c:out value="${pageContext.session.lastAccessedTime}"/></td>
            </tr>
            <tr>
                <td>maxInactiveInterval</td>
                <td><c:out value="${pageContext.session.maxInactiveInterval}"/></td>
            </tr>
            <tr>
                <th>pageContext.servletContext Name</th>
                <th>pageContext.servletContext Value</th>
            </tr>
            <tr>
                <td>serverInfo</td>
                <td><c:out value="${pageContext.servletContext.serverInfo}"/></td>
            </tr>
            <tr>
                <td>servletContextName</td>
                <td><c:out value="${pageContext.servletContext.servletContextName}"/></td>
            </tr>
            <tr>
                <td>minorVersion</td>
                <td><c:out value="${pageContext.servletContext.minorVersion}"/></td>
            </tr>
            <tr>
                <td>majorVersion</td>
                <td><c:out value="${pageContext.servletContext.majorVersion}"/></td>
            </tr>
            <tr>
                <th>pageContext.request Name</th>
                <th>pageContext.request Value</th>
            </tr>
            <tr>
                <td>characterEncoding</td>
                <td><c:out value="${pageContext.request.characterEncoding}"/></td>
            </tr>
            <tr>
                <td>contentType</td>
                <td><c:out value="${pageContext.request.contentType}"/></td>
            </tr>
            <tr>
                <td>locale</td>
                <td><c:out value="${pageContext.request.locale}"/></td>
            </tr>
            <tr>
                <td>locales</td>
                <td><c:out value="${pageContext.request.locales}"/></td>
            </tr>
            <tr>
                <td>protocol</td>
                <td><c:out value="${pageContext.request.protocol}"/></td>
            </tr>
            <tr>
                <td>remoteAddr</td>
                <td><c:out value="${pageContext.request.remoteAddr}"/></td>
            </tr>
            <tr>
                <td>remoteHost</td>
                <td><c:out value="${pageContext.request.remoteHost}"/></td>
            </tr>
            <tr>
                <td>scheme</td>
                <td><c:out value="${pageContext.request.scheme}"/></td>
            </tr>
            <tr>
                <td>serverName</td>
                <td><c:out value="${pageContext.request.serverName}"/></td>
            </tr>
            <tr>
                <td>serverPort</td>
                <td><c:out value="${pageContext.request.serverPort}"/></td>
            </tr>
            <tr>
                <td>secure</td>
                <td><c:out value="${pageContext.request.secure}"/></td>
            </tr>
            <tr>
                <th>Parameter Name</th>
                <th>Parameter Value</th>
            </tr>
            <c:forEach var="p" items="${param}">
                <tr>
                    <td><c:out value="${p.key}"/></td>
                    <td><c:out value="${p.value}"/></td>
                </tr>
            </c:forEach>
            <tr>
                <th>Header Name</th>
                <th>Header Value</th>
            </tr>
            <c:forEach var="h" items="${header}">
                <tr>
                    <td><c:out value="${h.key}"/></td>
                    <td><c:out value="${h.value}"/></td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
