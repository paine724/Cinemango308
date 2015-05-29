<%-- 
    Document   : signin
    Created on : Feb 25, 2015, 5:46:20 PM
    Author     : zeb
--%>

 <jsp:include page="header.jsp"></jsp:include>
 <body>
     <div class="container">
         <br>
       
 <form method="post" action="Signin">
     <div class="form-group">
     <label for="email">Email address</label>    
     <br>
     <input type="email" name="email" id="email" placeholder="Enter Email">
     </div>
     
     <div class="form-group">
     <label for="password">Password</label>   
     <br>
     <input type="password" name="password" id="passwordField">
     </div>
    <button type="submit" class="btn btn-default">Submit</button>
     
     
 </form>
         
         <a href="${pageContext.request.contextPath}/ResetPasswordForward"> Forgot Password?</a>       
     </div>
        <jsp:include page="footer.jsp"></jsp:include>


