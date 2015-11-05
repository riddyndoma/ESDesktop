/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Dell
 */
@Entity
@Table(name = "entree")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Entree.findAll", query = "SELECT e FROM Entree e"),
    @NamedQuery(name = "Entree.findByEntreeid", query = "SELECT e FROM Entree e WHERE e.entreeid = :entreeid"),
    @NamedQuery(name = "Entree.findByLibelle", query = "SELECT e FROM Entree e WHERE e.libelle = :libelle"),
    @NamedQuery(name = "Entree.deleteByLibelleAndMontant", query = "DELETE FROM Entree e WHERE e.libelle = :libelle AND e.montant = :montant"),
    @NamedQuery(name = "Entree.findByMontant", query = "SELECT e FROM Entree e WHERE e.montant = :montant"),
    @NamedQuery(name = "Entree.findByDateentree", query = "SELECT e FROM Entree e WHERE e.dateentree = :dateentree")})
public class Entree implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "entreeid")
    private Integer entreeid;
    @Column(name = "libelle")
    private String libelle;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "montant")
    private Double montant;
    @Column(name = "dateentree")
    @Temporal(TemporalType.DATE)
    private Date dateentree;
    @ManyToMany(mappedBy = "entreeList")
    private List<Depense> depenseList;
    @JoinColumn(name = "bailleurid", referencedColumnName = "bailleurid")
    @ManyToOne
    private Bailleurs bailleurid;

    public Entree() {
    }

    public Entree(Integer entreeid) {
        this.entreeid = entreeid;
    }

    public Integer getEntreeid() {
        return entreeid;
    }

    public void setEntreeid(Integer entreeid) {
        this.entreeid = entreeid;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public Date getDateentree() {
        return dateentree;
    }

    public void setDateentree(Date dateentree) {
        this.dateentree = dateentree;
    }

    @XmlTransient
    public List<Depense> getDepenseList() {
        return depenseList;
    }

    public void setDepenseList(List<Depense> depenseList) {
        this.depenseList = depenseList;
    }

    public Bailleurs getBailleurid() {
        return bailleurid;
    }

    public void setBailleurid(Bailleurs bailleurid) {
        this.bailleurid = bailleurid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (entreeid != null ? entreeid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Entree)) {
            return false;
        }
        Entree other = (Entree) object;
        if ((this.entreeid == null && other.entreeid != null) || (this.entreeid != null && !this.entreeid.equals(other.entreeid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Entree[ entreeid=" + entreeid + " ]";
    }

}
