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
        System.out.println(vector);
    }
    
    public boolean compareInVector(PathPoints point){
        // Searchs in the vector if the point has been added before, so it doesn't add it again
        for(PathPoints search : vector){
            if(search.getRow() == point.getRow() && search.getCol() == point.getCol())
                return true;
        }

        return false;
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

        // Initialize vector, row, col and the new_point variables
        vector = new Vector<PathPoints>(1);
        int row = 0, col = 0;
        PathPoints new_point;

        // Initialize queue
        Queue<PathPoints> queue = new LinkedList<>();
        PathPoints initial_point = new PathPoints<>(0, 0);

        queue.add(initial_point);       // set initial queue state
        vector.add(initial_point);      // Adds initial point to the path vector
        boolean found = false;          // found flag

        // while queue is NOT empty AND haven't found the end, do:
        while (!queue.isEmpty() && !found){
            // removes and stores head from queue (current point algorythm is stanging on)
            PathPoints current_point = queue.remove();
            row = current_point.getRow();
            col = current_point.getCol();     

            // If the current point is the end exits the cycle
            if(current_point.getRow() == 9 && current_point.getCol() == 9){
                queue.clear();      // Empty queue
                found = true;       // Reached end
                break;
            }

            /* Search Upwards */
            // Looks if Index in range AND if there's not an obstacle in that index
            if(row > 0 && !obstacles[row - 1][col]){
                // Creates a point above the current position
                new_point = new PathPoints<>(current_point.getRow() - 1, current_point.getCol());

                // If the point was added before, it doesn't add again
                if(!compareInVector(new_point)){
                    queue.add(new_point);
                    vector.add(new_point);
                }
            }

            /* Search Leftwards */
            // Looks if Index in range AND if there's not an obstacle in that index
            if(col > 0 && !obstacles[row][col - 1]){
                // Creates a point to the left of the current position
                new_point = new PathPoints<>(current_point.getRow(), current_point.getCol() - 1);

                // If the point was added before, it doesn't add again
                if(!compareInVector(new_point)){
                    queue.add(new_point);
                    vector.add(new_point);
                }
            }

            /* Search Downwards */
            // Looks if Index in range AND if there's not an obstacle in that index
            if(row < 9 && !obstacles[row + 1][col]){
                // Creates a point under the current position
                new_point = new PathPoints<>(current_point.getRow() + 1, current_point.getCol());

                // If the point was added before, it doesn't add again
                if(!compareInVector(new_point)){
                    queue.add(new_point);
                    vector.add(new_point);
                }
            }

            /* Search Rightwards */
            // Looks if Index in range AND if there's not an obstacle in that index
            if(col < 9 && !obstacles[row][col + 1]){
                // Creates a point to the right of the current position
                new_point = new PathPoints<>(current_point.getRow(), current_point.getCol() + 1);

                // If the point was added before, it doesn't add again
                if(!compareInVector(new_point)){
                    queue.add(new_point);
                    vector.add(new_point);
                }
            }
        }
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

    // Initialize new obstacles
    public void setObstacles(){
        obstacles = new boolean[10][10];
        
        /* Test  */
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
    }
}