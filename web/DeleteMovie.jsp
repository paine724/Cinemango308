<%-- 
    Document   : DeleteMovie
    Created on : Apr 7, 2015, 11:39:50 PM
    Author     : zeb
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<jsp:include page="adminheader.jsp"></jsp:include>
    <c:choose>
        <c:when test="${userinfobean.getRole() != 'Admin'}">
    </c:when>
    <c:otherwise>
        <br>
        <div class="container">
            <h2>DELETE MOVIE </h2>
            <form class="form-horizontal" method="post" action="AdminDeleteMovie">
                <fieldset>



                    <!-- Text input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="name">Movie Name</label>  
                        <div class="col-md-4">
                            <input id="name" name="name" placeholder="50 shades of grey" class="form-control input-md" type="text">

                        </div>
                    </div>

                    <!-- Button -->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="submit"></label>
                        <div class="col-md-4">
                            <button id="submit" name="submit" class="btn btn-primary">Submit</button>
                        </div>
                    </div>

                </fieldset>
            </form>
        </div>
        </body>
        </html>
    </c:otherwise>
</c:choose>