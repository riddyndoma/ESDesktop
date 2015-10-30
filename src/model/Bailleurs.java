/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Dell
 */
@Entity
@Table(name = "bailleurs")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bailleurs.findAll", query = "SELECT b FROM Bailleurs b"),
    @NamedQuery(name = "Bailleurs.deleteBailleurByCodeAndNom", query = "DELETE FROM Bailleurs b WHERE b.code = :code AND b.nom = :nom"),
    @NamedQuery(name = "Bailleurs.findByBailleurid", query = "SELECT b FROM Bailleurs b WHERE b.bailleurid = :bailleurid"),
    @NamedQuery(name = "Bailleurs.findByCode", query = "SELECT b FROM Bailleurs b WHERE b.code = :code"),
    @NamedQuery(name = "Bailleurs.findByNom", query = "SELECT b FROM Bailleurs b WHERE b.nom = :nom"),
    @NamedQuery(name = "Bailleurs.findByAdresse", query = "SELECT b FROM Bailleurs b WHERE b.adresse = :adresse")})
public class Bailleurs implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "bailleurid")
    private Integer bailleurid;
    @Column(name = "code")
    private String code;
    @Column(name = "nom")
    private String nom;
    @Column(name = "adresse")
    private String adresse;
    @OneToMany(mappedBy = "bailleurid")
    private List<Entree> entreeList;

    public Bailleurs() {
    }

    public Bailleurs(Integer bailleurid) {
        this.bailleurid = bailleurid;
    }

    public Integer getBailleurid() {
        return bailleurid;
    }

    public void setBailleurid(Integer bailleurid) {
        this.bailleurid = bailleurid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @XmlTransient
    public List<Entree> getEntreeList() {
        return entreeList;
    }

    public void setEntreeList(List<Entree> entreeList) {
        this.entreeList = entreeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bailleurid != null ? bailleurid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bailleurs)) {
            return false;
        }
        Bailleurs other = (Bailleurs) object;
        if ((this.bailleurid == null && other.bailleurid != null) || (this.bailleurid != null && !this.bailleurid.equals(other.bailleurid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Bailleurs[ bailleurid=" + bailleurid + " ]";
    }
    
}
