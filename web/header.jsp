<%--
    Document   : header
    Created on : Feb 26, 2015, 6:02:26 PM
    Author     : Andrew
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Cinemango</title>

        <!-- Bootstrap core CSS -->
        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="bootstrap/css/font-awesome.min.css" rel="stylesheet">
        <link href="bootstrap/css/animate.min.css" rel="stylesheet">
        <link href="bootstrap/css/prettyPhoto.css" rel="stylesheet">
        <link href="bootstrap/css/main.css" rel="stylesheet">
        <link href="bootstrap/css/responsive.css" rel="stylesheet">
        <link href="bootstrap/css/myaccount.css" rel="stylesheet">
        <link href="bootstrap/css/vip.css" rel="stylesheet">
        <link href="bootstrap/css/vipsettings.css" rel="stylesheet">
        <link href="boostrap/css/search-results.css" rel="stylesheet">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="css/jcarousel.css">
        <script src="bootstrap/js/jquery.js"></script>
        <script src="bootstrap/js/bootstrap.min.js"></script>
        <script src="bootstrap/js/bootstrap.min.js"></script>
        <script src="bootstrap/js/jquery.prettyPhoto.js"></script>
        <script src="bootstrap/js/jquery.isotope.min.js"></script>
        <script src="bootstrap/js/main.js"></script>
        <script src="bootstrap/js/wow.min.js"></script>
        <script src="http://code.jquery.com/jquery-1.11.2.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"></script>
        <script type="text/javascript">
            var $jq = jQuery.noConflict(true);
        </script>
        <script type="text/javascript" src="http://sorgalla.com/jcarousel/libs/jquery/jquery.js"></script>
        <script type="text/javascript" src="http://sorgalla.com/jcarousel/dist/jquery.jcarousel.min.js"></script>

        <script type="text/javascript" src="js/jcarousel.js"></script>
        <script src="js/location.js"></script>
        <script>
        $jq(document).ready(function () {
            $jq(".auto").autocomplete({
                source: "ProcessSearchBar",
                minLength: 1
            });
        });
        </script>
        <style>
	.ui-menu-item{
                background:#000;
                color:#FFF;
                width:25%
	}

	.ui-menu-item a{
		font-size:14px;
		font-weight:bold;
		text-transform:uppercase
	}
        </style>
    </head>
    <body>
        <header id="header">
            <div class="top-bar">
                <div class="container">
                    <div class="row"><img src="AdsForward"></div>
                    <div class="row">

                        <div class="col-sm-6 col-xs-8" align="center">
                            <span style="color:red" >Search </span>

                            <div class="search">
                                <form role="form" method="get" action="ProcessSearch">
                                    <input type="text" class="auto input-field search-form" placeholder="Enter City/State, Zip or Movie " name="search" id="search">

                                    <button type="submit" class="fa fa-search"></button>

                                </form>
                            </div>

                        </div>
                        <div align="right">
                            <a href="${pageContext.request.contextPath}/Giftcard" class="visited"> Gift Cards</a>    &nbsp;&nbsp;&nbsp;
                            <a href="${pageContext.request.contextPath}/SpecialOffers" class="visited"> Offers</a> &nbsp;&nbsp;&nbsp;
                            <a href="${pageContext.request.contextPath}/VisaOffer" class="visited"> Visa Signature</a>
                        </div>
                    </div>
                </div><!--/.container-->
            </div><!--/.top-bar-->

            <nav class="navbar navbar-inverse" role="banner">
                <div class="container">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a class="navbar-brand" href="${pageContext.request.contextPath}/IndexForwardServlet"><img src="bootstrap/images/logo.png" alt="logo"></a>
                    </div>

                    <div class="collapse navbar-collapse navbar-right">
                        <ul class="nav navbar-nav">

                            <li class="dropdown">
                                <a href="moviesnowplaying.jsp" class="dropdown-toggle">Movies <i class="fa fa-angle-down"></i></a>
                                <ul class="dropdown-menu">
                                    <div class="mega-menu">
                                        <div class="mega-menu-content">
                                            <div class="large-3 small-4 columns">
                                                <h3 class="heading-size-m heading-style-1">Now Playing</h3>
                                                <ul class="mega-menu-movie-list" >
                                                    <li>
                                                        <a id="MainMenuControl_moviesMenu_nowPlayingRepeater_movieLink_2" class="light" href="${pageContext.request.contextPath}/Movie?movieid=133">The Age of Adaline</a>
                                                    </li>
                                                    <li>
                                                        <a id="MainMenuControl_moviesMenu_nowPlayingRepeater_movieLink_2" class="light" href="${pageContext.request.contextPath}/Movie?movieid=1">Furious 7</a>
                                                    </li>
                                                    <li>
                                                        <a id="MainMenuControl_moviesMenu_nowPlayingRepeater_movieLink_2" class="light" href="${pageContext.request.contextPath}/Movie?movieid=2">Get Hard</a>
                                                    </li>



                                                    <li>
                                                        <a class="cta" href="${pageContext.request.contextPath}/PlayingNowForward">See All Now Playing</a>
                                                    </li>
                                                </ul>
                                            </div>
                                            <div class="large-3 small-4 columns">
                                                <header class="list-opening-this-week">
                                                    <h3 class="heading-size-m heading-style-1 inline">Opening This Week</h3>
                                                    <div class="list-legend">
                                                        <span class="header-definition icon-limited-release">= Limited Release</span>
                                                    </div>
                                                </header>
                                                <ul class="mega-menu-movie-list">

                                                    <li>
                                                        <a id="MainMenuControl_moviesMenu_openingRepeater_movieLink_0" class="light" href="${pageContext.request.contextPath}/Movie?movieid=5">Kung fu Panada</a>
                                                    </li>

                                                    <li>
                                                        <a id="MainMenuControl_moviesMenu_openingRepeater_movieLink_1" class="light" href="${pageContext.request.contextPath}/Movie?movieid=26">Spider-Man</a>
                                                    </li>

                                                    <li>
                                                        <a id="MainMenuControl_moviesMenu_openingRepeater_movieLink_2" class="icon-limited-release light" href="${pageContext.request.contextPath}/Movie?movieid=59">Back To The Future II</a>
                                                    </li>

                                                    <li>
                                                        <a id="MainMenuControl_moviesMenu_openingRepeater_movieLink_3" class="icon-limited-release light" href="${pageContext.request.contextPath}/Movie?movieid=72">Toy Story</a>
                                                    </li>




                                                </ul>
                                            </div>
                                            <div class="large-3 small-4 columns">

                                                <h3 class="heading-size-m heading-style-1 nav-movies-secondary-header">Coming Soon</h3>
                                                <ul class="mega-menu-movie-list">

                                                    <li>
                                                        <a id="MainMenuControl_moviesMenu_comingSoonRepeater_movieLink_0" class="light" href="${pageContext.request.contextPath}/Movie?movieid=3">Phil & Andrew Bake a Cake</a>
                                                    </li>

                                                    <li>
                                                        <a id="MainMenuControl_moviesMenu_comingSoonRepeater_movieLink_1" class="light" href="${pageContext.request.contextPath}/Movie?movieid=7">Kung Fu Hustle</a>
                                                    </li>


                                                    <li>
                                                        <a class="cta" href="${pageContext.request.contextPath}/UpcomingMovieForward">See All Coming Soon</a>
                                                    </li>
                                                </ul>
                                            </div>
                                            <div class="large-3 columns show-for-large-up">
                                                <h3 class="heading-size-m heading-style-1">Explore More</h3>
                                                <ul class="mega-menu-movie-list">
                                                    <li>
                                                        <a class="cta light" href="${pageContext.request.contextPath}/topBoxForward">Top Box Office</a>
                                                    </li>
                                                </ul>



                                            </div>
                                        </div>
                                    </div>
                                </ul>
                            </li>

                            <li class="dropdown">
                                <a href="movietimes.jsp" class="dropdown-toggle">Movie Times/Tickets <i class="fa fa-angle-down"></i></a>
                                <ul class="dropdown-menu">
                                    <ul class="mega-menu-movie-list" id="nearbyTheatres">
                                        <li><a href="theatersample.jsp">Sample Theater Page</a></li>
                                        <li><a href="movietimesandtickets.jsp">Movie Times with Location</a></li>
                                        <li><a href="losAngeles.jsp">Los Angeles, CA</a></li>
                                    </ul>
                                </ul>
                            </li>

                            <c:choose> 
                                <c:when test="${userinfobean.getUserId() >0}">
                                    <li><a href="${pageContext.request.contextPath}/Signout">Log Out</a></li>
                                    <li><a href="${pageContext.request.contextPath}/UserAccountOverview">${userinfobean.email}</a></li>
                                    </c:when>
                                    <c:otherwise>
                                    <li><a href="${pageContext.request.contextPath}/SignIn">Log in</a></li>
                                    <li><a href="${pageContext.request.contextPath}/SignUp">Sign Up</a></li>
                                    </c:otherwise>
                                </c:choose>



                        </ul>
                    </div>

                </div><!--/.container-->
            </nav><!--/nav-->

        </header><!--/header-->



