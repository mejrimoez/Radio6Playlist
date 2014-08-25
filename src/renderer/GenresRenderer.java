/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renderer;

import CrudPanels.Genre;
import java.awt.Component;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author moez
 */
public class GenresRenderer implements TableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        List<Genre> liste = (List) value;
        String s = "";
        for (int i = 0; i < liste.size(); i++) {
            s += liste.get(i).getNomGenre();
            if (i < liste.size() - 1) {
                s += ",";
            }
        }
        return new JLabel(s);
    }

}
