/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renderer;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import playlistradio6.StarRater;

/**
 *
 * @author moez
 */
public class StarsRenderer implements TableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        System.err.println(Integer.parseInt(String.valueOf(value)));
        StarRater star = new StarRater(Integer.parseInt(String.valueOf(value)));
        star.setOpaque(false);
        star.setEnabled(false);
        return star;
    }
}
