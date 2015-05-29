<%-- 
    Document   : adminheader
    Created on : Apr 7, 2015, 5:41:30 PM
    Author     : zeb
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<c:choose>
    <c:when test="${userinfobean.getRole() != 'Admin'}">

    </c:when>
    <c:otherwise>
        <html>
            <head>
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <meta charset="utf-8">
                <meta http-equiv="X-UA-Compatible" content="IE=edge">
                <meta name="viewport" content="width=device-width, initial-scale=1">
                <meta name="description" content="">
                <meta name="author" content="">

                <title>Cinemango: Administrator Mode</title>

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

                <script type="text/javascript" src="http://sorgalla.com/jcarousel/libs/jquery/jquery.js"></script>
                <script type="text/javascript" src="http://sorgalla.com/jcarousel/dist/jquery.jcarousel.min.js"></script>

                <script type="text/javascript" src="js/jcarousel.js"></script>
            </head>
            <body>
                <header id="header">
                    <div class="top-bar">
                        <div class="container">
                            <div class="row" align="center"><img src="AdsForward"></div>

                        </div>
                    </div><!--/.container-->
               

                <nav class="navbar navbar-inverse" role="banner">
                    <div class="container">
                        <div class="navbar-header">
                            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                                <span class="sr-only">Toggle navigation</span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                            </button>
                            <a class="navbar-brand" href="${pageContext.request.contextPath}/AdminHomepageForward"><img src="bootstrap/images/logo.png" alt="logo"></a>
                        </div>

                        <div class="collapse navbar-collapse navbar-right">
                            <ul class="nav navbar-nav">

                                <li class="dropdown">
                                    <a href="#" class="dropdown-toggle">User Actions <i class="fa fa-angle-down"></i></a>
                                    <ul class="dropdown-menu">
                                        <div class="mega-menu">


                                            <div class="mega-menu-content">
                                                <div class="large-3 small-4 columns">
                                                    <h3 class="heading-size-m heading-style-1">Add User</h3>
                                                    <ul class="mega-menu-movie-list" >



                                                        <li>
                                                            <a id="MainMenuControl_moviesMenu_nowPlayingRepeater_movieLink_2" class="light" href="${pageContext.request.contextPath}/AdminAddUserForward">Add New User</a>
                                                        </li>
                                                    </ul>
                                                </div>
                                                <div class="large-3 small-4 columns">
                                                    <header class="list-opening-this-week">
                                                        <h3 class="heading-size-m heading-style-1 inline">Delete User</h3>

                                                    </header>
                                                    <ul class="mega-menu-movie-list">

                                                        <li>
                                                            <a id="MainMenuControl_moviesMenu_openingRepeater_movieLink_0" class="light" href="${pageContext.request.contextPath}/AdminDeleteUserForward">Delete a User</a>
                                                        </li>

                                                    </ul>
                                                </div>
                                                <div class="large-3 small-4 columns">

                                                    <h3 class="heading-size-m heading-style-1 nav-movies-secondary-header">Modify User</h3>
                                                    <ul class="mega-menu-movie-list">

                                                        <li>
                                                            <a id="MainMenuControl_moviesMenu_comingSoonRepeater_movieLink_0" class="light" href="${pageContext.request.contextPath}/ModifyUserForward">Modify User</a>
                                                        </li>


                                                    </ul>
                                                </div>
                                                <div class="large-3 columns show-for-large-up">
                                                    <h3 class="heading-size-m heading-style-1">Other</h3>
                                                    <ul class="mega-menu-movie-list">
                                                        <li>
                                                            <a class="cta light" href="${pageContext.request.contextPath}/AdminRemoveComment">Remove Comment</a>
                                                        </li>

                                                    </ul>



                                                </div>
                                            </div>
                                        </div>
                                    </ul>
                                </li>

                                <li class="dropdown">
                                    <a href="#" class="dropdown-toggle">Movie Actions <i class="fa fa-angle-down"></i></a>
                                    <ul class="dropdown-menu">
                                        <li><a href="${pageContext.request.contextPath}/AddMovieForward">Add Movie</a></li>
                                        <li><a href="${pageContext.request.contextPath}/DeleteMovieForward">Delete Movie</a></li>
                                        <li><a href="${pageContext.request.contextPath}/ModifyMovieForward">Edit Movie </a></li>

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
        </c:otherwise>
    </c:choose>