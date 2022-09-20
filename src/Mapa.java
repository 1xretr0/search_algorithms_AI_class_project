// this class creates the graphic map where the chosen algorithm will work
import java.awt.*;
import java.awt.geom.*;

public class Mapa extends Canvas{
    /* Variables declaration */
    // Boolean to draw vector points on paint method (false)
    private boolean draw_path;
    // Matrix to store obstacles positions
    private boolean obstacles[][];

    public Mapa(){
        obstacles = new boolean[10][10];
        setObstacles();
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

        if(draw_path){
            //TODO
        }
    }

    public void setObstacles(){
        //TODO
    }
}
