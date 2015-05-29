/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ForwardServlet;

import Bean.ShowtimeBean;
import JPA.Movie;
import JPA.Showtime;
import JPA.Theatre;
import Movie.MovieManager;
import Theatre.TheatreManager;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Andrew
 */
public class TheatreForward extends HttpServlet {

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
        String theatreID = request.getParameter("theatreid");
        int theatreIdentity = Integer.parseInt(theatreID);
        TheatreManager manager = new TheatreManager();
        Theatre theatre = manager.getTheatre(theatreIdentity);
        List<Showtime> showTimes = theatre.getShowtimeList();
        ArrayList<ShowtimeBean> beanList = new ArrayList();
        MovieManager movieManager = new MovieManager();
        ArrayList<Integer> movieIds = new ArrayList();
        for (Showtime showtime : showTimes) {
            Movie m = showtime.getMovieID();
            int movieId = m.getMovieID();
            if(!movieIds.contains(movieId)){
                movieIds.add(movieId);
            }
            Date date;
            
            if (showtime.getMovieID().getMovieID() == movieId) {
                if (showtime.getTheatreID().getId() == parseInt(theatreID)) {
                    date = showtime.getDate();
                    ShowtimeBean beanToAdd = new ShowtimeBean();
                    beanToAdd.setPoster(m.getPoster());
                    beanToAdd.setMpaaRating(m.getMpaaRating());
                    beanToAdd.setGenre(m.getGenre());
                    beanToAdd.setMovieID(m.getMovieID());
                    beanToAdd.setShowtime(showtime);
                    beanToAdd.setMovieLength(m.getMovieLength());
                    beanToAdd.setTicketcap(showtime.getTicketcap());
                    beanToAdd.setName(m.getName());
                    beanToAdd.setDate(date);
                    beanList.add(beanToAdd);
                }
            }
        }

        request.getSession().setAttribute("movieids", movieIds);
        request.getSession().setAttribute("theatre", theatre);
        request.getSession().setAttribute("showtimes", beanList);
        getServletContext().getRequestDispatcher("/theatersample.jsp").forward(request, response);

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
