/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Movie;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author zeb
 */
public class AdminModifyMovie extends HttpServlet {

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
        String oldName = request.getParameter("oldname");
        String name = request.getParameter("name");
        String rating = request.getParameter("rating");
        String genre = request.getParameter("genre");
        String length = request.getParameter("length");
        String poster = request.getParameter("poster");
        String date = request.getParameter("date");
        MovieManager manager = new MovieManager();
        Integer movielength = Integer.parseInt(length);
        int movieID = manager.movieExists(oldName);
        boolean success = manager.updateMovie(name, genre, poster, date, movielength, rating, movieID);
        if (success) {
            request.getSession().setAttribute("Redirect", "Movie Modification Successful");
            request.getSession().setAttribute("RedirectLink", "ModifyMovieForward");
            this.getServletContext().getRequestDispatcher("/redirect.jsp").forward(request, response);
        } else {
            request.getSession().setAttribute("Redirect", "Movie Modification FAILURE");
            request.getSession().setAttribute("RedirectLink", "ModifyMovieForward");
            this.getServletContext().getRequestDispatcher("/redirect.jsp").forward(request, response);
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
