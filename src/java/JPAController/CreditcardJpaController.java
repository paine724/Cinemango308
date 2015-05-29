/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPAController;

import JPA.Creditcard;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import JPA.User;
import JPA.Payment;
import JPAController.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author zeb
 */
public class CreditcardJpaController implements Serializable {

    public CreditcardJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Creditcard creditcard) {
        if (creditcard.getPaymentList() == null) {
            creditcard.setPaymentList(new ArrayList<Payment>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            User userID = creditcard.getUserID();
            if (userID != null) {
                userID = em.getReference(userID.getClass(), userID.getUserID());
                creditcard.setUserID(userID);
            }
            List<Payment> attachedPaymentList = new ArrayList<Payment>();
            for (Payment paymentListPaymentToAttach : creditcard.getPaymentList()) {
                paymentListPaymentToAttach = em.getReference(paymentListPaymentToAttach.getClass(), paymentListPaymentToAttach.getId());
                attachedPaymentList.add(paymentListPaymentToAttach);
            }
            creditcard.setPaymentList(attachedPaymentList);
            em.persist(creditcard);
            if (userID != null) {
                userID.getCreditcardList().add(creditcard);
                userID = em.merge(userID);
            }
            for (Payment paymentListPayment : creditcard.getPaymentList()) {
                Creditcard oldCreditcardIDOfPaymentListPayment = paymentListPayment.getCreditcardID();
                paymentListPayment.setCreditcardID(creditcard);
                paymentListPayment = em.merge(paymentListPayment);
                if (oldCreditcardIDOfPaymentListPayment != null) {
                    oldCreditcardIDOfPaymentListPayment.getPaymentList().remove(paymentListPayment);
                    oldCreditcardIDOfPaymentListPayment = em.merge(oldCreditcardIDOfPaymentListPayment);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Creditcard creditcard) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Creditcard persistentCreditcard = em.find(Creditcard.class, creditcard.getCreditcardID());
            User userIDOld = persistentCreditcard.getUserID();
            User userIDNew = creditcard.getUserID();
            List<Payment> paymentListOld = persistentCreditcard.getPaymentList();
            List<Payment> paymentListNew = creditcard.getPaymentList();
            if (userIDNew != null) {
                userIDNew = em.getReference(userIDNew.getClass(), userIDNew.getUserID());
                creditcard.setUserID(userIDNew);
            }
            List<Payment> attachedPaymentListNew = new ArrayList<Payment>();
            for (Payment paymentListNewPaymentToAttach : paymentListNew) {
                paymentListNewPaymentToAttach = em.getReference(paymentListNewPaymentToAttach.getClass(), paymentListNewPaymentToAttach.getId());
                attachedPaymentListNew.add(paymentListNewPaymentToAttach);
            }
            paymentListNew = attachedPaymentListNew;
            creditcard.setPaymentList(paymentListNew);
            creditcard = em.merge(creditcard);
            if (userIDOld != null && !userIDOld.equals(userIDNew)) {
                userIDOld.getCreditcardList().remove(creditcard);
                userIDOld = em.merge(userIDOld);
            }
            if (userIDNew != null && !userIDNew.equals(userIDOld)) {
                userIDNew.getCreditcardList().add(creditcard);
                userIDNew = em.merge(userIDNew);
            }
            for (Payment paymentListOldPayment : paymentListOld) {
                if (!paymentListNew.contains(paymentListOldPayment)) {
                    paymentListOldPayment.setCreditcardID(null);
                    paymentListOldPayment = em.merge(paymentListOldPayment);
                }
            }
            for (Payment paymentListNewPayment : paymentListNew) {
                if (!paymentListOld.contains(paymentListNewPayment)) {
                    Creditcard oldCreditcardIDOfPaymentListNewPayment = paymentListNewPayment.getCreditcardID();
                    paymentListNewPayment.setCreditcardID(creditcard);
                    paymentListNewPayment = em.merge(paymentListNewPayment);
                    if (oldCreditcardIDOfPaymentListNewPayment != null && !oldCreditcardIDOfPaymentListNewPayment.equals(creditcard)) {
                        oldCreditcardIDOfPaymentListNewPayment.getPaymentList().remove(paymentListNewPayment);
                        oldCreditcardIDOfPaymentListNewPayment = em.merge(oldCreditcardIDOfPaymentListNewPayment);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = creditcard.getCreditcardID();
                if (findCreditcard(id) == null) {
                    throw new NonexistentEntityException("The creditcard with id " + id + " no longer exists.");
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
            Creditcard creditcard;
            try {
                creditcard = em.getReference(Creditcard.class, id);
                creditcard.getCreditcardID();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The creditcard with id " + id + " no longer exists.", enfe);
            }
            User userID = creditcard.getUserID();
            if (userID != null) {
                userID.getCreditcardList().remove(creditcard);
                userID = em.merge(userID);
            }
            List<Payment> paymentList = creditcard.getPaymentList();
            for (Payment paymentListPayment : paymentList) {
                paymentListPayment.setCreditcardID(null);
                paymentListPayment = em.merge(paymentListPayment);
            }
            em.remove(creditcard);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Creditcard> findCreditcardEntities() {
        return findCreditcardEntities(true, -1, -1);
    }

    public List<Creditcard> findCreditcardEntities(int maxResults, int firstResult) {
        return findCreditcardEntities(false, maxResults, firstResult);
    }

    private List<Creditcard> findCreditcardEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Creditcard.class));
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

    public Creditcard findCreditcard(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Creditcard.class, id);
        } finally {
            em.close();
        }
    }

    public int getCreditcardCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Creditcard> rt = cq.from(Creditcard.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
