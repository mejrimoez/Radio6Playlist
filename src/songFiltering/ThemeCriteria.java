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
public class ThemeCriteria implements FilterCriteria {

    private String theme;

    public ThemeCriteria(String theme) {
        this.theme = theme;
    }

    @Override
    public boolean passes(Object o) {
        return ((Chanson) o).getNomTheme().equals(theme);
    }

}
