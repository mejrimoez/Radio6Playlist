/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package songFiltering;

import CrudPanels.Chanson;
import java.util.Objects;

/**
 *
 * @author moez
 */
public class RatingCriteria implements FilterCriteria {

    private Integer rating;

    public RatingCriteria(Integer rating) {
        this.rating = rating;
    }

    @Override
    public boolean passes(Chanson o) {
        return Objects.equals(((Chanson) o).getClassification(), rating);
    }

}
