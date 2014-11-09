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
@Table(name = "Theme", catalog = "sql457399", schema = "")
@NamedQueries({
    @NamedQuery(name = "Theme.findAll", query = "SELECT t FROM Theme t"),
    @NamedQuery(name = "Theme.findByNomTheme", query = "SELECT t FROM Theme t WHERE t.nomTheme = :nomTheme")})
public class Theme implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "nomTheme")
    private String nomTheme;

    public Theme() {
    }

    public Theme(String nomTheme) {
        this.nomTheme = nomTheme;
    }

    public String getNomTheme() {
        return nomTheme;
    }

    public void setNomTheme(String nomTheme) {
        String oldNomTheme = this.nomTheme;
        this.nomTheme = nomTheme;
        changeSupport.firePropertyChange("nomTheme", oldNomTheme, nomTheme);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nomTheme != null ? nomTheme.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Theme)) {
            return false;
        }
        Theme other = (Theme) object;
        if ((this.nomTheme == null && other.nomTheme != null) || (this.nomTheme != null && !this.nomTheme.equals(other.nomTheme))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nomTheme;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

}
