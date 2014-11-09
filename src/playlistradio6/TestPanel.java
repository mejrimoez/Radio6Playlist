package playlistradio6;

import javax.swing.*;

/**
 * Created by moez on 09/11/14.
 */
public class TestPanel extends JFrame{
    public static void main(String[] args){
        new TestPanel();
    }

    public TestPanel(){
        super();
        this.add(new CustomComboBoxGroup());
        setLocationRelativeTo(null);
        setSize(600,400);
        setVisible(true);
    }


}
