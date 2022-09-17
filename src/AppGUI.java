import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
// this class manages the whole interface. could be invoked in the main flow at app class
public class AppGUI extends JFrame{
    // global button panel and section panels declaration
    private JPanel buttons, select_algo, map;
    // text fields declaration
    private JTextField t_algo, t_map;
    // button declaration
    private JButton b_show;

    public AppGUI(){
        // set window
        super("Search Algorithms");
        addWindowListener(new CW());
        setSize(720, 480);

        // global button panel
        buttons = new JPanel();
        buttons.setLayout(new GridLayout(2,0));
        // TODO

        // select algorithm panel
        select_algo = new JPanel();
        select_algo.setLayout(new BorderLayout());
        select_algo.add(new JLabel("Select one Search Algorithm to show:"),
        "Center");

        // add panels to display
        add(buttons, "West");

        setResizable(false);
        setVisible(true);
    }
    private class CW extends WindowAdapter{
        public void windowClosing(WindowEvent e){
            setVisible(false);
            dispose();
        }
    }
}


