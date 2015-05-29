<%-- 
    Document   : UserEditProfile
    Created on : Apr 23, 2015, 4:30:02 PM
    Author     : zeb
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="header.jsp"></jsp:include>
<div class="container" align="center">
    <br>
      <nav role="navigation" class="page-navigation">
                <ul class="list-inline">
                    <li class="page-navigation-item"><a href="UserAccountOverview" class="page-navigation-link is-selected">Dashboard</a></li>
                    <li class="page-navigation-item"><a href="${pageContext.request.contextPath}/AccountSettings" class="page-navigation-link">Account Settings</a></li>
                    <li class="page-navigation-item"><a href="${pageContext.request.contextPath}/EditProfileForward" class="page-navigation-link">Edit Profile</a></li>
                </ul>
            </nav>
    <br>
    <h2>Edit Profile</h2>
    <br>
    
           
<form class="form-horizontal" method="post" action="EditProfile">
<fieldset>
    
        
  
<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="firstname">First Name</label>  
  <div class="col-md-4">
  <input id="firstname" name="firstname" placeholder="" class="form-control input-md" type="text">
    
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="lastname">Last Name</label>  
  <div class="col-md-4">
  <input id="lastname" name="lastname" placeholder="" class="form-control input-md" type="text">
    
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
<jsp:include page="footer.jsp"></jsp:include>