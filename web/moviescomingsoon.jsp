<%-- 
    Document   : moviescomingsoon
    Created on : Feb 27, 2015, 5:10:31 PM
    Author     : zeb
--%>
<jsp:include page="header.jsp"></jsp:include>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container">
    <h1 class="page-header" style="color:black">
        MOVIES <span class="page-header-emphasis" style="color:gold">COMING SOON</span>
    </h1>

    <nav role="navigation" class="page-navigation">
        <ul class="list-inline">
            <li class="page-navigation-item"><a href="${pageContext.request.contextPath}/PlayingNowForward" class="page-navigation-link is-selected">Now Playing</a></li>
            <li class="page-navigation-item"><a href="${pageContext.request.contextPath}/UpcomingMovieForward" class="page-navigation-link">Coming Soon</a></li>
            <li class="page-navigation-item"><a href="${pageContext.request.contextPath}/topBoxForward" class="page-navigation-link">Top Box Office</a></li>
        </ul>
    </nav>
    <br>
</div>

<div class="container">
    <div class="row">
        <div class="large-9 columns">
            <div class="filter-group">
                <label class="browse-movies-filter" name="browse-filter">
                    FILTER MOVIES BY:
                </label>
                <form method="get" action="UpcomingMovieForward">
                    <select id="GenreDropdown" class="form-control" style="width:50%" name="GenreDropdown" onchange="submit()">

                        <option value="">All</option>
                        <c:if test="${not empty param.GenreDropdown}">
                        <option disabled>---------</option>
                        <option selected value="${param.GenreDropdown}">${param.GenreDropdown}</option>
                        </c:if>
                        <option disabled>---------</option>
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
                </form>
            </div>
            <br>
            <div class="thisweek">
                <h2> <u> Movies Coming Soon</u> </h2>
                <ul class="list-inline">

                    <c:forEach var="movie" items="${sessionScope.upcoming}">

                        <li class="visual-item">
                            <a class="visual-container" href="Movie?movieid=${movie.getMovieID()}">
                                <img src="${movie.getPoster()}" class="visual-thumb" style="width:150px; height: 222px">
                            </a>
                            <div class="visual-detail">
                                <a class="visual-title dark" href="Movie?movieid=${movie.getMovieID()}">
                                    ${movie.getName()}
                                </a>
                            </div>
                            <br>
                        </li>
                    </c:forEach>










                </ul>


            </div>


        </div>




    </div>
</div>



<jsp:include page="footer.jsp"></jsp:include>