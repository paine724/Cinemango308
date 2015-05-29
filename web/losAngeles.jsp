<%-- 
    Document   : losAngeles
    Created on : Mar 24, 2015, 4:28:45 PM
    Author     : Konstantinos Pagonis
--%>

<jsp:include page="header.jsp"></jsp:include>
    <br>

    <div class="container">
        <div class="Search-bar" align="center">
            <div class="search">
                <form role="form">
                    <input name="MainMenuControlshowtimesMenulocationTextBox" type="text" value="Los Angeles, CA" id="MainMenuControl_showtimesMenu_locationTextBox" class="global-search-input global-showtimes-input full-width" onkeypress="" placeholder="Enter a City, State or Zip Code" size="30">
                    <i class="fa fa-search"></i>
                </form>
                <span style="color:red" >Search </span>
            </div>
        </div>
        <h2> <u> Calendar </u></h2>
            <div class="panel">

                <script type="text/javascript" src="js/jcarousel.calendar.js"></script>
                <link rel="stylesheet" type="text/css" href="css/jcarousel.calendar.css">
                <div class="calendar-wrapper">
                    <div class="calendar">
                        <div class="loading">Loading calendar...</div>
                    </div>

                    <a href="#" class="calendar-control-prev">&lsaquo;</a>
                    <a href="#" class="calendar-control-next">&rsaquo;</a>
                </div>
            </div>

            <div class="showtimes-movie-container">
                <!--Only display this information on the TSP -->
            <div class="theatre-REGAL-LA-LIVE-STADIUM-14 jumbotron"> <!-- Begin Theatre -->
                <div class="showtimes-movie-overview container">
                    <h2><b><a href="#">REGAL LA LIVE STADIUM 14</a></b></h2>
                    <h4>1000 W. Olympic Blvd., Los Angeles, CA 90015 <a href="#">MAP</a> | <a href="#">AMENITIES</a></h4>
                    <div class="vip-movie-offers"> <!-- Begin movie -->
                        <div class="showtimes-movie-poster col-sm-3">
                            <a href="movieoverview.jsp">
                                <img alt="The SpongeBob Movie: Sponge Out of Water 3D showtimes and tickets" src="http://images.fandango.com/r95.8/ImageRenderer/134/0/redesign/static/img/default_poster.png/0/cp/cpc/images/masterrepository/fandango/179736/the-spongebob-movie%20-.jpg">
                            </a>
                        </div>
                        <!-- offers -->

                        <!-- end offers -->
                    </div>
                    <div class="showtimes-movie-detail follow-dark col-sm-2">
                        <h3 class="heading-size-m heading-style-1">
                            <a class="dark showtimes-movie-title" href="movieoverview.jsp">The SpongeBob Movie: Sponge Out of Water 3D</a>
                            <a class="follow-add" data-follow="true" data-type="Movie" data-id="179736" href="movieoverview.jsp"></a>
                        </h3>
                        <div class="showtimes-movie-rating rating-small">
                            <div class="showtimes-movie-rating-runtime heading-style-1">
                                <!-- Display rating -->
                                <font size="2">PG , </font>
                                <!-- Display runtime -->
                                <font size="2">1 hr 32 min </font>
                            </div>
                            <div class="showtimes-movie-genre">
                                <font size="2">Action/Adventure, Comedy </font>
                            </div>
                        </div>
                        <div class="clearfix"></div>
                    </div>
                
                    <div class="showtimes-theater-times-area col-sm-3">
                        <!--End TSP only info-->
                        <!-- Select showtime text--->
                        <h4 class="showtimes-times-header">Select a movie time to buy RealD 3D tickets</h4>


                        <div class="showtimes-times-area">

                            <div class="showtimes-times">

                                <a class="btn btn-inactive ignore-redirect" rel="nofollow" data-message="Looks like this movie has already started ? let?s try another showtime." role="menuitem" href="ticketcheckout.jsp"><time class="timeInfo" datetime="2015-02-27T10:25:00-05:00">10:25am</time></a>
                            </div>
                        </div>
                    </div> <!--End of movie -->


                        <div class="clearfix"></div>
                </div>
            </div> <!-- End of Theatre -->
            <div class="theatre-PACIFIC-THEATRES-AT-THE-GROVE jumbotron"> <!-- Begin Theatre -->
                <div class="showtimes-movie-overview container">
                    <h2><b><a href="#">PACIFIC THEATRES AT THE GROVE</a></b></h2> <!-- THEATRE NAME -->
                    <h4>189 The Grove Drive, Los Angeles, CA 90036 <a href="#">MAP</a> | <a href="#">AMENITIES</a></h4> <!-- THEATRE ADDRESS -->
                    <div class="vip-movie-offers"> <!-- Begin movie -->
                        <div class="showtimes-movie-poster col-sm-3">
                            <a href="movieoverview.jsp">
                                <img alt="The SpongeBob Movie: Sponge Out of Water 3D showtimes and tickets" src="http://images.fandango.com/r95.8/ImageRenderer/134/0/redesign/static/img/default_poster.png/0/cp/cpc/images/masterrepository/fandango/179736/the-spongebob-movie%20-.jpg">
                            </a>
                        </div>
                        <!-- offers -->

                        <!-- end offers -->
                    </div>
                    <div class="showtimes-movie-detail follow-dark col-sm-2">
                        <h3 class="heading-size-m heading-style-1">
                            <a class="dark showtimes-movie-title" href="movieoverview.jsp">The SpongeBob Movie: Sponge Out of Water 3D</a>
                            <a class="follow-add" data-follow="true" data-type="Movie" data-id="179736" href="movieoverview.jsp"></a>
                        </h3>
                        <div class="showtimes-movie-rating rating-small">
                            <div class="showtimes-movie-rating-runtime heading-style-1">
                                <!-- Display rating -->
                                <font size="2">PG , </font>
                                <!-- Display runtime -->
                                <font size="2">1 hr 32 min </font>
                            </div>
                            <div class="showtimes-movie-genre">
                                <font size="2">Action/Adventure, Comedy </font>
                            </div>
                        </div>
                        <div class="clearfix"></div>
                    </div>
                
                    <div class="showtimes-theater-times-area col-sm-3">
                        <!--End TSP only info-->
                        <!-- Select showtime text--->
                        <h4 class="showtimes-times-header">Select a movie time to buy RealD 3D tickets</h4>


                        <div class="showtimes-times-area">

                            <div class="showtimes-times">

                                <a class="btn btn-inactive ignore-redirect" rel="nofollow" data-message="Looks like this movie has already started ? let?s try another showtime." role="menuitem" href="ticketcheckout.jsp"><time class="timeInfo" datetime="2015-02-27T10:25:00-05:00">10:25am</time></a>
                            </div>
                        </div>
                    </div> <!--End of movie -->

                        <div class="clearfix"></div>
                </div>
            </div> <!-- End of Theatre -->
            <div class="theatre-BALDWIN-HILLS-CRENSHAW-PLAZA-15-XTREME jumbotron"> <!-- Begin Theatre -->
                <div class="showtimes-movie-overview container">
                    <h2><b><a href="#">BALDWIN HILLS CRENSHAW PLAZA 15 + XTREME</a></b></h2> <!-- THEATRE NAME -->
                    <h4>4020 Marlton Ave., Los Angeles, CA 90008 <a href="#">MAP</a> | <a href="#">AMENITIES</a></h4> <!-- THEATRE ADDRESS -->
                    <div class="vip-movie-offers"> <!-- Begin movie -->
                        <div class="showtimes-movie-poster col-sm-3">
                            <a href="movieoverview.jsp">
                                <img alt="The SpongeBob Movie: Sponge Out of Water 3D showtimes and tickets" src="http://images.fandango.com/r95.8/ImageRenderer/134/0/redesign/static/img/default_poster.png/0/cp/cpc/images/masterrepository/fandango/179736/the-spongebob-movie%20-.jpg">
                            </a>
                        </div>
                        <!-- offers -->

                        <!-- end offers -->
                    </div>
                    <div class="showtimes-movie-detail follow-dark col-sm-2">
                        <h3 class="heading-size-m heading-style-1">
                            <a class="dark showtimes-movie-title" href="movieoverview.jsp">The SpongeBob Movie: Sponge Out of Water 3D</a>
                            <a class="follow-add" data-follow="true" data-type="Movie" data-id="179736" href="movieoverview.jsp"></a>
                        </h3>
                        <div class="showtimes-movie-rating rating-small">
                            <div class="showtimes-movie-rating-runtime heading-style-1">
                                <!-- Display rating -->
                                <font size="2">PG , </font>
                                <!-- Display runtime -->
                                <font size="2">1 hr 32 min </font>
                            </div>
                            <div class="showtimes-movie-genre">
                                <font size="2">Action/Adventure, Comedy </font>
                            </div>
                        </div>
                        <div class="clearfix"></div>
                    </div>
                
                    <div class="showtimes-theater-times-area col-sm-3">
                        <!--End TSP only info-->
                        <!-- Select showtime text--->
                        <h4 class="showtimes-times-header">Select a movie time to buy RealD 3D tickets</h4>


                        <div class="showtimes-times-area">

                            <div class="showtimes-times">

                                <a class="btn btn-inactive ignore-redirect" rel="nofollow" data-message="Looks like this movie has already started ? let?s try another showtime." role="menuitem" href="ticketcheckout.jsp"><time class="timeInfo" datetime="2015-02-27T10:25:00-05:00">10:25am</time></a>
                            </div>
                        </div>
                    </div> <!--End of movie -->


                        <div class="clearfix"></div>
                </div>
            </div> <!-- End of Theatre -->
            </div>
    </div>

    <jsp:include page="footer.jsp"></jsp:include>