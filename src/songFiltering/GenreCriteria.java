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
public class GenreCriteria implements FilterCriteria {

    private final String genre;

    public GenreCriteria(String genre) {
        this.genre = genre;
    }

    @Override
    public boolean passes(Object o) {
        return ((Chanson) o).getNomGenre().equals(genre);
    }

}
