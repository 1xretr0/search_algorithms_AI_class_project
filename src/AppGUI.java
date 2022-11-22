import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// this class manages the whole interface. could be invoked in the main flow at app class
public class AppGUI extends JFrame implements ItemListener {
    /* Variables declaration */
    // Global button panel and section panels declaration
    private static JPanel CBox_panel, button_panel1, button_panel2, buttons;

    // Button declaration
    private static JButton b_show, b_set_start, b_set_finish;

    // Label declaration
    private static JLabel label1, label2, label3;

    // Multiple option panel declaration
    private static JComboBox<String> select_combo, pattern_combo;

    // Map declaration
    private static Map map;

    // Algorithm declaration
    private static Algorithm algorithm;

    // Obstacles declaration
    private static Obstacles obstacles;

    /* Constructor of the AppGUI */
    // The constructor sets the window, initializes all the panels, textfields,
    // button, labels, combobox and canvas;
    // and adds all the components to the window
    public AppGUI() {
        // Set window
        super("Search Algorithms");
        setLayout(new BorderLayout());
        setSize(517, 661);
        addWindowListener(new CW());

        // set Algorithm
        algorithm = new Algorithm();

        // set Obstacles
        obstacles = new Obstacles();

        // Set Canvas
        map = new Map(algorithm, obstacles);

        // Set Combo box and labels panel
        CBox_panel = new JPanel(new FlowLayout(0, 20, 5));

        // Set buttons panel
        buttons = new JPanel(new GridLayout(2, 1));
        button_panel1 = new JPanel(new FlowLayout(1));
        button_panel2 = new JPanel(new FlowLayout(1, 100, 0));

        // Array of strings of select combo list
        String algorithm_list[] = {
                "Breadth Search",
                "Depth Search",
                "Hill Climbing Search",
                "Best First Search",
                "A* Search"
        };

        // Array of strings of patterns combo list
        String patterns_list[] = {
                "Empty",
                "Pattern 1",
                "Pattern 2",
                "Pattern 3"
        };

        // Create combo list
        select_combo = new JComboBox<String>(algorithm_list);
        select_combo.addItemListener(this);

        pattern_combo = new JComboBox<String>(patterns_list);
        pattern_combo.addItemListener(this);

        // Create labels
        label1 = new JLabel("Select the algorithm: ");
        label2 = new JLabel("Breadth Search selected.");
        label3 = new JLabel("Select obstacles pattern: ");

        // Add labels and combobox to Combo box panel
        CBox_panel.add(label1);
        CBox_panel.add(select_combo);
        CBox_panel.add(label2);

        // Add pattern combo to button panel
        button_panel1.add(label3);
        button_panel1.add(pattern_combo);
        // Create and add set buttons to button2 panel
        b_set_start = new JButton("Set Start");
        b_set_start.addActionListener(new BotonSetStart());
        b_set_start.addItemListener(this);
        button_panel2.add(b_set_start);

        b_set_finish = new JButton("Set Finish");
        b_set_finish.addActionListener(new BotonSetFinish());
        b_set_finish.addItemListener(this);
        button_panel2.add(b_set_finish);

        // Create and add show button to button1 panel
        b_show = new JButton("Show Algorithm");
        b_show.addActionListener(new BotonShow()); // add function call
        button_panel1.add(b_show);

        // Add Combo box panel to display (top)
        add(CBox_panel, "North");

        // Add map canvas to display (middle)
        add(map, "Center");

        // Add buttons (1 and 2) to buttons pannel
        buttons.add(button_panel2);
        buttons.add(button_panel1);

        // Add buttons panel to display (bottom)
        add(buttons, "South");

        // Display GUI
        setResizable(false);
        setVisible(true);

    }

    private class BotonShow implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String choice = select_combo.getSelectedItem().toString();

            // Calls the method depending on the chosen algorithm
            if (choice == "Breadth Search")
                algorithm.breadthSearch();
            else if (choice == "Depth Search")
                algorithm.depthSearch();
            else if (choice == "Hill Climbing Search")
                algorithm.hillSearch();
            else if (choice == "Best First Search")
                algorithm.bestSearch();
            else if (choice == "A* Search")
                algorithm.aSearch();

            // draw Path method to draw the path line
            map.drawPath();
        }
    }

    private class BotonSetStart implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            algorithm.setTrueIsStartSet();
        }
    }

    private class BotonSetFinish implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            algorithm.setTrueIsFinishSet();
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == select_combo) {
            label2.setText(select_combo.getSelectedItem() + " selected");
        }

        if (e.getSource() == pattern_combo) {
            if (pattern_combo.getSelectedItem().toString() == "Empty") {
                obstacles.setObstacles(0);
                algorithm.setStartPoint(0, 0);
                algorithm.setFinishPoint(9, 9);
            } else if (pattern_combo.getSelectedItem().toString() == "Pattern 1") {
                obstacles.setObstacles(1);
                algorithm.setStartPoint(0, 0);
                algorithm.setFinishPoint(9, 9);
            } else if (pattern_combo.getSelectedItem().toString() == "Pattern 2") {
                obstacles.setObstacles(2);
                algorithm.setStartPoint(0, 0);
                algorithm.setFinishPoint(9, 9);
            } else if (pattern_combo.getSelectedItem().toString() == "Pattern 3") {
                obstacles.setObstacles(3);
                algorithm.setStartPoint(0, 0);
                algorithm.setFinishPoint(9, 9);
            }

            map.repaint();
        }
    }

    // Class to manage window close button
    private class CW extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            setVisible(false);
            dispose();
        }
    }

    /* Main method to run the programm */
    public static void main(String[] args) {
        AppGUI gui = new AppGUI();
    }
}
