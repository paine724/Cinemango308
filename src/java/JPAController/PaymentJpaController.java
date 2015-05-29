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
import JPA.Ticket;
import JPA.User;
import JPA.Creditcard;
import JPA.Giftcard;
import JPA.Payment;
import JPAController.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author zeb
 */
public class PaymentJpaController implements Serializable {

    public PaymentJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Payment payment) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ticket ticketID = payment.getTicketID();
            if (ticketID != null) {
                ticketID = em.getReference(ticketID.getClass(), ticketID.getId());
                payment.setTicketID(ticketID);
            }
            User userID = payment.getUserID();
            if (userID != null) {
                userID = em.getReference(userID.getClass(), userID.getUserID());
                payment.setUserID(userID);
            }
            Creditcard creditcardID = payment.getCreditcardID();
            if (creditcardID != null) {
                creditcardID = em.getReference(creditcardID.getClass(), creditcardID.getCreditcardID());
                payment.setCreditcardID(creditcardID);
            }
            Giftcard giftcardID = payment.getGiftcardID();
            if (giftcardID != null) {
                giftcardID = em.getReference(giftcardID.getClass(), giftcardID.getId());
                payment.setGiftcardID(giftcardID);
            }
            em.persist(payment);
            if (ticketID != null) {
                ticketID.getPaymentList().add(payment);
                ticketID = em.merge(ticketID);
            }
            if (userID != null) {
                userID.getPaymentList().add(payment);
                userID = em.merge(userID);
            }
            if (creditcardID != null) {
                creditcardID.getPaymentList().add(payment);
                creditcardID = em.merge(creditcardID);
            }
            if (giftcardID != null) {
                giftcardID.getPaymentList().add(payment);
                giftcardID = em.merge(giftcardID);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Payment payment) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Payment persistentPayment = em.find(Payment.class, payment.getId());
            Ticket ticketIDOld = persistentPayment.getTicketID();
            Ticket ticketIDNew = payment.getTicketID();
            User userIDOld = persistentPayment.getUserID();
            User userIDNew = payment.getUserID();
            Creditcard creditcardIDOld = persistentPayment.getCreditcardID();
            Creditcard creditcardIDNew = payment.getCreditcardID();
            Giftcard giftcardIDOld = persistentPayment.getGiftcardID();
            Giftcard giftcardIDNew = payment.getGiftcardID();
            if (ticketIDNew != null) {
                ticketIDNew = em.getReference(ticketIDNew.getClass(), ticketIDNew.getId());
                payment.setTicketID(ticketIDNew);
            }
            if (userIDNew != null) {
                userIDNew = em.getReference(userIDNew.getClass(), userIDNew.getUserID());
                payment.setUserID(userIDNew);
            }
            if (creditcardIDNew != null) {
                creditcardIDNew = em.getReference(creditcardIDNew.getClass(), creditcardIDNew.getCreditcardID());
                payment.setCreditcardID(creditcardIDNew);
            }
            if (giftcardIDNew != null) {
                giftcardIDNew = em.getReference(giftcardIDNew.getClass(), giftcardIDNew.getId());
                payment.setGiftcardID(giftcardIDNew);
            }
            payment = em.merge(payment);
            if (ticketIDOld != null && !ticketIDOld.equals(ticketIDNew)) {
                ticketIDOld.getPaymentList().remove(payment);
                ticketIDOld = em.merge(ticketIDOld);
            }
            if (ticketIDNew != null && !ticketIDNew.equals(ticketIDOld)) {
                ticketIDNew.getPaymentList().add(payment);
                ticketIDNew = em.merge(ticketIDNew);
            }
            if (userIDOld != null && !userIDOld.equals(userIDNew)) {
                userIDOld.getPaymentList().remove(payment);
                userIDOld = em.merge(userIDOld);
            }
            if (userIDNew != null && !userIDNew.equals(userIDOld)) {
                userIDNew.getPaymentList().add(payment);
                userIDNew = em.merge(userIDNew);
            }
            if (creditcardIDOld != null && !creditcardIDOld.equals(creditcardIDNew)) {
                creditcardIDOld.getPaymentList().remove(payment);
                creditcardIDOld = em.merge(creditcardIDOld);
            }
            if (creditcardIDNew != null && !creditcardIDNew.equals(creditcardIDOld)) {
                creditcardIDNew.getPaymentList().add(payment);
                creditcardIDNew = em.merge(creditcardIDNew);
            }
            if (giftcardIDOld != null && !giftcardIDOld.equals(giftcardIDNew)) {
                giftcardIDOld.getPaymentList().remove(payment);
                giftcardIDOld = em.merge(giftcardIDOld);
            }
            if (giftcardIDNew != null && !giftcardIDNew.equals(giftcardIDOld)) {
                giftcardIDNew.getPaymentList().add(payment);
                giftcardIDNew = em.merge(giftcardIDNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = payment.getId();
                if (findPayment(id) == null) {
                    throw new NonexistentEntityException("The payment with id " + id + " no longer exists.");
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
            Payment payment;
            try {
                payment = em.getReference(Payment.class, id);
                payment.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The payment with id " + id + " no longer exists.", enfe);
            }
            Ticket ticketID = payment.getTicketID();
            if (ticketID != null) {
                ticketID.getPaymentList().remove(payment);
                ticketID = em.merge(ticketID);
            }
            User userID = payment.getUserID();
            if (userID != null) {
                userID.getPaymentList().remove(payment);
                userID = em.merge(userID);
            }
            Creditcard creditcardID = payment.getCreditcardID();
            if (creditcardID != null) {
                creditcardID.getPaymentList().remove(payment);
                creditcardID = em.merge(creditcardID);
            }
            Giftcard giftcardID = payment.getGiftcardID();
            if (giftcardID != null) {
                giftcardID.getPaymentList().remove(payment);
                giftcardID = em.merge(giftcardID);
            }
            em.remove(payment);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Payment> findPaymentEntities() {
        return findPaymentEntities(true, -1, -1);
    }

    public List<Payment> findPaymentEntities(int maxResults, int firstResult) {
        return findPaymentEntities(false, maxResults, firstResult);
    }

    private List<Payment> findPaymentEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Payment.class));
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

    public Payment findPayment(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Payment.class, id);
        } finally {
            em.close();
        }
    }

    public int getPaymentCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Payment> rt = cq.from(Payment.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
