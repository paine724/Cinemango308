/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPA;

import java.io.Serializable;
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

/**
 *
 * @author zeb
 */
@Entity
@Table(name = "theatre")
@NamedQueries({
    @NamedQuery(name = "Theatre.findAll", query = "SELECT t FROM Theatre t"),
    @NamedQuery(name = "Theatre.findById", query = "SELECT t FROM Theatre t WHERE t.id = :id"),
    @NamedQuery(name = "Theatre.findByName", query = "SELECT t FROM Theatre t WHERE t.name = :name"),
    @NamedQuery(name = "Theatre.findByAddress", query = "SELECT t FROM Theatre t WHERE t.address = :address"),
    @NamedQuery(name = "Theatre.findByState", query = "SELECT t FROM Theatre t WHERE t.state = :state"),
    @NamedQuery(name = "Theatre.findByZipcode", query = "SELECT t FROM Theatre t WHERE t.zipcode = :zipcode"),
    @NamedQuery(name = "Theatre.findByAmenities", query = "SELECT t FROM Theatre t WHERE t.amenities = :amenities"),
    @NamedQuery(name = "Theatre.findByChain", query = "SELECT t FROM Theatre t WHERE t.chain = :chain"),
    @NamedQuery(name = "Theatre.findByCity", query = "SELECT t FROM Theatre t WHERE t.city = :city"),
    @NamedQuery(name = "Theatre.findByLatitude", query = "SELECT t FROM Theatre t WHERE t.latitude = :latitude"),
    @NamedQuery(name = "Theatre.findByLongitude", query = "SELECT t FROM Theatre t WHERE t.longitude = :longitude")})
public class Theatre implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    @Column(name = "state")
    private String state;
    @Column(name = "zipcode")
    private Integer zipcode;
    @Column(name = "amenities")
    private String amenities;
    @Column(name = "chain")
    private String chain;
    @Column(name = "city")
    private String city;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "latitude")
    private Float latitude;
    @Column(name = "longitude")
    private Float longitude;
    @JoinTable(name = "favoritetheatres", joinColumns = {
        @JoinColumn(name = "theatreID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "userID", referencedColumnName = "userID")})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<User> userList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "theatreID", fetch = FetchType.LAZY)
    private List<Ticket> ticketList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "theatreID", fetch = FetchType.LAZY)
    private List<Showtime> showtimeList;

    public Theatre() {
    }

    public Theatre(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getZipcode() {
        return zipcode;
    }

    public void setZipcode(Integer zipcode) {
        this.zipcode = zipcode;
    }

    public String getAmenities() {
        return amenities;
    }

    public void setAmenities(String amenities) {
        this.amenities = amenities;
    }

    public String getChain() {
        return chain;
    }

    public void setChain(String chain) {
        this.chain = chain;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
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

    public List<Showtime> getShowtimeList() {
        return showtimeList;
    }

    public void setShowtimeList(List<Showtime> showtimeList) {
        this.showtimeList = showtimeList;
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
        if (!(object instanceof Theatre)) {
            return false;
        }
        Theatre other = (Theatre) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "JPA.Theatre[ id=" + id + " ]";
    }
    
}
