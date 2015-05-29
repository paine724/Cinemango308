<%-- 
Document   : theatersample
Created on : Feb 27, 2015, 6:13:28 PM
Author     : zeb
--%>

<jsp:include page="header.jsp"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script>
    function favorite(ID) {
        $jq.get("FavoriteTheatre", {
            theatreID: ID
        });
    }
</script>
<br>

<div class="container">

    <h1 class="page-header" style="color:black">
        ${theatre.getName()}
        <c:choose> 
            <c:when test="${userinfobean.getUserId() >0}">
                <a href="#" onclick="favorite(${theatre.getId()});
                        return false;">
                    &#9829;
                </a>  </c:when> </c:choose>
                <span class="page-header-emphasis" style="color:gold">MOVIE TIMES+TICKETS</span>
            </h1>
            <div class="panel">


                 <br>
                <h2>
                    <span>
                ${theatre.getAddress()}, ${theatre.getCity()},${theatre.getState()} ${theatre.getZipcode()}| <a href="map.jsp"> MAP </a>| (555) 555-5555
            </span>
        </h2>
        <br>
        <h2 style="color:black"><u>Theater Amenities</u></h2>
                ${theatre.getAmenities()}
        <br>
        <!-- Button trigger modal -->
        <button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">
            Box Office Info
        </button>

        <!-- Modal -->
        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="myModalLabel">BOX OFFICE INFORMATION</h4>
                    </div>
                    <div class="modal-body">
                        THE THEATER RESERVES THE RIGHT TO DO STUFF
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>


        <!-- Button trigger modal -->
        <button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal2">
            Age Information
        </button>

        <!-- Modal -->
        <div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="myModalLabel">AGE INFORMATION</h4>
                    </div>
                    <div class="modal-body">
                        NO CHILDREN UNDER 12 MONTHS ADMITTED. ID MUST BE PRESENT FOR R AND NC17 MOVIES
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
        <br>



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

            <div class="showtimes-movie-overview">
                <div class="vip-movie-offers">
                    <c:forEach var="movieid" items="${sessionScope.movieids}">
                        <c:set var="counter" value="0" scope="page" />
                        <c:forEach var="showtime" items="${sessionScope.showtimes}">
                            <c:if test="${showtime.getMovieID()==movieid}">
                                <c:choose>
                                    <c:when test="${counter==0}">
                                        <div class="showtimes-movie-poster">
                                            <a href="${pageContext.request.contextPath}/Movie?movieid=${showtime.getMovieID()}">
                                                <img src="${showtime.getPoster()}" style="height:300px;width:200px;">
                                            </a>

                                        </div>
                                        <div class="showtimes-movie-detail follow-dark">
                                            <h3 class="heading-size-m heading-style-1">
                                                <a class="dark showtimes-movie-title" href="${pageContext.request.contextPath}/Movie?movieid=${showtime.getMovieID()}">${showtime.getName()}</a>

                                            </h3>
                                            <div class="showtimes-movie-rating rating-small">


                                                <div class="showtimes-movie-rating-runtime">
                                                    <!-- Display rating -->
                                                    ${showtime.getMpaaRating()} , 
                                                    <!-- Display runtime -->
                                                    ${showtime.getMovieLength()} min
                                                </div>
                                                <div class="showtimes-movie-genre">
                                                    ${showtime.getGenre()}
                                                </div>
                                            </div>
                                            <div class="clearfix"></div>
                                        </div>



                                        <div class="showtimes-theater-times-area">
                                            <!--End TSP only info-->
                                            <!-- Select showtime text--->
                                            <h4 class="showtimes-times-header">Select a movie time to buy tickets</h4>

                                            <div class="showtimes-times-area">

                                                <div class="showtimes-times">

                                                    <a class="btn btn-inactive ignore-redirect" rel="nofollow" data-message="Looks like this movie has already started ? let?s try another showtime." role="menuitem" href="PurchaseTicket0?showtime=${showtime.getShowtime().getId()}"><time class="timeInfo">
                                                            <fmt:formatNumber value="${showtime.getDate().getHours()}" minIntegerDigits="2"/>: <fmt:formatNumber value="${showtime.getDate().getMinutes()}" minIntegerDigits="2"/>    </time></a>
                                                    <br>
                                                </div>
                                            </div>
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="showtimes-times">

                                            <a class="btn btn-inactive ignore-redirect" rel="nofollow" data-message="Looks like this movie has already started ? let?s try another showtime." role="menuitem" href="PurchaseTicket0?showtime=${showtime.getShowtime().getId()}"><time class="timeInfo">
                                                    <fmt:formatNumber value="${showtime.getDate().getHours()}" minIntegerDigits="2"/>: <fmt:formatNumber value="${showtime.getDate().getMinutes()}" minIntegerDigits="2"/>    </time></a>
                                            <br>
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                                <c:set var="counter" value="1" scope="page" />
                            </c:if>
                        </c:forEach>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>

    <jsp:include page="footer.jsp"></jsp:include>