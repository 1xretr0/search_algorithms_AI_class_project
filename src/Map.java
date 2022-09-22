// this class creates the graphic map where the chosen algorithm will work
import java.awt.*;
import java.awt.geom.*;

public class Map extends Canvas implements Runnable{
    /* Variables declaration */
    // Boolean to draw vector points on paint method (false)
    private boolean draw_path =  false;
    // Thread to convert Canvas into a Thread for the GUI
    private Thread thread;
    // Algorithm variable to invoke Vector and Array of Arrays methods
    private Algorithm algoritmo;

    public Map(Algorithm algoritmo){
        this.algoritmo = algoritmo;
    }

    public void paint(Graphics gc){
        // create Graphics2D variable from Graphics
        Graphics2D gc2D = (Graphics2D) gc;

        /* Set Canvas */
        // Set Background color (Using rectangle due to setBackground not working this way)
        gc.setColor(Color.WHITE);
        gc.fillRect(0, 0, 500, 500);

        // Set grid lines color
        gc2D.setPaint(Color.GRAY);

        // vertical grid lines
        for(int i = 0; i <= 500; i = i + 50){
            gc2D.draw(new Line2D.Float(i, 0, i, 500));
        }

        // horizontal grid lines
        for(int i = 0; i <= 500; i = i + 50){
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

        // Indicators for start and finish labels
        gc2D.setColor(Color.BLUE);
        gc2D.drawString("S", 5, 15);
        gc2D.drawString("F", 490, 495);

        // draw obstacles
        gc2D.setColor(Color.BLACK);
        gc2D.setStroke(new BasicStroke(1.0f));
        for(int row = 0; row < 10; row++){
            for(int col = 0; col < 10; col++){
                // get the obstacles boolean value from the Array of Arrays
                if(algoritmo.getObstacles(row, col))
                    gc.fillRect(col * 50, row * 50, 50, 50);
            }
        }

        // Section where it only enters after the user has chosen an search Algorithm
        if(draw_path){
            try{
                // set variables for vector's size
                int i = 0, size = algoritmo.getVector().size();

                gc2D.setColor(Color.RED);
                gc2D.setStroke(new BasicStroke(3.0f));

                // Draws the path using the points stored in the vector
                while(i < size - 1){
                    double x1 = (double) (algoritmo.getPathPoint(i).getRow() * 50) + 25;
                    double y1 = (double) (algoritmo.getPathPoint(i).getCol() * 50) + 25;
                    double x2 = (double) (algoritmo.getPathPoint(i + 1).getRow() * 50) + 25;
                    double y2 = (double) (algoritmo.getPathPoint(i + 1).getCol() * 50) + 25;
                    gc2D.draw(new Line2D.Double(y1, x1, y2, x2));
                    thread.sleep(150);
                    i++;
                }
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            // Stops thread
            thread.interrupt();

            // Resets
            draw_path = false;
        }
    }

    // Thread method to convert Canvas into a Thread component
    public void run(){
        repaint();
    }

    // Allows the method paint to draw the path line and starts the thread
    public void drawPath(){
        draw_path = true;
        thread = new Thread(this);
        thread.start();
    }
}
