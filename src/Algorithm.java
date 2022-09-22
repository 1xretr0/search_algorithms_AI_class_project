// this class computes the chosen search algorithm using the matrix obstacles map and a vector to store the path
// used by the algorithm class to get to the finish

import java.util.*;
public class Algorithm {
    // Array of Arrays to store obstacles positions
    private boolean obstacles[][];
    // Vector to store the path chosen by the algorythm (vector of type PathPoints)
    private Vector<PathPoints> vector;

    public Algorithm(){
        setObstacles();
    }

    // returns the boolean value of the Array of arrays
    public boolean getObstacles(int row, int col){
        return obstacles[row][col];
    }

    // Returns the value of the vector in that index
    public PathPoints getPathPoint(int index){
        return vector.get(index);
    }

    public Vector getVector(){
        return new Vector<PathPoints>(vector);
    }

    // prints the stored points of the vector in console (WILL BE DELETED)
    public void printVector(){
        int size = vector.size();
        for(int i = 0; i < size; i++){
            System.out.println(getPathPoint(i).toString());
        }
    }

    // Initialize new obstacles
    public void setObstacles(){
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

    /* Example algorithm to test the map */
    // Will be changed in future releases
    public void findPath(){
        // Initialize vector and row, col;
        vector = new Vector<PathPoints>(1);
        int row = 0, col = 0;

        // flags to change direcction
        boolean down = true, right = true;

        while(!(row == 9 && col == 9)){
            // Create new point
            PathPoints point;

            // set the initial position on vector
            if(row == 0 && col == 0){
                point = new PathPoints<>(row, col);
                vector.add(point);
            }

            // Search downwards
            if(down && row < 9 && !obstacles[row + 1][col]){
                row++;
                point = new PathPoints<>(row, col);
                vector.add(point);
            }
            // Search right
            else if(right && col < 9 && !obstacles[row][col + 1]){
                col++;
                down = true;
                point = new PathPoints<>(row, col);
                vector.add(point);
            }
            // Search upwards
            else if(!obstacles[row - 1][col]){
                row--;
                down = false;
                point = new PathPoints<>(row, col);
                vector.add(point);
            }
        }

        printVector();
    }

    public void breadthSearch(){

        // Initialize vector and row, col;
        vector = new Vector<PathPoints>(1);
        int row = 0, col = 0;

        // Initialize queue
        Queue<PathPoints> q = new LinkedList<>();
        PathPoints initial_point = new PathPoints<>(0, 0);

        q.add(initial_point);       // set initial queue state
        boolean found = false;      // found flag

        // while (q.size() > 0 && found == false){

        // }
    }

    public void depthSearch(){
        /* -------------TODO------------- */
    }

    public void hillSearch(){
        /* -------------TODO------------- */
    }

    public void bestSearch(){
        /* -------------TODO------------- */
    }

    public void aSearch(){
        /* -------------TODO------------- */
    }
}
