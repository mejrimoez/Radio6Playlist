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

import CrudPanels.Chanson;
import CrudPanels.Genre;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author moez
 */
public class CustomComboBoxGroup extends JPanel {

    private Vector<Integer> allNumbersList;
    private final Set<Genre> genresDispo;
    private final List<CustomComboBox<Integer>> listCombo;

    private final ItemListener itemListener = new ItemListener() {
        @Override
        public void itemStateChanged(ItemEvent e) {
            CustomComboBox<Integer> source = (CustomComboBox<Integer>) e.getSource();
            if (e.getStateChange() == ItemEvent.SELECTED) {

                source.setNouv_choix(Integer.parseInt(String.valueOf(e.getItem())));

                // on fait diminuer le nombre restant 
                // on met à jour les genres pas séléctionnés
                int ecart = source.getNouv_choix() - source.getAncien_choix();

                if (ecart < 0) {
                    // diminuer le nombre restant si < à nbMAX
                    if (CustomComboBox.somme - ecart >= 0) {
                        CustomComboBox.somme -= ecart;
                        // parcourir toutes les autres jcombo pour augmenter les choix
                        for (CustomComboBox ccb : listCombo) {
                            DefaultComboBoxModel comboModel = ((DefaultComboBoxModel) ccb.getModel());
                            if (comboModel.getSize() + ecart < CustomComboBox.taillePlaylist) {
                                for (int i = 0; i <= (-ecart); i++) {
                                    comboModel.addElement(i + comboModel.getSize());
                                }
                            }
                        }

                        // update text label 
//                        GestionPlaylistUI.updateLabel("Il vous reste " + (CustomComboBox.taillePlaylist - CustomComboBox.somme) + " chansons");
//                        jLabel31.setText("Il vous reste " + (CustomComboBox.taillePlaylist - CustomComboBox.somme) + " chansons");
                    } else {
                        JOptionPane.showMessageDialog(null, "Vous ne pouvez plus ajouter des chansons !", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                } else if (ecart > 0) {
                    // ajouter l'ecart à la somme 
                    if (CustomComboBox.somme + ecart <= CustomComboBox.taillePlaylist) {
                        CustomComboBox.somme += ecart;

                        // parcourir toutes les autres jcombo pour diminuer les choix
                        for (CustomComboBox ccb : listCombo) {
                            DefaultComboBoxModel comboModel = ((DefaultComboBoxModel) ccb.getModel());
                            if (comboModel.getSize() - ecart > 0) {
                                for (int i = 0; i < ecart; i++) {
                                    comboModel.removeElementAt(comboModel.getSize() - i - 1);
                                }
                            }
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Vous ne pouvez plus ajouter des chansons !", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }

                }

            } else if (e.getStateChange() == ItemEvent.DESELECTED) {
                source.setAncien_choix(Integer.parseInt(String.valueOf(e.getItem())));
            }
        }
    };

    public CustomComboBoxGroup() {
        super();

        // initialize numbers
        allNumbersList = new Vector<>();
        for (int i = 0; i <= 10; i++) {
            allNumbersList.add(i);
        }
        // init the list of genres 
        genresDispo = new TreeSet<>();
        for (Object chans : GestionPlaylistUI.listChansons) {
            genresDispo.addAll(((Chanson) chans).getGenres());
        }
        this.setLayout(new java.awt.GridLayout(genresDispo.size(), 2));

        // init the list of jcombobox 
        listCombo = new ArrayList<>();
        for (Genre c : genresDispo) {
            JLabel l = new JLabel(c.getNomGenre());
            CustomComboBox<Integer> combo = new CustomComboBox<>();
            this.add(l);
            this.add(combo);
            listCombo.add(combo);
        }
        this.revalidate();
        // init the itemListeners pour la liste des combos 
        for (CustomComboBox<Integer> cb : listCombo) {
            cb.addItemListener(itemListener);
        }

        // test 
        System.err.println(allNumbersList);
        System.err.println(genresDispo);
        System.err.println(this);

        this.setVisible(true);
    }

    public void changerTaillePlaylist(int nouvTaille) {
        // reinitialize the numbers 
        allNumbersList.removeAllElements();
        for (int i = 0; i <= nouvTaille; i++) {
            allNumbersList.add(i);
        }
        // update comboboxes 
        for (CustomComboBox<Integer> cb : listCombo) {
            cb = new CustomComboBox<>(allNumbersList);
        }

    }

}
