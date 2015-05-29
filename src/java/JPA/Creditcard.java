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
@Table(name = "creditcard")
@NamedQueries({
    @NamedQuery(name = "Creditcard.findAll", query = "SELECT c FROM Creditcard c"),
    @NamedQuery(name = "Creditcard.findByCreditcardID", query = "SELECT c FROM Creditcard c WHERE c.creditcardID = :creditcardID"),
    @NamedQuery(name = "Creditcard.findByCardNumber", query = "SELECT c FROM Creditcard c WHERE c.cardNumber = :cardNumber"),
    @NamedQuery(name = "Creditcard.findByExpireDate", query = "SELECT c FROM Creditcard c WHERE c.expireDate = :expireDate"),
    @NamedQuery(name = "Creditcard.findByLastName", query = "SELECT c FROM Creditcard c WHERE c.lastName = :lastName"),
    @NamedQuery(name = "Creditcard.findByFirstName", query = "SELECT c FROM Creditcard c WHERE c.firstName = :firstName"),
    @NamedQuery(name = "Creditcard.findByMiddleInitial", query = "SELECT c FROM Creditcard c WHERE c.middleInitial = :middleInitial"),
    @NamedQuery(name = "Creditcard.findBySecurityCode", query = "SELECT c FROM Creditcard c WHERE c.securityCode = :securityCode"),
    @NamedQuery(name = "Creditcard.findByZipCode", query = "SELECT c FROM Creditcard c WHERE c.zipCode = :zipCode"),
    @NamedQuery(name = "Creditcard.findByCardType", query = "SELECT c FROM Creditcard c WHERE c.cardType = :cardType")})
public class Creditcard implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "creditcardID")
    private Integer creditcardID;
    @Basic(optional = false)
    @Column(name = "cardNumber")
    private double cardNumber;
    @Basic(optional = false)
    @Column(name = "expireDate")
    @Temporal(TemporalType.DATE)
    private Date expireDate;
    @Basic(optional = false)
    @Column(name = "lastName")
    private String lastName;
    @Basic(optional = false)
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "middleInitial")
    private Character middleInitial;
    @Basic(optional = false)
    @Column(name = "securityCode")
    private int securityCode;
    @Column(name = "zipCode")
    private Integer zipCode;
    @Basic(optional = false)
    @Column(name = "cardType")
    private String cardType;
    @JoinColumn(name = "userID", referencedColumnName = "userID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User userID;
    @OneToMany(mappedBy = "creditcardID", fetch = FetchType.LAZY)
    private List<Payment> paymentList;

    public Creditcard() {
    }

    public Creditcard(Integer creditcardID) {
        this.creditcardID = creditcardID;
    }

    public Creditcard(Integer creditcardID, double cardNumber, Date expireDate, String lastName, String firstName, int securityCode, String cardType) {
        this.creditcardID = creditcardID;
        this.cardNumber = cardNumber;
        this.expireDate = expireDate;
        this.lastName = lastName;
        this.firstName = firstName;
        this.securityCode = securityCode;
        this.cardType = cardType;
    }

    public Integer getCreditcardID() {
        return creditcardID;
    }

    public void setCreditcardID(Integer creditcardID) {
        this.creditcardID = creditcardID;
    }

    public double getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(double cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Character getMiddleInitial() {
        return middleInitial;
    }

    public void setMiddleInitial(Character middleInitial) {
        this.middleInitial = middleInitial;
    }

    public int getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(int securityCode) {
        this.securityCode = securityCode;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public User getUserID() {
        return userID;
    }

    public void setUserID(User userID) {
        this.userID = userID;
    }

    public List<Payment> getPaymentList() {
        return paymentList;
    }

    public void setPaymentList(List<Payment> paymentList) {
        this.paymentList = paymentList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (creditcardID != null ? creditcardID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Creditcard)) {
            return false;
        }
        Creditcard other = (Creditcard) object;
        if ((this.creditcardID == null && other.creditcardID != null) || (this.creditcardID != null && !this.creditcardID.equals(other.creditcardID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "JPA.Creditcard[ creditcardID=" + creditcardID + " ]";
    }
    
}
