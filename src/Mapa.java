// this class creates the graphic map where the chosen algorithm will work
import java.awt.*;
import java.awt.geom.*;

public class Mapa extends Canvas{
    /* Variables declaration */
    // Boolean to draw vector points on paint method (false)
    private boolean draw_path =  false;
    // Matrix to store obstacles positions
    private boolean obstacles[][];

    public Mapa(){
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

        // Indicators for start and finish labels
        gc2D.setColor(Color.BLUE);
        gc2D.drawString("S", 5, 15);
        gc2D.drawString("F", 490, 495);

        // draw obstacles
        gc2D.setColor(Color.BLACK);
        gc2D.setStroke(new BasicStroke(1.0f));
        for(int row = 0; row < 10; row++){
            for(int col = 0; col < 10; col++){
                if(obstacles[row][col])
                    gc.fillRect(col * 50, row * 50, 50, 50);
                System.out.print("[" + ((obstacles[row][col])?"1":"0") + "]");
            }
            System.out.print("\n");
        }

        // Section where it only enters after the user has chosen an search Algorithm
        if(draw_path){
            //TODO
        }
    }

    //
    public void pathFinder(){
        draw_path = true;
        repaint();
    }

    public void setObstacles(){
        // Initialize new obstacles
        obstacles = new boolean[10][10];

        /* Test */ 
        // It is planned to be random or user input
        /* How obstacles are gonna show in this example
         *    0  1  2  3  4  5  6  7  8  9
         * 0 [S][*][*][*][*][*][*][ ][ ][ ]
         * 1 [ ][*][*][*][ ][ ][ ][ ][*][ ]
         * 2 [ ][ ][ ][ ][ ][ ][*][ ][*][ ]
         * 3 [*][ ][ ][*][*][ ][*][ ][*][ ]
         * 4 [*][*][ ][*][*][ ][*][ ][ ][ ]
         * 5 [*][*][ ][*][*][*][*][*][ ][*]
         * 6 [*][*][ ][ ][ ][*][*][ ][ ][*]
         * 7 [*][*][*][ ][ ][ ][*][ ][ ][*]
         * 8 [*][*][*][*][ ][ ][ ][ ][ ][*]
         * 9 [*][*][*][*][ ][ ][*][*][ ][F]
         */
        obstacles[0][1] = true; obstacles[0][2] = true; obstacles[0][3] = true; obstacles[0][4] = true; obstacles[0][5] = true; obstacles[0][6] = true; 
        obstacles[1][2] = true; obstacles[1][2] = true; obstacles[1][2] = true; obstacles[1][8] = true; 
        obstacles[2][6] = true; obstacles[2][8] = true; 
        obstacles[3][0] = true; obstacles[3][3] = true; obstacles[3][4] = true; obstacles[3][6] = true; obstacles[3][8] = true; 
        obstacles[4][0] = true; obstacles[4][1] = true; obstacles[4][3] = true; obstacles[4][4] = true; obstacles[4][6] = true; 
        obstacles[5][0] = true; obstacles[5][1] = true; obstacles[5][3] = true; obstacles[5][4] = true; obstacles[5][5] = true; obstacles[5][6] = true; obstacles[5][7] = true; obstacles[5][9] = true; 
        obstacles[6][0] = true; obstacles[6][1] = true; obstacles[6][5] = true; obstacles[6][6] = true; obstacles[6][9] = true; 
        obstacles[7][0] = true; obstacles[7][1] = true; obstacles[7][2] = true; obstacles[7][6] = true; obstacles[7][9] = true; 
        obstacles[8][0] = true; obstacles[8][1] = true; obstacles[8][2] = true; obstacles[8][3] = true; obstacles[8][9] = true; 
        obstacles[9][0] = true; obstacles[9][1] = true; obstacles[9][2] = true; obstacles[9][3] = true; obstacles[9][6] = true; obstacles[9][7] = true; 
        //TODO
    }
}
