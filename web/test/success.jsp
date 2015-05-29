<%-- 
    Document   : success
    Created on : Apr 2, 2015, 10:48:10 PM
    Author     : zeb
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello ${sessionScope.userid}</h1>
        <h1>Hello ${sessionScope.email}</h1>
        <h1>Hello ${sessionScope.role}</h1>
        <h1>Hello ${sessionScope.fname}</h1>
        <h1>Hello ${sessionScope.lname}</h1>
    </body>
</html>
