<%-- 
    Document   : redirect
    Created on : Apr 7, 2015, 8:17:48 PM
    Author     : zeb
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="refresh" content="5;url=${pageContext.request.contextPath}/${sessionScope.RedirectLink}">
        <title>Redirecting..</title>
    </head>
    <body>
        <h1>${sessionScope.Redirect}!</h1>
        <h1>Redirecting...</h1>
    </body>
</html>
