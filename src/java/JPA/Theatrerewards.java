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
@Table(name = "theatrerewards")
@NamedQueries({
    @NamedQuery(name = "Theatrerewards.findAll", query = "SELECT t FROM Theatrerewards t"),
    @NamedQuery(name = "Theatrerewards.findById", query = "SELECT t FROM Theatrerewards t WHERE t.id = :id"),
    @NamedQuery(name = "Theatrerewards.findByAmcReward", query = "SELECT t FROM Theatrerewards t WHERE t.amcReward = :amcReward"),
    @NamedQuery(name = "Theatrerewards.findByRegalReward", query = "SELECT t FROM Theatrerewards t WHERE t.regalReward = :regalReward"),
    @NamedQuery(name = "Theatrerewards.findByCobbReward", query = "SELECT t FROM Theatrerewards t WHERE t.cobbReward = :cobbReward"),
    @NamedQuery(name = "Theatrerewards.findByReadingReward", query = "SELECT t FROM Theatrerewards t WHERE t.readingReward = :readingReward"),
    @NamedQuery(name = "Theatrerewards.findByWehnbergReward", query = "SELECT t FROM Theatrerewards t WHERE t.wehnbergReward = :wehnbergReward"),
    @NamedQuery(name = "Theatrerewards.findByPennPerksReward", query = "SELECT t FROM Theatrerewards t WHERE t.pennPerksReward = :pennPerksReward"),
    @NamedQuery(name = "Theatrerewards.findBySouthernReelReward", query = "SELECT t FROM Theatrerewards t WHERE t.southernReelReward = :southernReelReward"),
    @NamedQuery(name = "Theatrerewards.findByCarmikeReward", query = "SELECT t FROM Theatrerewards t WHERE t.carmikeReward = :carmikeReward"),
    @NamedQuery(name = "Theatrerewards.findByCinemaWestReward", query = "SELECT t FROM Theatrerewards t WHERE t.cinemaWestReward = :cinemaWestReward")})
public class Theatrerewards implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "amcReward")
    private String amcReward;
    @Column(name = "regalReward")
    private String regalReward;
    @Column(name = "cobbReward")
    private String cobbReward;
    @Column(name = "readingReward")
    private String readingReward;
    @Column(name = "wehnbergReward")
    private String wehnbergReward;
    @Column(name = "pennPerksReward")
    private String pennPerksReward;
    @Column(name = "southernReelReward")
    private String southernReelReward;
    @Column(name = "carmikeReward")
    private String carmikeReward;
    @Column(name = "cinemaWestReward")
    private String cinemaWestReward;
    @JoinColumn(name = "userID", referencedColumnName = "userID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User userID;

    public Theatrerewards() {
    }

    public Theatrerewards(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAmcReward() {
        return amcReward;
    }

    public void setAmcReward(String amcReward) {
        this.amcReward = amcReward;
    }

    public String getRegalReward() {
        return regalReward;
    }

    public void setRegalReward(String regalReward) {
        this.regalReward = regalReward;
    }

    public String getCobbReward() {
        return cobbReward;
    }

    public void setCobbReward(String cobbReward) {
        this.cobbReward = cobbReward;
    }

    public String getReadingReward() {
        return readingReward;
    }

    public void setReadingReward(String readingReward) {
        this.readingReward = readingReward;
    }

    public String getWehnbergReward() {
        return wehnbergReward;
    }

    public void setWehnbergReward(String wehnbergReward) {
        this.wehnbergReward = wehnbergReward;
    }

    public String getPennPerksReward() {
        return pennPerksReward;
    }

    public void setPennPerksReward(String pennPerksReward) {
        this.pennPerksReward = pennPerksReward;
    }

    public String getSouthernReelReward() {
        return southernReelReward;
    }

    public void setSouthernReelReward(String southernReelReward) {
        this.southernReelReward = southernReelReward;
    }

    public String getCarmikeReward() {
        return carmikeReward;
    }

    public void setCarmikeReward(String carmikeReward) {
        this.carmikeReward = carmikeReward;
    }

    public String getCinemaWestReward() {
        return cinemaWestReward;
    }

    public void setCinemaWestReward(String cinemaWestReward) {
        this.cinemaWestReward = cinemaWestReward;
    }

    public User getUserID() {
        return userID;
    }

    public void setUserID(User userID) {
        this.userID = userID;
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
        if (!(object instanceof Theatrerewards)) {
            return false;
        }
        Theatrerewards other = (Theatrerewards) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "JPA.Theatrerewards[ id=" + id + " ]";
    }
    
}
