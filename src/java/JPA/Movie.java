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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "movie")
@NamedQueries({
    @NamedQuery(name = "Movie.findAll", query = "SELECT m FROM Movie m"),
    @NamedQuery(name = "Movie.findByMovieID", query = "SELECT m FROM Movie m WHERE m.movieID = :movieID"),
    @NamedQuery(name = "Movie.findByName", query = "SELECT m FROM Movie m WHERE m.name = :name"),
    @NamedQuery(name = "Movie.findByDateReleased", query = "SELECT m FROM Movie m WHERE m.dateReleased = :dateReleased"),
    @NamedQuery(name = "Movie.findByMpaaRating", query = "SELECT m FROM Movie m WHERE m.mpaaRating = :mpaaRating"),
    @NamedQuery(name = "Movie.findByGenre", query = "SELECT m FROM Movie m WHERE m.genre = :genre"),
    @NamedQuery(name = "Movie.findByMovieLength", query = "SELECT m FROM Movie m WHERE m.movieLength = :movieLength"),
    @NamedQuery(name = "Movie.findByUpcoming", query = "SELECT m FROM Movie m WHERE m.upcoming = :upcoming"),
    @NamedQuery(name = "Movie.findByWeekendGross", query = "SELECT m FROM Movie m WHERE m.weekendGross = :weekendGross"),
    @NamedQuery(name = "Movie.findByNumTheatres", query = "SELECT m FROM Movie m WHERE m.numTheatres = :numTheatres"),
    @NamedQuery(name = "Movie.findByTheatreAverage", query = "SELECT m FROM Movie m WHERE m.theatreAverage = :theatreAverage"),
    @NamedQuery(name = "Movie.findByWeeksReleased", query = "SELECT m FROM Movie m WHERE m.weeksReleased = :weeksReleased"),
    @NamedQuery(name = "Movie.findByTotalGrossed", query = "SELECT m FROM Movie m WHERE m.totalGrossed = :totalGrossed"),
    @NamedQuery(name = "Movie.findByPoster", query = "SELECT m FROM Movie m WHERE m.poster = :poster"),
    @NamedQuery(name = "Movie.findByTrailer", query = "SELECT m FROM Movie m WHERE m.trailer = :trailer")})
public class Movie implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "movieID")
    private Integer movieID;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Column(name = "dateReleased")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateReleased;
    @Column(name = "mpaaRating")
    private String mpaaRating;
    @Column(name = "genre")
    private String genre;
    @Column(name = "movieLength")
    private Integer movieLength;
    @Column(name = "upcoming")
    private Boolean upcoming;
    @Column(name = "weekendGross")
    private Integer weekendGross;
    @Column(name = "numTheatres")
    private Integer numTheatres;
    @Column(name = "theatreAverage")
    private Integer theatreAverage;
    @Column(name = "weeksReleased")
    private Integer weeksReleased;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "totalGrossed")
    private Double totalGrossed;
    @Column(name = "poster")
    private String poster;
    @Column(name = "trailer")
    private String trailer;
    @ManyToMany(mappedBy = "movieList", fetch = FetchType.LAZY)
    private List<Actor> actorList;
    @JoinTable(name = "favoritemovies", joinColumns = {
        @JoinColumn(name = "movieID", referencedColumnName = "movieID")}, inverseJoinColumns = {
        @JoinColumn(name = "userID", referencedColumnName = "userID")})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<User> userList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "movieID", fetch = FetchType.LAZY)
    private List<Ticket> ticketList;
    @OneToMany(mappedBy = "movieid", fetch = FetchType.LAZY)
    private List<Movieratings> movieratingsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "movieid", fetch = FetchType.LAZY)
    private List<Moviereview> moviereviewList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "movieID", fetch = FetchType.LAZY)
    private List<Showtime> showtimeList;
    @OneToMany(mappedBy = "movieid", fetch = FetchType.LAZY)
    private List<Comment> commentList;

    public Movie() {
    }

    public Movie(Integer movieID) {
        this.movieID = movieID;
    }

    public Movie(Integer movieID, String name) {
        this.movieID = movieID;
        this.name = name;
    }

    public Integer getMovieID() {
        return movieID;
    }

    public void setMovieID(Integer movieID) {
        this.movieID = movieID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateReleased() {
        return dateReleased;
    }

    public void setDateReleased(Date dateReleased) {
        this.dateReleased = dateReleased;
    }

    public String getMpaaRating() {
        return mpaaRating;
    }

    public void setMpaaRating(String mpaaRating) {
        this.mpaaRating = mpaaRating;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getMovieLength() {
        return movieLength;
    }

    public void setMovieLength(Integer movieLength) {
        this.movieLength = movieLength;
    }

    public Boolean getUpcoming() {
        return upcoming;
    }

    public void setUpcoming(Boolean upcoming) {
        this.upcoming = upcoming;
    }

    public Integer getWeekendGross() {
        return weekendGross;
    }

    public void setWeekendGross(Integer weekendGross) {
        this.weekendGross = weekendGross;
    }

    public Integer getNumTheatres() {
        return numTheatres;
    }

    public void setNumTheatres(Integer numTheatres) {
        this.numTheatres = numTheatres;
    }

    public Integer getTheatreAverage() {
        return theatreAverage;
    }

    public void setTheatreAverage(Integer theatreAverage) {
        this.theatreAverage = theatreAverage;
    }

    public Integer getWeeksReleased() {
        return weeksReleased;
    }

    public void setWeeksReleased(Integer weeksReleased) {
        this.weeksReleased = weeksReleased;
    }

    public Double getTotalGrossed() {
        return totalGrossed;
    }

    public void setTotalGrossed(Double totalGrossed) {
        this.totalGrossed = totalGrossed;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public List<Actor> getActorList() {
        return actorList;
    }

    public void setActorList(List<Actor> actorList) {
        this.actorList = actorList;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<Ticket> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }

    public List<Movieratings> getMovieratingsList() {
        return movieratingsList;
    }

    public void setMovieratingsList(List<Movieratings> movieratingsList) {
        this.movieratingsList = movieratingsList;
    }

    public List<Moviereview> getMoviereviewList() {
        return moviereviewList;
    }

    public void setMoviereviewList(List<Moviereview> moviereviewList) {
        this.moviereviewList = moviereviewList;
    }

    public List<Showtime> getShowtimeList() {
        return showtimeList;
    }

    public void setShowtimeList(List<Showtime> showtimeList) {
        this.showtimeList = showtimeList;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (movieID != null ? movieID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Movie)) {
            return false;
        }
        Movie other = (Movie) object;
        if ((this.movieID == null && other.movieID != null) || (this.movieID != null && !this.movieID.equals(other.movieID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "JPA.Movie[ movieID=" + movieID + " ]";
    }
    
}
