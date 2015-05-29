/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPA;

import java.io.Serializable;
import javax.persistence.Basic;
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
import javax.persistence.Table;

/**
 *
 * @author zeb
 */
@Entity
@Table(name = "movieratings")
@NamedQueries({
    @NamedQuery(name = "Movieratings.findAll", query = "SELECT m FROM Movieratings m"),
    @NamedQuery(name = "Movieratings.findById", query = "SELECT m FROM Movieratings m WHERE m.id = :id"),
    @NamedQuery(name = "Movieratings.findByRating", query = "SELECT m FROM Movieratings m WHERE m.rating = :rating")})
public class Movieratings implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "rating")
    private Double rating;
    @JoinColumn(name = "movieid", referencedColumnName = "movieID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Movie movieid;
    @JoinColumn(name = "userid", referencedColumnName = "userID")
    @ManyToOne(fetch = FetchType.LAZY)
    private User userid;

    public Movieratings() {
    }

    public Movieratings(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Movie getMovieid() {
        return movieid;
    }

    public void setMovieid(Movie movieid) {
        this.movieid = movieid;
    }

    public User getUserid() {
        return userid;
    }

    public void setUserid(User userid) {
        this.userid = userid;
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
        if (!(object instanceof Movieratings)) {
            return false;
        }
        Movieratings other = (Movieratings) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "JPA.Movieratings[ id=" + id + " ]";
    }
    
}
