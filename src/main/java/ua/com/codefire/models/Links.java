/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.com.codefire.models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dmitriygeforce
 */
@Entity
@Table(name = "links", catalog = "new_schema", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"idlinks"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Links.findAll", query = "SELECT l FROM Links l")
    , @NamedQuery(name = "Links.findByIdFishes", query = "SELECT l FROM Links l WHERE l.idfishes = :idfishes")
    , @NamedQuery(name = "Links.findByIdlinks", query = "SELECT l FROM Links l WHERE l.idlinks = :idlinks")})
public class Links implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idlinks", nullable = false)
    private Integer idlinks;
    @JoinColumn(name = "idplaces", referencedColumnName = "idplaces", nullable = false)
    @ManyToOne(optional = false)
    private Places idplaces;
    @JoinColumn(name = "idfishes", referencedColumnName = "idfishes", nullable = false)
    @ManyToOne(optional = false)
    private Fishes idfishes;

    public Links() {
    }

    public Links(Integer idlinks) {
        this.idlinks = idlinks;
    }

    public Integer getIdlinks() {
        return idlinks;
    }

    public void setIdlinks(Integer idlinks) {
        this.idlinks = idlinks;
    }

    public Places getIdplaces() {
        return idplaces;
    }

    public void setIdplaces(Places idplaces) {
        this.idplaces = idplaces;
    }

    public Fishes getIdfishes() {
        return idfishes;
    }

    public Links(Places idplaces, Fishes idfishes) {
        this.idplaces = idplaces;
        this.idfishes = idfishes;
    }

    public void setIdfishes(Fishes idfishes) {
        this.idfishes = idfishes;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idlinks != null ? idlinks.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Links)) {
            return false;
        }
        Links other = (Links) object;
        if ((this.idlinks == null && other.idlinks != null) || (this.idlinks != null && !this.idlinks.equals(other.idlinks))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Links{" + "idlinks=" + idlinks + ", idplaces=" + idplaces + ", idfishes=" + idfishes + '}';
    }

    
    
}
