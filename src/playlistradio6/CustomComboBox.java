/*
 * Copyright (C) 2014 moez
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

package playlistradio6;

import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

/**
 *
 * @author moez
 * @param <E>
 */
public class CustomComboBox<E extends Integer> extends JComboBox<E>{

    private Vector<E> liste;
    private Integer nouv_choix;
    private Integer ancien_choix;
    // default value will be changed in UI
    public static int taillePlaylist = 10;
    public static int somme = 0;
    
    
    public CustomComboBox() {
        super();
        nouv_choix = 0;
        ancien_choix = 0;
        // create and populate list 
        liste = new Vector<>();
        for (int i=0;i<=taillePlaylist;i++)
            liste.add((E) new Integer(i));
        setModel(new DefaultComboBoxModel<>(liste));
        setVisible(true);
    }
    
    public CustomComboBox(Vector<E> list){
        super();
        setModel(new DefaultComboBoxModel<>(list));
    }

    public Integer getNouv_choix() {
        return nouv_choix;
    }

    public void setNouv_choix(Integer nouv_choix) {
        this.nouv_choix = nouv_choix;
    }

    public Integer getAncien_choix() {
        return ancien_choix;
    }

    public void setAncien_choix(Integer ancien_choix) {
        this.ancien_choix = ancien_choix;
    }

    public Vector<E> getListe() {
        return liste;
    }

    public void setListe(Vector<E> liste) {
        this.liste = liste;
    }
    
    
}
