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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author zeb
 */
@Entity
@Table(name = "actor")
@NamedQueries({
    @NamedQuery(name = "Actor.findAll", query = "SELECT a FROM Actor a"),
    @NamedQuery(name = "Actor.findByActorid", query = "SELECT a FROM Actor a WHERE a.actorid = :actorid"),
    @NamedQuery(name = "Actor.findByFirstname", query = "SELECT a FROM Actor a WHERE a.firstname = :firstname"),
    @NamedQuery(name = "Actor.findByLastName", query = "SELECT a FROM Actor a WHERE a.lastName = :lastName"),
    @NamedQuery(name = "Actor.findByDob", query = "SELECT a FROM Actor a WHERE a.dob = :dob"),
    @NamedQuery(name = "Actor.findByIMDBLink", query = "SELECT a FROM Actor a WHERE a.iMDBLink = :iMDBLink"),
    @NamedQuery(name = "Actor.findByPicture", query = "SELECT a FROM Actor a WHERE a.picture = :picture")})
public class Actor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "actorid")
    private Integer actorid;
    @Column(name = "firstname")
    private String firstname;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "dob")
    @Temporal(TemporalType.DATE)
    private Date dob;
    @Column(name = "IMDBLink")
    private String iMDBLink;
    @Column(name = "picture")
    private String picture;
    @JoinTable(name = "actorsinmovies", joinColumns = {
        @JoinColumn(name = "actorid", referencedColumnName = "actorid")}, inverseJoinColumns = {
        @JoinColumn(name = "movieid", referencedColumnName = "movieID")})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Movie> movieList;

    public Actor() {
    }

    public Actor(Integer actorid) {
        this.actorid = actorid;
    }

    public Integer getActorid() {
        return actorid;
    }

    public void setActorid(Integer actorid) {
        this.actorid = actorid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getIMDBLink() {
        return iMDBLink;
    }

    public void setIMDBLink(String iMDBLink) {
        this.iMDBLink = iMDBLink;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public List<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (actorid != null ? actorid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Actor)) {
            return false;
        }
        Actor other = (Actor) object;
        if ((this.actorid == null && other.actorid != null) || (this.actorid != null && !this.actorid.equals(other.actorid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "JPA.Actor[ actorid=" + actorid + " ]";
    }
    
}
