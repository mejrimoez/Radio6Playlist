/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FileSearch;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author moez
 */
public class PlaylistFileFilter extends FileFilter {

    private final String extension;
    private final String description;

    public PlaylistFileFilter() {
        this.extension = ".playlist";
        this.description = "Playlist Document";
    }

    @Override
    public boolean accept(File file) {
        if (file.isDirectory()) {
            return true;
        }
        return file.getName().endsWith(extension);
    }

    @Override
    public String getDescription() {
        return description + String.format(" (*%s)", extension);
    }
}
