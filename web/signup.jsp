<%--
    Document   : signup
    Created on : Feb 25, 2015, 6:43:13 PM
    Author     : zeb
--%>



<jsp:include page="header.jsp"></jsp:include>
            
            
            <div class="container center_div" align="center">
            <h1 style="color:black">Sign up with us today!</h1>
            <h2>${ErrorMsg.getErrorMsg()}</h2>
            <br>
            <form role="form" class="form-group-sm col-sm-offset-2" method="post" action="Signup">
                <div class="form-group">
                    <div class="col-sm-5 col-sm-offset-2">
                        <label for="fname">First Name:</label>
                        <input type="text" class="form-control" id="fname" name="fname" placeholder="Enter First name" size="40">
                        <br>
                        <label for="lname">Last Name:</label>
                        <input type="text" class="form-control" id="lname" name="lname" placeholder="Enter Last name" size="40">
                        <br>
                        <label for="email">Email:</label>
                        <input type="email" class="form-control" id="email" name="email" placeholder="Enter email" size="40">
                        <br>
                        <label for="pwd">Password:</label>
                        <input type="password" class="form-control" id="pwd" name="pass1" placeholder="Enter password">
                        <br>
                        <label for="pwd">Confirm Password:</label>
                        <input type="password" class="form-control" id="pwd" name="pass2" placeholder="Confirm password">
                    <br> <br>
                        <div class="btn-group btn-group-vertical center-block">
                            <button class="btn btn-default" style="width: 50%">Finish Sign Up</button>
                            <a href="signin.jsp" class="btn-link" role="button">Have an account? Log in!</a>
                        </div>
                    </div>
                </div>
            
            </form>
            </div>
        </div>


     <jsp:include page="footer.jsp"></jsp:include>
    
