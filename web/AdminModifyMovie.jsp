<%-- 
    Document   : AdminModifyMovie
    Created on : Apr 9, 2015, 4:35:01 PM
    Author     : zeb
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<jsp:include page="adminheader.jsp"></jsp:include>
    <c:choose>
        <c:when test="${userinfobean.getRole() != 'Admin'}">
    </c:when>
    <c:otherwise>
        <br>
        <div class="container">
            <div align="center">
                <h2>Modify a Movie </h2>
            </div>

            <br>
            <form class="form-horizontal" method="post" action="ModifyMovie">
                <fieldset>

                    <!-- Form Name -->


                    <!-- Text input-->
                    <div class="form-group">

                        <label class="col-md-4 control-label" for="name">Movie Name</label>  
                        <div class="col-md-4">
                            <input id="name" name="name" placeholder="Movie Name" class="form-control input-md" required="" type="text">

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