<%-- 
    Document   : SearchResults
    Created on : Apr 18, 2015, 8:26:19 PM
    Author     : zeb
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp"></jsp:include>
<link href="${pageContext.request.contextPath}/boostrap/css/search-results.css" rel="stylesheet">

<div class="container">
    <span align="center"> <h1 style="color:black">SEARCH RESULTS </h1> </span>
    <div class="large-12 columns">
        <span align="center">
            <h2>

                <!--   <a class="search-legend-nav" href="#cities">Cities</a> -->
                <c:if test="${not empty sessionScope.movielist}">
                    <a class="search-legend-nav" href="#movie">Movies (${sessionScope.movielist.size()}) |</a>
                    <a class="search-legend-nav" href="#trailer">Videos + Trailers (${sessionScope.movielist.size()})</a> |

                </c:if>
                <c:if test="${not empty sessionScope.theatrelist}">
                    <a class="search-legend-nav" href="#theatre">Theatres (${sessionScope.theatrelist.size()}) | </a>
                </c:if>
                <c:if test="${not empty sessionScope.actorlist}">
                    <a class="search-legend-nav" href="#theatre">Actors (${sessionScope.actorlist.size()})</a>
                </c:if>
                <!--  <a class="search-legend-nav" href="#castandcrew">Cast + Crew (18)</a>-->


            </h2>
        </span>

    </div>

    <br>
    <div class="search-results-listings row">
        <div class="large-9 columns">

            <ul class="results-list">
                <c:if test="${not empty sessionScope.movielist}">
                    <section id="movie">
                        <h1 style="color:black">
                            Movie Results
                        </h1>





                        <c:forEach var="movie" items="${sessionScope.movielist}">

                            <li><h3 style="color:black"><a href="Movie?movieid=${movie.getMovieID()}">${movie.getName()}</a></h3> </li>
                            <li><img src="${movie.getPoster()}"></li>
                            <li>   Release Date: ${movie.getDateReleased()}  </li>
                            <li>   ${movie.getMpaaRating()}| 
                                ${movie.getMovieLength()} minutes </li>
                            <li>${movie.getGenre()}</li>


                        </c:forEach>

                </ul>

                <ul class="results-list">
                    <section id="trailer">
                        <h1 style="color:black">
                            Trailer Results
                        </h1>
                        <c:forEach var="trailers" items="${sessionScope.movielist}">
                            <l1><h2>${trailers.getName()}</h2></l1>
                            <li>
                                <iframe width="500" height="300" src="${trailers.getTrailer()}" frameborder="10" allowfullscreen></iframe>
                                
                               
                            
                            
                            </li>



                        </c:forEach>

                    </section>

                </ul>
            </c:if>
            <c:if test="${not empty sessionScope.theatrelist}">
                <ul class="results-list">
                    <section id="theatre">
                        <h1 style="color:black">
                            Theatre Results
                        </h1>
                        <c:forEach var="theatres" items="${sessionScope.theatrelist}">

                            <li><h3 style="color:black"><a href="TheatreForward?theatreid=${theatres.getId()}">${theatres.getName()}</a></h3> </li>



                        </c:forEach>

                    </section>

                </ul>
            </c:if>
            
                 <c:if test="${not empty sessionScope.actorlist}">
                <ul class="results-list">
                    <section id="theatre">
                        <h1 style="color:black">
                            Actor Results
                        </h1>
                        <c:forEach var="actors" items="${sessionScope.actorlist}">

                            <li><h3 style="color:black"><a href="ActorServlet?actorid=${actors.getActorid()}">${actors.getFirstname()} ${actors.getLastName()}</a></h3> </li>



                        </c:forEach>

                    </section>

                </ul>
            </c:if>
        </div>
        </section>
    </div>

</div>
<jsp:include page="footer.jsp"></jsp:include>
