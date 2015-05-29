<%-- 
    Document   : ModifyUser
    Created on : Apr 7, 2015, 8:50:11 PM
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
            <form class="form-horizontal" method="post" action="AdminModifyUser">
                <fieldset>

                    <!-- Form Name -->
                    <legend>Modify User</legend>

                    <!-- Text input-->

                    <div class="form-group">
                        <label class="col-md-4 control-label" for="oldemail">Current email</label>  
                        <div class="col-md-4">
                            <input id="oldemail" name="oldemail" value="${Edit.getEmail()}" class="form-control input-md"  readOnly="true">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="email">New Email</label>  
                        <div class="col-md-4">
                            <input id="email" name="email" value="${Edit.getEmail()}" class="form-control input-md" type="email">

                        </div>
                    </div>

                    <!-- Text input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="fname">First Name</label>  
                        <div class="col-md-4">
                            <input id="fname" name="fname" value="${Edit.getFirstName()}" class="form-control input-md" type="text">

                        </div>
                    </div>

                    <!-- Text input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="lname">Last Name</label>  
                        <div class="col-md-4">
                            <input id="lname" name="lname" value="${Edit.getLastName()}" class="form-control input-md" type="text">

                        </div>
                    </div>


                    <!-- Select Basic -->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="role">Role</label>
                        <div class="col-md-4">
                            <select id="role" name="role" class="form-control" >
                                <option value="RegisteredUser" selected>User</option>
                                <option value="Admin">Administrator</option>
                            </select>
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