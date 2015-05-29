<%-- 
    Document   : ResetPasswordGetEmail
    Created on : Apr 25, 2015, 7:29:35 PM
    Author     : zeb
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="header.jsp"></jsp:include>
<br>
    <div class="container" align="center">
        <h2> RESET PASSWORD!</h2>
        <form action="ResetPassword" method="post" class="form-horizontal">
               <div class="form-group">
          <label class="col-md-4 control-label" for="emailField">Email </label>  
          <div class="col-md-4">
          <input id="emailField" name="email" type="email" placeholder="blah@gmail.com" class="form-control input-large" required="">

          </div>
        </div>
               <div class="form-group">
                        <label class="col-md-4 control-label" for="submit"></label>
                        <div class="col-md-4">
                            <button id="submit" name="submit" class="btn btn-primary">Submit</button>
                        </div>
                    </div>
            
        </form>
        
        
    </div>
<jsp:include page="footer.jsp"></jsp:include>
