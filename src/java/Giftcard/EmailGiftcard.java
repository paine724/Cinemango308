/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Giftcard;

import Bean.ErrorMessageBean;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Bean.UserInfoBean;
import JPA.User;
import User.UserManager;
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
 * @author Andrew
 */
public class EmailGiftcard extends HttpServlet {

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
        GiftCardManager manager = new GiftCardManager();
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        Double price = Double.parseDouble(request.getParameter("cost"));
        String email = request.getParameter("email");
        String msg= request.getParameter("message");
        String to= request.getParameter("to");
        String from= request.getParameter("from");
        int number = 0;
        for (int i = 0; i < quantity; i++) {
            number = 1000000000 + (int) (Math.random() * (1000000000 + 1));
            while (manager.checkNumberExists(number)) {
                number = 1000000000 + (int) (Math.random() * (1000000000 + 1));
            }
            manager.addGiftCard(number, price);

            final String username = "cinemango308@gmail.com";
            final String password = "buttsbuttsbutts";

            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

            Random random = new SecureRandom();
            BigInteger b = new BigInteger(130, random);
            String newpw = b.toString(32);
            //user.setPassword(newpw.hashCode());
            //manager.editProfile(user);
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
                message.setSubject("CINEMANGO-Email Giftcard");

                message.setText("Dear "+ to+ ",\n\n" + from
                        + " has sent you a Cinemango giftcard with the following message:\n"+ msg+ "\n Your giftcard number is: \n" + number + "\n" + "The value of your giftcard is $" + price + "0");

                Transport.send(message);

                request.getSession().setAttribute("Redirect", "Check your email for giftcard number!");
                request.getSession().setAttribute("RedirectLink", "index.jsp");
                this.getServletContext().getRequestDispatcher("/redirect.jsp").forward(request, response);

            } catch (MessagingException e) {
                request.getSession().setAttribute("Redirect", "SYSTEM ERROR: Email Failure");
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
