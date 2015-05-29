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
import JPA.Theatre;
import java.util.ArrayList;
import java.util.List;
import JPA.Movie;
import JPA.Theatrerewards;
import JPA.Movieratings;
import JPA.Moviereview;
import JPA.Creditcard;
import JPA.Comment;
import JPA.Payment;
import JPA.User;
import JPAController.exceptions.IllegalOrphanException;
import JPAController.exceptions.NonexistentEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author zeb
 */
public class UserJpaController implements Serializable {

    public UserJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(User user) {
        if (user.getTheatreList() == null) {
            user.setTheatreList(new ArrayList<Theatre>());
        }
        if (user.getMovieList() == null) {
            user.setMovieList(new ArrayList<Movie>());
        }
        if (user.getTheatrerewardsList() == null) {
            user.setTheatrerewardsList(new ArrayList<Theatrerewards>());
        }
        if (user.getMovieratingsList() == null) {
            user.setMovieratingsList(new ArrayList<Movieratings>());
        }
        if (user.getMoviereviewList() == null) {
            user.setMoviereviewList(new ArrayList<Moviereview>());
        }
        if (user.getCreditcardList() == null) {
            user.setCreditcardList(new ArrayList<Creditcard>());
        }
        if (user.getCommentList() == null) {
            user.setCommentList(new ArrayList<Comment>());
        }
        if (user.getPaymentList() == null) {
            user.setPaymentList(new ArrayList<Payment>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Theatre> attachedTheatreList = new ArrayList<Theatre>();
            for (Theatre theatreListTheatreToAttach : user.getTheatreList()) {
                theatreListTheatreToAttach = em.getReference(theatreListTheatreToAttach.getClass(), theatreListTheatreToAttach.getId());
                attachedTheatreList.add(theatreListTheatreToAttach);
            }
            user.setTheatreList(attachedTheatreList);
            List<Movie> attachedMovieList = new ArrayList<Movie>();
            for (Movie movieListMovieToAttach : user.getMovieList()) {
                movieListMovieToAttach = em.getReference(movieListMovieToAttach.getClass(), movieListMovieToAttach.getMovieID());
                attachedMovieList.add(movieListMovieToAttach);
            }
            user.setMovieList(attachedMovieList);
            List<Theatrerewards> attachedTheatrerewardsList = new ArrayList<Theatrerewards>();
            for (Theatrerewards theatrerewardsListTheatrerewardsToAttach : user.getTheatrerewardsList()) {
                theatrerewardsListTheatrerewardsToAttach = em.getReference(theatrerewardsListTheatrerewardsToAttach.getClass(), theatrerewardsListTheatrerewardsToAttach.getId());
                attachedTheatrerewardsList.add(theatrerewardsListTheatrerewardsToAttach);
            }
            user.setTheatrerewardsList(attachedTheatrerewardsList);
            List<Movieratings> attachedMovieratingsList = new ArrayList<Movieratings>();
            for (Movieratings movieratingsListMovieratingsToAttach : user.getMovieratingsList()) {
                movieratingsListMovieratingsToAttach = em.getReference(movieratingsListMovieratingsToAttach.getClass(), movieratingsListMovieratingsToAttach.getId());
                attachedMovieratingsList.add(movieratingsListMovieratingsToAttach);
            }
            user.setMovieratingsList(attachedMovieratingsList);
            List<Moviereview> attachedMoviereviewList = new ArrayList<Moviereview>();
            for (Moviereview moviereviewListMoviereviewToAttach : user.getMoviereviewList()) {
                moviereviewListMoviereviewToAttach = em.getReference(moviereviewListMoviereviewToAttach.getClass(), moviereviewListMoviereviewToAttach.getReviewID());
                attachedMoviereviewList.add(moviereviewListMoviereviewToAttach);
            }
            user.setMoviereviewList(attachedMoviereviewList);
            List<Creditcard> attachedCreditcardList = new ArrayList<Creditcard>();
            for (Creditcard creditcardListCreditcardToAttach : user.getCreditcardList()) {
                creditcardListCreditcardToAttach = em.getReference(creditcardListCreditcardToAttach.getClass(), creditcardListCreditcardToAttach.getCreditcardID());
                attachedCreditcardList.add(creditcardListCreditcardToAttach);
            }
            user.setCreditcardList(attachedCreditcardList);
            List<Comment> attachedCommentList = new ArrayList<Comment>();
            for (Comment commentListCommentToAttach : user.getCommentList()) {
                commentListCommentToAttach = em.getReference(commentListCommentToAttach.getClass(), commentListCommentToAttach.getId());
                attachedCommentList.add(commentListCommentToAttach);
            }
            user.setCommentList(attachedCommentList);
            List<Payment> attachedPaymentList = new ArrayList<Payment>();
            for (Payment paymentListPaymentToAttach : user.getPaymentList()) {
                paymentListPaymentToAttach = em.getReference(paymentListPaymentToAttach.getClass(), paymentListPaymentToAttach.getId());
                attachedPaymentList.add(paymentListPaymentToAttach);
            }
            user.setPaymentList(attachedPaymentList);
            em.persist(user);
            for (Theatre theatreListTheatre : user.getTheatreList()) {
                theatreListTheatre.getUserList().add(user);
                theatreListTheatre = em.merge(theatreListTheatre);
            }
            for (Movie movieListMovie : user.getMovieList()) {
                movieListMovie.getUserList().add(user);
                movieListMovie = em.merge(movieListMovie);
            }
            for (Theatrerewards theatrerewardsListTheatrerewards : user.getTheatrerewardsList()) {
                User oldUserIDOfTheatrerewardsListTheatrerewards = theatrerewardsListTheatrerewards.getUserID();
                theatrerewardsListTheatrerewards.setUserID(user);
                theatrerewardsListTheatrerewards = em.merge(theatrerewardsListTheatrerewards);
                if (oldUserIDOfTheatrerewardsListTheatrerewards != null) {
                    oldUserIDOfTheatrerewardsListTheatrerewards.getTheatrerewardsList().remove(theatrerewardsListTheatrerewards);
                    oldUserIDOfTheatrerewardsListTheatrerewards = em.merge(oldUserIDOfTheatrerewardsListTheatrerewards);
                }
            }
            for (Movieratings movieratingsListMovieratings : user.getMovieratingsList()) {
                User oldUseridOfMovieratingsListMovieratings = movieratingsListMovieratings.getUserid();
                movieratingsListMovieratings.setUserid(user);
                movieratingsListMovieratings = em.merge(movieratingsListMovieratings);
                if (oldUseridOfMovieratingsListMovieratings != null) {
                    oldUseridOfMovieratingsListMovieratings.getMovieratingsList().remove(movieratingsListMovieratings);
                    oldUseridOfMovieratingsListMovieratings = em.merge(oldUseridOfMovieratingsListMovieratings);
                }
            }
            for (Moviereview moviereviewListMoviereview : user.getMoviereviewList()) {
                User oldUseridOfMoviereviewListMoviereview = moviereviewListMoviereview.getUserid();
                moviereviewListMoviereview.setUserid(user);
                moviereviewListMoviereview = em.merge(moviereviewListMoviereview);
                if (oldUseridOfMoviereviewListMoviereview != null) {
                    oldUseridOfMoviereviewListMoviereview.getMoviereviewList().remove(moviereviewListMoviereview);
                    oldUseridOfMoviereviewListMoviereview = em.merge(oldUseridOfMoviereviewListMoviereview);
                }
            }
            for (Creditcard creditcardListCreditcard : user.getCreditcardList()) {
                User oldUserIDOfCreditcardListCreditcard = creditcardListCreditcard.getUserID();
                creditcardListCreditcard.setUserID(user);
                creditcardListCreditcard = em.merge(creditcardListCreditcard);
                if (oldUserIDOfCreditcardListCreditcard != null) {
                    oldUserIDOfCreditcardListCreditcard.getCreditcardList().remove(creditcardListCreditcard);
                    oldUserIDOfCreditcardListCreditcard = em.merge(oldUserIDOfCreditcardListCreditcard);
                }
            }
            for (Comment commentListComment : user.getCommentList()) {
                User oldUseridOfCommentListComment = commentListComment.getUserid();
                commentListComment.setUserid(user);
                commentListComment = em.merge(commentListComment);
                if (oldUseridOfCommentListComment != null) {
                    oldUseridOfCommentListComment.getCommentList().remove(commentListComment);
                    oldUseridOfCommentListComment = em.merge(oldUseridOfCommentListComment);
                }
            }
            for (Payment paymentListPayment : user.getPaymentList()) {
                User oldUserIDOfPaymentListPayment = paymentListPayment.getUserID();
                paymentListPayment.setUserID(user);
                paymentListPayment = em.merge(paymentListPayment);
                if (oldUserIDOfPaymentListPayment != null) {
                    oldUserIDOfPaymentListPayment.getPaymentList().remove(paymentListPayment);
                    oldUserIDOfPaymentListPayment = em.merge(oldUserIDOfPaymentListPayment);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(User user) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            User persistentUser = em.find(User.class, user.getUserID());
            List<Theatre> theatreListOld = persistentUser.getTheatreList();
            List<Theatre> theatreListNew = user.getTheatreList();
            List<Movie> movieListOld = persistentUser.getMovieList();
            List<Movie> movieListNew = user.getMovieList();
            List<Theatrerewards> theatrerewardsListOld = persistentUser.getTheatrerewardsList();
            List<Theatrerewards> theatrerewardsListNew = user.getTheatrerewardsList();
            List<Movieratings> movieratingsListOld = persistentUser.getMovieratingsList();
            List<Movieratings> movieratingsListNew = user.getMovieratingsList();
            List<Moviereview> moviereviewListOld = persistentUser.getMoviereviewList();
            List<Moviereview> moviereviewListNew = user.getMoviereviewList();
            List<Creditcard> creditcardListOld = persistentUser.getCreditcardList();
            List<Creditcard> creditcardListNew = user.getCreditcardList();
            List<Comment> commentListOld = persistentUser.getCommentList();
            List<Comment> commentListNew = user.getCommentList();
            List<Payment> paymentListOld = persistentUser.getPaymentList();
            List<Payment> paymentListNew = user.getPaymentList();
            List<String> illegalOrphanMessages = null;
            for (Theatrerewards theatrerewardsListOldTheatrerewards : theatrerewardsListOld) {
                if (!theatrerewardsListNew.contains(theatrerewardsListOldTheatrerewards)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Theatrerewards " + theatrerewardsListOldTheatrerewards + " since its userID field is not nullable.");
                }
            }
            for (Moviereview moviereviewListOldMoviereview : moviereviewListOld) {
                if (!moviereviewListNew.contains(moviereviewListOldMoviereview)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Moviereview " + moviereviewListOldMoviereview + " since its userid field is not nullable.");
                }
            }
            for (Creditcard creditcardListOldCreditcard : creditcardListOld) {
                if (!creditcardListNew.contains(creditcardListOldCreditcard)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Creditcard " + creditcardListOldCreditcard + " since its userID field is not nullable.");
                }
            }
            for (Payment paymentListOldPayment : paymentListOld) {
                if (!paymentListNew.contains(paymentListOldPayment)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Payment " + paymentListOldPayment + " since its userID field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Theatre> attachedTheatreListNew = new ArrayList<Theatre>();
            for (Theatre theatreListNewTheatreToAttach : theatreListNew) {
                theatreListNewTheatreToAttach = em.getReference(theatreListNewTheatreToAttach.getClass(), theatreListNewTheatreToAttach.getId());
                attachedTheatreListNew.add(theatreListNewTheatreToAttach);
            }
            theatreListNew = attachedTheatreListNew;
            user.setTheatreList(theatreListNew);
            List<Movie> attachedMovieListNew = new ArrayList<Movie>();
            for (Movie movieListNewMovieToAttach : movieListNew) {
                movieListNewMovieToAttach = em.getReference(movieListNewMovieToAttach.getClass(), movieListNewMovieToAttach.getMovieID());
                attachedMovieListNew.add(movieListNewMovieToAttach);
            }
            movieListNew = attachedMovieListNew;
            user.setMovieList(movieListNew);
            List<Theatrerewards> attachedTheatrerewardsListNew = new ArrayList<Theatrerewards>();
            for (Theatrerewards theatrerewardsListNewTheatrerewardsToAttach : theatrerewardsListNew) {
                theatrerewardsListNewTheatrerewardsToAttach = em.getReference(theatrerewardsListNewTheatrerewardsToAttach.getClass(), theatrerewardsListNewTheatrerewardsToAttach.getId());
                attachedTheatrerewardsListNew.add(theatrerewardsListNewTheatrerewardsToAttach);
            }
            theatrerewardsListNew = attachedTheatrerewardsListNew;
            user.setTheatrerewardsList(theatrerewardsListNew);
            List<Movieratings> attachedMovieratingsListNew = new ArrayList<Movieratings>();
            for (Movieratings movieratingsListNewMovieratingsToAttach : movieratingsListNew) {
                movieratingsListNewMovieratingsToAttach = em.getReference(movieratingsListNewMovieratingsToAttach.getClass(), movieratingsListNewMovieratingsToAttach.getId());
                attachedMovieratingsListNew.add(movieratingsListNewMovieratingsToAttach);
            }
            movieratingsListNew = attachedMovieratingsListNew;
            user.setMovieratingsList(movieratingsListNew);
            List<Moviereview> attachedMoviereviewListNew = new ArrayList<Moviereview>();
            for (Moviereview moviereviewListNewMoviereviewToAttach : moviereviewListNew) {
                moviereviewListNewMoviereviewToAttach = em.getReference(moviereviewListNewMoviereviewToAttach.getClass(), moviereviewListNewMoviereviewToAttach.getReviewID());
                attachedMoviereviewListNew.add(moviereviewListNewMoviereviewToAttach);
            }
            moviereviewListNew = attachedMoviereviewListNew;
            user.setMoviereviewList(moviereviewListNew);
            List<Creditcard> attachedCreditcardListNew = new ArrayList<Creditcard>();
            for (Creditcard creditcardListNewCreditcardToAttach : creditcardListNew) {
                creditcardListNewCreditcardToAttach = em.getReference(creditcardListNewCreditcardToAttach.getClass(), creditcardListNewCreditcardToAttach.getCreditcardID());
                attachedCreditcardListNew.add(creditcardListNewCreditcardToAttach);
            }
            creditcardListNew = attachedCreditcardListNew;
            user.setCreditcardList(creditcardListNew);
            List<Comment> attachedCommentListNew = new ArrayList<Comment>();
            for (Comment commentListNewCommentToAttach : commentListNew) {
                commentListNewCommentToAttach = em.getReference(commentListNewCommentToAttach.getClass(), commentListNewCommentToAttach.getId());
                attachedCommentListNew.add(commentListNewCommentToAttach);
            }
            commentListNew = attachedCommentListNew;
            user.setCommentList(commentListNew);
            List<Payment> attachedPaymentListNew = new ArrayList<Payment>();
            for (Payment paymentListNewPaymentToAttach : paymentListNew) {
                paymentListNewPaymentToAttach = em.getReference(paymentListNewPaymentToAttach.getClass(), paymentListNewPaymentToAttach.getId());
                attachedPaymentListNew.add(paymentListNewPaymentToAttach);
            }
            paymentListNew = attachedPaymentListNew;
            user.setPaymentList(paymentListNew);
            user = em.merge(user);
            for (Theatre theatreListOldTheatre : theatreListOld) {
                if (!theatreListNew.contains(theatreListOldTheatre)) {
                    theatreListOldTheatre.getUserList().remove(user);
                    theatreListOldTheatre = em.merge(theatreListOldTheatre);
                }
            }
            for (Theatre theatreListNewTheatre : theatreListNew) {
                if (!theatreListOld.contains(theatreListNewTheatre)) {
                    theatreListNewTheatre.getUserList().add(user);
                    theatreListNewTheatre = em.merge(theatreListNewTheatre);
                }
            }
            for (Movie movieListOldMovie : movieListOld) {
                if (!movieListNew.contains(movieListOldMovie)) {
                    movieListOldMovie.getUserList().remove(user);
                    movieListOldMovie = em.merge(movieListOldMovie);
                }
            }
            for (Movie movieListNewMovie : movieListNew) {
                if (!movieListOld.contains(movieListNewMovie)) {
                    movieListNewMovie.getUserList().add(user);
                    movieListNewMovie = em.merge(movieListNewMovie);
                }
            }
            for (Theatrerewards theatrerewardsListNewTheatrerewards : theatrerewardsListNew) {
                if (!theatrerewardsListOld.contains(theatrerewardsListNewTheatrerewards)) {
                    User oldUserIDOfTheatrerewardsListNewTheatrerewards = theatrerewardsListNewTheatrerewards.getUserID();
                    theatrerewardsListNewTheatrerewards.setUserID(user);
                    theatrerewardsListNewTheatrerewards = em.merge(theatrerewardsListNewTheatrerewards);
                    if (oldUserIDOfTheatrerewardsListNewTheatrerewards != null && !oldUserIDOfTheatrerewardsListNewTheatrerewards.equals(user)) {
                        oldUserIDOfTheatrerewardsListNewTheatrerewards.getTheatrerewardsList().remove(theatrerewardsListNewTheatrerewards);
                        oldUserIDOfTheatrerewardsListNewTheatrerewards = em.merge(oldUserIDOfTheatrerewardsListNewTheatrerewards);
                    }
                }
            }
            for (Movieratings movieratingsListOldMovieratings : movieratingsListOld) {
                if (!movieratingsListNew.contains(movieratingsListOldMovieratings)) {
                    movieratingsListOldMovieratings.setUserid(null);
                    movieratingsListOldMovieratings = em.merge(movieratingsListOldMovieratings);
                }
            }
            for (Movieratings movieratingsListNewMovieratings : movieratingsListNew) {
                if (!movieratingsListOld.contains(movieratingsListNewMovieratings)) {
                    User oldUseridOfMovieratingsListNewMovieratings = movieratingsListNewMovieratings.getUserid();
                    movieratingsListNewMovieratings.setUserid(user);
                    movieratingsListNewMovieratings = em.merge(movieratingsListNewMovieratings);
                    if (oldUseridOfMovieratingsListNewMovieratings != null && !oldUseridOfMovieratingsListNewMovieratings.equals(user)) {
                        oldUseridOfMovieratingsListNewMovieratings.getMovieratingsList().remove(movieratingsListNewMovieratings);
                        oldUseridOfMovieratingsListNewMovieratings = em.merge(oldUseridOfMovieratingsListNewMovieratings);
                    }
                }
            }
            for (Moviereview moviereviewListNewMoviereview : moviereviewListNew) {
                if (!moviereviewListOld.contains(moviereviewListNewMoviereview)) {
                    User oldUseridOfMoviereviewListNewMoviereview = moviereviewListNewMoviereview.getUserid();
                    moviereviewListNewMoviereview.setUserid(user);
                    moviereviewListNewMoviereview = em.merge(moviereviewListNewMoviereview);
                    if (oldUseridOfMoviereviewListNewMoviereview != null && !oldUseridOfMoviereviewListNewMoviereview.equals(user)) {
                        oldUseridOfMoviereviewListNewMoviereview.getMoviereviewList().remove(moviereviewListNewMoviereview);
                        oldUseridOfMoviereviewListNewMoviereview = em.merge(oldUseridOfMoviereviewListNewMoviereview);
                    }
                }
            }
            for (Creditcard creditcardListNewCreditcard : creditcardListNew) {
                if (!creditcardListOld.contains(creditcardListNewCreditcard)) {
                    User oldUserIDOfCreditcardListNewCreditcard = creditcardListNewCreditcard.getUserID();
                    creditcardListNewCreditcard.setUserID(user);
                    creditcardListNewCreditcard = em.merge(creditcardListNewCreditcard);
                    if (oldUserIDOfCreditcardListNewCreditcard != null && !oldUserIDOfCreditcardListNewCreditcard.equals(user)) {
                        oldUserIDOfCreditcardListNewCreditcard.getCreditcardList().remove(creditcardListNewCreditcard);
                        oldUserIDOfCreditcardListNewCreditcard = em.merge(oldUserIDOfCreditcardListNewCreditcard);
                    }
                }
            }
            for (Comment commentListOldComment : commentListOld) {
                if (!commentListNew.contains(commentListOldComment)) {
                    commentListOldComment.setUserid(null);
                    commentListOldComment = em.merge(commentListOldComment);
                }
            }
            for (Comment commentListNewComment : commentListNew) {
                if (!commentListOld.contains(commentListNewComment)) {
                    User oldUseridOfCommentListNewComment = commentListNewComment.getUserid();
                    commentListNewComment.setUserid(user);
                    commentListNewComment = em.merge(commentListNewComment);
                    if (oldUseridOfCommentListNewComment != null && !oldUseridOfCommentListNewComment.equals(user)) {
                        oldUseridOfCommentListNewComment.getCommentList().remove(commentListNewComment);
                        oldUseridOfCommentListNewComment = em.merge(oldUseridOfCommentListNewComment);
                    }
                }
            }
            for (Payment paymentListNewPayment : paymentListNew) {
                if (!paymentListOld.contains(paymentListNewPayment)) {
                    User oldUserIDOfPaymentListNewPayment = paymentListNewPayment.getUserID();
                    paymentListNewPayment.setUserID(user);
                    paymentListNewPayment = em.merge(paymentListNewPayment);
                    if (oldUserIDOfPaymentListNewPayment != null && !oldUserIDOfPaymentListNewPayment.equals(user)) {
                        oldUserIDOfPaymentListNewPayment.getPaymentList().remove(paymentListNewPayment);
                        oldUserIDOfPaymentListNewPayment = em.merge(oldUserIDOfPaymentListNewPayment);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = user.getUserID();
                if (findUser(id) == null) {
                    throw new NonexistentEntityException("The user with id " + id + " no longer exists.");
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
            User user;
            try {
                user = em.getReference(User.class, id);
                user.getUserID();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The user with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Theatrerewards> theatrerewardsListOrphanCheck = user.getTheatrerewardsList();
            for (Theatrerewards theatrerewardsListOrphanCheckTheatrerewards : theatrerewardsListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This User (" + user + ") cannot be destroyed since the Theatrerewards " + theatrerewardsListOrphanCheckTheatrerewards + " in its theatrerewardsList field has a non-nullable userID field.");
            }
            List<Moviereview> moviereviewListOrphanCheck = user.getMoviereviewList();
            for (Moviereview moviereviewListOrphanCheckMoviereview : moviereviewListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This User (" + user + ") cannot be destroyed since the Moviereview " + moviereviewListOrphanCheckMoviereview + " in its moviereviewList field has a non-nullable userid field.");
            }
            List<Creditcard> creditcardListOrphanCheck = user.getCreditcardList();
            for (Creditcard creditcardListOrphanCheckCreditcard : creditcardListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This User (" + user + ") cannot be destroyed since the Creditcard " + creditcardListOrphanCheckCreditcard + " in its creditcardList field has a non-nullable userID field.");
            }
            List<Payment> paymentListOrphanCheck = user.getPaymentList();
            for (Payment paymentListOrphanCheckPayment : paymentListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This User (" + user + ") cannot be destroyed since the Payment " + paymentListOrphanCheckPayment + " in its paymentList field has a non-nullable userID field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Theatre> theatreList = user.getTheatreList();
            for (Theatre theatreListTheatre : theatreList) {
                theatreListTheatre.getUserList().remove(user);
                theatreListTheatre = em.merge(theatreListTheatre);
            }
            List<Movie> movieList = user.getMovieList();
            for (Movie movieListMovie : movieList) {
                movieListMovie.getUserList().remove(user);
                movieListMovie = em.merge(movieListMovie);
            }
            List<Movieratings> movieratingsList = user.getMovieratingsList();
            for (Movieratings movieratingsListMovieratings : movieratingsList) {
                movieratingsListMovieratings.setUserid(null);
                movieratingsListMovieratings = em.merge(movieratingsListMovieratings);
            }
            List<Comment> commentList = user.getCommentList();
            for (Comment commentListComment : commentList) {
                commentListComment.setUserid(null);
                commentListComment = em.merge(commentListComment);
            }
            em.remove(user);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<User> findUserEntities() {
        return findUserEntities(true, -1, -1);
    }

    public List<User> findUserEntities(int maxResults, int firstResult) {
        return findUserEntities(false, maxResults, firstResult);
    }

    private List<User> findUserEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(User.class));
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

    public User findUser(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(User.class, id);
        } finally {
            em.close();
        }
    }

    public int getUserCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<User> rt = cq.from(User.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
