<%-- 
    Document   : deleteuser
    Created on : Apr 7, 2015, 6:48:47 PM
    Author     : zeb
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="adminheader.jsp"></jsp:include>
<c:choose>
    <c:when test="${userinfobean.getRole() != 'Admin'}">
    </c:when>
    <c:otherwise>
        <br>
        <div class="container">
            <h2 align="center">
                Delete a User by Email 
            </h2>   

            <form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/AdminDeleteUser">
                <fieldset>


                    <!-- Text input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="textinput">Email</label>  
                        <div class="col-md-5">
                            <input id="emailField" name="email" placeholder="email@email.com" class="form-control input-md" type="text">

                        </div>
                    </div>

                    <!-- Button -->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="submit"> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;    </label>
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