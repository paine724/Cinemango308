/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import JPAController.PhotoJpaController;
import Theatre.TheatreManager;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Andrew
 */
public class test2Servlet extends HttpServlet {
    
    private EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("Cinemango308PU");

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
        try (PrintWriter out = response.getWriter()) {
            Cookie[] cookies = request.getCookies();
            float latitude=0;
            float longitude=0;
            int radius=10;
            for(Cookie cookie : cookies){
                if("latitude".equals(cookie.getName())){
                    latitude = parseFloat(cookie.getValue());
                }
                if("longitude".equals(cookie.getName())){
                    longitude = parseFloat(cookie.getValue());
                }
                if("radius".equals(cookie.getName())){
                    radius = parseInt(cookie.getValue());
                }
            }
            TheatreManager manager = new TheatreManager();
            List<Object[]> result=manager.getTheatreByLocation(latitude, longitude, radius);
            String output="";
            /*output+="{";
            for(Object[] q:result){
                output+="\t\""+q[0]+"\": {";
                //output+="\t{";
                output+="\t\t\"ID\": \""+q[0]+"\",";
                output+="\t\t\"Name\": \""+q[1]+"\",";
                output+="\t\t\"Address\": \""+q[2]+"\",";
                output+="\t\t\"City\": \""+q[3]+"\",";
                output+="\t\t\"State\": \""+q[4]+"\",";
                output+="\t\t\"Zipcode\": \""+q[5]+"\",";
                output+="\t\t\"Distance\": \""+q[6]+"\"";
                output+="\t},";
            }
            output=output.substring(0,output.length()-1);
            output+="}";*/
            for(Object[] q:result){
                //output+="\t\""+q[0]+"\": {";
                output+="<li>";
                output+="<a href=#>";
                output+=q[1];
                /*
                output+="\"ID\": \""+q[0]+"\",";
                output+="\"Name\": \""+q[1]+"\",";
                output+="\"Address\": \""+q[2]+"\",";
                output+="\"City\": \""+q[3]+"\",";
                output+="\"State\": \""+q[4]+"\",";
                output+="\"Zipcode\": \""+q[5]+"\",";
                output+="\"Distance\": \""+q[6]+"\"";*/
                output+="</a>";
                output+="</li>";
            }
            output+="<input name=\"MainMenuControlshowtimesMenulocationTextBox\" type=\"text\" value=\"\" id=\"MainMenuControl_showtimesMenu_locationTextBox\" class=\"global-search-input global-showtimes-input full-width\" onkeypress=\"\" placeholder=\"Enter a City, State or Zip Code\">\n" +
"                                    <input type=\"submit\" value=\"search\">";
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
