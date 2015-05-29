<%-- 
    Document   : redcarpet
    Created on : Feb 25, 2015, 7:27:19 PM
    Author     : zeb
--%>

 <jsp:include page="header.jsp"></jsp:include>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <c:forEach var="current" items="${RedCarpet}">
     <c:out value="${current}"/><BR>
 </c:forEach>
     <div class =" container">
                 <script type="text/javascript" src="js/jcarousel.redcarpet.js"></script>
                    <link rel="stylesheet" type="text/css" href="css/jcarousel.redcarpet.css">
                <div class="redcarpet-wrapper">
                    <div class="redcarpet">
                        <div class="loading">Loading carousel items...</div>
                    </div>

                    <a href="#" class="redcarpet-control-prev">&lsaquo;</a>
                    <a href="#" class="redcarpet-control-next">&rsaquo;</a>
                </div>
     </div>
<jsp:include page="footer.jsp"></jsp:include>