/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Search;

import Actor.ActorManager;
import JPA.Actor;
import JPA.Movie;
import JPA.Theatre;
import Movie.MovieManager;
import Theatre.TheatreManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.*;

/**
 *
 * @author zeb
 */
public class ProcessSearch extends HttpServlet {

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
            throws ServletException, IOException, Exception {
        String search = request.getParameter("search");
        //is it a zipcode?
        boolean isZipcode = Pattern.matches("^[0-9]{5}$", search);
        boolean cityCommaState = Pattern.matches("^[a-zA-Z ]{3,},[a-zA-Z ]{2,}", search);

        try (PrintWriter out = response.getWriter()) {
            if (isZipcode) {
                TheatreManager manager = new TheatreManager();
                List<Object[]> a = manager.searchByZipcode(search);
                ArrayList<Theatre> theatres = new ArrayList<Theatre>();
                for (Object[] q : a) {
                    theatres.add(manager.getTheatre((int) q[0]));
                }
                request.getSession().setAttribute("theatrelist", theatres);
                this.getServletContext().getRequestDispatcher("/SearchResults.jsp").forward(request, response);
            } else if (cityCommaState) {
                TheatreManager manager = new TheatreManager();
                List<Object[]> a = manager.searchByCityState(search);
                ArrayList<Theatre> theatres = new ArrayList<Theatre>();
                for (Object[] q : a) {
                    theatres.add(manager.getTheatre((int) q[0]));
                }
                request.getSession().setAttribute("theatrelist", theatres);
                this.getServletContext().getRequestDispatcher("/SearchResults.jsp").forward(request, response);
            } else {
                MovieManager movieManager = new MovieManager();
                TheatreManager theatreManager = new TheatreManager();
                ActorManager actorManager = new ActorManager();

                ArrayList<Movie> movies = movieManager.search(search);
                ArrayList<Theatre> theatres = theatreManager.search(search);
                ArrayList<Actor> actors = actorManager.search(search);

                if (movies.size() == 1 && theatres.size() == 0 && actors.size() == 0) {
                    this.getServletContext().getRequestDispatcher("/Movie?movieid=" + movies.get(0).getMovieID()).forward(request, response);
                } else if (movies.size() == 0 && theatres.size() == 1 && actors.size() == 0) {
                    this.getServletContext().getRequestDispatcher("/TheatreForward?theatreid=" + theatres.get(0).getId()).forward(request, response);
                } else if (movies.size() == 0 && theatres.size() == 0 && actors.size() == 1) {
                    this.getServletContext().getRequestDispatcher("/ActorServlet?actorid=" + actors.get(0).getActorid()).forward(request, response);
                } else {
                    request.getSession().setAttribute("movielist", movies);
                    request.getSession().setAttribute("theatrelist", theatres);
                    request.getSession().setAttribute("actorlist", actors);
                    this.getServletContext().getRequestDispatcher("/SearchResults.jsp").forward(request, response);
                }
            }
        }
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ProcessSearch.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ProcessSearch.class.getName()).log(Level.SEVERE, null, ex);
        }
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
