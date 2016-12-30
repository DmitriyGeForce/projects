/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.com.codefire.models;

import com.fasterxml.jackson.annotation.JsonView;
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
@Table(name = "places", catalog = "new_schema", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"idplaces"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Places.findAll", query = "SELECT p FROM Places p")
    , @NamedQuery(name = "Places.findByIdplaces", query = "SELECT p FROM Places p WHERE p.idplaces = :idplaces")
    , @NamedQuery(name = "Places.findByPlaceName", query = "SELECT p FROM Places p WHERE p.placeName LIKE :placeName")
    , @NamedQuery(name = "Places.findByCoord", query = "SELECT p FROM Places p WHERE p.placeGeoLong BETWEEN :param1 AND :param2 AND p.placeGeoLat BETWEEN :param3 AND :param4")
    , @NamedQuery(name = "Places.findByPlaceGeoLat", query = "SELECT p FROM Places p WHERE p.placeGeoLat = :placeGeoLat")
    , @NamedQuery(name = "Places.findByPlaceGeoLong", query = "SELECT p FROM Places p WHERE p.placeGeoLong = :placeGeoLong")
    , @NamedQuery(name = "Places.findByPlaceDescript", query = "SELECT p FROM Places p WHERE p.placeDescript = :placeDescript")})
public class Places implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idplaces", nullable = false)
    @JsonView(View.Summary.class)
    private Integer idplaces;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1024)
    @Column(name = "place_name", nullable = false, length = 1024)
    @JsonView(View.Summary.class)
    private String placeName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "place_geo_lat", nullable = false, length = 45)
    @JsonView(View.Summary.class)
    private String placeGeoLat;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "place_geo_long", nullable = false, length = 45)
    @JsonView(View.Summary.class)
    private String placeGeoLong;
    @Size(max = 45)
    @Column(name = "place_descript", length = 45)
    @JsonView(View.Summary.class)
    private String placeDescript;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idplaces")
    private List<Links> linksList;

    public Places() {
    }

    public Places(Integer idplaces) {
        this.idplaces = idplaces;
    }

    public Places(Integer idplaces, String placeName, String placeGeoLat, String placeGeoLong) {
        this.idplaces = idplaces;
        this.placeName = placeName;
        this.placeGeoLat = placeGeoLat;
        this.placeGeoLong = placeGeoLong;
    }

    public Places(String placeName) {
        this.placeName = placeName;
    }

    public void setIdplaces(Integer idplaces) {
        this.idplaces = idplaces;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getPlaceGeoLat() {
        return placeGeoLat;
    }

    public void setPlaceGeoLat(String placeGeoLat) {
        this.placeGeoLat = placeGeoLat;
    }

    public String getPlaceGeoLong() {
        return placeGeoLong;
    }

    public void setPlaceGeoLong(String placeGeoLong) {
        this.placeGeoLong = placeGeoLong;
    }

    public String getPlaceDescript() {
        return placeDescript;
    }

    public void setPlaceDescript(String placeDescript) {
        this.placeDescript = placeDescript;
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
        hash += (idplaces != null ? idplaces.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Places)) {
            return false;
        }
        Places other = (Places) object;
        if ((this.idplaces == null && other.idplaces != null) || (this.idplaces != null && !this.idplaces.equals(other.idplaces))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ua.com.codefire.models.Places[ idplaces=" + idplaces + " ]";
    }
    
}
