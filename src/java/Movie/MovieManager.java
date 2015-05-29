package Movie;

import JPA.Actor;
import JPA.Comment;
import JPA.Movie;
import JPA.Movieratings;
import JPA.Moviereview;
import JPA.User;
import JPAController.CommentJpaController;
import JPAController.MovieJpaController;
import JPAController.MovieratingsJpaController;
import JPAController.MoviereviewJpaController;
import JPAController.exceptions.NonexistentEntityException;
import User.UserManager;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
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
public class MovieManager {

    private HashMap<Movie, Integer> movie;
    private Map<Actor, String> castAndCrew;
    private ArrayList<Movie> moviesNowPlaying;
    private ArrayList<Movie> newDVDRelease;

    private EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("Cinemango308PU");

    MovieJpaController controller = new MovieJpaController(emFactory);

    public Movie addMovie(String name, String rating, String genre, String movieLength, String poster, String date) throws ParseException {
        Movie movie = new Movie();
        DateFormat format = new SimpleDateFormat("yyyy-MMMM-d", Locale.ENGLISH);
        Date releaseDate = format.parse(date);

        movie.setDateReleased(releaseDate);
        movie.setGenre(genre);
        movie.setPoster(poster);
        movie.setName(name);
        movie.setMpaaRating(rating);
        movie.setMovieLength(Integer.parseInt(movieLength));
        controller.create(movie);

        return movie;

    }

    public ArrayList<Movie> getUpcomingMovies() {
        EntityManager em = emFactory.createEntityManager();
        ArrayList<Movie> upcomingMovies = new ArrayList();
        Query query = em.createQuery("select m.movieID from Movie m where m.upcoming=TRUE");
        try {
            List<Integer> result = query.getResultList();
            for (Integer q : result) {
                Movie m = getMovie(q.intValue());
                upcomingMovies.add(m);
            }
            em.close();
        } catch (NoResultException e) {
            em.close();
            upcomingMovies = null;
        }
        return upcomingMovies;
    }

    public ArrayList<Movie> search(String s) {
        EntityManager em = emFactory.createEntityManager();
        ArrayList<Movie> movies = new ArrayList<Movie>();
        Query query = em.createQuery("Select m.movieID, m.name from Movie m where m.name like :name");
        query.setParameter("name", "%" + s + "%");
        try {
            List<Object[]> result = query.getResultList();
            for (Object[] q : result) {
                Movie m = getMovie((int) q[0]);
                movies.add(m);
            }
            em.close();

            return movies;
        } catch (NoResultException e) {
            em.close();
            return null;
        }
    }

    public Map getAllMovies() {
        return movie;
    }

    public ArrayList<Movie> getRecentMovies() {
        EntityManager em = emFactory.createEntityManager();

        Query query = em.createQuery("select m.movieID from Movie m where m.upcoming=FALSE");
         ArrayList<Movie> recentMovies = new ArrayList();
          try {
            List<Integer> result = query.getResultList();
            for (Integer q : result) {
                Movie m = getMovie(q.intValue());
                recentMovies.add(m);
            }
            em.close();
        } catch (NoResultException e) {
            em.close();
            recentMovies = null;
        }
        return recentMovies;
    }

    public Movie getMovie(int ID) {
        MovieJpaController controller = new MovieJpaController(emFactory);
        Movie movie = controller.findMovie(ID);
        return movie;
    }

    public ArrayList<Movie> filter(String filter) {
        ArrayList<Movie> a = new ArrayList();
        return a;
    }

    public List getDVDReleases() {
        return newDVDRelease;
    }

    public int movieExists(String name) {
        EntityManager em = emFactory.createEntityManager();

        Query query = em.createQuery("Select m.movieID from Movie m where m.name= :name");
        query.setParameter("name", name);
        try {
            Integer result = (Integer) query.getSingleResult();
            em.close();
            return result;
        } catch (NoResultException e) {
            em.close();
            return -1;
        }

    }

    public void setFlagged(Moviereview m) throws Exception {
        EntityManager em = emFactory.createEntityManager();
        MoviereviewJpaController rcontroller = new MoviereviewJpaController(emFactory);
        rcontroller.edit(m);
    }

    public boolean updateMovie(String name, String genre, String poster, String date, Integer length, String rating, int id) {
        EntityManager em = emFactory.createEntityManager();
        boolean updateHappened;
        Movie movie = controller.findMovie(id);
        movie.setGenre(genre);
        movie.setPoster(poster);
        movie.setMpaaRating(rating);
        movie.setName(name);
        movie.setMovieLength(length);

        DateFormat format = new SimpleDateFormat("yyyy-MMMM-d", Locale.ENGLISH);
        try {
            Date releaseDate = format.parse(date);
            movie.setDateReleased(releaseDate);

            try {
                em.getTransaction().begin();
                controller.edit(movie);
                em.getTransaction().commit();
                updateHappened = true;

            } catch (NonexistentEntityException ex) {
                Logger.getLogger(MovieManager.class.getName()).log(Level.SEVERE, null, ex);
                updateHappened = false;
            } catch (Exception ex) {
                Logger.getLogger(MovieManager.class.getName()).log(Level.SEVERE, null, ex);
                updateHappened = false;
            }
            em.close();

        } catch (ParseException ex) {
            updateHappened = false;
        }

        return updateHappened;
    }

