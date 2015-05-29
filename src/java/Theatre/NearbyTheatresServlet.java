/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Theatre;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Andrew
 */
public class NearbyTheatresServlet extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            String lat  = request.getParameter("Latitude");
            String lon= request.getParameter("Longitude");
            String rad= request.getParameter("Radius");
            
            float latitude= parseFloat(lat);
            float longitude= parseFloat(lon);
            int radius= parseInt(rad);
            
            TheatreManager manager = new TheatreManager();
            List<Object[]> result=manager.getTheatreByLocation(latitude, longitude, radius);
            String output="";
            for(Object[] q:result){
                output+="<li>";
                output+="<a href='TheatreForward?theatreid="+q[0]+"'>";
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
