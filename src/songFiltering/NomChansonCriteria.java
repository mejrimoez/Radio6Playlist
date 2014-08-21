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
public class NomChansonCriteria implements FilterCriteria {

    String nomChanson;

    public NomChansonCriteria(String nomChanson) {
        this.nomChanson = nomChanson;
    }

    @Override
    public boolean passes(Chanson o) {
        return ((Chanson) o).getNomChanson().equals(nomChanson);
    }

}
