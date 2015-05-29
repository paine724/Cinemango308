/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Payment;

import Bean.PurchaseBean;
import Bean.TicketBean;
import Bean.UserInfoBean;
import Giftcard.GiftCardManager;
import JPA.Giftcard;
import JPA.Movie;
import JPA.Payment;
import JPA.Showtime;
import JPA.Theatre;
import JPA.Ticket;
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
public class TicketPurchase extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            TicketBean bean = (TicketBean) request.getSession().getAttribute("tickets");
            int numChildren = bean.getChildren();
            int numAdults = bean.getAdults();
            int numSeniors = bean.getSeniors();
            int showtimeid = bean.getShowtimeid();
            TicketManager ticketManager = new TicketManager();
            GiftCardManager giftcardManager = new GiftCardManager();
            UserManager userManager = new UserManager();
            PaymentManager paymentManager = new PaymentManager();
            ShowtimeManager STmanager = new ShowtimeManager();
            Showtime showtime = STmanager.find(showtimeid);
            double cost = (numAdults * 10) + (numChildren * 6) + (numSeniors * 5);
            if (request.getParameter("purchaseRadio").equals("giftcard")) {
                String giftcardnum = request.getParameter("giftcardnumber");

                int number = Integer.parseInt(giftcardnum);
                boolean giftcardExists = giftcardManager.checkNumberExists(number);
                Theatre theatre = showtime.getTheatreID();
                int theatreID = theatre.getId();
                String failure = "TheatreForward?theatreid=" + theatreID;
                if (giftcardExists) {
                    Giftcard giftcard = giftcardManager.getGiftCardByCode(number);
                    double balance = giftcard.getBalance();
                    if (balance < cost) {
                        request.getSession().setAttribute("Redirect", "FAILURE: Insufficient Gift card balance!");
                        request.getSession().setAttribute("RedirectLink", failure);
                        this.getServletContext().getRequestDispatcher("/redirect.jsp").forward(request, response);
                    } else {
                        giftcard.setBalance(balance - cost);
                        boolean happened = giftcardManager.editGiftcard(giftcard);
                        if (happened) {
                            List<Ticket> tickets = new ArrayList();
                            for (int i = 0; i < numAdults; i++) {
                                Ticket ticket = ticketManager.addTicket(showtime, 1);
                                tickets.add(ticket);
                            }
                            for (int i = 0; i < numChildren; i++) {
                                Ticket ticket = ticketManager.addTicket(showtime, 2);
                                tickets.add(ticket);
                            }
                            for (int i = 0; i < numSeniors; i++) {
                                Ticket ticket = ticketManager.addTicket(showtime, 3);
                                tickets.add(ticket);
                            }
                            UserInfoBean userinfobean = (UserInfoBean) request.getSession().getAttribute("userinfobean");
                            if (userinfobean != null) {
                                //user
                                int userID = userinfobean.getUserId();
                                   List<PurchaseBean> pbeans = new ArrayList();
                                for (Ticket t : tickets) {
                                    Payment payment = new Payment();
                                    payment.setGiftcardID(giftcard);
                                    payment.setTicketID(t);
                                    payment.setUserID(userManager.getRegisteredUser(userID));
                                    paymentManager.addPayment(payment);
                                    PurchaseBean purchaseBean = new PurchaseBean();
                                    Movie m = t.getMovieID();
                                    String mname = m.getName();
                                    purchaseBean.setMovieName(mname);
                                    Theatre th = t.getTheatreID();
                                    String tname = th.getName();
                                    purchaseBean.setTheatreName(tname);
                                    purchaseBean.setPrice(t.getPrice());
                                    purchaseBean.setTicketType(t.getAge());
                                    pbeans.add(purchaseBean);
                                }
                                request.getSession().setAttribute("ticketlist", tickets);
                                request.getSession().setAttribute("ticketcost", cost);
                                request.getSession().setAttribute("pbeans", pbeans);
                                this.getServletContext().getRequestDispatcher("/TicketPrintout.jsp").forward(request, response);
                            } else {
                                //guest
                                List<PurchaseBean> pbeans = new ArrayList();
                                for (Ticket t : tickets) {
                                    PurchaseBean purchaseBean = new PurchaseBean();
                                    Movie m = t.getMovieID();
                                    String mname = m.getName();
                                    purchaseBean.setMovieName(mname);
                                    Theatre th = t.getTheatreID();
                                    String tname = th.getName();
                                    purchaseBean.setTheatreName(tname);
                                    purchaseBean.setPrice(t.getPrice());
                                    purchaseBean.setTicketType(t.getAge());
                                    pbeans.add(purchaseBean);
                                }

                                request.getSession().setAttribute("ticketlist", tickets);
                                request.getSession().setAttribute("ticketcost", cost);
                                request.getSession().setAttribute("pbeans", pbeans);
                                
                                this.getServletContext().getRequestDispatcher("/TicketPrintout.jsp").forward(request, response);
                            }
                        } else {
                            request.getSession().setAttribute("Redirect", "Something went wrong! Try again later");
                            request.getSession().setAttribute("RedirectLink", failure);
                            this.getServletContext().getRequestDispatcher("/redirect.jsp").forward(request, response);
                        }
                    }
                } else {
                    request.getSession().setAttribute("Redirect", "FAILURE: No giftcard with this number found");
                    request.getSession().setAttribute("RedirectLink", failure);
                    this.getServletContext().getRequestDispatcher("/redirect.jsp").forward(request, response);
                }
            } else if (request.getParameter("purchaseRadio").equals("creditcard")) {
                if(!request.getParameter("preselectedCC").equals("0")){
                    
                }else{
                    String cardnumber = request.getParameter("cardNumber");
                    String month = request.getParameter("month");
                    String year = request.getParameter("year");
                    String fname = request.getParameter("firstname");
                    String lname = request.getParameter("lastname");
                    String zipcode = request.getParameter("zipcode");
                    String seccode = request.getParameter("seccode");
                }
                List<Ticket> tickets = new ArrayList();
                for (int i = 0; i < numAdults; i++) {
                    Ticket ticket = ticketManager.addTicket(showtime, 1);
                    tickets.add(ticket);
                }
                for (int i = 0; i < numChildren; i++) {
                    Ticket ticket = ticketManager.addTicket(showtime, 2);
                    tickets.add(ticket);
                }
                for (int i = 0; i < numSeniors; i++) {
                    Ticket ticket = ticketManager.addTicket(showtime, 3);
                    tickets.add(ticket);
                }
                UserInfoBean userinfobean = (UserInfoBean) request.getSession().getAttribute("userinfobean");
                if (userinfobean != null) {
                    //user
                    int userID = userinfobean.getUserId();
                    List<PurchaseBean> pbeans = new ArrayList();
                    for (Ticket t : tickets) {
                        PurchaseBean purchaseBean = new PurchaseBean();
                        Payment payment = new Payment();
                        payment.setTicketID(t);
                        Movie m = payment.getTicketID().getMovieID();
                        String mname = m.getName();
                        purchaseBean.setMovieName(mname);
                        Theatre th = t.getTheatreID();
                        String tname = th.getName();
                        purchaseBean.setTheatreName(tname);
                        payment.setTotal(t.getPrice());
                        purchaseBean.setPrice(t.getPrice());
                        purchaseBean.setTicketType(t.getAge());
                        pbeans.add(purchaseBean);
                        payment.setUserID(userManager.getRegisteredUser(userID));
                        paymentManager.addPayment(payment);
                    }
                    request.getSession().setAttribute("ticketlist", tickets);
                    request.getSession().setAttribute("ticketcost", cost);
                    request.getSession().setAttribute("pbeans", pbeans);
                    this.getServletContext().getRequestDispatcher("/TicketPrintout.jsp").forward(request, response);
                } else {
                    //guest
                    List<PurchaseBean> pbeans = new ArrayList();
                    for (Ticket t : tickets) {
                        PurchaseBean purchaseBean = new PurchaseBean();
                        Movie m = t.getMovieID();
                        String mname = m.getName();
                        purchaseBean.setMovieName(mname);
                        Theatre th = t.getTheatreID();
                        String tname = th.getName();
                        purchaseBean.setTheatreName(tname);
                        purchaseBean.setPrice(t.getPrice());
                        purchaseBean.setTicketType(t.getAge());
                        pbeans.add(purchaseBean);
                    }

                    request.getSession().setAttribute("ticketlist", tickets);
                    request.getSession().setAttribute("ticketcost", cost);
                    request.getSession().setAttribute("pbeans", pbeans);
                    this.getServletContext().getRequestDispatcher("/TicketPrintout.jsp").forward(request, response);
                }
            } else {
                request.getSession().setAttribute("Redirect", "FAILURE: Shouldn't even get here you noob!!");
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
