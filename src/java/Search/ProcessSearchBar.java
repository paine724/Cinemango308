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
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Andrew
 */
public class ProcessSearchBar extends HttpServlet {

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
        String search = request.getParameter("term");
        try (PrintWriter out = response.getWriter()) {
            MovieManager movieManager = new MovieManager();
            TheatreManager theatreManager= new TheatreManager();
            ActorManager actorManager= new ActorManager();
            ArrayList<Movie> movies = movieManager.search(search);
            ArrayList<Theatre> theatres= theatreManager.search(search);
            ArrayList<Actor> actors= actorManager.search(search);
            String output="";
            output+="[";
            for(Movie m:movies){
                /*
                output+="{";
                output+="label: \""+m.getName()+"\",";
                output+="url: \"Movie?movieid="+m.getMovieID()+"\",";
                output+="category: \"Movie\"";
                output+="},";
                */
                output+="\""+m.getName()+"\",";
            }
            for(Theatre t:theatres){
                output+="\""+t.getName()+"\",";
            }
            for(Actor a:actors){
                output+="\""+a.getFirstname()+" "+a.getLastName()+"\",";
            }
            output=output.substring(0,output.length()-1);
            output+="]";
            out.print(output);
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
