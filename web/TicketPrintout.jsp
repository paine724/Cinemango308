<%-- 
    Document   : TicketPrintout
    Created on : Apr 25, 2015, 4:31:34 PM
    Author     : Andrew
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp"></jsp:include>
    <!DOCTYPE html>
    <div class="container">
        <div align="center">
            <h2> You have spent $${sessionScope.ticketcost}0 </h2>
        <h4> Here is a breakdown of your ticket purchase(s):</h4> 
    </div>
        <div align="center">    
    <c:forEach var="bean" items="${sessionScope.pbeans}">
        <div align="left" style="border:1px solid black;width: 45%">
        <b>Movie</b>: ${bean.getMovieName()}<br>
        <b>Theatre</b>: ${bean.getTheatreName()}<br>
        <b>Ticket Price</b>: $${bean.getPrice()}0<br>
        <b>Type</b>: 
        <c:if test="${bean.getTicketType() == 1}">
            Adult <br>
        </c:if>
        <c:if test="${bean.getTicketType() == 2}">
            Child <br>
        </c:if>
        <c:if test="${bean.getTicketType() == 3}">
            Senior Citizen <br>
        </c:if>
            <br>
            </div>
    </c:forEach>
        </div>
        <div align="center">
        <a href="IndexForwardServlet"> Go back to homepage</a>
        </div>
</div>
<jsp:include page="footer.jsp"></jsp:include>
