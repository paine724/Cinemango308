/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ForwardServlet;

import Bean.UserInfoBean;
import JPA.User;
import User.UserManager;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author zeb
 */
public class ModifyUser2 extends HttpServlet {

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
       String email= request.getParameter("email");
       UserManager manager= new UserManager();
       int userID= manager.emailExists(email);
       if(userID<0){
               request.getSession().setAttribute("Redirect","ERROR: Invalid Email");
            request.getSession().setAttribute("RedirectLink","ModifyUserForward");
                this.getServletContext().getRequestDispatcher("/redirect.jsp").forward(request, response);
       }
       else{
         User toEdit= manager.getRegisteredUser(userID);
           UserInfoBean user=new UserInfoBean();
           user.setFirstName(toEdit.getFirstName());
            user.setLastName(toEdit.getLastName());
           user.setEmail(toEdit.getEmail());
           user.setUserId(toEdit.getUserID());
           user.setRole(toEdit.getRole());
           //user.setPassword(toEdit.getPassword());
           request.getSession().setAttribute("Edit", user);
           
            getServletContext().getRequestDispatcher("/ModifyUser.jsp").forward(request, response);
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
