import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
// this class manages the whole interface. could be invoked in the main flow at app class
public class AppGUI extends JFrame implements ItemListener{
    private static JFrame frame;
    // global button panel and section panels declaration
    private static JPanel button_panel, select_algo, map;
    // text fields declaration
    private static JTextField t_algo, t_map;
    // button declaration
    private static JButton b_show;
    // lavel declaration
    private static JLabel l, l1;
    private static JComboBox select_combo;

    public static void main(String[] args){
        // set window
        frame = new JFrame("frame");
        frame.setLayout(new FlowLayout());
        frame.setSize(720, 480);
        // frame.addWindowListener(new CW());

        AppGUI gui = new AppGUI();

        // global button panel
        button_panel = new JPanel();
        // button_panel.setLayout(new GridLayout(2,0));

        // select algorithm panel
        // select_algo = new JPanel();
        // select_algo.setLayout(new BorderLayout());
        // select_algo.add(new JLabel("Select one Search Algorithm to show:"),
        // "Center");

        // add select panel to button panel
        // button_panel.add(select_algo);

        // array of strings of select combo list
        String algorithm_list[] = {
            "Depth Search",
            "Breadth Search",
            "Hill Climbing Search",
            "Best First Search",
            "A* Search"
        };

        select_combo = new JComboBox(algorithm_list);
        select_combo.addItemListener(gui);

        // create labels
        l = new JLabel("Select the algorithm: ");
        l1 = new JLabel("Depth Search selected.");

        // add label to panel
        button_panel.add(l);
        // add combobox to panel
        button_panel.add(select_combo);
        button_panel.add(l1);

        // add panels to display
        frame.add(button_panel);

        // display gui
        frame.setResizable(false);
        frame.setVisible(true);

    }
    // class to manage window close button
    private class CW extends WindowAdapter{
        public void windowClosing(WindowEvent e){
            frame.setVisible(false);
            frame.dispose();
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == select_combo){
            l1.setText(select_combo.getSelectedItem() + " selected");
        }
    }
}


