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
import JPA.User;
import java.util.ArrayList;
import java.util.List;
import JPA.Ticket;
import JPA.Showtime;
import JPA.Theatre;
import JPAController.exceptions.IllegalOrphanException;
import JPAController.exceptions.NonexistentEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author zeb
 */
public class TheatreJpaController implements Serializable {

    public TheatreJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Theatre theatre) {
        if (theatre.getUserList() == null) {
            theatre.setUserList(new ArrayList<User>());
        }
        if (theatre.getTicketList() == null) {
            theatre.setTicketList(new ArrayList<Ticket>());
        }
        if (theatre.getShowtimeList() == null) {
            theatre.setShowtimeList(new ArrayList<Showtime>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<User> attachedUserList = new ArrayList<User>();
            for (User userListUserToAttach : theatre.getUserList()) {
                userListUserToAttach = em.getReference(userListUserToAttach.getClass(), userListUserToAttach.getUserID());
                attachedUserList.add(userListUserToAttach);
            }
            theatre.setUserList(attachedUserList);
            List<Ticket> attachedTicketList = new ArrayList<Ticket>();
            for (Ticket ticketListTicketToAttach : theatre.getTicketList()) {
                ticketListTicketToAttach = em.getReference(ticketListTicketToAttach.getClass(), ticketListTicketToAttach.getId());
                attachedTicketList.add(ticketListTicketToAttach);
            }
            theatre.setTicketList(attachedTicketList);
            List<Showtime> attachedShowtimeList = new ArrayList<Showtime>();
            for (Showtime showtimeListShowtimeToAttach : theatre.getShowtimeList()) {
                showtimeListShowtimeToAttach = em.getReference(showtimeListShowtimeToAttach.getClass(), showtimeListShowtimeToAttach.getId());
                attachedShowtimeList.add(showtimeListShowtimeToAttach);
            }
            theatre.setShowtimeList(attachedShowtimeList);
            em.persist(theatre);
            for (User userListUser : theatre.getUserList()) {
                userListUser.getTheatreList().add(theatre);
                userListUser = em.merge(userListUser);
            }
            for (Ticket ticketListTicket : theatre.getTicketList()) {
                Theatre oldTheatreIDOfTicketListTicket = ticketListTicket.getTheatreID();
                ticketListTicket.setTheatreID(theatre);
                ticketListTicket = em.merge(ticketListTicket);
                if (oldTheatreIDOfTicketListTicket != null) {
                    oldTheatreIDOfTicketListTicket.getTicketList().remove(ticketListTicket);
                    oldTheatreIDOfTicketListTicket = em.merge(oldTheatreIDOfTicketListTicket);
                }
            }
            for (Showtime showtimeListShowtime : theatre.getShowtimeList()) {
                Theatre oldTheatreIDOfShowtimeListShowtime = showtimeListShowtime.getTheatreID();
                showtimeListShowtime.setTheatreID(theatre);
                showtimeListShowtime = em.merge(showtimeListShowtime);
                if (oldTheatreIDOfShowtimeListShowtime != null) {
                    oldTheatreIDOfShowtimeListShowtime.getShowtimeList().remove(showtimeListShowtime);
                    oldTheatreIDOfShowtimeListShowtime = em.merge(oldTheatreIDOfShowtimeListShowtime);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Theatre theatre) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Theatre persistentTheatre = em.find(Theatre.class, theatre.getId());
            List<User> userListOld = persistentTheatre.getUserList();
            List<User> userListNew = theatre.getUserList();
            List<Ticket> ticketListOld = persistentTheatre.getTicketList();
            List<Ticket> ticketListNew = theatre.getTicketList();
            List<Showtime> showtimeListOld = persistentTheatre.getShowtimeList();
            List<Showtime> showtimeListNew = theatre.getShowtimeList();
            List<String> illegalOrphanMessages = null;
            for (Ticket ticketListOldTicket : ticketListOld) {
                if (!ticketListNew.contains(ticketListOldTicket)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Ticket " + ticketListOldTicket + " since its theatreID field is not nullable.");
                }
            }
            for (Showtime showtimeListOldShowtime : showtimeListOld) {
                if (!showtimeListNew.contains(showtimeListOldShowtime)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Showtime " + showtimeListOldShowtime + " since its theatreID field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<User> attachedUserListNew = new ArrayList<User>();
            for (User userListNewUserToAttach : userListNew) {
                userListNewUserToAttach = em.getReference(userListNewUserToAttach.getClass(), userListNewUserToAttach.getUserID());
                attachedUserListNew.add(userListNewUserToAttach);
            }
            userListNew = attachedUserListNew;
            theatre.setUserList(userListNew);
            List<Ticket> attachedTicketListNew = new ArrayList<Ticket>();
            for (Ticket ticketListNewTicketToAttach : ticketListNew) {
                ticketListNewTicketToAttach = em.getReference(ticketListNewTicketToAttach.getClass(), ticketListNewTicketToAttach.getId());
                attachedTicketListNew.add(ticketListNewTicketToAttach);
            }
            ticketListNew = attachedTicketListNew;
            theatre.setTicketList(ticketListNew);
            List<Showtime> attachedShowtimeListNew = new ArrayList<Showtime>();
            for (Showtime showtimeListNewShowtimeToAttach : showtimeListNew) {
                showtimeListNewShowtimeToAttach = em.getReference(showtimeListNewShowtimeToAttach.getClass(), showtimeListNewShowtimeToAttach.getId());
                attachedShowtimeListNew.add(showtimeListNewShowtimeToAttach);
            }
            showtimeListNew = attachedShowtimeListNew;
            theatre.setShowtimeList(showtimeListNew);
            theatre = em.merge(theatre);
            for (User userListOldUser : userListOld) {
                if (!userListNew.contains(userListOldUser)) {
                    userListOldUser.getTheatreList().remove(theatre);
                    userListOldUser = em.merge(userListOldUser);
                }
            }
            for (User userListNewUser : userListNew) {
                if (!userListOld.contains(userListNewUser)) {
                    userListNewUser.getTheatreList().add(theatre);
                    userListNewUser = em.merge(userListNewUser);
                }
            }
            for (Ticket ticketListNewTicket : ticketListNew) {
                if (!ticketListOld.contains(ticketListNewTicket)) {
                    Theatre oldTheatreIDOfTicketListNewTicket = ticketListNewTicket.getTheatreID();
                    ticketListNewTicket.setTheatreID(theatre);
                    ticketListNewTicket = em.merge(ticketListNewTicket);
                    if (oldTheatreIDOfTicketListNewTicket != null && !oldTheatreIDOfTicketListNewTicket.equals(theatre)) {
                        oldTheatreIDOfTicketListNewTicket.getTicketList().remove(ticketListNewTicket);
                        oldTheatreIDOfTicketListNewTicket = em.merge(oldTheatreIDOfTicketListNewTicket);
                    }
                }
            }
            for (Showtime showtimeListNewShowtime : showtimeListNew) {
                if (!showtimeListOld.contains(showtimeListNewShowtime)) {
                    Theatre oldTheatreIDOfShowtimeListNewShowtime = showtimeListNewShowtime.getTheatreID();
                    showtimeListNewShowtime.setTheatreID(theatre);
                    showtimeListNewShowtime = em.merge(showtimeListNewShowtime);
                    if (oldTheatreIDOfShowtimeListNewShowtime != null && !oldTheatreIDOfShowtimeListNewShowtime.equals(theatre)) {
                        oldTheatreIDOfShowtimeListNewShowtime.getShowtimeList().remove(showtimeListNewShowtime);
                        oldTheatreIDOfShowtimeListNewShowtime = em.merge(oldTheatreIDOfShowtimeListNewShowtime);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = theatre.getId();
                if (findTheatre(id) == null) {
                    throw new NonexistentEntityException("The theatre with id " + id + " no longer exists.");
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
            Theatre theatre;
            try {
                theatre = em.getReference(Theatre.class, id);
                theatre.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The theatre with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Ticket> ticketListOrphanCheck = theatre.getTicketList();
            for (Ticket ticketListOrphanCheckTicket : ticketListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Theatre (" + theatre + ") cannot be destroyed since the Ticket " + ticketListOrphanCheckTicket + " in its ticketList field has a non-nullable theatreID field.");
            }
            List<Showtime> showtimeListOrphanCheck = theatre.getShowtimeList();
            for (Showtime showtimeListOrphanCheckShowtime : showtimeListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Theatre (" + theatre + ") cannot be destroyed since the Showtime " + showtimeListOrphanCheckShowtime + " in its showtimeList field has a non-nullable theatreID field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<User> userList = theatre.getUserList();
            for (User userListUser : userList) {
                userListUser.getTheatreList().remove(theatre);
                userListUser = em.merge(userListUser);
            }
            em.remove(theatre);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Theatre> findTheatreEntities() {
        return findTheatreEntities(true, -1, -1);
    }

    public List<Theatre> findTheatreEntities(int maxResults, int firstResult) {
        return findTheatreEntities(false, maxResults, firstResult);
    }

    private List<Theatre> findTheatreEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Theatre.class));
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

    public Theatre findTheatre(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Theatre.class, id);
        } finally {
            em.close();
        }
    }

    public int getTheatreCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Theatre> rt = cq.from(Theatre.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
