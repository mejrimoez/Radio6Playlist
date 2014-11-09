/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CrudPanels;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author moez
 */
@Entity
@Table(name = "Genre", catalog = "sql457399", schema = "")
@NamedQueries({
    @NamedQuery(name = "Genre.findAll", query = "SELECT g FROM Genre g"),
    @NamedQuery(name = "Genre.findByNomGenre", query = "SELECT g FROM Genre g WHERE g.nomGenre = :nomGenre")})
public class Genre implements Serializable, Comparable<Genre> {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "nomGenre")
    private String nomGenre;

    @ManyToMany(cascade = CascadeType.MERGE, mappedBy = "genres")
    private List<Chanson> chansons;

    public Genre() {
    }

    public Genre(String nomGenre) {
        this.nomGenre = nomGenre;
    }

    public String getNomGenre() {
        return nomGenre;
    }

    public void setNomGenre(String nomGenre) {
        String oldNomGenre = this.nomGenre;
        this.nomGenre = nomGenre;
        changeSupport.firePropertyChange("nomGenre", oldNomGenre, nomGenre);
    }

    public List<Chanson> getChansons() {
        return chansons;
    }

    public void setChansons(List<Chanson> chansons) {
        this.chansons = chansons;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nomGenre != null ? nomGenre.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Genre)) {
            return false;
        }
        Genre other = (Genre) object;
        if ((this.nomGenre == null && other.nomGenre != null) || (this.nomGenre != null && !this.nomGenre.equals(other.nomGenre))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nomGenre;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

    @Override
    public int compareTo(Genre o) {
        return getNomGenre().compareTo(o.getNomGenre());
    }

}
