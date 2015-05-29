<%-- 
    Document   : index
    Created on : Apr 5, 2015, 10:15:27 PM
    Author     : Philip
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <!DOCTYPE html>
    <!--
    To change this license header, choose License Headers in Project Properties.
    To change this template file, choose Tools | Templates
    and open the template in the editor.
    -->
    <jsp:include page="header.jsp"></jsp:include>
        <div class="container">
            
            <br>
            <h2 > <u> Buy Movie Tickets </u></h2>
            <h4> <a href="PlayingNowForward">See All Movies </a></h4>
            <div class="panel">

                <script type="text/javascript" src="js/jcarousel.movietickets.js"></script>
                <link rel="stylesheet" type="text/css" href="css/jcarousel.movietickets.css">
                <div class="movietickets-wrapper">
                    <div class="movietickets">
                        <div class="loading">Loading movies...</div>
                    </div>

                    <a href="#" class="movietickets-control-prev">&lsaquo;</a>
                    <a href="#" class="movietickets-control-next">&rsaquo;</a>
                </div>
            </div>
            <br>
        </div>
        <section id="main-slider" class="no-margin">
            <div class="carousel slide">
                <ol class="carousel-indicators">
                    <li data-target="#main-slider" data-slide-to="0" class="active"></li>
                    <li data-target="#main-slider" data-slide-to="1"></li>
                    <li data-target="#main-slider" data-slide-to="2"></li>
                </ol>
                <div class="carousel-inner">

                    <div class="item active" style="background-image: url(bootstrap/images/slider/bg1.jpg)">
                        <div class="container">
                            <div class="row slide-margin">
                                <div class="col-sm-6">
                                    <div class="carousel-content">
                                        <h1 class="animation animated-item-1">Who are your favorite actors?</h1>
                                        <h2 class="animation animated-item-2">Cinemango loves Tobey Maguire! </h2>
                                        <a class="btn-slide animation animated-item-3" href="ActorServlet?actorid=12">Read More</a>
                                    </div>
                                </div>

                                <div class="col-sm-6 hidden-xs animation animated-item-4">
                                    <div class="slider-img">
                                        <img src="bootstrap/images/toby.png" class="img-responsive">
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div><!--/.item-->

                    <div class="item" style="background-image: url(bootstrap/images/slider/bg2.jpg)">
                        <div class="container">
                            <div class="row slide-margin">
                                <div class="col-sm-6">
                                    <div class="carousel-content">
                                        <h1 class="animation animated-item-1">The King of Awkward Method acting!</h1>
                                        <h2 class="animation animated-item-2">Michael Cera!</h2>
                                        <a class="btn-slide animation animated-item-3" href="ActorServlet?actorid=93">Read More</a>
                                    </div>
                                </div>

                                <div class="col-sm-6 hidden-xs animation animated-item-4">
                                    <div class="slider-img">
                                        <img src="bootstrap/images/cera.png" class="img-responsive">
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div><!--/.item-->

                    <div class="item" style="background-image: url(bootstrap/images/slider/bg3.jpg)">
                        <div class="container">
                            <div class="row slide-margin">
                                <div class="col-sm-6">
                                    <div class="carousel-content">
                                        <h1 class="animation animated-item-1">Let's just steal the declaration of independence again!</h1>
                                        <h2 class="animation animated-item-2">Nicolas Cage #onetruegod</h2>
                                        <a class="btn-slide animation animated-item-3" href="ActorServlet?actorid=76">Read More</a>
                                    </div>
                                </div>
                                <div class="col-sm-6 hidden-xs animation animated-item-4">
                                    <div class="slider-img">
                                        <img src="bootstrap/images/niccage.png" class="img-responsive">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div><!--/.item-->
                </div><!--/.carousel-inner-->
            </div><!--/.carousel-->
            <a class="prev hidden-xs" href="#main-slider" data-slide="prev">
                <i class="fa fa-chevron-left"></i>
            </a>
            <a class="next hidden-xs" href="#main-slider" data-slide="next">
                <i class="fa fa-chevron-right"></i>
            </a>
        </section><!--/#main-slider-->

        <section id="recent-works">
            <div class="container">
                <div class="center wow fadeInDown">
                    <h2>Spotlight</h2>
                    <p class="lead">Here are the Cinemango Team's favorite movies</p>
                </div>

                <div class="row">
                    <div class="col-xs-12 col-sm-4 col-md-3">
                        <div class="recent-work-wrap">
                            <img class="img-responsive" src="bootstrap/images/portfolio/recent/item1.png" alt="cake!!!">
                            <div class="overlay">
                                <div class="recent-work-inner">
                                    <h3><a href="Movie?movieid=3">Phil & Andrew Bake a Cake</a> </h3>
                                    <p>A movie about baking delicious treats!</p>
                                    <a class="preview" href="bootstrap/images/portfolio/full/item1_1.png" rel="prettyPhoto"><i class="fa fa-eye"></i> View </a>
                                </div> 
                            </div>
                        </div>
                    </div>   

                    <div class="col-xs-12 col-sm-4 col-md-3">
                        <div class="recent-work-wrap">
                            <img class="img-responsive" src="bootstrap/images/portfolio/recent/item2.png" alt="toys!!">
                            <div class="overlay">
                                <div class="recent-work-inner">
                                    <h3><a href="Movie?movieid=73">Toy Story</a></h3>
                                    <p>A child's toys come to life in this Pixar classic</p>
                                    <a class="preview" href="bootstrap/images/portfolio/full/item2.png" rel="prettyPhoto"><i class="fa fa-eye"></i> View</a>
                                </div> 
                            </div>
                        </div>
                    </div> 

                    <div class="col-xs-12 col-sm-4 col-md-3">
                        <div class="recent-work-wrap">
                            <img class="img-responsive" src="bootstrap/images/portfolio/recent/item3.png" alt="">
                            <div class="overlay">
                                <div class="recent-work-inner">
                                    <h3><a href="Movie?movieid=5">Kung Fu Panda</a></h3>
                                    <p> A lazy panda learns the art of Asian martial arts.</p>
                                    <a class="preview" href="bootstrap/images/portfolio/full/item3.png" rel="prettyPhoto"><i class="fa fa-eye"></i> View</a>
                                </div> 
                            </div>
                        </div>
                    </div>   

                    <div class="col-xs-12 col-sm-4 col-md-3">
                        <div class="recent-work-wrap">
                            <img class="img-responsive" src="bootstrap/images/portfolio/recent/item4.png" alt="">
                            <div class="overlay">
                                <div class="recent-work-inner">
                                    <h3><a href="Movie?movieid=91">The Dark Knight </a></h3>
                                    <p>The quintessential Batman movie that showcases a disheveled crime fighter and psychotic arch nemesis</p>
                                    <a class="preview" href="bootstrap/images/portfolio/full/item4.png" rel="prettyPhoto"><i class="fa fa-eye"></i> View</a>
                                </div> 
                            </div>
                        </div>
                    </div>   

                    <div class="col-xs-12 col-sm-4 col-md-3">
                        <div class="recent-work-wrap">
                            <img class="img-responsive" src="bootstrap/images/portfolio/recent/item5.png" alt="">
                            <div class="overlay">
                                <div class="recent-work-inner">
                                    <h3><a href="Movie?movieid=57">Back to the Future</a></h3>
                                    <p>The ever famous Marty McFly and his dolorean!</p>
                                    <a class="preview" href="bootstrap/images/portfolio/full/item5.png" rel="prettyPhoto"><i class="fa fa-eye"></i> View</a>
                                </div> 
                            </div>
                        </div>
                    </div>   

                    <div class="col-xs-12 col-sm-4 col-md-3">
                        <div class="recent-work-wrap">
                            <img class="img-responsive" src="bootstrap/images/portfolio/recent/item6.png" alt="">
                            <div class="overlay">
                                <div class="recent-work-inner">
                                    <h3><a href="Movie?movieid=52">Star Trek</a></h3>
                                    <p>The classic Sci-Fi Movie!</p>
                                    <a class="preview" href="bootstrap/images/portfolio/full/item6.png" rel="prettyPhoto"><i class="fa fa-eye"></i> View</a>
                                </div> 
                            </div>
                        </div>
                    </div> 

                    <div class="col-xs-12 col-sm-4 col-md-3">
                        <div class="recent-work-wrap">
                            <img class="img-responsive" src="bootstrap/images/portfolio/recent/item7.png" alt="">
                            <div class="overlay">
                                <div class="recent-work-inner">
                                    <h3><a href="#">Spider-Man </a></h3>
                                    <p>Peter Parker is a lonely college student until he finds out he has awesome super powers.</p>
                                    <a class="preview" href="bootstrap/images/portfolio/full/item7.png" rel="prettyPhoto"><i class="fa fa-eye"></i> View</a>
                                </div> 
                            </div>
                        </div>
                    </div>   

                    <div class="col-xs-12 col-sm-4 col-md-3">
                        <div class="recent-work-wrap">
                            <img class="img-responsive" src="bootstrap/images/portfolio/recent/item8.png" alt="">
                            <div class="overlay">
                                <div class="recent-work-inner">
                                    <h3><a href="#">The Social Network</a></h3>
                                    <p>Silicon Valley's Mark Zuckerburg invents a social media platform that takes the world by storm but compromises his ethics and personality</p>
                                    <a class="preview" href="bootstrap/images/portfolio/full/item8.png" rel="prettyPhoto"><i class="fa fa-eye"></i> View</a>
                                </div> 
                            </div>
                        </div>
                    </div>   
                </div><!--/.row-->
                
                
                
        
            </div><!--/.container-->
        </section><!--/#recent-works-->



    <jsp:include page="footer.jsp"></jsp:include>
