
// this class creates the graphic map where the chosen algorithm will work
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import java.lang.Math;

public class Map extends Canvas implements Runnable, MouseListener {
    /* Variables declaration */
    // Boolean to draw vector points on paint method (false)
    private boolean draw_path = false;


    // Thread to convert Canvas into a Thread for the GUI
    private Thread thread;

    // Algorithm variable to invoke Vector and Array of Arrays methods
    private Algorithm algorithm;

    // Obstacles variable to draw the obstacles on the canvas
    private Obstacles obstacles;

    public Map(Algorithm algorithm, Obstacles obstacles) {
        this.algorithm = algorithm;
        this.obstacles = obstacles;
        addMouseListener(this);
    }

    public void mouseClicked(MouseEvent e) {
    }
    
    public void mouseClicked(MouseEvent e) {
    }  
    public void mouseEntered(MouseEvent e) {
    }  
    public void mouseExited(MouseEvent e) {
    }  
    public void mousePressed(MouseEvent e) {
        obstacles.setObstacles(Math.round(e.getY()/50), Math.round(e.getX()/50));
        repaint();
    }  
    public void mouseReleased(MouseEvent e) {
    } 

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    // Waits for a Click Mouse Event
    public void mousePressed(MouseEvent e) {
        try {
            // Sets start if the start button was pressed
            if (algorithm.getIsStartSet()) {
                if (!obstacles.getObstacles(Math.round(e.getY() / 50), Math.round(e.getX() / 50)))
                    algorithm.setStartPoint(Math.round(e.getY() / 50), Math.round(e.getX() / 50));
                algorithm.setFalseIsStartSet();

            // Sets finish if the finish button was pressed
            } else if (algorithm.getIsFinishSet()) {
                if (!obstacles.getObstacles(Math.round(e.getY() / 50), Math.round(e.getX() / 50)))
                    algorithm.setFinishPoint(Math.round(e.getY() / 50), Math.round(e.getX() / 50));
                algorithm.setFalseIsFinishSet();

            // Sets an obstacle if no button was pressed
            } else if (!((algorithm.getStartPoint().getRow() == Math.round(e.getY() / 50)
                    && algorithm.getStartPoint().getCol() == Math.round(e.getX() / 50))
                    || (algorithm.getFinishPoint().getRow() == Math.round(e.getY() / 50)
                            && algorithm.getFinishPoint().getCol() == Math.round(e.getX() / 50))))
                obstacles.setObstacles(Math.round(e.getY() / 50), Math.round(e.getX() / 50));

            repaint();
        } catch (ArrayIndexOutOfBoundsException ex) {
        }
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void paint(Graphics gc) {
        // create Graphics2D variable from Graphics
        Graphics2D gc2D = (Graphics2D) gc;

        /* Set Canvas */
        // Set Background color (Using rectangle due to setBackground not working this
        // way)
        gc.setColor(Color.WHITE);
        gc.fillRect(0, 0, 500, 500);

        // Set grid lines color
        gc2D.setPaint(Color.GRAY);

        // vertical grid lines
        for (int i = 0; i <= 500; i = i + 50) {
            gc2D.draw(new Line2D.Float(i, 0, i, 500));
        }

        // horizontal grid lines
        for (int i = 0; i <= 500; i = i + 50) {
            gc2D.draw(new Line2D.Float(0, i, 500, i));
        }

        /* Borders */

        // Color and Stroke
        gc2D.setColor(Color.BLACK);
        gc2D.setStroke(new BasicStroke(2.0f));

        // horizontal borders
        gc2D.draw(new Line2D.Float(0, 1, 500, 1));
        gc2D.draw(new Line2D.Float(0, 500, 500, 500));

        // vertical borders
        gc2D.draw(new Line2D.Float(1, 0, 1, 500));
        gc2D.draw(new Line2D.Float(500, 0, 500, 500));

        /* Obstacles */
        // draw obstacles
        gc2D.setColor(Color.BLACK);
        gc2D.setStroke(new BasicStroke(1.0f));
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                // get the obstacles boolean value from the Array of Arrays
                if (obstacles.getObstacles(row, col))
                    gc.fillRect(col * 50, row * 50, 50, 50);
            }
        }

