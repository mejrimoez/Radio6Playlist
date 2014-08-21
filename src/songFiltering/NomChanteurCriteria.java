/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package songFiltering;

import CrudPanels.Chanson;

/**
 *
 * @author moez
 */
public class NomChanteurCriteria implements FilterCriteria {

    private String nomChanteur;

    public NomChanteurCriteria(String nomChanteur) {
        this.nomChanteur = nomChanteur;
    }

    @Override
    public boolean passes(Chanson o) {
        return ((Chanson) o).getNomChanteur().equals(nomChanteur);
    }

}
