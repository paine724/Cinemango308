<%-- 
    Document   : movietimes
    Created on : Feb 25, 2015, 7:18:19 PM
    Author     : zeb
--%>

        <jsp:include page="header.jsp"></jsp:include>
        <div class="container center-div" align="center">
            <div class="jumbotron">
                <h1><strong>Movie Times!</strong></h2> 
                <h2><strong> Tell us your location to find a theatre near you.</strong><h1>
                <div class="form-group">
                    <input type="text" class="form-control" id="usr" placeholder="Enter a city/state or zip code.">
                    <button type="button" class="btn btn-success">Search</button>
                </div>
                <h2><i>OR...</i></h2>
            </div>
            <br>
        </div>
            <div class="container" align="center">
            <div class="jumbotron" align="center">
                    <h2>Select Manually Below:</h2>
                <div class="row">
                    <div class="col-xs-3">
                    <h3><b><a href="moviestates.jsp">Movie Times by State</a></b></h3>
                    <ul class="list-group">
                        <li class="list-group-item"><a href="#">New York</a></li>
                        <li class="list-group-item"><a href="#">California</a></li>
                        <li class="list-group-item"><a href="#">Florida</a></li>
                        <li class="list-group-item"><a href="#">Texas</a></li>
                        <li class="list-group-item"><a href="#">Illinois</a></li>
                        <li class="list-group-item"><a href="#">Pennsylvania</a></li>
                        <li class="list-group-item"><a href="#">Arizona</a></li>
                        <li class="list-group-item"><a href="#">New Jersey</a></li>
                        <li class="list-group-item"><a href="#">Massachusetts</a></li>
                    </ul>
                    </div>
                <div class="col-xs-3">
                    <h3><b><a href="moviecities.jsp">Movie Times by City</a></b></h3>
                    <ul class="list-group">
                        <li class="list-group-item"><a href="newyorkcity.jsp">New York City, NY</a></li>
                        <li class="list-group-item"><a href="#">San Francisco-Oakland-San Jose, CA</a></li>
                        <li class="list-group-item"><a href="#">Chicago, IL</a></li>
                        <li class="list-group-item"><a href="#">Los Angeles, CA</a></li>
                        <li class="list-group-item"><a href="#">Boston, MA</a></li>
                        <li class="list-group-item"><a href="#">Washington, DC</a></li>
                        <li class="list-group-item"><a href="#">Philadelphia, PA</a></li>
                        <li class="list-group-item"><a href="#">Austin, TX</a></li>
                        <li class="list-group-item"><a href="#">Seattle, WA</a></li>
                    </ul>
                </div>
                <div class="col-xs-3">
                    <h3><b><a href="moviezipcode.jsp">Movie Times by Zip Code</a></b></h3>
                    <ul class="list-group">
                        <li class="list-group-item"><a href="#">10023</a></li>
                        <li class="list-group-item"><a href="#">10003</a></li>
                        <li class="list-group-item"><a href="#">94103</a></li>
                        <li class="list-group-item"><a href="#">10016</a></li>
                        <li class="list-group-item"><a href="#">02111</a></li>
                        <li class="list-group-item"><a href="#">10994</a></li>
                        <li class="list-group-item"><a href="#">94043</a></li>
                        <li class="list-group-item"><a href="#">94568</a></li>
                        <li class="list-group-item"><a href="#">19406</a></li>
                    </ul>
                </div>
                <div class="col-xs-3">
                    <h3><b><a href="movietheaters.jsp">Movie Times by Theatres</a></b></h3>
                    <ul class="list-group">
                        <li class="list-group-item"><a href="#">AMC</a></li>
                        <li class="list-group-item"><a href="#">Regal</a></li>
                        <li class="list-group-item"><a href="#">Cinemark</a></li>
                        <li class="list-group-item"><a href="#">Carmike</a></li>
                        <li class="list-group-item"><a href="#">Cobb</a></li>
                        <li class="list-group-item"><a href="#">Reading</a></li>
                        <li class="list-group-item"><a href="#">Southern Theatres</a></li>
                        <li class="list-group-item"><a href="#">Alamo</a></li>
                        <li class="list-group-item"><a href="#">Georgia</a></li>
                        <li class="list-group-item"><a href="#">Pacific</a></li>
                </ul>
                </div>
                </div>
            </div>
            </div>
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
