/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Movie;

import Bean.MovieInfoBean;
import JPA.Movie;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author zeb
 */
public class ModifyMovie extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        MovieManager manager = new MovieManager();
        String moviename = request.getParameter("name");
        int movieID = manager.movieExists(moviename);
        if (movieID < 0) {
            request.getSession().setAttribute("Redirect", "ERROR: Movie Doesn't exist");
            request.getSession().setAttribute("RedirectLink", "ModifyMovieForward");
            this.getServletContext().getRequestDispatcher("/redirect.jsp").forward(request, response);
        } else {
            Movie toEdit = manager.getMovie(movieID);
            MovieInfoBean movie = new MovieInfoBean();
            movie.setName(toEdit.getName());
            movie.setPoster(toEdit.getPoster());
            movie.setGenre(toEdit.getGenre());
            movie.setMpaaRating(toEdit.getMpaaRating());
            movie.setMovieLength(toEdit.getMovieLength());
            movie.setDateReleased(toEdit.getDateReleased());

            //user.setPassword(toEdit.getPassword());
            request.getSession().setAttribute("EditMovie", movie);
            getServletContext().getRequestDispatcher("/ModifyMovie.jsp").forward(request, response);
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
