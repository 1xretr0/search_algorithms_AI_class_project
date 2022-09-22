import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// this class manages the whole interface. could be invoked in the main flow at app class
public class AppGUI extends JFrame implements ItemListener{
    /* Variables declaration */
    // Global button panel and section panels declaration
    private static JPanel CBox_panel, button_panel;

    // Button declaration
    private static JButton b_show;

    // Label declaration
    private static JLabel label1, label2;

    // Multiple option panel declaration
    private static JComboBox<String> select_combo;

    // Map declaration
    private static Map map;

    // Algorithm declaration
    private static Algorithm algorithm;

    /* Constructor of the AppGUI */
    // The constructor sets the window, initializes all the panels, textfields, button, labels, combobox and canvas;
    // and adds all the components to the window
    public AppGUI(){
        // Set window
        super("Search Algorithms");
        setLayout(new BorderLayout());
        setSize(517, 611);
        addWindowListener(new CW());

        // set Algorithm
        algorithm = new Algorithm();

        // Set Canvas
        map = new Map(algorithm);

        // Set Combo box and labels panel
        CBox_panel = new JPanel(new FlowLayout(0, 20, 5));

        // Set button panel
        button_panel = new JPanel(new FlowLayout(1));

        // Array of strings of select combo list
        String algorithm_list[] = {
            "Depth Search",
            "Breadth Search",
            "Hill Climbing Search",
            "Best First Search",
            "A* Search"
        };

        // Create combo list
        select_combo = new JComboBox<String>(algorithm_list);
        select_combo.addItemListener(this);

        // Create labels
        label1 = new JLabel("Select the algorithm: ");
        label2 = new JLabel("Depth Search selected.");

        // Add labels and combobox to Combo box panel
        CBox_panel.add(label1);
        CBox_panel.add(select_combo);
        CBox_panel.add(label2);

        // Create and add button to button panel
        b_show = new JButton("Show Algorithm");
        b_show.addActionListener(new BotonShow()); // add function call
        button_panel.add(b_show);

        // Add Combo box panel to display (top)
        add(CBox_panel, "North");

        // Add map canvas to display (middle)
        add(map, "Center");

        // Add button panel to display (bottom)
        add(button_panel, "South");

        // Display GUI
        setResizable(false);
        setVisible(true);

    }

    private class BotonShow implements ActionListener{
        public void actionPerformed(ActionEvent e){
            String choice = select_combo.getSelectedItem().toString();

            // Calls the method depending on the chosen algorithm
            if (choice == "Depth Search"){
                algorithm.breathSearch();
            }
            else{
                return;
            }

            // draw Path method to draw the path line
            map.drawPath();
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == select_combo){
            label2.setText(select_combo.getSelectedItem() + " selected");
        }
    }

    // Class to manage window close button
    private class CW extends WindowAdapter{
        public void windowClosing(WindowEvent e){
            setVisible(false);
            dispose();
        }
    }

    /* Main method to run the programm */
    public static void main(String[] args){
        AppGUI gui = new AppGUI();
    }
}
