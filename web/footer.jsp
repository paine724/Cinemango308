<%-- 
    Document   : footer
    Created on : Feb 26, 2015, 6:05:32 PM
    Author     : Andrew
--%>

<div class="container">
    <h2> <u> Offers </u></h2>
    <div class="panel">
        <section class="module-standard offers-module">
            <div class="large-3 medium-3 small-6 columns">
                <article>
                    <a href="http://www.redcross.org">
                        <img src="bootstrap/images/redcross.png">
                        <h4 class="content-title"><b>Save Lives Today!</b></h4>
                    </a>
                    <p>
                        Receive gratification in knowing you saved lives!

                    </p>
                    <a href="http://www.redcross.org" target="_blank">
                        I'LL HELP!
                    </a>
                </article>
            </div>
            
             <div class="large-3 medium-3 small-6 columns">
                <article>
                    <a href="/GiftcardForward">
                        <img src="http://images.fandango.com/r95.8/ImageRenderer/270/0/redesign/static/img/noxsquare.jpg/0/images/spotlight/promounit_300x15013.png">
                        <h4 class="content-title"><b>Cinemango Giftcards!</b></h4>
                    </a>
                    <p>
                        Don't Miss out on the coolest gift cards in town!

                    </p>
                    <a href="${pageContext.request.contextPath}/Giftcard">
                        BUY GIFT CARDS!
                    </a>
                </article>
            </div>
            
                   <div class="large-3 medium-3 small-6 columns">
                <article>
                    <a href="http://stjude.org">
                        <img src="bootstrap/images/judes.png">
                        <h4 class="content-title"><b>Support St.Judes!</b></h4>
                    </a>
                    <p>
                        Help St. Judes help children suffering from cancer

                    </p>
                    <a href="http://stjude.org" target="_blank">
                        Support St.Judes!
                    </a>
                </article>
            </div>
            
            
        </section>
    </div>
</div>    
 
<br>
<section id="bottom">
    <div class="container wow fadeInDown" data-wow-duration="1000ms" data-wow-delay="600ms">
        <div class="row">
            <div class="col-md-3 col-sm-6">
                <div class="widget">
                    <h3>Experience + Explore</h3>
                    <ul>
                        <li><a href="${pageContext.request.contextPath}/PlayingNowForward">Movies in Theaters</a></li>
                        <li><a href="${pageContext.request.contextPath}/SpecialOffers">Special Offers</a></li>
                        <li><a href="${pageContext.request.contextPath}/Giftcard">Gift Cards</a></li>
                    </ul>
                </div>    
            </div><!--/.col-md-3-->

            <div class="col-md-3 col-sm-6">
                <div class="widget">
                    <h3>Features and Guides</h3>
                    <ul>
                        <li><a href="${pageContext.request.contextPath}/HelpForward">FAQ</a></li>
                        <li><a href="${pageContext.request.contextPath}/privacyPolicyForward">Privacy Policy</a></li>
                        <li><a href="${pageContext.request.contextPath}/purchasePolicyForward">Purchase Policy</a></li> 
                        <li><a href="${pageContext.request.contextPath}/termsOfServiceForward">Terms of Service</a></li> 
                    </ul>
                </div>    
            </div><!--/.col-md-3-->

            <div class="col-md-3 col-sm-6">
                <div class="widget">
                    <h3>Photos</h3>
                    <ul>
                        <li><a href="${pageContext.request.contextPath}/RedCarpetForward">Red Carpet</a></li>
                      
                    </ul>
                </div>    
            </div><!--/.col-md-3-->
        </div>
    </div>
</section><!--/#bottom-->
<footer id="footer" class="midnight-blue">
    <div class="container">
        <div class="row">
            <div class="col-sm-6">
                &copy; 2015 Cinemango All Rights Reserved.
            </div>
            <div class="col-sm-6">
                <ul class="pull-right">
                    <li><a href="${pageContext.request.contextPath}/HelpForward">Help</a></li>
                    <li><a href="${pageContext.request.contextPath}/feedbackForward" >Feedback</a></li>
                    <li><a href="${pageContext.request.contextPath}/SignInForward">My Cinemango</a></li>
                    <li><a href="${pageContext.request.contextPath}/aboutForward">About Cinemango </a></li>
                </ul>
            </div>
        </div>
    </div>
</footer><!--/#footer-->
</body>
</html>