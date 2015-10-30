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
import javax.persistence.JoinTable;
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
@Table(name = "depense")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Depense.findAll", query = "SELECT d FROM Depense d"),
    @NamedQuery(name = "Depense.findByDepenseid", query = "SELECT d FROM Depense d WHERE d.depenseid = :depenseid"),
    @NamedQuery(name = "Depense.findByMontant", query = "SELECT d FROM Depense d WHERE d.montant = :montant"),
    @NamedQuery(name = "Depense.findByDatedepense", query = "SELECT d FROM Depense d WHERE d.datedepense = :datedepense")})
public class Depense implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "depenseid")
    private Integer depenseid;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "montant")
    private Double montant;
    @Column(name = "datedepense")
    @Temporal(TemporalType.DATE)
    private Date datedepense;
    @JoinTable(name = "concerner", joinColumns = {
        @JoinColumn(name = "depenseid", referencedColumnName = "depenseid")}, inverseJoinColumns = {
        @JoinColumn(name = "entreeid", referencedColumnName = "entreeid")})
    @ManyToMany
    private List<Entree> entreeList;
    @JoinColumn(name = "refdepenseid", referencedColumnName = "refdepenseid")
    @ManyToOne
    private Refdepense refdepenseid;

    public Depense() {
    }

    public Depense(Integer depenseid) {
        this.depenseid = depenseid;
    }

    public Integer getDepenseid() {
        return depenseid;
    }

    public void setDepenseid(Integer depenseid) {
        this.depenseid = depenseid;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public Date getDatedepense() {
        return datedepense;
    }

    public void setDatedepense(Date datedepense) {
        this.datedepense = datedepense;
    }

    @XmlTransient
    public List<Entree> getEntreeList() {
        return entreeList;
    }

    public void setEntreeList(List<Entree> entreeList) {
        this.entreeList = entreeList;
    }

    public Refdepense getRefdepenseid() {
        return refdepenseid;
    }

    public void setRefdepenseid(Refdepense refdepenseid) {
        this.refdepenseid = refdepenseid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (depenseid != null ? depenseid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Depense)) {
            return false;
        }
        Depense other = (Depense) object;
        if ((this.depenseid == null && other.depenseid != null) || (this.depenseid != null && !this.depenseid.equals(other.depenseid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Depense[ depenseid=" + depenseid + " ]";
    }
    
}
