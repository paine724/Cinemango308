<%-- 
    Document   : actor
    Created on : Apr 21, 2015, 9:08:02 PM
    Author     : zeb
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp"></jsp:include>
    <div class="container">
        <div class="people-overview">


            <div class="page-header-container">
                <div class="row">
                    <div class="large-12 columns">
                        <h1 class="page-header heading-style-1 heading-size-xl">
                            <a href="${sessionScope.actor.getIMDBLink()}">${sessionScope.actor.getFirstname()} ${sessionScope.actor.getLastName()}</a>
                        <span class="subpage-header">
                            Overview
                        </span>
                    </h1>

                </div>
            </div>
        </div>

        <div class="row"><!-- people-overview-details -->
            <div class="flex-grid">
                <div class="flex-grid-grouping-one">


                    <section class="people-detail-section">
                        <div class="people-portrait">
                            <a href="${sessionScope.actor.getIMDBLink()}"><img src="${sessionScope.actor.getPicture()}"></a>
                        </div>

                        <div class="people-detail-container">


                            <span class="people-dob">Date of Birth<br>${sessionScope.actor.getDob()}</span>



                        </div>
                    </section>


                    <div class="biography">
                        <h2 class="heading-size-l heading-style-1 biography-header">Biography</h2>
                        <p> Wanting to be a good actor is not good enough. You must want to be a great actor. You just have to have that. </p> - ${sessionScope.actor.getFirstname()} ${sessionScope.actor.getLastName()}



                    </div>




                </div>
            </div>
        </div>
    </div>
</div>


</div>


<jsp:include page="footer.jsp"></jsp:include>
