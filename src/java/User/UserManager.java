package User;

import JPA.Creditcard;
import JPA.Movie;
import JPA.Theatre;
import java.util.HashMap;
import java.util.Map;
import JPA.User;
import JPAController.CreditcardJpaController;
import JPAController.UserJpaController;
import JPAController.exceptions.NonexistentEntityException;
import Movie.MovieManager;
import Theatre.TheatreManager;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Konstantinos Pagonis
 */
public class UserManager {

    private static HashMap<User, String> users;
    private EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("Cinemango308PU");
    UserJpaController controller = new UserJpaController(emFactory);

    public Map getAllUsers() {

        return users;
    }

    public User getRegisteredUser(int userID) {
        UserJpaController controller = new UserJpaController(emFactory);
        User user = controller.findUser(userID);
        return user;
    }

    public User addUser(String fname, String lname, String email, int password) {
        //EntityManager em=emFactory.createEntityManager();
        //em.getTransaction().begin();
        JPA.User user = new User();
        user.setFirstName(fname);
        user.setLastName(lname);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole("RegisteredUser");

        controller.create(user);

        /*em.persist(a);
         em.getTransaction().commit();
         em.close();
         */
        return user;
    }

    public User addUser(String fname, String lname, String email, int password, String role) {
        //EntityManager em=emFactory.createEntityManager();
        //em.getTransaction().begin();
        JPA.User user = new User();
        user.setFirstName(fname);
        user.setLastName(lname);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(role);

        controller.create(user);

        /*em.persist(a);
         em.getTransaction().commit();
         em.close();
         */
        return user;
    }

    public void addCreditCard(int userID, double creditCardNumber, String expDate, String lname, String fname, char minitial, int securityCode, int zipCode, String cardType) throws ParseException {
        //EntityManager em=emFactory.createEntityManager();
        //em.getTransaction().begin();
        JPA.Creditcard card = new Creditcard();
        User user = getRegisteredUser(userID);
        card.setUserID(user);
        card.setCardNumber(creditCardNumber);
        card.setFirstName(fname);
        card.setLastName(lname);
        card.setMiddleInitial(minitial);
        card.setSecurityCode(securityCode);
        card.setZipCode(zipCode);
        DateFormat format = new SimpleDateFormat("yyyy/mm/dd", Locale.ENGLISH);
        Date date = format.parse(expDate);
        card.setExpireDate(date);
        card.setCardType(cardType);

        CreditcardJpaController creditCard = new CreditcardJpaController(emFactory);
        creditCard.create(card);

        /*em.persist(a);
         em.getTransaction().commit();
         em.close();
         */
    }

    public boolean removeUser(String email) {
        EntityManager em = emFactory.createEntityManager();
        try {

            Query query = em.createQuery("Select u.userID from User u where u.email= :email");
            query.setParameter("email", email);
            Integer result = (Integer) query.getSingleResult();

            //User removedUser=em.getReference(User.class, user.getUserID());
            User user = em.find(User.class, result);

            em.getTransaction().begin();
            em.remove(user);
            em.getTransaction().commit();
            //Query deleting= em.createQuery("DELETE from User u where u.userID= :userID");
            //deleting.setParameter("userID", result);
            //em.remove(user);
            em.close();
            return true;
        } catch (NoResultException n) {

            em.close();
            return false;

        }
    }

