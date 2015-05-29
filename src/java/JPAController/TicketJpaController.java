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
import JPA.Theatre;
import JPA.Showtime;
import JPA.Payment;
import JPA.Ticket;
import JPAController.exceptions.IllegalOrphanException;
import JPAController.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author zeb
 */
public class TicketJpaController implements Serializable {

    public TicketJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Ticket ticket) {
        if (ticket.getPaymentList() == null) {
            ticket.setPaymentList(new ArrayList<Payment>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Movie movieID = ticket.getMovieID();
            if (movieID != null) {
                movieID = em.getReference(movieID.getClass(), movieID.getMovieID());
                ticket.setMovieID(movieID);
            }
            Theatre theatreID = ticket.getTheatreID();
            if (theatreID != null) {
                theatreID = em.getReference(theatreID.getClass(), theatreID.getId());
                ticket.setTheatreID(theatreID);
            }
            Showtime showtimeID = ticket.getShowtimeID();
            if (showtimeID != null) {
                showtimeID = em.getReference(showtimeID.getClass(), showtimeID.getId());
                ticket.setShowtimeID(showtimeID);
            }
            List<Payment> attachedPaymentList = new ArrayList<Payment>();
            for (Payment paymentListPaymentToAttach : ticket.getPaymentList()) {
                paymentListPaymentToAttach = em.getReference(paymentListPaymentToAttach.getClass(), paymentListPaymentToAttach.getId());
                attachedPaymentList.add(paymentListPaymentToAttach);
            }
            ticket.setPaymentList(attachedPaymentList);
            em.persist(ticket);
            if (movieID != null) {
                movieID.getTicketList().add(ticket);
                movieID = em.merge(movieID);
            }
            if (theatreID != null) {
                theatreID.getTicketList().add(ticket);
                theatreID = em.merge(theatreID);
            }
            if (showtimeID != null) {
                showtimeID.getTicketList().add(ticket);
                showtimeID = em.merge(showtimeID);
            }
            for (Payment paymentListPayment : ticket.getPaymentList()) {
                Ticket oldTicketIDOfPaymentListPayment = paymentListPayment.getTicketID();
                paymentListPayment.setTicketID(ticket);
                paymentListPayment = em.merge(paymentListPayment);
                if (oldTicketIDOfPaymentListPayment != null) {
                    oldTicketIDOfPaymentListPayment.getPaymentList().remove(paymentListPayment);
                    oldTicketIDOfPaymentListPayment = em.merge(oldTicketIDOfPaymentListPayment);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Ticket ticket) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ticket persistentTicket = em.find(Ticket.class, ticket.getId());
            Movie movieIDOld = persistentTicket.getMovieID();
            Movie movieIDNew = ticket.getMovieID();
            Theatre theatreIDOld = persistentTicket.getTheatreID();
            Theatre theatreIDNew = ticket.getTheatreID();
            Showtime showtimeIDOld = persistentTicket.getShowtimeID();
            Showtime showtimeIDNew = ticket.getShowtimeID();
            List<Payment> paymentListOld = persistentTicket.getPaymentList();
            List<Payment> paymentListNew = ticket.getPaymentList();
            List<String> illegalOrphanMessages = null;
            for (Payment paymentListOldPayment : paymentListOld) {
                if (!paymentListNew.contains(paymentListOldPayment)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Payment " + paymentListOldPayment + " since its ticketID field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (movieIDNew != null) {
                movieIDNew = em.getReference(movieIDNew.getClass(), movieIDNew.getMovieID());
                ticket.setMovieID(movieIDNew);
            }
            if (theatreIDNew != null) {
                theatreIDNew = em.getReference(theatreIDNew.getClass(), theatreIDNew.getId());
                ticket.setTheatreID(theatreIDNew);
            }
            if (showtimeIDNew != null) {
                showtimeIDNew = em.getReference(showtimeIDNew.getClass(), showtimeIDNew.getId());
                ticket.setShowtimeID(showtimeIDNew);
            }
            List<Payment> attachedPaymentListNew = new ArrayList<Payment>();
            for (Payment paymentListNewPaymentToAttach : paymentListNew) {
                paymentListNewPaymentToAttach = em.getReference(paymentListNewPaymentToAttach.getClass(), paymentListNewPaymentToAttach.getId());
                attachedPaymentListNew.add(paymentListNewPaymentToAttach);
            }
            paymentListNew = attachedPaymentListNew;
            ticket.setPaymentList(paymentListNew);
            ticket = em.merge(ticket);
            if (movieIDOld != null && !movieIDOld.equals(movieIDNew)) {
                movieIDOld.getTicketList().remove(ticket);
                movieIDOld = em.merge(movieIDOld);
            }
            if (movieIDNew != null && !movieIDNew.equals(movieIDOld)) {
                movieIDNew.getTicketList().add(ticket);
                movieIDNew = em.merge(movieIDNew);
            }
            if (theatreIDOld != null && !theatreIDOld.equals(theatreIDNew)) {
                theatreIDOld.getTicketList().remove(ticket);
                theatreIDOld = em.merge(theatreIDOld);
            }
            if (theatreIDNew != null && !theatreIDNew.equals(theatreIDOld)) {
                theatreIDNew.getTicketList().add(ticket);
                theatreIDNew = em.merge(theatreIDNew);
            }
            if (showtimeIDOld != null && !showtimeIDOld.equals(showtimeIDNew)) {
                showtimeIDOld.getTicketList().remove(ticket);
                showtimeIDOld = em.merge(showtimeIDOld);
            }
            if (showtimeIDNew != null && !showtimeIDNew.equals(showtimeIDOld)) {
                showtimeIDNew.getTicketList().add(ticket);
                showtimeIDNew = em.merge(showtimeIDNew);
            }
            for (Payment paymentListNewPayment : paymentListNew) {
                if (!paymentListOld.contains(paymentListNewPayment)) {
                    Ticket oldTicketIDOfPaymentListNewPayment = paymentListNewPayment.getTicketID();
                    paymentListNewPayment.setTicketID(ticket);
                    paymentListNewPayment = em.merge(paymentListNewPayment);
                    if (oldTicketIDOfPaymentListNewPayment != null && !oldTicketIDOfPaymentListNewPayment.equals(ticket)) {
                        oldTicketIDOfPaymentListNewPayment.getPaymentList().remove(paymentListNewPayment);
                        oldTicketIDOfPaymentListNewPayment = em.merge(oldTicketIDOfPaymentListNewPayment);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = ticket.getId();
                if (findTicket(id) == null) {
                    throw new NonexistentEntityException("The ticket with id " + id + " no longer exists.");
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
            Ticket ticket;
            try {
                ticket = em.getReference(Ticket.class, id);
                ticket.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ticket with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Payment> paymentListOrphanCheck = ticket.getPaymentList();
            for (Payment paymentListOrphanCheckPayment : paymentListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Ticket (" + ticket + ") cannot be destroyed since the Payment " + paymentListOrphanCheckPayment + " in its paymentList field has a non-nullable ticketID field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Movie movieID = ticket.getMovieID();
            if (movieID != null) {
                movieID.getTicketList().remove(ticket);
                movieID = em.merge(movieID);
            }
            Theatre theatreID = ticket.getTheatreID();
            if (theatreID != null) {
                theatreID.getTicketList().remove(ticket);
                theatreID = em.merge(theatreID);
            }
            Showtime showtimeID = ticket.getShowtimeID();
            if (showtimeID != null) {
                showtimeID.getTicketList().remove(ticket);
                showtimeID = em.merge(showtimeID);
            }
            em.remove(ticket);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Ticket> findTicketEntities() {
        return findTicketEntities(true, -1, -1);
    }

    public List<Ticket> findTicketEntities(int maxResults, int firstResult) {
        return findTicketEntities(false, maxResults, firstResult);
    }

    private List<Ticket> findTicketEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Ticket.class));
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

    public Ticket findTicket(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Ticket.class, id);
        } finally {
            em.close();
        }
    }

    public int getTicketCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Ticket> rt = cq.from(Ticket.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
