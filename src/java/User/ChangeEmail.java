/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User;

import Bean.ErrorMessageBean;
import Bean.UserInfoBean;
import JPAController.exceptions.NonexistentEntityException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Andrew
 */
public class ChangeEmail extends HttpServlet {

    private static EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("Cinemango308PU");

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
            throws ServletException, IOException, NonexistentEntityException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        String email = "";
        try (PrintWriter out = response.getWriter()) {
            boolean emailValid = false;
            UserManager manager = new UserManager();
            if (request.getParameter("newemail") != null) {
                email = request.getParameter("newemail");
                if (email.equals(request.getParameter("confirmemail"))) {
                    Pattern p = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
                    Matcher m = p.matcher(email);
                    if (m.find()) {
                        if (!(manager.emailExists(email) > 0)) {
                            UserInfoBean bean = (UserInfoBean) request.getSession().getAttribute("userinfobean");
                            int userid = (int) bean.getUserId();
                            manager.updateEmail(userid, email);
                            bean.setEmail(email);
                            emailValid = true;
                            request.getSession().setAttribute("userinfobean", bean);
                            request.getSession().setAttribute("ErrorMsg", new ErrorMessageBean());
                            getServletContext().getRequestDispatcher("/AccountSettings").forward(request, response);
                        }
                    }
                }
            }
            if (!emailValid) {
                ErrorMessageBean ErrorMsg = new ErrorMessageBean("Invalid Email");
                request.getSession().setAttribute("ErrorMsg", ErrorMsg);
                getServletContext().getRequestDispatcher("/AccountSettings").forward(request, response);
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
            Logger.getLogger(ChangeEmail.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ChangeEmail.class.getName()).log(Level.SEVERE, null, ex);
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
