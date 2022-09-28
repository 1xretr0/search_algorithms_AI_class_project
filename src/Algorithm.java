// this class computes the chosen search algorithm using the matrix obstacles map and a vector to store the path
// used by the algorithm class to get to the finish

import java.io.*;
import java.util.*;
public class Algorithm {
    // Array of Arrays to store obstacles positions
    private boolean obstacles[][];
    // Vector to store the path chosen by the algorythm (vector of type PathPoints)
    private Vector<PathPoints> vector;

    // CLASS CONSTRUCTOR
	public Algorithm(){
        setObstacles(0);
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

    // Searchs for the max value in the Hashmap
    public PathPoints maxValuePoint(HashMap<PathPoints, Integer> hashmap){
        int max = 18;
        PathPoints maxValuePoint = new PathPoints(0, 0);
        for(PathPoints point : hashmap.keySet()){
            if(hashmap.get(point) < max){
                maxValuePoint = point;
                max = hashmap.get(point);
            }
        }

        return maxValuePoint;
    }

    // BREADTH SEARCH ALGORITHM
	public void breadthSearch(){
        // Initialize vector, row, col and the new_point variables
        vector = new Vector<PathPoints>(1);
        int row = 0, col = 0;
        PathPoints new_point;

        /* Vector shall add every point of queue BEFORE it goes through. As it is a queue it WILL eventually get there,
         * and vector also helps storing all the points it has visited or will visit. */

        // Initialize queue
        Queue<PathPoints> queue = new LinkedList<>();
        PathPoints initial_point = new PathPoints(0, 0);

        queue.add(initial_point);       // set initial queue state
        vector.add(initial_point);      // Adds initial point to the path vector

        // while queue is NOT empty AND haven't found the end, do:
        while (!queue.isEmpty()){
            // removes and stores head from queue (current point algorythm is stanging on)
            PathPoints current_point = queue.remove();
            row = current_point.getRow();
            col = current_point.getCol();

            // If the current point is the end exits the cycle
            if(current_point.getRow() == 9 && current_point.getCol() == 9){
                queue.clear();      // Empty queue
                break;
            }

            /* Search Upwards */
            // Looks if Index in range AND if there's not an obstacle in that index
            if(row > 0 && !obstacles[row - 1][col]){
                // Creates a point above the current position
                new_point = new PathPoints(current_point.getRow() - 1, current_point.getCol());

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
                new_point = new PathPoints(current_point.getRow(), current_point.getCol() - 1);

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
                new_point = new PathPoints(current_point.getRow() + 1, current_point.getCol());

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
                new_point = new PathPoints(current_point.getRow(), current_point.getCol() + 1);

                // If the point was added before, it doesn't add again
                if(!compareInVector(new_point)){
                    queue.add(new_point);
                    vector.add(new_point);
                }
            }
        }
    }

    // DEPTH SEARCH ALGORITHM
	public void depthSearch(){
        // Initialize vector, row, col and the new_point variables
        vector = new Vector<PathPoints>(1);
        int row = 0, col = 0;
        PathPoints new_point;

        /* Vector shall add every point stack AFTER it goes through. As it is a stack it might not go through all
         * the stored points, and vector also helps storing all the points it has visited. */

        // Initialize stack
        Stack<PathPoints> stack = new Stack<>();

        PathPoints initial_point = new PathPoints(0, 0);
        stack.push(initial_point);       // set initial stack state

        // while stack is NOT empty AND haven't found the end, do:
        while (!stack.isEmpty()){
            // removes and stores head from stack (current point algorythm is stanging on)
            PathPoints current_point = stack.pop();
            vector.add(current_point);

            row = current_point.getRow();
            col = current_point.getCol();

            // If the current point is the end exits the cycle
            if(current_point.getRow() == 9 && current_point.getCol() == 9){
                stack.clear();      // Empty stack
                break;
            }

            /* Search Upwards */
            // Looks if Index in range AND if there's not an obstacle in that index
            if(row > 0 && !obstacles[row - 1][col]){
                // Creates a point above the current position
                new_point = new PathPoints(current_point.getRow() - 1, current_point.getCol());

                // If the point was added before, it doesn't add again
                if(!compareInVector(new_point)){
                    stack.push(new_point);
                }
            }

            /* Search Leftwards */
            // Looks if Index in range AND if there's not an obstacle in that index
            if(col > 0 && !obstacles[row][col - 1]){
                // Creates a point to the left of the current position
                new_point = new PathPoints(current_point.getRow(), current_point.getCol() - 1);

                // If the point was added before, it doesn't add again
                if(!compareInVector(new_point)){
                    stack.push(new_point);
                }
            }

            /* Search Downwards */
            // Looks if Index in range AND if there's not an obstacle in that index
            if(row < 9 && !obstacles[row + 1][col]){
                // Creates a point under the current position
                new_point = new PathPoints(current_point.getRow() + 1, current_point.getCol());

                // If the point was added before, it doesn't add again
                if(!compareInVector(new_point)){
                    stack.push(new_point);
                }
            }

            /* Search Rightwards */
            // Looks if Index in range AND if there's not an obstacle in that index
            if(col < 9 && !obstacles[row][col + 1]){
                // Creates a point to the right of the current position
                new_point = new PathPoints(current_point.getRow(), current_point.getCol() + 1);
                vector.add(current_point);

                // If the point was added before, it doesn't add again
                if(!compareInVector(new_point)){
                    stack.push(new_point);
                }
            }
        }
    }

    // HILL SEARCH ALGORITHM
	public void hillSearch(){
        // Initialize vector, row, col
        vector = new Vector<PathPoints>(1);
        int row = 0, col = 0;

        // set and add initial point
        PathPoints initial_point = new PathPoints(0, 0);
        vector.add(initial_point);

        // PathPoints current_point = initial_point;
        PathPoints current_point = new PathPoints(initial_point.getRow(), initial_point.getCol());

        // declare new point var
        PathPoints new_point;

		// initialize map to store evaluated points with its distance value
		HashMap<PathPoints, Integer> evaluated_points = new HashMap<PathPoints, Integer>();

        // while the current point is not the goal point
        while (current_point.getRow() != 9 && current_point.getCol() != 9){
			/* Search Upwards */
			// Looks if Index in range AND if there's not an obstacle in that index
			if (row > 0 && !obstacles[row - 1][col]) {
				// Creates a point above the current position
				new_point = new PathPoints(current_point.getRow() - 1, current_point.getCol());

				// If the point was added before, it doesn't add again
				if (!compareInVector(new_point)) {
					/* ---------------TODO---------------- */
				}
			}
        }
    }

    // BEST FIRST SEARCH ALGORITHM
	public void bestSearch(){
        // Initialize vector, row, col
        vector = new Vector<PathPoints>(1);
        int row = 0, col = 0;
        PathPoints new_point;

        // initialize map to store evaluated points with its distance value
		HashMap<PathPoints, Integer> evaluated_points = new HashMap<PathPoints, Integer>();

        // set, evaluate and add initial point
        PathPoints initial_point = new PathPoints(0, 0);
        evaluated_points.put(initial_point, distance(initial_point));

        while(!evaluated_points.isEmpty()){
            // Get Point with max Value from HashMap
            PathPoints current_point = maxValuePoint(evaluated_points);
            evaluated_points.remove(current_point);
            vector.add(current_point);
            System.out.println(evaluated_points);
            System.out.println(vector);

            row = current_point.getRow();
            col = current_point.getCol();
            
            // If the current point is the end exits the cycle
            if(current_point.getRow() == 9 && current_point.getCol() == 9){
                evaluated_points.clear();      // Empty stack
                break;
            }
            
            /* Search Upwards */
            // Looks if Index in range AND if there's not an obstacle in that index
            if(row > 0 && !obstacles[row - 1][col]){
                // Creates a point above the current position
                new_point = new PathPoints(current_point.getRow() - 1, current_point.getCol());

                // If the point was added before, it doesn't add again
                if(!compareInVector(new_point)){
                    evaluated_points.put(new_point, distance(new_point));
                }
            }

            /* Search Leftwards */
            // Looks if Index in range AND if there's not an obstacle in that index
            if(col > 0 && !obstacles[row][col - 1]){
                // Creates a point to the left of the current position
                new_point = new PathPoints(current_point.getRow(), current_point.getCol() - 1);

                // If the point was added before, it doesn't add again
                if(!compareInVector(new_point)){
                    evaluated_points.put(new_point, distance(new_point));
                }
            }

            /* Search Downwards */
            // Looks if Index in range AND if there's not an obstacle in that index
            if(row < 9 && !obstacles[row + 1][col]){
                // Creates a point under the current position
                new_point = new PathPoints(current_point.getRow() + 1, current_point.getCol());

                // If the point was added before, it doesn't add again
                if(!compareInVector(new_point)){
                    evaluated_points.put(new_point, distance(new_point));
                }
            }

            /* Search Rightwards */
            // Looks if Index in range AND if there's not an obstacle in that index
            if(col < 9 && !obstacles[row][col + 1]){
                // Creates a point to the right of the current position
                new_point = new PathPoints(current_point.getRow(), current_point.getCol() + 1);

                // If the point was added before, it doesn't add again
                if(!compareInVector(new_point)){
                    evaluated_points.put(new_point, distance(new_point));
                }
            }
        }
    }

    // A* SEARCH ALGORITHM
	public void aSearch(){
        /* -------------TODO------------- */
    }

    // EVALUATION FUNCTION
	private int distance(PathPoints point){
        // Evaluation is how close the point is to the end, lower distance equals better evaluation
        int row_dist = 9 - point.getRow();
        int col_dist = 9 - point.getCol();

        return row_dist + col_dist;
	}

	// Initialize new obstacles
    public void setObstacles(int t){
        obstacles = new boolean[10][10];

        int template = t;

        switch(template){       // MANUAL CHANGE
            case 0:{
                /* Test EMPTY */
                // It is planned to be random or user input
                /* How obstacles are gonna show in this example
                *    0  1  2  3  4  5  6  7  8  9
                * 0 [S][ ][ ][ ][ ][ ][ ][ ][ ][ ]
                * 1 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
                * 2 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
                * 3 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
                * 4 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
                * 5 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
                * 6 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
                * 7 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
                * 8 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
                * 9 [ ][ ][ ][ ][ ][ ][ ][ ][ ][F]
                */
                break;
            }
            case 1:{
                /* Test TEMPLATE 1 */
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
                break;
            }
            case 2:{
                /* Test TEMPLATE 2 */
                // It is planned to be random or user input
                /* How obstacles are gonna show in this example
                *    0  1  2  3  4  5  6  7  8  9
                * 0 [S][ ][*][*][*][*][*][*][*][*]
                * 1 [ ][ ][ ][ ][*][*][*][*][*][*]
                * 2 [*][ ][ ][ ][ ][ ][*][*][*][*]
                * 3 [*][ ][ ][*][ ][ ][ ][ ][*][*]
                * 4 [*][*][ ][ ][*][*][ ][ ][*][ ]
                * 5 [*][*][ ][ ][*][*][*][*][ ][ ]
                * 6 [*][*][ ][ ][ ][*][*][*][ ][ ]
                * 7 [*][*][ ][ ][ ][*][*][*][ ][ ]
                * 8 [*][*][ ][ ][ ][ ][ ][ ][ ][ ]
                * 9 [*][*][*][*][ ][ ][ ][ ][ ][F]
                */

                obstacles[0][2] = true; obstacles[0][3] = true; obstacles[0][4] = true; obstacles[0][5] = true; obstacles[0][6] = true; obstacles[0][7] = true; obstacles[0][8] = true; obstacles[0][9] = true;
                obstacles[1][4] = true; obstacles[1][5] = true; obstacles[1][6] = true; obstacles[1][7] = true; obstacles[1][8] = true; obstacles[1][9] = true;
                obstacles[2][0] = true; obstacles[2][6] = true; obstacles[2][7] = true; obstacles[2][8] = true; obstacles[2][9] = true;
                obstacles[3][0] = true; obstacles[3][3] = true; obstacles[3][8] = true; obstacles[3][9] = true;
                obstacles[4][0] = true; obstacles[4][1] = true; obstacles[4][4] = true; obstacles[4][5] = true; obstacles[4][8] = true;
                obstacles[5][0] = true; obstacles[5][1] = true; obstacles[5][4] = true; obstacles[5][5] = true; obstacles[5][6] = true; obstacles[5][7] = true;
                obstacles[6][0] = true; obstacles[6][1] = true; obstacles[6][5] = true; obstacles[6][6] = true; obstacles[6][7] = true;
                obstacles[7][0] = true; obstacles[7][1] = true; obstacles[7][5] = true; obstacles[7][6] = true; obstacles[7][7] = true;
                obstacles[8][0] = true; obstacles[8][1] = true;
                obstacles[9][0] = true; obstacles[9][1] = true; obstacles[9][2] = true; obstacles[9][3] = true;
                break;
            }
            case 3:{
                /* Test TEMPLATE 3 */
                // It is planned to be random or user input
                /* How obstacles are gonna show in this example
                *    0  1  2  3  4  5  6  7  8  9
                * 0 [S][ ][ ][ ][ ][ ][ ][ ][ ][ ]
                * 1 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
                * 2 [ ][ ][ ][*][*][*][ ][ ][ ][ ]
                * 3 [ ][ ][*][ ][ ][*][*][ ][ ][ ]
                * 4 [*][*][*][ ][ ][*][*][*][ ][ ]
                * 5 [*][*][*][ ][ ][*][*][*][ ][ ]
                * 6 [ ][ ][*][*][*][*][*][*][ ][ ]
                * 7 [ ][ ][ ][*][*][*][*][*][ ][ ]
                * 8 [*][*][ ][ ][*][*][*][*][ ][ ]
                * 9 [*][*][ ][ ][ ][ ][ ][ ][ ][F]
                */
                obstacles[2][3] = true; obstacles[2][4] = true; obstacles[2][5] = true;
                obstacles[3][2] = true; obstacles[3][5] = true; obstacles[3][6] = true;
                obstacles[4][0] = true; obstacles[4][1] = true; obstacles[4][2] = true; obstacles[4][5] = true; obstacles[4][6] = true; obstacles[4][7] = true;
                obstacles[5][0] = true; obstacles[5][1] = true; obstacles[5][2] = true; obstacles[5][5] = true; obstacles[5][6] = true; obstacles[5][7] = true;
                obstacles[6][2] = true; obstacles[6][3] = true; obstacles[6][4] = true; obstacles[6][5] = true; obstacles[6][6] = true; obstacles[6][7] = true;
                obstacles[7][3] = true; obstacles[7][4] = true; obstacles[7][5] = true; obstacles[7][6] = true; obstacles[7][7] = true;
                obstacles[8][0] = true; obstacles[8][1] = true; obstacles[8][4] = true; obstacles[8][5] = true; obstacles[8][6] = true; obstacles[8][7] = true;
                obstacles[9][0] = true; obstacles[9][1] = true;
                break;
            }
        }
    }
}