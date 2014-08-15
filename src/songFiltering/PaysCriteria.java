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
public class PaysCriteria implements FilterCriteria {

    private String pays;

    public PaysCriteria(String pays) {
        this.pays = pays;
    }

    @Override
    public boolean passes(Object o) {
        return ((Chanson) o).getNomPays().equals(pays);
    }

}
