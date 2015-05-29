<%-- 
    Document   : useraccountoverview
    Created on : Apr 4, 2015, 9:44:13 PM
    Author     : zeb
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="header.jsp"></jsp:include>

    <body>
        <br>

        <div class="container">
            <h1 style="color:black"> Hello ${userinfobean.email}</h1>

        <nav role="navigation" class="page-navigation">
            <ul class="list-inline">
                <li class="page-navigation-item"><a href="#" class="page-navigation-link is-selected">Dashboard</a></li>
                <li class="page-navigation-item"><a href="AccountSettings" class="page-navigation-link">Account Settings</a></li>
                <li class="page-navigation-item"><a href="${pageContext.request.contextPath}/EditProfileForward" class="page-navigation-link">Edit Profile</a></li>
            </ul>
        </nav>
    </div>
    <section class="overview-grouping results-movies row">


        <h2 class="heading-style-1 heading-size-l section-header inline">Purchase History</h2>
        <div class="panel">
            <c:forEach var="payment" items="${sessionScope.payments}">
                ${payment.getTicketID().getMovieID().getName()}
                <br>
                ${payment.getTicketID().getPrice()}0
                <br>
                <br>
                <br>
              
                
            </c:forEach>
            <c:if test="${empty sessionScope.payments}">
            It's never been easier to go to the movies. Bypass the lines, guarantee your ticket, and if something comes up, no worries - return or exchange it. 
 </c:if>

        </div>
    </section>
    <section class="overview-grouping results-movies row">

        <h2 class="heading-style-1 heading-size-l section-header inline">My Theaters</h2>
        <div class="panel">
            <c:forEach var="theatre" items="${favoritetheatrebean.getTheatreList()}">
                <p> <a href="TheatreForward?theatreid=<c:out value='${theatre.getId()}'/>"><c:out value="${theatre.getName()}"/> </a></p>
            </c:forEach>
            <c:if test="${empty favoritetheatrebean.getTheatreList()}">
                <p>Favoriting a theatre is the simplest way of accessing theatres in Fandango! Favorite one now.<p>
                </c:if>
        </div> 

    </section>
    <section class="overview-grouping results-movies row">


        <h2 class="heading-style-1 heading-size-l section-header inline">My Movies</h2>
        <div class="panel">		
            <c:forEach var="movies" items="${favoritemoviebean.getMovieId()}">
                <p> <a href="Movie?movieid=<c:out value='${movies.getMovieID()}'/>"><c:out value="${movies.getName()}"/> </a></p>
            </c:forEach>
            <c:if test="${empty favoritemoviebean.getMovieId()}">
                <p>Customize your Cinemango. Save the movies you love. Get started now.</p>
            </c:if>

        </div>

    </section>
    <section class="overview-grouping results-reviews row" id="reviews">
        <div class="large-12 columns">


            <div class="row">
                <div class="large-3 columns small-12">

                    <h2 class="heading-style-1 heading-size-l section-header inline">My Reviews</h2>
                    <div class="panel">
                        <c:forEach var="reviews" items="${userReviews}">
                            <p> <a href="Movie?movieid=${reviews.getMovieid().getMovieID()}"><c:out value="${reviews.getReview()}"/> </a> - <i> ${reviews.getMovieid().getName()}</i></p>
                        </c:forEach>
                        <c:if test="${empty userReviews}">
                            <p>Review your favorite movies and see those reviews here!</p>
                        </c:if>

                    </div>
                </div>

            </div>
        </div>
    </section>


</body>


<jsp:include page="footer.jsp"></jsp:include>