        // Section where it only enters after the user has chosen an search Algorithm
        // Draws searched zone
        if (draw_path) {
            try {
                // set variables for vector's size
                int i = 0, size = algorithm.getVector().size();

                // Draws the searched path using the points stored in the vector
                while (i < size) {
                    gc2D.setColor(new Color(252, 240, 68));
                    double x = (double) ((algorithm.getPathPoint(i).getRow() * 50) + 1);
                    double y = (double) ((algorithm.getPathPoint(i).getCol() * 50) + 1);

                    gc2D.fill(new Rectangle.Double(y, x, 48, 48));

                    Thread.sleep(150);

                    /* Start and finish */

                    // Indicators for start and finish labels
                    gc2D.setColor(Color.BLUE);
                    gc2D.drawString("S", (algorithm.getStartPoint().getCol() * 50) + 5,
                            (algorithm.getStartPoint().getRow() * 50) + 15);
                    gc2D.drawString("F", (algorithm.getFinishPoint().getCol() * 50) + 40,
                            (algorithm.getFinishPoint().getRow() * 50) + 45);

                    /* Borders */
                    // Color and Stroke
                    gc2D.setColor(Color.BLACK);
                    gc2D.setStroke(new BasicStroke(2.0f));

                    // horizontal borders
                    gc2D.draw(new Line2D.Float(0, 1, 500, 1));
                    gc2D.draw(new Line2D.Float(0, 500, 500, 500));

                    // vertical borders
                    gc2D.draw(new Line2D.Float(1, 0, 1, 500));
                    gc2D.draw(new Line2D.Float(500, 0, 500, 500));

                    i++;
                }

                // Set line colors and stroke
                gc2D.setColor(Color.RED);
                gc2D.setStroke(new BasicStroke(3.0f));

                algorithm.reverseVector();
                algorithm.searchPath();

                i = 0;
                size = algorithm.getVector().size();

                // Draws Circle an the start of path
                gc2D.fill(new Ellipse2D.Double((algorithm.getPathPoint(i).getCol() * 50) + 20,
                        (algorithm.getPathPoint(i).getRow() * 50) + 20, 10, 10));
                // Draw Line
                while (i < size - 1) {
                    double x1 = (double) (algorithm.getPathPoint(i).getRow() * 50) + 25;
                    double y1 = (double) (algorithm.getPathPoint(i).getCol() * 50) + 25;
                    double x2 = (double) (algorithm.getPathPoint(i + 1).getRow() * 50) + 25;
                    double y2 = (double) (algorithm.getPathPoint(i + 1).getCol() * 50) + 25;
                    
                    gc2D.draw(new Line2D.Double(y1, x1, y2, x2));

                    Thread.sleep(150);

                    if (algorithm.getPathPoint(i + 1).getRow() == 0 && algorithm.getPathPoint(i + 1).getCol() == 0)
                        break;

                    i++;
                }

                // Draws circle at the end of path
                gc2D.fill(new Ellipse2D.Double((algorithm.getStartPoint().getCol() * 50) + 20,
                        (algorithm.getStartPoint().getRow() * 50) + 20, 10, 10));

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Stops thread
            thread.interrupt();

            // Resets
            draw_path = false;
        }

        /* Start and finish */

        // Indicators for start and finish labels
        gc2D.setColor(Color.BLUE);
        gc2D.drawString("S", (algorithm.getStartPoint().getCol() * 50) + 5,
                (algorithm.getStartPoint().getRow() * 50) + 15);
        gc2D.drawString("F", (algorithm.getFinishPoint().getCol() * 50) + 40,
                (algorithm.getFinishPoint().getRow() * 50) + 45);
    }

    // Thread method to convert Canvas into a Thread component
    public void run() {
        repaint();
    }

    // Allows the method paint to draw the path line and starts the thread
    public void drawPath() {
        draw_path = true;
        thread = new Thread(this);
        thread.start();
    }
}
