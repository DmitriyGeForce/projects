/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.com.codefire.models;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author dmitriygeforce
 */
@Entity
@Table(name = "fishes", catalog = "new_schema", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"fish_name"})
    , @UniqueConstraint(columnNames = {"idfishes"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Fishes.findAll", query = "SELECT f FROM Fishes f")
    , @NamedQuery(name = "Fishes.findByIdfishes", query = "SELECT f FROM Fishes f WHERE f.idfishes = :idfishes")
    , @NamedQuery(name = "Fishes.findByFishName", query = "SELECT f FROM Fishes f WHERE f.fishName = :fishName")})
public class Fishes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idfishes", nullable = false)
    private Integer idfishes;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "fish_name", nullable = false, length = 45)
    private String fishName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idfishes")
    private List<Links> linksList;

    public Fishes() {
    }

    public Fishes(Integer idfishes) {
        this.idfishes = idfishes;
    }

    public Fishes(Integer idfishes, String fishName) {
        this.idfishes = idfishes;
        this.fishName = fishName;
    }

    public Integer getIdfishes() {
        return idfishes;
    }

    public void setIdfishes(Integer idfishes) {
        this.idfishes = idfishes;
    }

    public String getFishName() {
        return fishName;
    }

    public void setFishName(String fishName) {
        this.fishName = fishName;
    }

    @XmlTransient
    public List<Links> getLinksList() {
        return linksList;
    }

    public void setLinksList(List<Links> linksList) {
        this.linksList = linksList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idfishes != null ? idfishes.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fishes)) {
            return false;
        }
        Fishes other = (Fishes) object;
        if ((this.idfishes == null && other.idfishes != null) || (this.idfishes != null && !this.idfishes.equals(other.idfishes))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ua.com.codefire.models.Fishes[ idfishes=" + idfishes + " ]";
    }
    
}
