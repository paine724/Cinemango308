/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ForwardServlet;

import Bean.CommentBean;
import Bean.RatingBean;
import JPA.Comment;
import JPA.Movie;
import JPA.Moviereview;
import JPA.User;
import Movie.MovieManager;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Andrew
 */
public class MovieForward extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int movieID = parseInt(request.getParameter("movieid"));
        MovieManager manager = new MovieManager();
        Movie movie = manager.getMovie(movieID);
        List<Moviereview> comments=movie.getMoviereviewList();
        List<CommentBean> commentbeans=new ArrayList();
        int size= manager.getNumRatings(movieID);
        RatingBean beanz= new RatingBean();
            beanz.setMovieID(movieID);
            //bean.setRating(rating);
            //bean.setUserID(userid);
            beanz.setSize(size);
            beanz.setRating(manager.getAverageRating(movieID));
        for(Moviereview c: comments){
            User user=c.getUserid();
            String firstName=user.getFirstName();
            String lastName= user.getLastName();
            String comment=c.getReview();
            CommentBean bean= new CommentBean();
            bean.setComment(comment);
            bean.setUserFirstName(firstName);
            bean.setUserLastName(lastName);
            bean.setCommentID(c.getReviewID());
            commentbeans.add(bean);
        }
        request.getSession().setAttribute("movie", movie);
        request.getSession().setAttribute("comments", commentbeans);
        request.getSession().setAttribute("ratingbean", beanz);
        
        getServletContext().getRequestDispatcher("/movieoverview.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