    public boolean removeMovie(String name) {
        EntityManager em = emFactory.createEntityManager();
        try {

            Query query = em.createQuery("Select m.movieID from Movie m where m.name= :name");
            query.setParameter("name", name);
            Integer result = (Integer) query.getSingleResult();

            //User removedUser=em.getReference(User.class, user.getUserID());
            Movie movie = em.find(Movie.class, result);

            em.getTransaction().begin();
            em.remove(movie);
            em.getTransaction().commit();

            em.close();
            return true;
        } catch (NoResultException n) {

            em.close();
            return false;

        }
    }

    public void adduserReview(int movieID, int userID, String review) {
        UserManager userManager = new UserManager();
        User user = userManager.getRegisteredUser(userID);
        MovieManager movieManager = new MovieManager();
        Moviereview userReview = new Moviereview();
        Movie m = movieManager.getMovie(movieID);
        userReview.setMovieid(m);
        userReview.setUserid(user);
        userReview.setReview(review);
        userReview.setFlagged(Boolean.FALSE);

        MoviereviewJpaController rcontroller = new MoviereviewJpaController(emFactory);
        rcontroller.create(userReview);
    }

    public Moviereview getReviewFromID(int id) {
        MoviereviewJpaController rcontroller = new MoviereviewJpaController(emFactory);
        Moviereview review = rcontroller.findMoviereview(id);
        return review;
    }

    public boolean deleteFlaggedReview(int id) {
        MoviereviewJpaController rcontroller = new MoviereviewJpaController(emFactory);
        boolean happened;
        try {
            rcontroller.destroy(id);
            return happened = true;
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(MovieManager.class.getName()).log(Level.SEVERE, null, ex);
            return happened = false;
        }
    }

    public ArrayList<Moviereview> getAllFlaggedReviews() {
        EntityManager em = emFactory.createEntityManager();

        ArrayList<Moviereview> flaggedReviews = new ArrayList();
        Query query = em.createQuery("select m.reviewID from Moviereview m where m.flagged=TRUE");
        try {
            List<Integer> result = query.getResultList();
            for (Integer q : result) {
                Moviereview m = getReviewFromID(q.intValue());
                flaggedReviews.add(m);
            }
            em.close();
        } catch (NoResultException e) {
            em.close();
            flaggedReviews = null;
        }
        return flaggedReviews;
    }

    public boolean movieRatingExists(int userid, int movieid) {
        EntityManager em = emFactory.createEntityManager();
        Query query = em.createQuery("Select r.id from Movieratings r where r.movieid= :movieid and r.userid= :userid");
        query.setParameter("movieid", getMovie(movieid));
        UserManager um=new UserManager();
        query.setParameter("userid", um.getRegisteredUser(userid));
        try {
            query.getSingleResult();
            em.close();
            return true;
        } catch (NoResultException e) {
            em.close();
            return false;
        }
    }

    public void rateMovie(int userid, int movieid, double rating) {
        UserManager userManager = new UserManager();
        if (movieRatingExists(userid, movieid)) {
            System.out.println("Rating Exists");
            return;
        }
        Movieratings mr = new Movieratings();
        mr.setMovieid(getMovie(movieid));
        mr.setUserid(userManager.getRegisteredUser(userid));
        mr.setRating(rating);
        MovieratingsJpaController rcontroller = new MovieratingsJpaController(emFactory);
        rcontroller.create(mr);
        
    }
    public int getNumRatings(int movieid){
        Movie movie=getMovie(movieid);
        List<Moviereview> ratings=movie.getMoviereviewList();
        int size=ratings.size();
        return size;
    }
    public double getAverageRating(int movieid){
        Movie movie=getMovie(movieid);
        List<Moviereview> ratings=movie.getMoviereviewList();
        EntityManager em = emFactory.createEntityManager();
        Query query = em.createQuery("Select AVG(r.rating) from Movieratings r where r.movieid= :movieid");
        query.setParameter("movieid",getMovie(movieid));
        try{
            double result =(double) query.getSingleResult();
            return result;
        }catch(NullPointerException e) {
            em.close();
            return 0;
        }catch(NoResultException e) {
            em.close();
            return 0;
        }
    }
}
