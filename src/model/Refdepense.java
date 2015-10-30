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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "refdepense")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Refdepense.findAll", query = "SELECT r FROM Refdepense r"),
    @NamedQuery(name = "Refdepense.findByRefdepenseid", query = "SELECT r FROM Refdepense r WHERE r.refdepenseid = :refdepenseid"),
    @NamedQuery(name = "Refdepense.findByLibelle", query = "SELECT r FROM Refdepense r WHERE r.libelle = :libelle")})
public class Refdepense implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "refdepenseid")
    private Integer refdepenseid;
    @Column(name = "libelle")
    private String libelle;
    @OneToMany(mappedBy = "refdepenseid")
    private List<Depense> depenseList;
    @JoinColumn(name = "typeid", referencedColumnName = "typeid")
    @ManyToOne
    private Type typeid;

    public Refdepense() {
    }

    public Refdepense(Integer refdepenseid) {
        this.refdepenseid = refdepenseid;
    }

    public Integer getRefdepenseid() {
        return refdepenseid;
    }

    public void setRefdepenseid(Integer refdepenseid) {
        this.refdepenseid = refdepenseid;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @XmlTransient
    public List<Depense> getDepenseList() {
        return depenseList;
    }

    public void setDepenseList(List<Depense> depenseList) {
        this.depenseList = depenseList;
    }

    public Type getTypeid() {
        return typeid;
    }

    public void setTypeid(Type typeid) {
        this.typeid = typeid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (refdepenseid != null ? refdepenseid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Refdepense)) {
            return false;
        }
        Refdepense other = (Refdepense) object;
        if ((this.refdepenseid == null && other.refdepenseid != null) || (this.refdepenseid != null && !this.refdepenseid.equals(other.refdepenseid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Refdepense[ refdepenseid=" + refdepenseid + " ]";
    }
    
}
