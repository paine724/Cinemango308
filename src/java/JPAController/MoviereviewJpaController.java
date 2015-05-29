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
import JPA.Movie;
import JPA.Moviereview;
import JPA.User;
import JPAController.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author zeb
 */
public class MoviereviewJpaController implements Serializable {

    public MoviereviewJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Moviereview moviereview) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Movie movieid = moviereview.getMovieid();
            if (movieid != null) {
                movieid = em.getReference(movieid.getClass(), movieid.getMovieID());
                moviereview.setMovieid(movieid);
            }
            User userid = moviereview.getUserid();
            if (userid != null) {
                userid = em.getReference(userid.getClass(), userid.getUserID());
                moviereview.setUserid(userid);
            }
            em.persist(moviereview);
            if (movieid != null) {
                movieid.getMoviereviewList().add(moviereview);
                movieid = em.merge(movieid);
            }
            if (userid != null) {
                userid.getMoviereviewList().add(moviereview);
                userid = em.merge(userid);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Moviereview moviereview) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Moviereview persistentMoviereview = em.find(Moviereview.class, moviereview.getReviewID());
            Movie movieidOld = persistentMoviereview.getMovieid();
            Movie movieidNew = moviereview.getMovieid();
            User useridOld = persistentMoviereview.getUserid();
            User useridNew = moviereview.getUserid();
            if (movieidNew != null) {
                movieidNew = em.getReference(movieidNew.getClass(), movieidNew.getMovieID());
                moviereview.setMovieid(movieidNew);
            }
            if (useridNew != null) {
                useridNew = em.getReference(useridNew.getClass(), useridNew.getUserID());
                moviereview.setUserid(useridNew);
            }
            moviereview = em.merge(moviereview);
            if (movieidOld != null && !movieidOld.equals(movieidNew)) {
                movieidOld.getMoviereviewList().remove(moviereview);
                movieidOld = em.merge(movieidOld);
            }
            if (movieidNew != null && !movieidNew.equals(movieidOld)) {
                movieidNew.getMoviereviewList().add(moviereview);
                movieidNew = em.merge(movieidNew);
            }
            if (useridOld != null && !useridOld.equals(useridNew)) {
                useridOld.getMoviereviewList().remove(moviereview);
                useridOld = em.merge(useridOld);
            }
            if (useridNew != null && !useridNew.equals(useridOld)) {
                useridNew.getMoviereviewList().add(moviereview);
                useridNew = em.merge(useridNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = moviereview.getReviewID();
                if (findMoviereview(id) == null) {
                    throw new NonexistentEntityException("The moviereview with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Moviereview moviereview;
            try {
                moviereview = em.getReference(Moviereview.class, id);
                moviereview.getReviewID();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The moviereview with id " + id + " no longer exists.", enfe);
            }
            Movie movieid = moviereview.getMovieid();
            if (movieid != null) {
                movieid.getMoviereviewList().remove(moviereview);
                movieid = em.merge(movieid);
            }
            User userid = moviereview.getUserid();
            if (userid != null) {
                userid.getMoviereviewList().remove(moviereview);
                userid = em.merge(userid);
            }
            em.remove(moviereview);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Moviereview> findMoviereviewEntities() {
        return findMoviereviewEntities(true, -1, -1);
    }

    public List<Moviereview> findMoviereviewEntities(int maxResults, int firstResult) {
        return findMoviereviewEntities(false, maxResults, firstResult);
    }

    private List<Moviereview> findMoviereviewEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Moviereview.class));
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

    public Moviereview findMoviereview(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Moviereview.class, id);
        } finally {
            em.close();
        }
    }

    public int getMoviereviewCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Moviereview> rt = cq.from(Moviereview.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
