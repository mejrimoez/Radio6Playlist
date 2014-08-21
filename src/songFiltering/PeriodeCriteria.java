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
public class PeriodeCriteria implements FilterCriteria {

    private final int periode;

    public PeriodeCriteria(int periode) {
        this.periode = periode;
    }

    @Override
    public boolean passes(Chanson o) {
        return (((Chanson) o).getPeriode() == periode);
    }

}
