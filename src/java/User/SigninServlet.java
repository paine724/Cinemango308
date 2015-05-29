/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User;

import Bean.ErrorMessageBean;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Bean.UserInfoBean;

/**
 *
 * @author Andrew
 */
public class SigninServlet extends HttpServlet {

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
        String email = request.getParameter("email");
        int password = request.getParameter("password").hashCode();
        int userid = -1;
        String userRole;
        String firstName;
        UserManager manager = new UserManager();
        if (manager.emailExists(email) > 0) {
            userid = manager.getUserIdFromLogin(email, password);
        } else {
            userid = -1;
        }
        if (userid != -1) {
            UserInfoBean bean = new UserInfoBean();
            bean.setEmail(manager.getRegisteredUser(userid).getEmail());

            userRole = manager.getRegisteredUser(userid).getRole();
            firstName = manager.getRegisteredUser(userid).getFirstName();
            bean.setUserId(userid);
            bean.setRole(userRole);
            bean.setFirstName(firstName);
            bean.setEmail(email);
            request.getSession().setAttribute("userinfobean", bean);
            request.getSession().setAttribute("ErrorMsg", new ErrorMessageBean());
            if (userRole.equalsIgnoreCase("Admin")) {
                this.getServletContext().getRequestDispatcher("/AdminHomepageForward").forward(request, response);

            } else {
                this.getServletContext().getRequestDispatcher("/UserAccountOverview").forward(request, response);

            }

        } else {
            ErrorMessageBean ErrorMsg = new ErrorMessageBean("Invalid Login");
            request.getSession().setAttribute("ErrorMsg", ErrorMsg);
            this.getServletContext().getRequestDispatcher("/signin.jsp").forward(request, response);
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
