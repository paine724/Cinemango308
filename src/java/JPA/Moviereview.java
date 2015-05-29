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
@Table(name = "moviereview")
@NamedQueries({
    @NamedQuery(name = "Moviereview.findAll", query = "SELECT m FROM Moviereview m"),
    @NamedQuery(name = "Moviereview.findByReviewID", query = "SELECT m FROM Moviereview m WHERE m.reviewID = :reviewID"),
    @NamedQuery(name = "Moviereview.findByRating", query = "SELECT m FROM Moviereview m WHERE m.rating = :rating"),
    @NamedQuery(name = "Moviereview.findByReview", query = "SELECT m FROM Moviereview m WHERE m.review = :review"),
    @NamedQuery(name = "Moviereview.findByNumHelpful", query = "SELECT m FROM Moviereview m WHERE m.numHelpful = :numHelpful"),
    @NamedQuery(name = "Moviereview.findByFlagged", query = "SELECT m FROM Moviereview m WHERE m.flagged = :flagged")})
public class Moviereview implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "reviewID")
    private Integer reviewID;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "rating")
    private Double rating;
    @Column(name = "review")
    private String review;
    @Column(name = "numHelpful")
    private Integer numHelpful;
    @Column(name = "flagged")
    private Boolean flagged;
    @JoinColumn(name = "movieid", referencedColumnName = "movieID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Movie movieid;
    @JoinColumn(name = "userid", referencedColumnName = "userID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User userid;

    public Moviereview() {
    }

    public Moviereview(Integer reviewID) {
        this.reviewID = reviewID;
    }

    public Integer getReviewID() {
        return reviewID;
    }

    public void setReviewID(Integer reviewID) {
        this.reviewID = reviewID;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Integer getNumHelpful() {
        return numHelpful;
    }

    public void setNumHelpful(Integer numHelpful) {
        this.numHelpful = numHelpful;
    }

    public Boolean getFlagged() {
        return flagged;
    }

    public void setFlagged(Boolean flagged) {
        this.flagged = flagged;
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
        hash += (reviewID != null ? reviewID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Moviereview)) {
            return false;
        }
        Moviereview other = (Moviereview) object;
        if ((this.reviewID == null && other.reviewID != null) || (this.reviewID != null && !this.reviewID.equals(other.reviewID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "JPA.Moviereview[ reviewID=" + reviewID + " ]";
    }
    
}
