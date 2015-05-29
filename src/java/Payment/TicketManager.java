/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Payment;

import JPA.Movie;
import JPA.Showtime;
import JPA.Ticket;
import JPAController.TicketJpaController;
import Movie.MovieManager;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Andrew
 */
public class TicketManager {

    private EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("Cinemango308PU");
    TicketJpaController controller = new TicketJpaController(emFactory);
    ShowtimeManager showtimemanager = new ShowtimeManager();
    MovieManager moviemanager = new MovieManager();

    public Ticket addTicket(Showtime showtime, int age) {
        Ticket ticket = new Ticket();
        Movie m = showtime.getMovieID();
        ticket.setMovieID(m);
        ticket.setShowtimeID(showtime);
        ticket.setTheatreID(showtime.getTheatreID());
        ticket.setAge(age);
        Calendar currentDate= Calendar.getInstance();
        Date date= currentDate.getTime();
        ticket.setDate(date);
        if(age==1){
            ticket.setPrice(10.00);
            
        }
        else if(age==2){
            ticket.setPrice(6.00);
            
        }
        else{
            ticket.setPrice(5.00);
        }
         controller.create(ticket);
         
         return ticket;
    }
}
