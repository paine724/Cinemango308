/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ForwardServlet;

import Bean.TicketBean;
import Bean.UserInfoBean;
import JPA.Creditcard;
import JPA.User;
import User.UserManager;
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
 * @author zeb
 */
public class PurchaseTicket1 extends HttpServlet {

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
        String showtime=request.getParameter("showtime");
        String adultNum=request.getParameter("adultTickets");
        String seniorNum= request.getParameter("seniorTickets");
        String childNum=request.getParameter("childTickets");
        int adult=Integer.parseInt(adultNum);
        int seniors= Integer.parseInt(seniorNum);
        int children= Integer.parseInt(childNum);
        TicketBean bean= new TicketBean();
        bean.setAdults(adult);
        bean.setChildren(children);
        bean.setSeniors(seniors);
        bean.setShowtimeid(parseInt(showtime));
        request.getSession().setAttribute("tickets", bean);
        List<Creditcard> cc=new ArrayList<>();
        UserManager userManager=new UserManager();
        UserInfoBean userinfobean=(UserInfoBean) request.getSession().getAttribute("userinfobean");
        if(userinfobean!=null){
            User a = userManager.getRegisteredUser(userinfobean.getUserId());
            cc=a.getCreditcardList();
            request.getSession().setAttribute("cclist",cc);
        }
        getServletContext().getRequestDispatcher("/ticketcheckout2.jsp").forward(request, response);
    
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
