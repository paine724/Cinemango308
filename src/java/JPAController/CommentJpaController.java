/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPAController;

import JPA.Comment;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import JPA.Movie;
import JPA.User;
import JPAController.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author zeb
 */
public class CommentJpaController implements Serializable {

    public CommentJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Comment comment) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Movie movieid = comment.getMovieid();
            if (movieid != null) {
                movieid = em.getReference(movieid.getClass(), movieid.getMovieID());
                comment.setMovieid(movieid);
            }
            User userid = comment.getUserid();
            if (userid != null) {
                userid = em.getReference(userid.getClass(), userid.getUserID());
                comment.setUserid(userid);
            }
            em.persist(comment);
            if (movieid != null) {
                movieid.getCommentList().add(comment);
                movieid = em.merge(movieid);
            }
            if (userid != null) {
                userid.getCommentList().add(comment);
                userid = em.merge(userid);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Comment comment) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Comment persistentComment = em.find(Comment.class, comment.getId());
            Movie movieidOld = persistentComment.getMovieid();
            Movie movieidNew = comment.getMovieid();
            User useridOld = persistentComment.getUserid();
            User useridNew = comment.getUserid();
            if (movieidNew != null) {
                movieidNew = em.getReference(movieidNew.getClass(), movieidNew.getMovieID());
                comment.setMovieid(movieidNew);
            }
            if (useridNew != null) {
                useridNew = em.getReference(useridNew.getClass(), useridNew.getUserID());
                comment.setUserid(useridNew);
            }
            comment = em.merge(comment);
            if (movieidOld != null && !movieidOld.equals(movieidNew)) {
                movieidOld.getCommentList().remove(comment);
                movieidOld = em.merge(movieidOld);
            }
            if (movieidNew != null && !movieidNew.equals(movieidOld)) {
                movieidNew.getCommentList().add(comment);
                movieidNew = em.merge(movieidNew);
            }
            if (useridOld != null && !useridOld.equals(useridNew)) {
                useridOld.getCommentList().remove(comment);
                useridOld = em.merge(useridOld);
            }
            if (useridNew != null && !useridNew.equals(useridOld)) {
                useridNew.getCommentList().add(comment);
                useridNew = em.merge(useridNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = comment.getId();
                if (findComment(id) == null) {
                    throw new NonexistentEntityException("The comment with id " + id + " no longer exists.");
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
            Comment comment;
            try {
                comment = em.getReference(Comment.class, id);
                comment.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The comment with id " + id + " no longer exists.", enfe);
            }
            Movie movieid = comment.getMovieid();
            if (movieid != null) {
                movieid.getCommentList().remove(comment);
                movieid = em.merge(movieid);
            }
            User userid = comment.getUserid();
            if (userid != null) {
                userid.getCommentList().remove(comment);
                userid = em.merge(userid);
            }
            em.remove(comment);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Comment> findCommentEntities() {
        return findCommentEntities(true, -1, -1);
    }

    public List<Comment> findCommentEntities(int maxResults, int firstResult) {
        return findCommentEntities(false, maxResults, firstResult);
    }

    private List<Comment> findCommentEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Comment.class));
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

    public Comment findComment(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Comment.class, id);
        } finally {
            em.close();
        }
    }

    public int getCommentCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Comment> rt = cq.from(Comment.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
