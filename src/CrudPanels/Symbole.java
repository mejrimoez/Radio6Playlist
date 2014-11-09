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
@Table(name = "Symbole", catalog = "sql457399", schema = "")
@NamedQueries({
    @NamedQuery(name = "Symbole.findAll", query = "SELECT s FROM Symbole s"),
    @NamedQuery(name = "Symbole.findByNomSymbole", query = "SELECT s FROM Symbole s WHERE s.nomSymbole = :nomSymbole")})
public class Symbole implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "nomSymbole")
    private String nomSymbole;

    public Symbole() {
    }

    public Symbole(String nomSymbole) {
        this.nomSymbole = nomSymbole;
    }

    public String getNomSymbole() {
        return nomSymbole;
    }

    public void setNomSymbole(String nomSymbole) {
        String oldNomSymbole = this.nomSymbole;
        this.nomSymbole = nomSymbole;
        changeSupport.firePropertyChange("nomSymbole", oldNomSymbole, nomSymbole);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nomSymbole != null ? nomSymbole.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Symbole)) {
            return false;
        }
        Symbole other = (Symbole) object;
        if ((this.nomSymbole == null && other.nomSymbole != null) || (this.nomSymbole != null && !this.nomSymbole.equals(other.nomSymbole))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nomSymbole;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

}
