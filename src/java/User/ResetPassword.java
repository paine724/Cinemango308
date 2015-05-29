/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User;

import Bean.UserInfoBean;
import JPA.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Properties;
import java.security.SecureRandom;
import java.util.Random;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.math.BigInteger;

/**
 *
 * @author zeb
 */
public class ResetPassword extends HttpServlet {

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
        final String username = "cinemango308@gmail.com";
        final String password = "buttsbuttsbutts";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        UserManager manager = new UserManager();
        String email = request.getParameter("email");
        int userID = manager.emailExists(email);

        if (userID == -1) {
            request.getSession().setAttribute("Redirect", "FAILURE: Your email doesn't exist on this website!");
            request.getSession().setAttribute("RedirectLink", "ResetPasswordGetEmail.jsp");
            this.getServletContext().getRequestDispatcher("/redirect.jsp").forward(request, response);
        } else {
            User user=manager.getRegisteredUser(userID);
            Random random = new SecureRandom();
            BigInteger b=new BigInteger(130, random);
            String newpw=b.toString(32);
            user.setPassword(newpw.hashCode());
            manager.editProfile(user);
            Session session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                        }
                    });

            try {

                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("cinemango308@gmail.com"));
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(email));
                message.setSubject("CINEMANGO-Reset Password Request");
                
                message.setText("Dear Customer,"
                        + "\n\n Please login with the following password: \n"+ newpw+"\n"+"Please keep in mind that this password will be permanent unless you change it by going to: http://localhost:8084/Cinemango308/AccountSettings after you login. \n -Team Cinemango");

                Transport.send(message);

                System.out.println("Done");
                request.getSession().setAttribute("Redirect", "Email Sent Successfully");
                request.getSession().setAttribute("RedirectLink", "index.jsp");
                this.getServletContext().getRequestDispatcher("/redirect.jsp").forward(request, response);

            } catch (MessagingException e) {
                request.getSession().setAttribute("Redirect", "Email Failure");
                request.getSession().setAttribute("RedirectLink", "index.jsp");
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
