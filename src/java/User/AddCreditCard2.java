/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User;

import Bean.ErrorMessageBean;
import Bean.UserInfoBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Philip
 */
public class AddCreditCard2 extends HttpServlet {

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
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");

        long creditcard = 0;
        String fname = "", lname = "", minitial = "", expDate = "", securityCode = "", zipCode = "", creditCardSegment = "", creditCardNumber = "", cardType = "";

        boolean fnameValid = false;
        boolean lnameValid = false;
        boolean creditCardSegmentValid = false;
        boolean creditCardNumberValid = false;
        boolean minitialValid = false;
        boolean expDateValid = false;
        boolean zipCodeValid = false;
        boolean securityCodeValid = false;
        boolean cardTypeValid = false;
        try (PrintWriter out = response.getWriter()) {
            if (request.getParameter("firstname") != null) {
                fname = request.getParameter("firstname");
                if (fname.equals("")) {
                    fnameValid = false;
                } else {
                    if (fname.matches("[a-zA-Z]{1,}")) {
                        fnameValid = true;
                    }
                }
            }

            if (request.getParameter("lastname") != null) {
                lname = request.getParameter("lastname");
                if (lname.equals("")) {
                    lnameValid = false;
                } else {
                    if (lname.matches("[a-zA-Z]{1,}")) {
                        lnameValid = true;
                    }
                }
            }
            if (request.getParameter("cardtype") != null) {
                cardType = request.getParameter("cardtype");
                if (cardType.equals("")) {
                    cardTypeValid = false;
                } else {
                    if (lname.matches("[a-zA-Z]{1,}")) {
                        cardTypeValid = true;
                    }
                }
            }
            if (request.getParameter("middleinitial") != null) {
                minitial = request.getParameter("middleinitial");
                if (minitial.equals("")) {
                    minitialValid = false;
                } else {
                    if (minitial.matches("[a-zA-Z]{0,1}")) {
                        minitialValid = true;
                    }
                }
            }
            if (request.getParameter("securitycode") != null) {
                securityCode = request.getParameter("securitycode");
                if (securityCode.equals("")) {
                    securityCodeValid = false;
                } else {
                    if (securityCode.matches("[0-9]{3}")) {
                        securityCodeValid = true;
                    }
                }
            }
            if (request.getParameter("zipcode") != null) {
                zipCode = request.getParameter("zipcode");
                if (zipCode.equals("")) {
                    zipCodeValid = false;
                } else {
                    if (zipCode.matches("[0-9]{5}")) {
                        zipCodeValid = true;
                    }
                }
            }
            if (request.getParameter("creditcard1") != null) {
                creditCardSegment = request.getParameter("creditcard1");
                if (creditCardSegment.equals("")) {
                    creditCardSegmentValid = false;
                } else {
                    if (creditCardSegment.matches("[0-9]{4}")) {
                        creditCardSegmentValid = true;
                    }
                }
                creditCardNumber = creditCardNumber.concat(creditCardSegment);
            }
            if (request.getParameter("creditcard2") != null) {
                creditCardSegment = request.getParameter("creditcard2");
                if (creditCardSegment.equals("")) {
                    creditCardSegmentValid = false;
                } else {
                    if (creditCardSegment.matches("[0-9]{4}")) {
                        creditCardSegmentValid = true;
                    }
                }
                creditCardNumber = creditCardNumber.concat(creditCardSegment);
            }
            if (request.getParameter("creditcard3") != null) {
                creditCardSegment = request.getParameter("creditcard3");
                if (creditCardSegment.equals("")) {
                    creditCardSegmentValid = false;
                } else {
                    if (creditCardSegment.matches("[0-9]{4}")) {
                        creditCardSegmentValid = true;
                    }
                }
                creditCardNumber = creditCardNumber.concat(creditCardSegment);
            }
            if (request.getParameter("creditcard4") != null) {
                creditCardSegment = request.getParameter("creditcard4");
                if (creditCardSegment.equals("")) {
                    creditCardSegmentValid = false;
                } else {
                    if (creditCardSegment.matches("[0-9]{4}")) {
                        creditCardSegmentValid = true;
                    }
                }
                creditCardNumber = creditCardNumber.concat(creditCardSegment);
            }

            if (request.getParameter("expdate") != null) {
                expDate = request.getParameter("expdate");

                Pattern p = Pattern.compile("^[0-9]{4}[/]{1}[0-9]{2}$", Pattern.CASE_INSENSITIVE);
                Matcher m = p.matcher(expDate);
                if (m.find()) {
                    expDateValid = true;
                } else {
                    expDateValid = false;
                }
            }

            UserManager manager = new UserManager();
            if (creditCardNumber.matches("[0-9]{16}")) {
                if (!(manager.creditCardNumberExists(creditCardNumber))) {
                    creditCardNumberValid = true;
                }
            }
            ErrorMessageBean ErrorMsg = new ErrorMessageBean();
            if (!fnameValid) {
                ErrorMsg.setErrorMsg(ErrorMsg + "Please Enter First Name<BR>");
            }
            if (!lnameValid) {
                ErrorMsg.setErrorMsg(ErrorMsg + "Please Enter Last Name<BR>");
            }
            if (!zipCodeValid) {
                ErrorMsg.setErrorMsg(ErrorMsg + "Please Enter Zip Code<BR>");
            }
            if (!expDateValid) {
                ErrorMsg.setErrorMsg(ErrorMsg + "Please Enter Expiration Date<BR>");
            }
            if (!securityCodeValid) {
                ErrorMsg.setErrorMsg(ErrorMsg + "Please Enter Security Code<BR>");
            }
            if (!creditCardNumberValid) {
                ErrorMsg.setErrorMsg(ErrorMsg + "Please Enter Credit Card Number<BR>");
            }

            if (!ErrorMsg.getErrorMsg().equals("")) {
                request.getSession().setAttribute("ErrorMsg", ErrorMsg);
                request.getSession().setAttribute("Redirect", "Credit Card Failed");
                request.getSession().setAttribute("RedirectLink", "/AccountSettings");
                this.getServletContext().getRequestDispatcher("/redirect.jsp").forward(request, response);

            }
            if (creditCardNumberValid && fnameValid && lnameValid && securityCodeValid && expDateValid && zipCodeValid) {
                double creditCard = Double.parseDouble(creditCardNumber);
                int zip = Integer.parseInt(zipCode);
                int security = Integer.parseInt(securityCode);
                expDate = expDate.concat("/01");
                char middleI = minitial.charAt(0);
                UserInfoBean bean = (UserInfoBean) request.getSession().getAttribute("userinfobean");
                int userID = bean.getUserId();
                manager.addCreditCard(userID, creditCard, expDate, lname, fname, middleI, security, zip, cardType);
                //int userid=user.getUserID().intValue();
                request.getSession().setAttribute("ErrorMsg", new ErrorMessageBean());
                request.getSession().setAttribute("Redirect", "Credit Card Successful");
                request.getSession().setAttribute("RedirectLink", "/AccountSettings");
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(AddCreditCard2.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (ParseException ex) {
            Logger.getLogger(AddCreditCard2.class.getName()).log(Level.SEVERE, null, ex);
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
