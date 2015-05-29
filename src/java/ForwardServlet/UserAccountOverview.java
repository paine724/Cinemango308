/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ForwardServlet;

import Bean.ErrorMessageBean;
import Bean.FavoriteMovieBean;
import Bean.FavoriteTheatreBean;
import Bean.UserInfoBean;
import JPA.Movie;
import JPA.Moviereview;
import JPA.Payment;
import JPA.Theatre;
import JPA.User;
import User.UserManager;
import java.io.IOException;
import java.io.PrintWriter;
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
public class UserAccountOverview extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            UserInfoBean bean = (UserInfoBean) request.getSession().getAttribute("userinfobean");
            FavoriteTheatreBean theatrebean = new FavoriteTheatreBean();
            FavoriteMovieBean moviebean= new FavoriteMovieBean();
           
            request.getSession().setAttribute("ErrorMsg", new ErrorMessageBean());
            if (bean.getUserId() > 0) {
                UserManager manager = new UserManager();
                User user = manager.getRegisteredUser(bean.getUserId());
                List<Theatre> theatres = user.getTheatreList();
                theatrebean.setTheatreList(theatres);
                theatrebean.setUserId(bean.getUserId());
                request.getSession().setAttribute("favoritetheatrebean", theatrebean);
                
                List<Movie> movies= user.getMovieList();
                moviebean.setMovieId(movies);
                moviebean.setUserId(bean.getUserId());
                request.getSession().setAttribute("favoritemoviebean", moviebean);
                
                List<Moviereview> userReviews=user.getMoviereviewList();
                request.getSession().setAttribute("userReviews", userReviews);
                List<Payment> payments=user.getPaymentList();
                //payments.get(0).getTicketID().getPrice()
                request.getSession().setAttribute("payments", payments);
                getServletContext().getRequestDispatcher("/useraccountoverview.jsp").forward(request, response);
               
            } else {
                request.getSession().setAttribute("userinfobean", new UserInfoBean());
                getServletContext().getRequestDispatcher("/SignIn").forward(request, response);
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
