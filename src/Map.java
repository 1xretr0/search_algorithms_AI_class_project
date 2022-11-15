// this class creates the graphic map where the chosen algorithm will work
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import java.lang.Math;

public class Map extends Canvas implements Runnable, MouseListener{
    /* Variables declaration */
    // Boolean to draw vector points on paint method (false)
    private boolean draw_path =  false;

    // Thread to convert Canvas into a Thread for the GUI
    private Thread thread;

    // Algorithm variable to invoke Vector and Array of Arrays methods
    private Algorithm algorithm;

    // Obstacles variable to draw the obstacles on the canvas
    private Obstacles obstacles;

    public Map(Algorithm algorithm, Obstacles obstacles){
        this.algorithm = algorithm;
        this.obstacles = obstacles;
        addMouseListener(this);
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

        /* Start and finish */

        // Indicators for start and finish labels
        gc2D.setColor(Color.BLUE);
        gc2D.drawString("S", 5, 15);
        gc2D.drawString("F", 490, 495);

        /* Obstacles */
        // draw obstacles
        gc2D.setColor(Color.BLACK);
        gc2D.setStroke(new BasicStroke(1.0f));
        for(int row = 0; row < 10; row++){
            for(int col = 0; col < 10; col++){
                // get the obstacles boolean value from the Array of Arrays
                if(obstacles.getObstacles(row, col))
                    gc.fillRect(col * 50, row * 50, 50, 50);
            }
        }

        // Section where it only enters after the user has chosen an search Algorithm
        if(draw_path){
            try{
                // set variables for vector's size
                int i = 0, size = algorithm.getVector().size();

                gc2D.setColor(Color.YELLOW);
                gc2D.setStroke(new BasicStroke(3.0f));

                // Draws the searched path using the points stored in the vector
                while(i < size){
                    // double x1 = (double) (algorithm.getPathPoint(i).getRow() * 50);
                    // double y1 = (double) (algorithm.getPathPoint(i).getCol() * 50);
                    // double x2 = (double) (algorithm.getPathPoint(i + 1).getRow() * 50);
                    // double y2 = (double) (algorithm.getPathPoint(i + 1).getCol() * 50);

                    double x = (double) (algorithm.getPathPoint(i).getRow() * 50);
                    double y = (double) (algorithm.getPathPoint(i).getCol() * 50);
                    gc2D.fill(new Rectangle.Double(y, x, 50, 50));
                    Thread.sleep(150);

                    // if(algorithm.getPathPoint(i + 1).getRow() == 9 && algorithm.getPathPoint(i + 1).getCol() == 9)
                    //     break;

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
