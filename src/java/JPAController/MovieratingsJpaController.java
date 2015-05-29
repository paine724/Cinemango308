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
import JPA.Movieratings;
import JPA.User;
import JPAController.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author zeb
 */
public class MovieratingsJpaController implements Serializable {

    public MovieratingsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Movieratings movieratings) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Movie movieid = movieratings.getMovieid();
            if (movieid != null) {
                movieid = em.getReference(movieid.getClass(), movieid.getMovieID());
                movieratings.setMovieid(movieid);
            }
            User userid = movieratings.getUserid();
            if (userid != null) {
                userid = em.getReference(userid.getClass(), userid.getUserID());
                movieratings.setUserid(userid);
            }
            em.persist(movieratings);
            if (movieid != null) {
                movieid.getMovieratingsList().add(movieratings);
                movieid = em.merge(movieid);
            }
            if (userid != null) {
                userid.getMovieratingsList().add(movieratings);
                userid = em.merge(userid);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Movieratings movieratings) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Movieratings persistentMovieratings = em.find(Movieratings.class, movieratings.getId());
            Movie movieidOld = persistentMovieratings.getMovieid();
            Movie movieidNew = movieratings.getMovieid();
            User useridOld = persistentMovieratings.getUserid();
            User useridNew = movieratings.getUserid();
            if (movieidNew != null) {
                movieidNew = em.getReference(movieidNew.getClass(), movieidNew.getMovieID());
                movieratings.setMovieid(movieidNew);
            }
            if (useridNew != null) {
                useridNew = em.getReference(useridNew.getClass(), useridNew.getUserID());
                movieratings.setUserid(useridNew);
            }
            movieratings = em.merge(movieratings);
            if (movieidOld != null && !movieidOld.equals(movieidNew)) {
                movieidOld.getMovieratingsList().remove(movieratings);
                movieidOld = em.merge(movieidOld);
            }
            if (movieidNew != null && !movieidNew.equals(movieidOld)) {
                movieidNew.getMovieratingsList().add(movieratings);
                movieidNew = em.merge(movieidNew);
            }
            if (useridOld != null && !useridOld.equals(useridNew)) {
                useridOld.getMovieratingsList().remove(movieratings);
                useridOld = em.merge(useridOld);
            }
            if (useridNew != null && !useridNew.equals(useridOld)) {
                useridNew.getMovieratingsList().add(movieratings);
                useridNew = em.merge(useridNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = movieratings.getId();
                if (findMovieratings(id) == null) {
                    throw new NonexistentEntityException("The movieratings with id " + id + " no longer exists.");
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
            Movieratings movieratings;
            try {
                movieratings = em.getReference(Movieratings.class, id);
                movieratings.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The movieratings with id " + id + " no longer exists.", enfe);
            }
            Movie movieid = movieratings.getMovieid();
            if (movieid != null) {
                movieid.getMovieratingsList().remove(movieratings);
                movieid = em.merge(movieid);
            }
            User userid = movieratings.getUserid();
            if (userid != null) {
                userid.getMovieratingsList().remove(movieratings);
                userid = em.merge(userid);
            }
            em.remove(movieratings);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Movieratings> findMovieratingsEntities() {
        return findMovieratingsEntities(true, -1, -1);
    }

    public List<Movieratings> findMovieratingsEntities(int maxResults, int firstResult) {
        return findMovieratingsEntities(false, maxResults, firstResult);
    }

    private List<Movieratings> findMovieratingsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Movieratings.class));
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

    public Movieratings findMovieratings(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Movieratings.class, id);
        } finally {
            em.close();
        }
    }

    public int getMovieratingsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Movieratings> rt = cq.from(Movieratings.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
