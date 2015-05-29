<%-- 
    Document   : AdminAddMovie
    Created on : Apr 7, 2015, 10:57:04 PM
    Author     : zeb
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<jsp:include page="adminheader.jsp"></jsp:include>
    <c:choose>
        <c:when test="${userinfobean.getRole() != 'Admin'}">
    </c:when>
    <c:otherwise>

        <br>
        <div class="container">

            <h2 align="center">Add a Movie</h2>
            <br>
            <form class="form-horizontal" method="post" action="AdminAddMovie">
                <fieldset>


                    <!-- Text input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="name">Name</label>  
                        <div class="col-md-4">
                            <input id="name" name="name"  class="form-control input-md" required="" type="text">

                        </div>
                    </div>

                    <!-- Select Basic -->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="rating">MPAA Rating</label>
                        <div class="col-md-4">
                            <select id="rating" name="rating" class="form-control">
                                <option value="G">G</option>
                                <option value="PG">PG</option>
                                <option value="PG13">PG-13</option>
                                <option value="R">R</option>
                                <option value="NC17">NC-17</option>
                            </select>
                        </div>
                    </div>

                    <!-- Select Basic -->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="genre">Genre</label>
                        <div class="col-md-4">
                            <select id="genre" name="genre" class="form-control">
                                <option value="3D">3D</option>
                                <option value="Animation/adventure">Animation/adventure</option>
                                <option value="Animated">Animated</option>
                                <option value="Children's/Family">Children's/Family</option>
                                <option value="Comedy">Comedy</option>
                                <option value="Concert/Special Events">Concert/Special Events</option>
                                <option value="Culture &amp; Society">Culture &amp; Society</option>
                                <option value="Documentary">Documentary</option>
                                <option value="Drama">Drama</option>
                                <option value="Epic">Epic</option>
                                <option value="Family">Family</option>
                                <option value="Health and Fitness">Health and Fitness</option>
                                <option value="Horror">Horror</option>
                                <option value="IMAX">IMAX</option>
                                <option value="Music/Performing Arts">Music/Performing Arts</option>
                                <option value="Nature">Nature</option>
                                <option value="Romance">Romance</option>
                                <option value="Sci-Fi/Fantasy">Sci-Fi/Fantasy</option>
                                <option value="Sports and Recreation">Sports and Recreation</option>
                                <option value="Western">Western</option>
                            </select>
                        </div>
                    </div>

                    <!-- Text input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="length">Movie Length</label>  
                        <div class="col-md-4">
                            <input id="length" name="length" placeholder="(in minutes)" class="form-control input-md" type="text">

                        </div>
                    </div>

                    <!-- Text input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="poster">Link to Poster</label>  
                        <div class="col-md-4">
                            <input id="poster" name="poster" placeholder="" class="form-control input-md" type="text">

                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-4 control-label" for="date">date</label>  
                        <div class="col-md-4">
                            <input id="poster" name="date" placeholder="2015-April-20" class="form-control input-md" type="text">

                        </div>
                    </div>
                    <!-- Button -->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="submit">Submit</label>
                        <div class="col-md-4">
                            <button id="submit" name="submit" class="btn btn-primary">Submit</button>
                        </div>
                    </div>

                </fieldset>
            </form>


        </div>
        </body>
        </html>
    </c:otherwise>
</c:choose>