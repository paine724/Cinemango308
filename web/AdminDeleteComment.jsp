<%-- 
    Document   : AdminDeleteComment
    Created on : Apr 23, 2015, 12:59:25 AM
    Author     : zeb
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <body>
        <jsp:include page="adminheader.jsp"></jsp:include>
        <c:choose>
            <c:when test="${userinfobean.getRole() != 'Admin'}">

            </c:when>
        
        <c:otherwise> 




            <div class="container">
                <br>
                <br>
                <br>
                <h2> Delete Flagged Review</h2>
                <br>
                <br>
                <br>
                <c:choose> 
                    <c:when test="${not empty sessionScope.flagged}">
                        <form method="post" action="AdminDeleteComment">
                            <select class="form-control" name="review">
                                <c:forEach var="comment" items="${sessionScope.flagged}">
                                    <option value="${comment.getReviewID()}">${comment.getReview()}</option>
                                </c:forEach>
                            </select>



                            <br>
                            <input type="submit" class="btn btn-default">
                        </form>
                    </c:when>
                    <c:otherwise> 
                        There are no flagged reviews on the site!
                        
                        </c:otherwise>
                </c:choose> 


            </div>
        </c:otherwise>
            </c:choose>  
    </body>
</html>
