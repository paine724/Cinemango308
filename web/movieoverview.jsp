<%-- 
    Document   : movieoverview
    Created on : Feb 27, 2015, 5:06:48 PM
    Author     : Andrew
--%>

<jsp:include page="header.jsp"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
    function favorite(ID) {
        $jq.get("FavoriteMovie", {
            movieID: ID
        });
    }
    function flag(ID) {
        $jq.get("FlagReview", {
            commentid: ID
        });
    }
    function rate(x) {
        $jq.get("RateMovie", {
            rating: x,
            movieid: ${movie.getMovieID()}
        });
    }
    function highlight(x) {
        unhighlight(5);
        for (i = 1; i <= x; i++) {
            document.getElementById("rating" + i).innerHTML = "&#9733;";
        }
    }
    function unhighlight(x) {
        for (i = 1; i <= x; i++) {
            document.getElementById("rating" + i).innerHTML = "&#9734;";
        }
    }
</script>
<link href="bootstrap/css/movieDetails.css" rel="stylesheet">
<div class="container">
    <h2>${movie.getName()} 
        <c:choose> 
            <c:when test="${userinfobean.getUserId() >0}">
                <a href="#" onclick="favorite(${movie.getMovieID()});
                        return false;">&#9825</a>
            </c:when> </c:choose></h2>

        <div class="movie-detail-section">
            <div class="movie-poster"><img src="${movie.getPoster()}">
            <ul class="movie-detail-list">
                <li class="movie-detail-release-status">
                    Released
                </li>
                <li class="movie-detail-release-date">
                    ${movie.getDateReleased()}
                </li>
                <li class="movie-rating">
                    ${movie.getMpaaRating()} , ${movie.getMovieLength()} min
                </li>
                <li class="movie-user-rating">
                    <c:choose>
                        <c:when test="${userinfobean.getUserId() > 0}">
                            <c:forEach begin="1" end="5" varStatus="loop">
                                <a href="#" onclick="rate(${loop.index});
                                        return false;" onmouseover="highlight(${loop.index});" onmouseout="unhighlight(${loop.index});">
                                    <c:choose>
                                        <c:when test="${ratingbean.getRating()>loop.index}">
                                            &#9733;
                                        </c:when>
                                        <c:otherwise>
                                            &#9734;    
                                        </c:otherwise>
                                    </c:choose>
                                </a>
                            </c:forEach>
                        </c:when>

                        <c:otherwise>
                            <c:forEach begin="1" end="5" varStatus="loop">
                                <a href="#">
                                    <c:choose>
                                        <c:when test="${ratingbean.getRating()>loop.index}">
                                            &#9733;
                                        </c:when>
                                        <c:otherwise>
                                            &#9734;    
                                        </c:otherwise>
                                    </c:choose>
                                </a>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </li>
                <li>
                    ${ratingbean.getSize()} total ratings
                </li>
            </ul>
        </div>

        <div class="movie-detail-container"><iframe width="560" height="315" src="${movie.getTrailer()}" frameborder="0" allowfullscreen></iframe></div>

    </div>


</div>
<div class="container">
    <h2>CAST AND CREW</h2>
    <div class="movie-detail-section">
        <script type="text/javascript">
            (function ($) {
                $(function () {
                    var jcarousel = $('.castcrew').jcarousel({
                        wrap: 'circular',
                    });

                    $('.castcrew-control-prev')
                            .on('jcarouselcontrol:active', function () {
                                $(this).removeClass('inactive');
                            })
                            .on('jcarouselcontrol:inactive', function () {
                                $(this).addClass('inactive');
                            })
                            .jcarouselControl({
                                target: '-=1'
                            });

                    $('.castcrew-control-next')
                            .on('jcarouselcontrol:active', function () {
                                $(this).removeClass('inactive');
                            })
                            .on('jcarouselcontrol:inactive', function () {
                                $(this).addClass('inactive');
                            })
                            .jcarouselControl({
                                target: '+=1'
                            });

                    var setup = function (data) {
                        var html = '<ul>';

                        $.each(data.items, function () {
                            html += '<li><a href="' + this.href + '"><img src="' + this.src + '" alt="' + this.title + '"></li></a>';
                        });

                        html += '</ul>';

                        // Append items
                        jcarousel
                                .html(html);

                        // Reload carousel
                        jcarousel
                                .jcarousel('reload');
                    };

                    $.getJSON('ActorJSON?movieid=${movie.getMovieID()}', setup);
                });
            })(jQuery);
        </script>

        <link rel="stylesheet" type="text/css" href="css/jcarousel.castcrew.css">
        <div class="castcrew-wrapper">
            <div class="castcrew">
                <div class="loading">Loading castcrew...</div>
            </div>

            <a href="#" class="castcrew-control-prev">&lsaquo;</a>
            <a href="#" class="castcrew-control-next">&rsaquo;</a>
        </div>
    </div>
</div>
<div class="container">
   
    <c:choose> 
        <c:when test="${userinfobean.getUserId() >0}">
             <h2>ADD MOVIE REVIEWS</h2>
            <form method="post" action="AddMovieReview">
                <textarea rows="8" name="comment" class="form-control">
Write a comment about ${movie.getName()}!
                </textarea>
                <input type="hidden" name="movieID" value="${movie.getMovieID()}">
                <input type="hidden" name="userID" value="${userinfobean.getUserId()}">
                <br>
                <input type="submit" class="btn btn-default" value="Submit">
            </form>
        </c:when> 
    </c:choose> 
    <c:choose> 
        <c:when test="${empty userinfobean}">
            <a href="${pageContext.request.contextPath}/SignIn"> Log into write a movie comment!</a>

        </c:when>
    </c:choose> 

    <h2>ALL MOVIE REVIEWS</h2>
    <c:choose> 
        <c:when test="${not empty comments}">
            <c:forEach var="comment" items="${comments}">
                <h3> ${comment.getComment()}       </h3>
                <h4>-&nbsp<i>${comment.getUserLastName()} , ${comment.getUserFirstName()}</i></h4>
                <c:choose> 
                    <c:when test="${userinfobean.getUserId() >0}">
                        <a href="#" onclick="flag(${comment.getCommentID()});
                        return false;">Flag this review?</a>
                    </c:when>
                </c:choose> 
            </c:forEach>

        </c:when>
        <c:when test="${empty comments}">

            No movie reviews yet :'(
        </c:when>
    </c:choose> 
</div>
<jsp:include page="footer.jsp"></jsp:include>