<%-- 
    Document   : movietimesandtickets
    Created on : Mar 1, 2015, 2:49:13 PM
    Author     : Konstantinos Pagonis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Movie Times & Tickets</title>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
        <div class="container">
        <div class="jumbotron">
            <h1 style="color:black">Movie Times and Tickets Near <span class="page-header-emphasis" style="color:gold"> Your Location</span></h1>
            <span>
                <a href="#">All Theatres</a> |
                <!--<a href="#">Cinemango Ticket Times</a> |-->
                <a href="signin.jsp">My Theatres</a>
            </span>
                <div class="filter-group">
                    <label class="browse-movies-filter" name="browse-filter">
                        Nearby Theatres:
                    </label>
                    <select id="theatreDropdown" class="form-control" style="width:50%" name="theatreDropdown">
                        <option selected="selected" value="">Select A Theatre</option>
                        <option value="Action/Adventure">Action/Adventure</option>
                        <option value="Animated">Animated</option>
                        <option value="Art House/Foreign">Art House/Foreign</option>
                        <option value="Children's/Family">Children's/Family</option>
                        <option value="Comedy">Comedy</option>
                        <option value="Documentary">Documentary</option>
                        <option value="Drama">Drama</option>
                        <option value="Family">Family</option>
                        <option value="Historical Film">Historical Film</option>
                        <option value="Horror">Horror</option>
                        <option value="Music/Performing Arts">Music/Performing Arts</option>
                        <option value="Romance">Romance</option>
                        <option value="Sci-Fi/Fantasy">Sci-Fi/Fantasy</option>
                        <option value="Suspense/Thriller">Suspense/Thriller</option>
                        <option value="War">War</option>
                    </select>
                </div>
            </div>
            <div class="jumbotron">
            <div id="AABQP" class="showtimes-theater">
    
        <div class="showtimes-theater-header">
            
            <div class="showtimes-theater-header-overview">
                <div class="showtimes-theater-details follow-light">
                    <h3 class="heading-size-m heading-style-1">
                        <a class="light showtimes-theater-title" href="theatersample.jsp">
                            <b>Sample Theatre Page</b>
                        </a>
                        <a class="follow-add" data-follow="true" data-type="Theater" data-id="AABQP" href="#"></a>
                    </h3>
                    <div class="showtimes-theater-location">
                        <a class="showtimes-theater-address light" href="map.jsp" rel="no follow">630 Old Location Road, Location City, NY&nbsp;11530</a>
                        <span class="showtimes-theater-address-shortcuts">
                            <a class="showtimes-theater-map" href="map.jsp" rel="no follow">Map</a>
                        <!--    | <a class="showtimes-theater-amenities" href="#amenitiesFlyout">Amenities</a> -->
                            <ul class="theater-amenities">
                                
                                        <li>
                                            Wheelchair Accessible
                                        </li>
                                    
                                        <li>
                                            Listening Devices
                                        </li>
                                    
                                        <li>
                                            Café
                                        </li>
                                    
                                        <li>
                                            Digital Projection
                                        </li>
                                    
                            </ul>
                        </span>
                    </div>
                </div>
                <div class="showtimes-theater-options">
                    
                        <a class="showtimes-theater-option icon-option-kiosk" href="#" data-message="Skip the box office line and pick up your tickets at a self-service kiosk." data-title="Kiosk" alt="Kiosk" title="Kiosk"></a>
                    
                </div>
            </div>
        </div>
    

    <!-- OFFERS GO HERE--->
    <!-- <div class="showtimes-theater-offer">
             
   </div>-->

    
        <div class="showtimes-movie-container">
            <h3 class="heading-size-m heading-style-1 tsp-no-showtimes"><a href ="movieoverview.jsp">Spongebob Movie.</a>
            </h3>
            <a href="ticketcheckout.jsp">10:45</a>
        </div>
    

    <!-- Displays showtimes + format type for each movie-->
    
    <!-- This is for the TSP -->
    
</div>
        </div>
        </div>
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
