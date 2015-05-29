/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User;

import Bean.ErrorMessageBean;
import JPA.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author zeb
 */
public class AdminAddUser extends HttpServlet {

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

        String email = "", fname = "", lname = "", role = "";
        int password = 0;
        boolean emailValid = false;
        boolean fnameValid = false;
        boolean lnameValid = false;
        boolean passwordValid = false;
        try (PrintWriter out = response.getWriter()) {
            role = request.getParameter("role");
            if (request.getParameter("firstname") != null) {
                fname = request.getParameter("firstname");
                if (fname.equals("")) {
                    fnameValid = false;
                } else {
                    if (fname.matches("[a-zA-Z]{0,}")) {
                        fnameValid = true;
                    }
                }
            }

            if (request.getParameter("lastname") != null) {
                lname = request.getParameter("lastname");
                if (lname.equals("")) {
                    lnameValid = false;
                } else {
                    if (lname.matches("[a-zA-Z]{0,}")) {
                        lnameValid = true;
                    }
                }
            }

            if (request.getParameter("email") != null) {
                email = request.getParameter("email");
                UserManager manager = new UserManager();
                Pattern p = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
                Matcher m = p.matcher(email);
                if (m.find() && (!(manager.emailExists(email) > 0))) {
                    emailValid = true;
                }
            }
            String pass = request.getParameter("password");
            password = pass.hashCode();
            ErrorMessageBean ErrorMsg = new ErrorMessageBean();
            if (!fnameValid) {
                ErrorMsg.setErrorMsg(ErrorMsg + "Please Enter First Name<BR>");
            }
            if (!lnameValid) {
                ErrorMsg.setErrorMsg(ErrorMsg + "Please Enter Last Name<BR>");
            }
            if (!emailValid) {
                ErrorMsg.setErrorMsg(ErrorMsg + "Please Enter Email<BR>");
            }
            if (!ErrorMsg.getErrorMsg().equals("")) {
                request.getSession().setAttribute("ErrorMsg", ErrorMsg);
                request.getSession().setAttribute("Redirect", "User Addition Failed");
                request.getSession().setAttribute("RedirectLink", "AdminAddUser.jsp");
                this.getServletContext().getRequestDispatcher("/redirect.jsp").forward(request, response);

            }
            if (emailValid && fnameValid && lnameValid) {
                UserManager manager = new UserManager();
                User user = manager.addUser(fname, lname, email, password, role);
                //int userid=user.getUserID().intValue();
                request.getSession().setAttribute("ErrorMsg", new ErrorMessageBean());
                request.getSession().setAttribute("Redirect", "User Addition Successful");
                request.getSession().setAttribute("RedirectLink", "AdminAddUser.jsp");
                this.getServletContext().getRequestDispatcher("/redirect.jsp").forward(request, response);
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
