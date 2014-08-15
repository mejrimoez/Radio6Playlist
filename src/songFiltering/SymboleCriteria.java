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
public class SymboleCriteria implements FilterCriteria {

    private String symbole;

    public SymboleCriteria(String symbole) {
        this.symbole = symbole;
    }

    @Override
    public boolean passes(Object o) {
        return ((Chanson) o).getNomSymbole().equals(symbole);
    }

}
