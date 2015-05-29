<%-- 
    Document   : AdminAddUser
    Created on : Apr 7, 2015, 6:04:12 PM
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
            <form class="form-horizontal" method="post" action="AdminAddUser">
                <fieldset>

                    <!-- Form Name -->
                    <legend>Add a User</legend>

                    <!-- Text input-->
                    <div class="control-group">
                        <label class="control-label" for="firstname">First Name</label>
                        <div class="controls">
                            <input id="firstname" name="firstname" placeholder="First Name" class="input-large" required="" type="text">

                        </div>
                    </div>

                    <!-- Text input-->
                    <div class="control-group">
                        <label class="control-label" for="lastname">Last Name</label>
                        <div class="controls">
                            <input id="lastname" name="lastname" placeholder="Last Name" class="input-large" required="" type="text">

                        </div>
                    </div>

                    <!-- Text input-->
                    <div class="control-group">
                        <label class="control-label" for="email">Email</label>
                        <div class="controls">
                            <input id="email" name="email" placeholder="hey@hello.com" class="input-large" type="text">

                        </div>
                    </div>

                    <!-- Password input-->
                    <div class="control-group">
                        <label class="control-label" for="password">Password </label>
                        <div class="controls">
                            <input id="password" name="password" placeholder="placeholder" class="input-large" type="password">

                        </div>
                    </div>

                    <!-- Text input-->
                    <div class="control-group">
                        <label class="control-label" for="address">Address</label>
                        <div class="controls">
                            <input id="address" name="address" placeholder="123 place" class="input-large" type="text">

                        </div>
                    </div>

                    <!-- Multiple Radios -->
                    <div class="control-group">
                        <label class="control-label" for="role">Role</label>
                        <div class="controls">
                            <label class="radio" for="role-0">
                                <input name="role" id="role-0" value="Registered User" checked="checked" type="radio">
                                Registered User
                            </label>
                            <label class="radio" for="role-1">
                                <input name="role" id="role-1" value="Administrator" type="radio">
                                Administrator
                            </label>
                        </div>
                    </div>

                    <!-- Button -->
                    <div class="control-group">
                        <label class="control-label" for="submit"></label>
                        <div class="controls">
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