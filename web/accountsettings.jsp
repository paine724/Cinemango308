<%-- 
    Document   : accountsettings
    Created on : Apr 4, 2015, 10:05:48 PM
    Author     : zeb
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="header.jsp"></jsp:include>
    <body>
        <div class="container">
            <br>

            <nav role="navigation" class="page-navigation">
                <ul class="list-inline">
                    <li class="page-navigation-item"><a href="UserAccountOverview" class="page-navigation-link is-selected">Dashboard</a></li>
                    <li class="page-navigation-item"><a href="#" class="page-navigation-link">Account Settings</a></li>
                    <li class="page-navigation-item"><a href="${pageContext.request.contextPath}/EditProfileForward" class="page-navigation-link">Edit Profile</a></li>
                </ul>
            </nav>
            <h2>${ErrorMsg.getErrorMsg()}</h2>
            <h2> Change Email </h2>
             
          <form method="post" action="ChangeEmail">
        <div class="form-group">
         <label for="oldemail">Current Email:</label>   
          ${userinfobean.email}
        </div>
        <br>
     <div class="form-group">
     <label for="newemail">Email address</label>    
     <br>
     <input type="email" name="newemail" id="email" placeholder="Enter Email">
     </div>
     
     <div class="form-group">
    <label for="email">Confirm Email address</label>    
     <br>
     <input type="email" name="confirmemail" id="email" placeholder="Confirm Email">
     </div>
    <button type="submit" class="btn btn-default">Submit</button>
     
     
 </form>

         
            <h2> Change Password </h2>
             
          <form method="post" action="ChangePassword">

     <div class="form-group">
     <label for="oldpw">Current Password</label>    
     <br>
     <input type="password" name="oldpw" id="passwordField" placeholder="Enter Current Password">
     </div>
     
     <div class="form-group">
    <label for="password">New Password</label>    
     <br>
     <input type="password" name="password" id="passwordField" placeholder="New Password">
     </div>
    <button type="submit" class="btn btn-default">Submit</button>
     
     
 </form>   
        <jsp:include page="AddCreditCard.jsp"></jsp:include>    
            
            
        </div>


    </body>



<jsp:include page="footer.jsp"></jsp:include>