/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPA;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author zeb
 */
@Entity
@Table(name = "showtime")
@NamedQueries({
    @NamedQuery(name = "Showtime.findAll", query = "SELECT s FROM Showtime s"),
    @NamedQuery(name = "Showtime.findById", query = "SELECT s FROM Showtime s WHERE s.id = :id"),
    @NamedQuery(name = "Showtime.findByDate", query = "SELECT s FROM Showtime s WHERE s.date = :date"),
    @NamedQuery(name = "Showtime.findByTicketcap", query = "SELECT s FROM Showtime s WHERE s.ticketcap = :ticketcap")})
public class Showtime implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Column(name = "ticketcap")
    private Integer ticketcap;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "showtimeID", fetch = FetchType.LAZY)
    private List<Ticket> ticketList;
    @JoinColumn(name = "movieID", referencedColumnName = "movieID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Movie movieID;
    @JoinColumn(name = "theatreID", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Theatre theatreID;

    public Showtime() {
    }

    public Showtime(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getTicketcap() {
        return ticketcap;
    }

    public void setTicketcap(Integer ticketcap) {
        this.ticketcap = ticketcap;
    }

    public List<Ticket> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }

    public Movie getMovieID() {
        return movieID;
    }

    public void setMovieID(Movie movieID) {
        this.movieID = movieID;
    }

    public Theatre getTheatreID() {
        return theatreID;
    }

    public void setTheatreID(Theatre theatreID) {
        this.theatreID = theatreID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Showtime)) {
            return false;
        }
        Showtime other = (Showtime) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "JPA.Showtime[ id=" + id + " ]";
    }
    
}
