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
import javax.persistence.FetchType;
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
import javax.persistence.Transient;

/**
 *
 * @author moez
 */
@Entity
@Table(name = "Chanson", catalog = "sql457399", schema = "")
@NamedQueries({
    @NamedQuery(name = "Chanson.findAll", query = "SELECT c FROM Chanson c"),
    @NamedQuery(name = "Chanson.findByNumChanson", query = "SELECT c FROM Chanson c WHERE c.numChanson = :numChanson"),
    @NamedQuery(name = "Chanson.findByNomTheme", query = "SELECT c FROM Chanson c WHERE c.nomTheme = :nomTheme"),
    @NamedQuery(name = "Chanson.findByNomChanteur", query = "SELECT c FROM Chanson c WHERE c.nomChanteur = :nomChanteur"),
    @NamedQuery(name = "Chanson.findByNomSymbole", query = "SELECT c FROM Chanson c WHERE c.nomSymbole = :nomSymbole"),
    @NamedQuery(name = "Chanson.findByNomPays", query = "SELECT c FROM Chanson c WHERE c.nomPays = :nomPays"),
    @NamedQuery(name = "Chanson.findByNomChanson", query = "SELECT c FROM Chanson c WHERE c.nomChanson = :nomChanson"),
    @NamedQuery(name = "Chanson.findByPeriode", query = "SELECT c FROM Chanson c WHERE c.periode = :periode"),
    @NamedQuery(name = "Chanson.findByClassification", query = "SELECT c FROM Chanson c WHERE c.classification = :classification")})
public class Chanson implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numChanson")
    private Integer numChanson;
    @JoinColumn(name = "nomTheme", referencedColumnName = "nomTheme")
    @ManyToOne
    private Theme nomTheme;
    @JoinColumn(name = "nomChanteur", referencedColumnName = "nomChanteur")
    @ManyToOne
    private Chanteur nomChanteur;
    @JoinColumn(name = "nomSymbole", referencedColumnName = "nomSymbole")
    @ManyToOne
    private Symbole nomSymbole;
    @JoinColumn(name = "nomPays", referencedColumnName = "nomPays")
    @ManyToOne
    private Pays nomPays;

    @Column(name = "nomChanson")
    private String nomChanson;
    @Column(name = "periode")
    private Integer periode;
    @Column(name = "classification")
    private Integer classification;
    @Column(name = "nomFichier")
    private String nomFichier;
    @Column(name = "cheminFichier")
    private String cheminFichier;
    @Column(name = "longueur")
    private Integer longueur;

    @JoinTable(name = "Chanson_Genre", joinColumns = {
        @JoinColumn(name = "chansons_numChanson", referencedColumnName = "numChanson")
    }, inverseJoinColumns = {
        @JoinColumn(name = "genres_nomGenre", referencedColumnName = "nomGenre")
    })
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Genre> genres;

    public Chanson() {
    }

    public Chanson(Integer numChanson) {
        this.numChanson = numChanson;
    }

    public Integer getNumChanson() {
        return numChanson;
    }

    public void setNumChanson(Integer numChanson) {
        Integer oldNumChanson = this.numChanson;
        this.numChanson = numChanson;
        changeSupport.firePropertyChange("numChanson", oldNumChanson, numChanson);
    }

    public Theme getNomTheme() {
        return nomTheme;
    }

    public void setNomTheme(Theme nomTheme) {
        Theme oldNomTheme = this.nomTheme;
        this.nomTheme = nomTheme;
        changeSupport.firePropertyChange("nomTheme", oldNomTheme, nomTheme);
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        List<Genre> oldGenres = this.genres;
        this.genres = genres;
        changeSupport.firePropertyChange("nomGenre", oldGenres, genres);
    }

    public Chanteur getNomChanteur() {
        return nomChanteur;
    }

    public void setNomChanteur(Chanteur nomChanteur) {
        Chanteur oldNomChanteur = this.nomChanteur;
        this.nomChanteur = nomChanteur;
        changeSupport.firePropertyChange("nomChanteur", oldNomChanteur, nomChanteur);
    }

    public Symbole getNomSymbole() {
        return nomSymbole;
    }

    public void setNomSymbole(Symbole nomSymbole) {
        Symbole oldNomSymbole = this.nomSymbole;
        this.nomSymbole = nomSymbole;
        changeSupport.firePropertyChange("nomSymbole", oldNomSymbole, nomSymbole);
    }

    public String getCheminFichier() {
        return cheminFichier;
    }

    public void setCheminFichier(String cheminFichier) {
        String oldCheminFichier = this.cheminFichier;
        this.cheminFichier = cheminFichier;
        changeSupport.firePropertyChange("cheminFichier", oldCheminFichier, cheminFichier);
    }

    public String getNomFichier() {
        return nomFichier;
    }

    public void setNomFichier(String nomFichier) {
        String oldNomFichier = this.nomFichier;
        this.nomFichier = nomFichier;
        changeSupport.firePropertyChange("nomFichier", oldNomFichier, nomFichier);
    }

    public Pays getNomPays() {
        return nomPays;
    }

    public void setNomPays(Pays nomPays) {
        Pays oldNomPays = this.nomPays;
        this.nomPays = nomPays;
        changeSupport.firePropertyChange("nomPays", oldNomPays, nomPays);
    }

    public String getNomChanson() {
        return nomChanson;
    }

    public void setNomChanson(String nomChanson) {
        String oldNomChanson = this.nomChanson;
        this.nomChanson = nomChanson;
        changeSupport.firePropertyChange("nomChanson", oldNomChanson, nomChanson);
    }

    public Integer getPeriode() {
        return periode;
    }

    public void setPeriode(Integer periode) {
        Integer oldPeriode = this.periode;
        this.periode = periode;
        changeSupport.firePropertyChange("periode", oldPeriode, periode);
    }

    public Integer getClassification() {
        return classification;
    }

    public void setClassification(Integer classification) {
        Integer oldClassification = this.classification;
        this.classification = classification;
        changeSupport.firePropertyChange("classification", oldClassification, classification);
    }

    public Integer getLongueur() {
        return longueur;
    }

    public void setLongueur(Integer longueur) {
        Integer oldLongueur = this.longueur;
        this.longueur = longueur;
        changeSupport.firePropertyChange("longueur", oldLongueur, longueur);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numChanson != null ? numChanson.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Chanson)) {
            return false;
        }
        Chanson other = (Chanson) object;
        if ((this.nomFichier == null && other.nomFichier != null) || (this.nomFichier != null && !this.nomFichier.equals(other.nomFichier))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        if (longueur != null) {
            return nomChanson + " " + (longueur / 60) + ":" + (longueur % 60);
        }
        if (nomChanson != null) {
            return nomChanson;
        }
        return nomFichier;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

}
