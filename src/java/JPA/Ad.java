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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author zeb
 */
@Entity
@Table(name = "ad")
@NamedQueries({
    @NamedQuery(name = "Ad.findAll", query = "SELECT a FROM Ad a"),
    @NamedQuery(name = "Ad.findByAdID", query = "SELECT a FROM Ad a WHERE a.adID = :adID"),
    @NamedQuery(name = "Ad.findByDataPath", query = "SELECT a FROM Ad a WHERE a.dataPath = :dataPath"),
    @NamedQuery(name = "Ad.findByAdWidth", query = "SELECT a FROM Ad a WHERE a.adWidth = :adWidth"),
    @NamedQuery(name = "Ad.findByAdHeight", query = "SELECT a FROM Ad a WHERE a.adHeight = :adHeight"),
    @NamedQuery(name = "Ad.findByAdType", query = "SELECT a FROM Ad a WHERE a.adType = :adType")})
public class Ad implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "adID")
    private Integer adID;
    @Column(name = "dataPath")
    private String dataPath;
    @Column(name = "adWidth")
    private Integer adWidth;
    @Column(name = "adHeight")
    private Integer adHeight;
    @Column(name = "adType")
    private String adType;

    public Ad() {
    }

    public Ad(Integer adID) {
        this.adID = adID;
    }

    public Integer getAdID() {
        return adID;
    }

    public void setAdID(Integer adID) {
        this.adID = adID;
    }

    public String getDataPath() {
        return dataPath;
    }

    public void setDataPath(String dataPath) {
        this.dataPath = dataPath;
    }

    public Integer getAdWidth() {
        return adWidth;
    }

    public void setAdWidth(Integer adWidth) {
        this.adWidth = adWidth;
    }

    public Integer getAdHeight() {
        return adHeight;
    }

    public void setAdHeight(Integer adHeight) {
        this.adHeight = adHeight;
    }

    public String getAdType() {
        return adType;
    }

    public void setAdType(String adType) {
        this.adType = adType;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (adID != null ? adID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ad)) {
            return false;
        }
        Ad other = (Ad) object;
        if ((this.adID == null && other.adID != null) || (this.adID != null && !this.adID.equals(other.adID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "JPA.Ad[ adID=" + adID + " ]";
    }
    
}