    public boolean updateUser(String firstName, String lastName, String email, String role, int id) {
        EntityManager em = emFactory.createEntityManager();
        boolean updateHappened;
        User user = controller.findUser(id);
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setRole(role);
        try {
            em.getTransaction().begin();

            controller.edit(user);
            em.getTransaction().commit();
            updateHappened = true;
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, ex);
            updateHappened = false;
        } catch (Exception ex) {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, ex);
            updateHappened = false;
        }
        em.close();
        return updateHappened;
    }

    public boolean checkSignUp(String s) {
        return false;
    }

    public boolean checkLogin(String email, int password) {
        /*EntityManager em=emFactory.createEntityManager();
         //em.getTransaction().begin();
         String q= "SELECT u.userID FROM user AS u WHERE u.email='"  + s+"'";
         TypedQuery<Integer> query=
         em.createQuery(q, Integer.class);
         List<Integer> results= query.getResultList();
         for(Integer result: results){
         System.out.println("asdf: "+result.toString());
         }
         */
        EntityManager em = emFactory.createEntityManager();
        Query query = em.createQuery("Select u.password from User u where u.email= :email");
        query.setParameter("email", email);

        Integer result = (Integer) query.getSingleResult();
        em.close();
        if (result == password) {
            return true;
        }
        return false;
    }

    public int getUserIdFromLogin(String email, int password) {
        if (checkLogin(email, password)) {
            EntityManager em = emFactory.createEntityManager();
            Query query = em.createQuery("Select u.userID from User u where u.email= :email");
            query.setParameter("email", email);
            Integer result = (Integer) query.getSingleResult();
            em.close();
            return result;
        } else {
            return -1;
        }
    }

    public String getRole(int id) {
        EntityManager em = emFactory.createEntityManager();
        Query query = em.createQuery("Select u.role from User u where u.userID= :id");
        query.setParameter("id", id);
        String result = (String) query.getSingleResult();
        em.close();
        return result;
    }

    public int emailExists(String email) {
        EntityManager em = emFactory.createEntityManager();
        Query query = em.createQuery("Select u.userID from User u where u.email= :email");
        query.setParameter("email", email);
        try {
            Integer result = (Integer) query.getSingleResult();
            em.close();
            return result;
        } catch (NoResultException e) {
            em.close();
            return -1;
        }
    }

    public boolean creditCardNumberExists(String creditCardNumber) {
        EntityManager em = emFactory.createEntityManager();
        Query query = em.createQuery("Select c.creditcardID from Creditcard c where c.cardNumber= :creditCardNumber");
        double cardNumber = Double.parseDouble(creditCardNumber);
        query.setParameter("creditCardNumber", cardNumber);
        try {
            query.getSingleResult();
            em.close();
            return true;
        } catch (NoResultException e) {
            em.close();
            return false;
        }
    }

    public boolean checkHashedPassword(String s) {
        return false;
    }

    public boolean updatePassword(int userID, int oldpassword, int password) throws NonexistentEntityException, Exception {
        User user = controller.findUser(userID);
        if (oldpassword == user.getPassword()) {
            user.setPassword(password);
            controller.edit(user);
            return true;
        }
        return false;
    }

    public void updateEmail(int userID, String email) throws NonexistentEntityException, Exception {
        User user = controller.findUser(userID);
        user.setEmail(email);
        controller.edit(user);
    }

    public boolean isTheatreAlreadyFavorited(User user, int theatreID) {
        List<Theatre> favoriteTheatres = user.getTheatreList();
        if (favoriteTheatres == null) {
            return false;
        } else {
            for (Theatre T : favoriteTheatres) {
                if (theatreID == T.getId()) {
                    return true;
                }

            }
        }
        return false;
    }

    public boolean addFavoriteTheatre(User user, int theatreID) throws NonexistentEntityException {
        TheatreManager theatreManager = new TheatreManager();
        List<Theatre> theatres = user.getTheatreList();
        Theatre theatre = theatreManager.getTheatre(theatreID);
        theatres.add(theatre);
        try {
            controller.edit(user);
            return true;
        } catch (Exception ex) {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean removeFavoriteTheatre(User user, int theatreID) throws NonexistentEntityException {
        TheatreManager theatreManager = new TheatreManager();
        List<Theatre> theatres = user.getTheatreList();
        Theatre theatre = theatreManager.getTheatre(theatreID);
        theatres.remove(theatre);
        try {
            controller.edit(user);
            return true;
        } catch (Exception ex) {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    public boolean isMovieAlreadyFavorited(User user, int movieID) {
        List<Movie> favoriteMovies = user.getMovieList();
        if (favoriteMovies == null) {
            return false;
        } else {
            for (Movie m : favoriteMovies) {
                if (movieID == m.getMovieID()) {
                    return true;
                }

            }
        }
        return false;
    }

    public boolean addFavoriteMovie(User user, int movieID) throws NonexistentEntityException {
        MovieManager movieManager = new MovieManager();
        List<Movie> movies = user.getMovieList();
        Movie movie = movieManager.getMovie(movieID);
        movies.add(movie);
        try {
            controller.edit(user);
            return true;
        } catch (Exception ex) {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean removeFavoriteMovie(User user, int movieID) throws NonexistentEntityException {
        MovieManager movieManager = new MovieManager();
        List<Movie> movies = user.getMovieList();
        Movie movie = movieManager.getMovie(movieID);
        movies.remove(movie);
        try {
            controller.edit(user);
            return true;
        } catch (Exception ex) {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean editProfile(User user){
         try {
            controller.edit(user);
            return true;
        } catch (Exception ex) {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
