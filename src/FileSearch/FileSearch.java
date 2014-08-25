/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FileSearch;

import CrudPanels.Chanson;
import CrudPanels.Chanteur;
import CrudPanels.ChanteurJpaController;
import CrudPanels.Genre;
import CrudPanels.GenreJpaController;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManagerFactory;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.Tag;
import javax.persistence.Persistence;
import org.jaudiotagger.tag.FieldKey;

public class FileSearch {

    private final List<Chanson> result = new ArrayList<>();
    private final EntityManagerFactory emf;

    public FileSearch() {
        this.emf = Persistence.createEntityManagerFactory("PersistanceUnit");
    }

    public List<Chanson> getResult() {
        return result;
    }

    public void searchDirectory(File directory) {
        if (directory.isDirectory()) {
            search(directory);
        } else {
            System.out.println(directory.getAbsoluteFile() + " is not a directory!");
        }

    }

    private void search(File file) {

        if (file.isDirectory()) {
            System.out.println("Searching directory ... " + file.getAbsoluteFile());

            //do you have permission to read this directory?
            if (file.canRead()) {
                for (File temp : file.listFiles()) {
                    Chanson c = new Chanson();
                    if (temp.isDirectory()) {
                        search(temp);
                    } else {
                        if (temp.getName().toLowerCase().endsWith(".mp3")
                                || temp.getName().toLowerCase().endsWith(".wav")
                                || temp.getName().toLowerCase().endsWith(".wma")) {

                            try {
                                Tag tag;
                                java.util.logging.Logger.getLogger("org.jaudiotagger").setLevel(Level.OFF);
                                AudioFile f = AudioFileIO.read(temp.getAbsoluteFile());
                                tag = f.getTag();

                                try {
                                    // longueur de la chanson en secondes !!!
                                    c.setLongueur(f.getAudioHeader().getTrackLength());
                                } catch (Exception e) {
                                }

                                try {
                                    // remplir le chanteur si introuvable et le sauvegarder ...
                                    ChanteurJpaController controller = new ChanteurJpaController(emf);
                                    if (controller.findChanteur(tag.getFirst(FieldKey.ARTIST)) == null) {
                                        controller.create(new Chanteur(tag.getFirst(FieldKey.ARTIST)));
                                    }
                                    c.setNomChanteur(new Chanteur(tag.getFirst(FieldKey.ARTIST)));
                                } catch (Exception e) {
                                }

                                try {
                                    String title = tag.getFirst(FieldKey.TITLE);
                                    if (!title.equals("null") && !title.equals(" ") && !title.equals("")) {
                                        // remplir le nom de la chanson
                                        c.setNomChanson(title);
                                    } else {
                                        c.setNomChanson(temp.getName().replace(".mp3", " "));
                                    }

                                } catch (Exception e) {
                                }

                                try {
                                    if (tag.getFirst(FieldKey.GENRE).matches("[^\\(\\) 0-9]+")) {
                                        // remplir le genre de la chanson
                                        GenreJpaController controller = new GenreJpaController(emf);
                                        if (controller.findGenre(tag.getFirst(FieldKey.GENRE)) == null) {
                                            controller.create(new Genre(tag.getFirst(FieldKey.GENRE)));
                                        }
                                        List<Genre> listGenres = new ArrayList<>();
                                        listGenres.add(new Genre(tag.getFirst(FieldKey.GENRE)));
                                        c.setGenres(listGenres);
                                    }
                                } catch (Exception e) {
                                }

                                try {
                                    // remplir la période
                                    c.setPeriode(Integer.parseInt(tag.getFirst(FieldKey.YEAR)));
                                } catch (Exception e) {
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            c.setCheminFichier(temp.getAbsolutePath());
                            c.setNomFichier(temp.getName());
                            if (c.getNomChanson() == null) {
                                c.setNomChanson("Inconnu");
                            }
                            if (c.getClassification() == null) {
                                c.setClassification(0);
                            }
                            result.add(c);

                        }
                    }
                }

            } else {
                System.out.println(file.getAbsoluteFile() + "Permission Denied");
            }
        }

    }

}
