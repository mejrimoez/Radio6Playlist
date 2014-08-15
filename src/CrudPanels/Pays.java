/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CrudPanels;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author moez
 */
@Entity
@Table(name = "Pays", catalog = "radio6", schema = "")
@NamedQueries({
    @NamedQuery(name = "Pays.findAll", query = "SELECT p FROM Pays p"),
    @NamedQuery(name = "Pays.findByNomPays", query = "SELECT p FROM Pays p WHERE p.nomPays = :nomPays")})
public class Pays implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "nomPays")
    private String nomPays;

    public Pays() {
    }

    public Pays(String nomPays) {
        this.nomPays = nomPays;
    }

    public String getNomPays() {
        return nomPays;
    }

    public void setNomPays(String nomPays) {
        String oldNomPays = this.nomPays;
        this.nomPays = nomPays;
        changeSupport.firePropertyChange("nomPays", oldNomPays, nomPays);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nomPays != null ? nomPays.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pays)) {
            return false;
        }
        Pays other = (Pays) object;
        if ((this.nomPays == null && other.nomPays != null) || (this.nomPays != null && !this.nomPays.equals(other.nomPays))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nomPays;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

}
