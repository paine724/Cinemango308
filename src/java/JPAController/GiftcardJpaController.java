/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPAController;

import JPA.Giftcard;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
public class GiftcardJpaController implements Serializable {

    public GiftcardJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Giftcard giftcard) {
        if (giftcard.getPaymentList() == null) {
            giftcard.setPaymentList(new ArrayList<Payment>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Payment> attachedPaymentList = new ArrayList<Payment>();
            for (Payment paymentListPaymentToAttach : giftcard.getPaymentList()) {
                paymentListPaymentToAttach = em.getReference(paymentListPaymentToAttach.getClass(), paymentListPaymentToAttach.getId());
                attachedPaymentList.add(paymentListPaymentToAttach);
            }
            giftcard.setPaymentList(attachedPaymentList);
            em.persist(giftcard);
            for (Payment paymentListPayment : giftcard.getPaymentList()) {
                Giftcard oldGiftcardIDOfPaymentListPayment = paymentListPayment.getGiftcardID();
                paymentListPayment.setGiftcardID(giftcard);
                paymentListPayment = em.merge(paymentListPayment);
                if (oldGiftcardIDOfPaymentListPayment != null) {
                    oldGiftcardIDOfPaymentListPayment.getPaymentList().remove(paymentListPayment);
                    oldGiftcardIDOfPaymentListPayment = em.merge(oldGiftcardIDOfPaymentListPayment);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Giftcard giftcard) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Giftcard persistentGiftcard = em.find(Giftcard.class, giftcard.getId());
            List<Payment> paymentListOld = persistentGiftcard.getPaymentList();
            List<Payment> paymentListNew = giftcard.getPaymentList();
            List<Payment> attachedPaymentListNew = new ArrayList<Payment>();
            for (Payment paymentListNewPaymentToAttach : paymentListNew) {
                paymentListNewPaymentToAttach = em.getReference(paymentListNewPaymentToAttach.getClass(), paymentListNewPaymentToAttach.getId());
                attachedPaymentListNew.add(paymentListNewPaymentToAttach);
            }
            paymentListNew = attachedPaymentListNew;
            giftcard.setPaymentList(paymentListNew);
            giftcard = em.merge(giftcard);
            for (Payment paymentListOldPayment : paymentListOld) {
                if (!paymentListNew.contains(paymentListOldPayment)) {
                    paymentListOldPayment.setGiftcardID(null);
                    paymentListOldPayment = em.merge(paymentListOldPayment);
                }
            }
            for (Payment paymentListNewPayment : paymentListNew) {
                if (!paymentListOld.contains(paymentListNewPayment)) {
                    Giftcard oldGiftcardIDOfPaymentListNewPayment = paymentListNewPayment.getGiftcardID();
                    paymentListNewPayment.setGiftcardID(giftcard);
                    paymentListNewPayment = em.merge(paymentListNewPayment);
                    if (oldGiftcardIDOfPaymentListNewPayment != null && !oldGiftcardIDOfPaymentListNewPayment.equals(giftcard)) {
                        oldGiftcardIDOfPaymentListNewPayment.getPaymentList().remove(paymentListNewPayment);
                        oldGiftcardIDOfPaymentListNewPayment = em.merge(oldGiftcardIDOfPaymentListNewPayment);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = giftcard.getId();
                if (findGiftcard(id) == null) {
                    throw new NonexistentEntityException("The giftcard with id " + id + " no longer exists.");
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
            Giftcard giftcard;
            try {
                giftcard = em.getReference(Giftcard.class, id);
                giftcard.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The giftcard with id " + id + " no longer exists.", enfe);
            }
            List<Payment> paymentList = giftcard.getPaymentList();
            for (Payment paymentListPayment : paymentList) {
                paymentListPayment.setGiftcardID(null);
                paymentListPayment = em.merge(paymentListPayment);
            }
            em.remove(giftcard);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Giftcard> findGiftcardEntities() {
        return findGiftcardEntities(true, -1, -1);
    }

    public List<Giftcard> findGiftcardEntities(int maxResults, int firstResult) {
        return findGiftcardEntities(false, maxResults, firstResult);
    }

    private List<Giftcard> findGiftcardEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Giftcard.class));
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

    public Giftcard findGiftcard(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Giftcard.class, id);
        } finally {
            em.close();
        }
    }

    public int getGiftcardCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Giftcard> rt = cq.from(Giftcard.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
