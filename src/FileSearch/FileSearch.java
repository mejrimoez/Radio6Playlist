/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FileSearch;

import CrudPanels.Chanson;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileSearch {

    private List<Chanson> result = new ArrayList<>();

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
                            c.setCheminFichier(temp.getAbsolutePath());
                            c.setNomFichier(temp.getName());
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
