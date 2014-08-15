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
@Table(name = "Chanteur", catalog = "radio6", schema = "")
@NamedQueries({
    @NamedQuery(name = "Chanteur.findAll", query = "SELECT c FROM Chanteur c"),
    @NamedQuery(name = "Chanteur.findByNomChanteur", query = "SELECT c FROM Chanteur c WHERE c.nomChanteur = :nomChanteur")})
public class Chanteur implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "nomChanteur")
    private String nomChanteur;

    public Chanteur() {
    }

    public Chanteur(String nomChanteur) {
        this.nomChanteur = nomChanteur;
    }

    public String getNomChanteur() {
        return nomChanteur;
    }

    public void setNomChanteur(String nomChanteur) {
        String oldNomChanteur = this.nomChanteur;
        this.nomChanteur = nomChanteur;
        changeSupport.firePropertyChange("nomChanteur", oldNomChanteur, nomChanteur);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nomChanteur != null ? nomChanteur.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Chanteur)) {
            return false;
        }
        Chanteur other = (Chanteur) object;
        if ((this.nomChanteur == null && other.nomChanteur != null) || (this.nomChanteur != null && !this.nomChanteur.equals(other.nomChanteur))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nomChanteur;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

}
