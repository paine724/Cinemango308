/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPAController;

import JPA.Theatrerewards;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import JPA.User;
import JPAController.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author zeb
 */
public class TheatrerewardsJpaController implements Serializable {

    public TheatrerewardsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Theatrerewards theatrerewards) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            User userID = theatrerewards.getUserID();
            if (userID != null) {
                userID = em.getReference(userID.getClass(), userID.getUserID());
                theatrerewards.setUserID(userID);
            }
            em.persist(theatrerewards);
            if (userID != null) {
                userID.getTheatrerewardsList().add(theatrerewards);
                userID = em.merge(userID);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Theatrerewards theatrerewards) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Theatrerewards persistentTheatrerewards = em.find(Theatrerewards.class, theatrerewards.getId());
            User userIDOld = persistentTheatrerewards.getUserID();
            User userIDNew = theatrerewards.getUserID();
            if (userIDNew != null) {
                userIDNew = em.getReference(userIDNew.getClass(), userIDNew.getUserID());
                theatrerewards.setUserID(userIDNew);
            }
            theatrerewards = em.merge(theatrerewards);
            if (userIDOld != null && !userIDOld.equals(userIDNew)) {
                userIDOld.getTheatrerewardsList().remove(theatrerewards);
                userIDOld = em.merge(userIDOld);
            }
            if (userIDNew != null && !userIDNew.equals(userIDOld)) {
                userIDNew.getTheatrerewardsList().add(theatrerewards);
                userIDNew = em.merge(userIDNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = theatrerewards.getId();
                if (findTheatrerewards(id) == null) {
                    throw new NonexistentEntityException("The theatrerewards with id " + id + " no longer exists.");
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
            Theatrerewards theatrerewards;
            try {
                theatrerewards = em.getReference(Theatrerewards.class, id);
                theatrerewards.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The theatrerewards with id " + id + " no longer exists.", enfe);
            }
            User userID = theatrerewards.getUserID();
            if (userID != null) {
                userID.getTheatrerewardsList().remove(theatrerewards);
                userID = em.merge(userID);
            }
            em.remove(theatrerewards);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Theatrerewards> findTheatrerewardsEntities() {
        return findTheatrerewardsEntities(true, -1, -1);
    }

    public List<Theatrerewards> findTheatrerewardsEntities(int maxResults, int firstResult) {
        return findTheatrerewardsEntities(false, maxResults, firstResult);
    }

    private List<Theatrerewards> findTheatrerewardsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Theatrerewards.class));
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

    public Theatrerewards findTheatrerewards(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Theatrerewards.class, id);
        } finally {
            em.close();
        }
    }

    public int getTheatrerewardsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Theatrerewards> rt = cq.from(Theatrerewards.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
