package playlistradio6;

import javax.swing.*;

/**
 * Created by moez on 09/11/14.
 */
public class TestPanel extends JDialog{
    public static void main(String[] args){
        new GestionPlaylistUI();
        new TestPanel();
    }

    public TestPanel(){
        super();
        this.add(new CustomComboBoxGroup());
        setLocationRelativeTo(null);
        setSize(200,200);
        setVisible(true);
    }


}
