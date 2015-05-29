/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPAController;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import JPA.Actor;
import java.util.ArrayList;
import java.util.List;
import JPA.User;
import JPA.Ticket;
import JPA.Movieratings;
import JPA.Moviereview;
import JPA.Showtime;
import JPA.Comment;
import JPA.Movie;
import JPAController.exceptions.IllegalOrphanException;
import JPAController.exceptions.NonexistentEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author zeb
 */
public class MovieJpaController implements Serializable {

    public MovieJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Movie movie) {
        if (movie.getActorList() == null) {
            movie.setActorList(new ArrayList<Actor>());
        }
        if (movie.getUserList() == null) {
            movie.setUserList(new ArrayList<User>());
        }
        if (movie.getTicketList() == null) {
            movie.setTicketList(new ArrayList<Ticket>());
        }
        if (movie.getMovieratingsList() == null) {
            movie.setMovieratingsList(new ArrayList<Movieratings>());
        }
        if (movie.getMoviereviewList() == null) {
            movie.setMoviereviewList(new ArrayList<Moviereview>());
        }
        if (movie.getShowtimeList() == null) {
            movie.setShowtimeList(new ArrayList<Showtime>());
        }
        if (movie.getCommentList() == null) {
            movie.setCommentList(new ArrayList<Comment>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Actor> attachedActorList = new ArrayList<Actor>();
            for (Actor actorListActorToAttach : movie.getActorList()) {
                actorListActorToAttach = em.getReference(actorListActorToAttach.getClass(), actorListActorToAttach.getActorid());
                attachedActorList.add(actorListActorToAttach);
            }
            movie.setActorList(attachedActorList);
            List<User> attachedUserList = new ArrayList<User>();
            for (User userListUserToAttach : movie.getUserList()) {
                userListUserToAttach = em.getReference(userListUserToAttach.getClass(), userListUserToAttach.getUserID());
                attachedUserList.add(userListUserToAttach);
            }
            movie.setUserList(attachedUserList);
            List<Ticket> attachedTicketList = new ArrayList<Ticket>();
            for (Ticket ticketListTicketToAttach : movie.getTicketList()) {
                ticketListTicketToAttach = em.getReference(ticketListTicketToAttach.getClass(), ticketListTicketToAttach.getId());
                attachedTicketList.add(ticketListTicketToAttach);
            }
            movie.setTicketList(attachedTicketList);
            List<Movieratings> attachedMovieratingsList = new ArrayList<Movieratings>();
            for (Movieratings movieratingsListMovieratingsToAttach : movie.getMovieratingsList()) {
                movieratingsListMovieratingsToAttach = em.getReference(movieratingsListMovieratingsToAttach.getClass(), movieratingsListMovieratingsToAttach.getId());
                attachedMovieratingsList.add(movieratingsListMovieratingsToAttach);
            }
            movie.setMovieratingsList(attachedMovieratingsList);
            List<Moviereview> attachedMoviereviewList = new ArrayList<Moviereview>();
            for (Moviereview moviereviewListMoviereviewToAttach : movie.getMoviereviewList()) {
                moviereviewListMoviereviewToAttach = em.getReference(moviereviewListMoviereviewToAttach.getClass(), moviereviewListMoviereviewToAttach.getReviewID());
                attachedMoviereviewList.add(moviereviewListMoviereviewToAttach);
            }
            movie.setMoviereviewList(attachedMoviereviewList);
            List<Showtime> attachedShowtimeList = new ArrayList<Showtime>();
            for (Showtime showtimeListShowtimeToAttach : movie.getShowtimeList()) {
                showtimeListShowtimeToAttach = em.getReference(showtimeListShowtimeToAttach.getClass(), showtimeListShowtimeToAttach.getId());
                attachedShowtimeList.add(showtimeListShowtimeToAttach);
            }
            movie.setShowtimeList(attachedShowtimeList);
            List<Comment> attachedCommentList = new ArrayList<Comment>();
            for (Comment commentListCommentToAttach : movie.getCommentList()) {
                commentListCommentToAttach = em.getReference(commentListCommentToAttach.getClass(), commentListCommentToAttach.getId());
                attachedCommentList.add(commentListCommentToAttach);
            }
            movie.setCommentList(attachedCommentList);
            em.persist(movie);
            for (Actor actorListActor : movie.getActorList()) {
                actorListActor.getMovieList().add(movie);
                actorListActor = em.merge(actorListActor);
            }
            for (User userListUser : movie.getUserList()) {
                userListUser.getMovieList().add(movie);
                userListUser = em.merge(userListUser);
            }
            for (Ticket ticketListTicket : movie.getTicketList()) {
                Movie oldMovieIDOfTicketListTicket = ticketListTicket.getMovieID();
                ticketListTicket.setMovieID(movie);
                ticketListTicket = em.merge(ticketListTicket);
                if (oldMovieIDOfTicketListTicket != null) {
                    oldMovieIDOfTicketListTicket.getTicketList().remove(ticketListTicket);
                    oldMovieIDOfTicketListTicket = em.merge(oldMovieIDOfTicketListTicket);
                }
            }
            for (Movieratings movieratingsListMovieratings : movie.getMovieratingsList()) {
                Movie oldMovieidOfMovieratingsListMovieratings = movieratingsListMovieratings.getMovieid();
                movieratingsListMovieratings.setMovieid(movie);
                movieratingsListMovieratings = em.merge(movieratingsListMovieratings);
                if (oldMovieidOfMovieratingsListMovieratings != null) {
                    oldMovieidOfMovieratingsListMovieratings.getMovieratingsList().remove(movieratingsListMovieratings);
                    oldMovieidOfMovieratingsListMovieratings = em.merge(oldMovieidOfMovieratingsListMovieratings);
                }
            }
            for (Moviereview moviereviewListMoviereview : movie.getMoviereviewList()) {
                Movie oldMovieidOfMoviereviewListMoviereview = moviereviewListMoviereview.getMovieid();
                moviereviewListMoviereview.setMovieid(movie);
                moviereviewListMoviereview = em.merge(moviereviewListMoviereview);
                if (oldMovieidOfMoviereviewListMoviereview != null) {
                    oldMovieidOfMoviereviewListMoviereview.getMoviereviewList().remove(moviereviewListMoviereview);
                    oldMovieidOfMoviereviewListMoviereview = em.merge(oldMovieidOfMoviereviewListMoviereview);
                }
            }
            for (Showtime showtimeListShowtime : movie.getShowtimeList()) {
                Movie oldMovieIDOfShowtimeListShowtime = showtimeListShowtime.getMovieID();
                showtimeListShowtime.setMovieID(movie);
                showtimeListShowtime = em.merge(showtimeListShowtime);
                if (oldMovieIDOfShowtimeListShowtime != null) {
                    oldMovieIDOfShowtimeListShowtime.getShowtimeList().remove(showtimeListShowtime);
                    oldMovieIDOfShowtimeListShowtime = em.merge(oldMovieIDOfShowtimeListShowtime);
                }
            }
            for (Comment commentListComment : movie.getCommentList()) {
                Movie oldMovieidOfCommentListComment = commentListComment.getMovieid();
                commentListComment.setMovieid(movie);
                commentListComment = em.merge(commentListComment);
                if (oldMovieidOfCommentListComment != null) {
                    oldMovieidOfCommentListComment.getCommentList().remove(commentListComment);
                    oldMovieidOfCommentListComment = em.merge(oldMovieidOfCommentListComment);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Movie movie) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Movie persistentMovie = em.find(Movie.class, movie.getMovieID());
            List<Actor> actorListOld = persistentMovie.getActorList();
            List<Actor> actorListNew = movie.getActorList();
            List<User> userListOld = persistentMovie.getUserList();
            List<User> userListNew = movie.getUserList();
            List<Ticket> ticketListOld = persistentMovie.getTicketList();
            List<Ticket> ticketListNew = movie.getTicketList();
            List<Movieratings> movieratingsListOld = persistentMovie.getMovieratingsList();
            List<Movieratings> movieratingsListNew = movie.getMovieratingsList();
            List<Moviereview> moviereviewListOld = persistentMovie.getMoviereviewList();
            List<Moviereview> moviereviewListNew = movie.getMoviereviewList();
            List<Showtime> showtimeListOld = persistentMovie.getShowtimeList();
            List<Showtime> showtimeListNew = movie.getShowtimeList();
            List<Comment> commentListOld = persistentMovie.getCommentList();
            List<Comment> commentListNew = movie.getCommentList();
            List<String> illegalOrphanMessages = null;
            for (Ticket ticketListOldTicket : ticketListOld) {
                if (!ticketListNew.contains(ticketListOldTicket)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Ticket " + ticketListOldTicket + " since its movieID field is not nullable.");
                }
            }
            for (Moviereview moviereviewListOldMoviereview : moviereviewListOld) {
                if (!moviereviewListNew.contains(moviereviewListOldMoviereview)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Moviereview " + moviereviewListOldMoviereview + " since its movieid field is not nullable.");
                }
            }
            for (Showtime showtimeListOldShowtime : showtimeListOld) {
                if (!showtimeListNew.contains(showtimeListOldShowtime)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Showtime " + showtimeListOldShowtime + " since its movieID field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Actor> attachedActorListNew = new ArrayList<Actor>();
            for (Actor actorListNewActorToAttach : actorListNew) {
                actorListNewActorToAttach = em.getReference(actorListNewActorToAttach.getClass(), actorListNewActorToAttach.getActorid());
                attachedActorListNew.add(actorListNewActorToAttach);
            }
            actorListNew = attachedActorListNew;
            movie.setActorList(actorListNew);
            List<User> attachedUserListNew = new ArrayList<User>();
            for (User userListNewUserToAttach : userListNew) {
                userListNewUserToAttach = em.getReference(userListNewUserToAttach.getClass(), userListNewUserToAttach.getUserID());
                attachedUserListNew.add(userListNewUserToAttach);
            }
            userListNew = attachedUserListNew;
            movie.setUserList(userListNew);
            List<Ticket> attachedTicketListNew = new ArrayList<Ticket>();
            for (Ticket ticketListNewTicketToAttach : ticketListNew) {
                ticketListNewTicketToAttach = em.getReference(ticketListNewTicketToAttach.getClass(), ticketListNewTicketToAttach.getId());
                attachedTicketListNew.add(ticketListNewTicketToAttach);
            }
            ticketListNew = attachedTicketListNew;
            movie.setTicketList(ticketListNew);
            List<Movieratings> attachedMovieratingsListNew = new ArrayList<Movieratings>();
            for (Movieratings movieratingsListNewMovieratingsToAttach : movieratingsListNew) {
                movieratingsListNewMovieratingsToAttach = em.getReference(movieratingsListNewMovieratingsToAttach.getClass(), movieratingsListNewMovieratingsToAttach.getId());
                attachedMovieratingsListNew.add(movieratingsListNewMovieratingsToAttach);
            }
            movieratingsListNew = attachedMovieratingsListNew;
            movie.setMovieratingsList(movieratingsListNew);
            List<Moviereview> attachedMoviereviewListNew = new ArrayList<Moviereview>();
            for (Moviereview moviereviewListNewMoviereviewToAttach : moviereviewListNew) {
                moviereviewListNewMoviereviewToAttach = em.getReference(moviereviewListNewMoviereviewToAttach.getClass(), moviereviewListNewMoviereviewToAttach.getReviewID());
                attachedMoviereviewListNew.add(moviereviewListNewMoviereviewToAttach);
            }
            moviereviewListNew = attachedMoviereviewListNew;
            movie.setMoviereviewList(moviereviewListNew);
            List<Showtime> attachedShowtimeListNew = new ArrayList<Showtime>();
            for (Showtime showtimeListNewShowtimeToAttach : showtimeListNew) {
                showtimeListNewShowtimeToAttach = em.getReference(showtimeListNewShowtimeToAttach.getClass(), showtimeListNewShowtimeToAttach.getId());
                attachedShowtimeListNew.add(showtimeListNewShowtimeToAttach);
            }
            showtimeListNew = attachedShowtimeListNew;
            movie.setShowtimeList(showtimeListNew);
            List<Comment> attachedCommentListNew = new ArrayList<Comment>();
            for (Comment commentListNewCommentToAttach : commentListNew) {
                commentListNewCommentToAttach = em.getReference(commentListNewCommentToAttach.getClass(), commentListNewCommentToAttach.getId());
                attachedCommentListNew.add(commentListNewCommentToAttach);
            }
            commentListNew = attachedCommentListNew;
            movie.setCommentList(commentListNew);
            movie = em.merge(movie);
            for (Actor actorListOldActor : actorListOld) {
                if (!actorListNew.contains(actorListOldActor)) {
                    actorListOldActor.getMovieList().remove(movie);
                    actorListOldActor = em.merge(actorListOldActor);
                }
            }
            for (Actor actorListNewActor : actorListNew) {
                if (!actorListOld.contains(actorListNewActor)) {
                    actorListNewActor.getMovieList().add(movie);
                    actorListNewActor = em.merge(actorListNewActor);
                }
            }
            for (User userListOldUser : userListOld) {
                if (!userListNew.contains(userListOldUser)) {
                    userListOldUser.getMovieList().remove(movie);
                    userListOldUser = em.merge(userListOldUser);
                }
            }
            for (User userListNewUser : userListNew) {
                if (!userListOld.contains(userListNewUser)) {
                    userListNewUser.getMovieList().add(movie);
                    userListNewUser = em.merge(userListNewUser);
                }
            }
            for (Ticket ticketListNewTicket : ticketListNew) {
                if (!ticketListOld.contains(ticketListNewTicket)) {
                    Movie oldMovieIDOfTicketListNewTicket = ticketListNewTicket.getMovieID();
                    ticketListNewTicket.setMovieID(movie);
                    ticketListNewTicket = em.merge(ticketListNewTicket);
                    if (oldMovieIDOfTicketListNewTicket != null && !oldMovieIDOfTicketListNewTicket.equals(movie)) {
                        oldMovieIDOfTicketListNewTicket.getTicketList().remove(ticketListNewTicket);
                        oldMovieIDOfTicketListNewTicket = em.merge(oldMovieIDOfTicketListNewTicket);
                    }
                }
            }
            for (Movieratings movieratingsListOldMovieratings : movieratingsListOld) {
                if (!movieratingsListNew.contains(movieratingsListOldMovieratings)) {
                    movieratingsListOldMovieratings.setMovieid(null);
                    movieratingsListOldMovieratings = em.merge(movieratingsListOldMovieratings);
                }
            }
            for (Movieratings movieratingsListNewMovieratings : movieratingsListNew) {
                if (!movieratingsListOld.contains(movieratingsListNewMovieratings)) {
                    Movie oldMovieidOfMovieratingsListNewMovieratings = movieratingsListNewMovieratings.getMovieid();
                    movieratingsListNewMovieratings.setMovieid(movie);
                    movieratingsListNewMovieratings = em.merge(movieratingsListNewMovieratings);
                    if (oldMovieidOfMovieratingsListNewMovieratings != null && !oldMovieidOfMovieratingsListNewMovieratings.equals(movie)) {
                        oldMovieidOfMovieratingsListNewMovieratings.getMovieratingsList().remove(movieratingsListNewMovieratings);
                        oldMovieidOfMovieratingsListNewMovieratings = em.merge(oldMovieidOfMovieratingsListNewMovieratings);
                    }
                }
            }
            for (Moviereview moviereviewListNewMoviereview : moviereviewListNew) {
                if (!moviereviewListOld.contains(moviereviewListNewMoviereview)) {
                    Movie oldMovieidOfMoviereviewListNewMoviereview = moviereviewListNewMoviereview.getMovieid();
                    moviereviewListNewMoviereview.setMovieid(movie);
                    moviereviewListNewMoviereview = em.merge(moviereviewListNewMoviereview);
                    if (oldMovieidOfMoviereviewListNewMoviereview != null && !oldMovieidOfMoviereviewListNewMoviereview.equals(movie)) {
                        oldMovieidOfMoviereviewListNewMoviereview.getMoviereviewList().remove(moviereviewListNewMoviereview);
                        oldMovieidOfMoviereviewListNewMoviereview = em.merge(oldMovieidOfMoviereviewListNewMoviereview);
                    }
                }
            }
            for (Showtime showtimeListNewShowtime : showtimeListNew) {
                if (!showtimeListOld.contains(showtimeListNewShowtime)) {
                    Movie oldMovieIDOfShowtimeListNewShowtime = showtimeListNewShowtime.getMovieID();
                    showtimeListNewShowtime.setMovieID(movie);
                    showtimeListNewShowtime = em.merge(showtimeListNewShowtime);
                    if (oldMovieIDOfShowtimeListNewShowtime != null && !oldMovieIDOfShowtimeListNewShowtime.equals(movie)) {
                        oldMovieIDOfShowtimeListNewShowtime.getShowtimeList().remove(showtimeListNewShowtime);
                        oldMovieIDOfShowtimeListNewShowtime = em.merge(oldMovieIDOfShowtimeListNewShowtime);
                    }
                }
            }
            for (Comment commentListOldComment : commentListOld) {
                if (!commentListNew.contains(commentListOldComment)) {
                    commentListOldComment.setMovieid(null);
                    commentListOldComment = em.merge(commentListOldComment);
                }
            }
            for (Comment commentListNewComment : commentListNew) {
                if (!commentListOld.contains(commentListNewComment)) {
                    Movie oldMovieidOfCommentListNewComment = commentListNewComment.getMovieid();
                    commentListNewComment.setMovieid(movie);
                    commentListNewComment = em.merge(commentListNewComment);
                    if (oldMovieidOfCommentListNewComment != null && !oldMovieidOfCommentListNewComment.equals(movie)) {
                        oldMovieidOfCommentListNewComment.getCommentList().remove(commentListNewComment);
                        oldMovieidOfCommentListNewComment = em.merge(oldMovieidOfCommentListNewComment);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = movie.getMovieID();
                if (findMovie(id) == null) {
                    throw new NonexistentEntityException("The movie with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Movie movie;
            try {
                movie = em.getReference(Movie.class, id);
                movie.getMovieID();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The movie with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Ticket> ticketListOrphanCheck = movie.getTicketList();
            for (Ticket ticketListOrphanCheckTicket : ticketListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Movie (" + movie + ") cannot be destroyed since the Ticket " + ticketListOrphanCheckTicket + " in its ticketList field has a non-nullable movieID field.");
            }
            List<Moviereview> moviereviewListOrphanCheck = movie.getMoviereviewList();
            for (Moviereview moviereviewListOrphanCheckMoviereview : moviereviewListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Movie (" + movie + ") cannot be destroyed since the Moviereview " + moviereviewListOrphanCheckMoviereview + " in its moviereviewList field has a non-nullable movieid field.");
            }
            List<Showtime> showtimeListOrphanCheck = movie.getShowtimeList();
            for (Showtime showtimeListOrphanCheckShowtime : showtimeListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Movie (" + movie + ") cannot be destroyed since the Showtime " + showtimeListOrphanCheckShowtime + " in its showtimeList field has a non-nullable movieID field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Actor> actorList = movie.getActorList();
            for (Actor actorListActor : actorList) {
                actorListActor.getMovieList().remove(movie);
                actorListActor = em.merge(actorListActor);
            }
            List<User> userList = movie.getUserList();
            for (User userListUser : userList) {
                userListUser.getMovieList().remove(movie);
                userListUser = em.merge(userListUser);
            }
            List<Movieratings> movieratingsList = movie.getMovieratingsList();
            for (Movieratings movieratingsListMovieratings : movieratingsList) {
                movieratingsListMovieratings.setMovieid(null);
                movieratingsListMovieratings = em.merge(movieratingsListMovieratings);
            }
            List<Comment> commentList = movie.getCommentList();
            for (Comment commentListComment : commentList) {
                commentListComment.setMovieid(null);
                commentListComment = em.merge(commentListComment);
            }
            em.remove(movie);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Movie> findMovieEntities() {
        return findMovieEntities(true, -1, -1);
    }

    public List<Movie> findMovieEntities(int maxResults, int firstResult) {
        return findMovieEntities(false, maxResults, firstResult);
    }

    private List<Movie> findMovieEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Movie.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Movie findMovie(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Movie.class, id);
        } finally {
            em.close();
        }
    }

    public int getMovieCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Movie> rt = cq.from(Movie.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
