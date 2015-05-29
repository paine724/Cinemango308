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
import JPA.Showtime;
import JPA.Theatre;
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
public class ShowtimeJpaController implements Serializable {

    public ShowtimeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Showtime showtime) {
        if (showtime.getTicketList() == null) {
            showtime.setTicketList(new ArrayList<Ticket>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Movie movieID = showtime.getMovieID();
            if (movieID != null) {
                movieID = em.getReference(movieID.getClass(), movieID.getMovieID());
                showtime.setMovieID(movieID);
            }
            Theatre theatreID = showtime.getTheatreID();
            if (theatreID != null) {
                theatreID = em.getReference(theatreID.getClass(), theatreID.getId());
                showtime.setTheatreID(theatreID);
            }
            List<Ticket> attachedTicketList = new ArrayList<Ticket>();
            for (Ticket ticketListTicketToAttach : showtime.getTicketList()) {
                ticketListTicketToAttach = em.getReference(ticketListTicketToAttach.getClass(), ticketListTicketToAttach.getId());
                attachedTicketList.add(ticketListTicketToAttach);
            }
            showtime.setTicketList(attachedTicketList);
            em.persist(showtime);
            if (movieID != null) {
                movieID.getShowtimeList().add(showtime);
                movieID = em.merge(movieID);
            }
            if (theatreID != null) {
                theatreID.getShowtimeList().add(showtime);
                theatreID = em.merge(theatreID);
            }
            for (Ticket ticketListTicket : showtime.getTicketList()) {
                Showtime oldShowtimeIDOfTicketListTicket = ticketListTicket.getShowtimeID();
                ticketListTicket.setShowtimeID(showtime);
                ticketListTicket = em.merge(ticketListTicket);
                if (oldShowtimeIDOfTicketListTicket != null) {
                    oldShowtimeIDOfTicketListTicket.getTicketList().remove(ticketListTicket);
                    oldShowtimeIDOfTicketListTicket = em.merge(oldShowtimeIDOfTicketListTicket);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Showtime showtime) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Showtime persistentShowtime = em.find(Showtime.class, showtime.getId());
            Movie movieIDOld = persistentShowtime.getMovieID();
            Movie movieIDNew = showtime.getMovieID();
            Theatre theatreIDOld = persistentShowtime.getTheatreID();
            Theatre theatreIDNew = showtime.getTheatreID();
            List<Ticket> ticketListOld = persistentShowtime.getTicketList();
            List<Ticket> ticketListNew = showtime.getTicketList();
            List<String> illegalOrphanMessages = null;
            for (Ticket ticketListOldTicket : ticketListOld) {
                if (!ticketListNew.contains(ticketListOldTicket)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Ticket " + ticketListOldTicket + " since its showtimeID field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (movieIDNew != null) {
                movieIDNew = em.getReference(movieIDNew.getClass(), movieIDNew.getMovieID());
                showtime.setMovieID(movieIDNew);
            }
            if (theatreIDNew != null) {
                theatreIDNew = em.getReference(theatreIDNew.getClass(), theatreIDNew.getId());
                showtime.setTheatreID(theatreIDNew);
            }
            List<Ticket> attachedTicketListNew = new ArrayList<Ticket>();
            for (Ticket ticketListNewTicketToAttach : ticketListNew) {
                ticketListNewTicketToAttach = em.getReference(ticketListNewTicketToAttach.getClass(), ticketListNewTicketToAttach.getId());
                attachedTicketListNew.add(ticketListNewTicketToAttach);
            }
            ticketListNew = attachedTicketListNew;
            showtime.setTicketList(ticketListNew);
            showtime = em.merge(showtime);
            if (movieIDOld != null && !movieIDOld.equals(movieIDNew)) {
                movieIDOld.getShowtimeList().remove(showtime);
                movieIDOld = em.merge(movieIDOld);
            }
            if (movieIDNew != null && !movieIDNew.equals(movieIDOld)) {
                movieIDNew.getShowtimeList().add(showtime);
                movieIDNew = em.merge(movieIDNew);
            }
            if (theatreIDOld != null && !theatreIDOld.equals(theatreIDNew)) {
                theatreIDOld.getShowtimeList().remove(showtime);
                theatreIDOld = em.merge(theatreIDOld);
            }
            if (theatreIDNew != null && !theatreIDNew.equals(theatreIDOld)) {
                theatreIDNew.getShowtimeList().add(showtime);
                theatreIDNew = em.merge(theatreIDNew);
            }
            for (Ticket ticketListNewTicket : ticketListNew) {
                if (!ticketListOld.contains(ticketListNewTicket)) {
                    Showtime oldShowtimeIDOfTicketListNewTicket = ticketListNewTicket.getShowtimeID();
                    ticketListNewTicket.setShowtimeID(showtime);
                    ticketListNewTicket = em.merge(ticketListNewTicket);
                    if (oldShowtimeIDOfTicketListNewTicket != null && !oldShowtimeIDOfTicketListNewTicket.equals(showtime)) {
                        oldShowtimeIDOfTicketListNewTicket.getTicketList().remove(ticketListNewTicket);
                        oldShowtimeIDOfTicketListNewTicket = em.merge(oldShowtimeIDOfTicketListNewTicket);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = showtime.getId();
                if (findShowtime(id) == null) {
                    throw new NonexistentEntityException("The showtime with id " + id + " no longer exists.");
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
            Showtime showtime;
            try {
                showtime = em.getReference(Showtime.class, id);
                showtime.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The showtime with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Ticket> ticketListOrphanCheck = showtime.getTicketList();
            for (Ticket ticketListOrphanCheckTicket : ticketListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Showtime (" + showtime + ") cannot be destroyed since the Ticket " + ticketListOrphanCheckTicket + " in its ticketList field has a non-nullable showtimeID field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Movie movieID = showtime.getMovieID();
            if (movieID != null) {
                movieID.getShowtimeList().remove(showtime);
                movieID = em.merge(movieID);
            }
            Theatre theatreID = showtime.getTheatreID();
            if (theatreID != null) {
                theatreID.getShowtimeList().remove(showtime);
                theatreID = em.merge(theatreID);
            }
            em.remove(showtime);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Showtime> findShowtimeEntities() {
        return findShowtimeEntities(true, -1, -1);
    }

    public List<Showtime> findShowtimeEntities(int maxResults, int firstResult) {
        return findShowtimeEntities(false, maxResults, firstResult);
    }

    private List<Showtime> findShowtimeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Showtime.class));
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

    public Showtime findShowtime(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Showtime.class, id);
        } finally {
            em.close();
        }
    }

    public int getShowtimeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Showtime> rt = cq.from(Showtime.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
