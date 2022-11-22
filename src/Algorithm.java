// this class computes the chosen search algorithm using the matrix obstacles map and a vector to store the path
// used by the algorithm class to get to the finish

import java.util.*;
import java.lang.Math;

public class Algorithm {
    // Array of Arrays to store obstacles positions
    private Obstacles obstacles;

    // Vector to store the path chosen by the algorythm (vector of type PathPoints)
    private Vector<PathPoints> vector;

    // Points for start and finish positions
    private PathPoints start_point, finish_point;

    // Boolean to change Start and Finish on MouseListener
    private boolean isStartSet = false, isFinishSet = false;

    // CLASS CONSTRUCTOR
    public Algorithm() {
        obstacles = new Obstacles();
        start_point = new PathPoints(0, 0);
        finish_point = new PathPoints(9, 9);
    }

    /* Setters and Getters for start and finish booleans to true */
    public void setTrueIsStartSet() {
        isStartSet = true;
    }

    public void setFalseIsStartSet() {
        isStartSet = false;
    }

    public boolean getIsStartSet() {
        return isStartSet;
    }

    public void setTrueIsFinishSet() {
        isFinishSet = true;
    }

    public void setFalseIsFinishSet() {
        isFinishSet = false;
    }

    public boolean getIsFinishSet() {
        return isFinishSet;
    }

    /* Start setter and getter */
    public void setStartPoint(int row, int col) {
        start_point = new PathPoints(row, col);
    }

    public PathPoints getStartPoint() {
        return new PathPoints(start_point.getRow(), start_point.getCol());
    }

    /* finish setter and getter */
    public void setFinishPoint(int row, int col) {
        finish_point = new PathPoints(row, col);
    }

    public PathPoints getFinishPoint() {
        return new PathPoints(finish_point.getRow(), finish_point.getCol());
    }

    // Returns the value of the vector in that index
    public PathPoints getPathPoint(int index) {
        return this.vector.get(index);
    }

    // Gets and return vector values
    public Vector<PathPoints> getVector() {
        return new Vector<PathPoints>(this.vector);
    }

    // Flip vector content to draw the line to the start
    public void reverseVector() {
        Collections.reverse(vector);
    }

    // Removes far away points to create the final path
    public void searchPath() {
        for (int i = 0; i < vector.size() - 1; i++) {
            if (!((vector.get(i).getRow() == vector.get(i + 1).getRow() &&
                    vector.get(i).getCol() == vector.get(i + 1).getCol() + 1) ||
                    (vector.get(i).getRow() == vector.get(i + 1).getRow() + 1 &&
                            vector.get(i).getCol() == vector.get(i + 1).getCol())
                    ||
                    (vector.get(i).getRow() == vector.get(i + 1).getRow() &&
                            vector.get(i).getCol() == vector.get(i + 1).getCol() - 1)
                    ||
                    (vector.get(i).getRow() == vector.get(i + 1).getRow() - 1 &&
                            vector.get(i).getCol() == vector.get(i + 1).getCol()))) {
                vector.remove(i + 1);
                i--;
            }
        }
    }

    // Compares the current point doesn't exists in the vector
    public boolean compareInVector(PathPoints point) {
        // Searchs in the vector if the point has been added before, so it doesn't add
        // it again
        for (PathPoints search : this.vector) {
            if (search.getRow() == point.getRow() && search.getCol() == point.getCol())
                return true;
        }
        return false;
    }

    // Compares the current point doesn't exists in the Hashmap
    public boolean compareInHashmap(PathPoints point, HashMap<PathPoints, Integer> hashmap) {
        // Searchs in the Hashmap if the point has been added before, so it doesn't add
        // it again
        for (HashMap.Entry<PathPoints, Integer> search : hashmap.entrySet()) {
            if (search.getKey().getRow() == point.getRow() && search.getKey().getCol() == point.getCol())
                return true;
        }
        return false;
    }

    // Searchs for the max value in the Hashmap
    public PathPoints maxValuePoint(HashMap<PathPoints, Integer> hashmap) {
        int max = 40;
        PathPoints maxValuePoint = new PathPoints(0, 0);
        for (PathPoints point : hashmap.keySet()) {
            if (hashmap.get(point) < max) {
                maxValuePoint = point;
                max = hashmap.get(point);
            }
        }

        return maxValuePoint;
    }

    // BREADTH SEARCH ALGORITHM
    public void breadthSearch() {
        // Initialize vector, row, col, obstacles the new_point variables
        vector = new Vector<PathPoints>(1);
        int row = 0, col = 0;
        obstacles.getObstacles();
        PathPoints new_point;

        /*
         * Vector shall add every point of queue BEFORE it goes through. As it is a
         * queue it WILL eventually get there,
         * and vector also helps storing all the points it has visited or will visit.
         */

        // Initialize queue
        Queue<PathPoints> queue = new LinkedList<>();
        PathPoints initial_point = new PathPoints(start_point.getRow(), start_point.getCol());

        queue.add(initial_point); // set initial queue state
        vector.add(initial_point); // Adds initial point to the path vector

        // while queue is NOT empty AND haven't found the end, do:
        while (!queue.isEmpty()) {
            // removes and stores head from queue (current point algorythm is stanging on)
            PathPoints current_point = queue.remove();
            row = current_point.getRow();
            col = current_point.getCol();

            // If the current point is the end exits the cycle
            if (current_point.getRow() == finish_point.getRow() && current_point.getCol() == finish_point.getCol()) {
                queue.clear(); // Empty stack
                break;
            }

            /* Search Upwards */
            // Looks if Index in range AND if there's not an obstacle in that index
            if (row > 0 && !obstacles.getObstacles(row - 1, col)) {
                // Creates a point above the current position
                new_point = new PathPoints(current_point.getRow() - 1, current_point.getCol());

                // If the point was added before, it doesn't add again
                if (!compareInVector(new_point)) {
                    queue.add(new_point);
                    vector.add(new_point);
                }

                if (new_point.getRow() == finish_point.getRow() && new_point.getCol() == finish_point.getCol()) {
                    queue.clear(); // Empty queue
                    break;
                }
            }

            /* Search Leftwards */
            // Looks if Index in range AND if there's not an obstacle in that index
            if (col > 0 && !obstacles.getObstacles(row, col - 1)) {
                // Creates a point to the left of the current position
                new_point = new PathPoints(current_point.getRow(), current_point.getCol() - 1);

                // If the point was added before, it doesn't add again
                if (!compareInVector(new_point)) {
                    queue.add(new_point);
                    vector.add(new_point);
                }

                if (new_point.getRow() == finish_point.getRow() && new_point.getCol() == finish_point.getCol()) {
                    queue.clear(); // Empty queue
                    break;
                }
            }

            /* Search Downwards */
            // Looks if Index in range AND if there's not an obstacle in that index
            if (row < 9 && !obstacles.getObstacles(row + 1, col)) {
                // Creates a point under the current position
                new_point = new PathPoints(current_point.getRow() + 1, current_point.getCol());

                // If the point was added before, it doesn't add again
                if (!compareInVector(new_point)) {
                    queue.add(new_point);
                    vector.add(new_point);
                }

                if (new_point.getRow() == finish_point.getRow() && new_point.getCol() == finish_point.getCol()) {
                    queue.clear(); // Empty queue
                    break;
                }
            }

            /* Search Rightwards */
            // Looks if Index in range AND if there's not an obstacle in that index
            if (col < 9 && !obstacles.getObstacles(row, col + 1)) {
                // Creates a point to the right of the current position
                new_point = new PathPoints(current_point.getRow(), current_point.getCol() + 1);

                // If the point was added before, it doesn't add again
                if (!compareInVector(new_point)) {
                    queue.add(new_point);
                    vector.add(new_point);
                }

                if (new_point.getRow() == finish_point.getRow() && new_point.getCol() == finish_point.getCol()) {
                    queue.clear(); // Empty queue
                    break;
                }
            }
        }
    }

    // DEPTH SEARCH ALGORITHM
    public void depthSearch() {
        // Initialize vector, row, col, obstacles and the new_point variables
        vector = new Vector<PathPoints>(1);
        int row = 0, col = 0;
        obstacles.getObstacles();
        PathPoints new_point;

        /*
         * Vector shall add every point stack AFTER it goes through. As it is a stack it
         * might not go through all
         * the stored points, and vector also helps storing all the points it has
         * visited.
         */

        // Initialize stack
        Stack<PathPoints> stack = new Stack<>();

        PathPoints initial_point = new PathPoints(start_point.getRow(), start_point.getCol());
        stack.push(initial_point); // set initial stack state

        // while stack is NOT empty AND haven't found the end, do:
        while (!stack.isEmpty()) {
            // removes and stores head from stack (current point algorythm is stanging on)
            PathPoints current_point = stack.pop();
            vector.add(current_point);

            row = current_point.getRow();
            col = current_point.getCol();

            // If the current point is the end exits the cycle
            if (current_point.getRow() == finish_point.getRow() && current_point.getCol() == finish_point.getCol()) {
                stack.clear(); // Empty stack
                break;
            }

            /* Search Upwards */
            // Looks if Index in range AND if there's not an obstacle in that index
            if (row > 0 && !obstacles.getObstacles(row - 1, col)) {
                // Creates a point above the current position
                new_point = new PathPoints(current_point.getRow() - 1, current_point.getCol());

                // If the point was added before, it doesn't add again
                if (!compareInVector(new_point)) {
                    stack.push(new_point);
                }
            }

            /* Search Leftwards */
            // Looks if Index in range AND if there's not an obstacle in that index
            if (col > 0 && !obstacles.getObstacles(row, col - 1)) {
                // Creates a point to the left of the current position
                new_point = new PathPoints(current_point.getRow(), current_point.getCol() - 1);

                // If the point was added before, it doesn't add again
                if (!compareInVector(new_point)) {
                    stack.push(new_point);
                }
            }

            /* Search Downwards */
            // Looks if Index in range AND if there's not an obstacle in that index
            if (row < 9 && !obstacles.getObstacles(row + 1, col)) {
                // Creates a point under the current position
                new_point = new PathPoints(current_point.getRow() + 1, current_point.getCol());

                // If the point was added before, it doesn't add again
                if (!compareInVector(new_point)) {
                    stack.push(new_point);
                }
            }

            /* Search Rightwards */
            // Looks if Index in range AND if there's not an obstacle in that index
            if (col < 9 && !obstacles.getObstacles(row, col + 1)) {
                // Creates a point to the right of the current position
                new_point = new PathPoints(current_point.getRow(), current_point.getCol() + 1);

                // If the point was added before, it doesn't add again
                if (!compareInVector(new_point)) {
                    stack.push(new_point);
                }
            }
        }
    }

    // HILL SEARCH ALGORITHM
    public void hillSearch() {
        // Initialize vector, row, col and obstacles
        vector = new Vector<PathPoints>(1);
        int row = 0, col = 0;
        obstacles.getObstacles();

        // initialize map to store evaluated points with its distance value
        HashMap<PathPoints, Integer> evaluated_points = new HashMap<PathPoints, Integer>();

        // set and add initial point
        PathPoints initial_point = new PathPoints(start_point.getRow(), start_point.getCol());
        vector.add(initial_point);
        // evaluated_points.put(initial_point, distance(initial_point));

        // PathPoints current_point = initial_point;
        PathPoints current_point = new PathPoints(initial_point.getRow(), initial_point.getCol());

        // declare new point var
        PathPoints new_point;

        // while the current point is not the goal point
        while (current_point.getRow() <= 9 && current_point.getCol() <= 9) {
            row = current_point.getRow();
            col = current_point.getCol();

            // If the current point is the end exits the cycle
            if (current_point.getRow() == finish_point.getRow() && current_point.getCol() == finish_point.getCol()) {
                evaluated_points.clear();
                break;
            }

            /* Search Upwards */
            // Looks if Index in range AND if there's not an obstacle in that index
            if (row > 0 && !obstacles.getObstacles(row - 1, col)) {
                // Creates a point above the current position
                new_point = new PathPoints(current_point.getRow() - 1, current_point.getCol());

                // If the point was added before, it doesn't add again
                if (!compareInVector(new_point) && !compareInHashmap(new_point, evaluated_points))
                    evaluated_points.put(new_point, distance(new_point));
            }

            /* Search left */
            // Looks if Index in range AND if there's not an obstacle in that index
            if (col > 0 && !obstacles.getObstacles(row, col - 1)) {
                // set new point to left
                new_point = new PathPoints(current_point.getRow(), current_point.getCol() - 1);

                // if point was not added before
                if (!compareInVector(new_point) && !compareInHashmap(new_point, evaluated_points))
                    evaluated_points.put(new_point, distance(new_point));
            }

            /* Search down */
            // Looks if Index in range AND if there's not an obstacle in that index
            if (row < 9 && !obstacles.getObstacles(row + 1, col)) {
                // set new point below
                new_point = new PathPoints(current_point.getRow() + 1, current_point.getCol());

                // if point was not added before
                if (!compareInVector(new_point) && !compareInHashmap(new_point, evaluated_points))
                    evaluated_points.put(new_point, distance(new_point));
            }

            /* Search right */
            // Looks if Index in range AND if there's not an obstacle in that index
            if (col < 9 && !obstacles.getObstacles(row, col + 1)) {
                // set new point to right
                new_point = new PathPoints(current_point.getRow(), current_point.getCol() + 1);

                // if point was not added before
                if (!compareInVector(new_point) && !compareInHashmap(new_point, evaluated_points))
                    evaluated_points.put(new_point, distance(new_point));

            }

            current_point = maxValuePoint(evaluated_points);

            if (!(current_point.getRow() == 0 && current_point.getCol() == 0))
                vector.add(current_point);

            if ((current_point.getRow() == finish_point.getRow() && current_point.getCol() == finish_point.getCol())
                    || evaluated_points.isEmpty())
                break;

            evaluated_points.clear();
        }
    }

    // BEST FIRST SEARCH ALGORITHM
    public void bestSearch() {
        // Initialize vector, row, col and obstacles
        vector = new Vector<PathPoints>(1);
        int row = 0, col = 0;
        obstacles.getObstacles();
        PathPoints new_point;

        // initialize map to store evaluated points with its distance value
        HashMap<PathPoints, Integer> evaluated_points = new HashMap<PathPoints, Integer>();

        // set, evaluate and add initial point
        PathPoints initial_point = new PathPoints(start_point.getRow(), start_point.getCol());
        evaluated_points.put(initial_point, distance(initial_point));

        while (!evaluated_points.isEmpty()) {
            // Get Point with max Value from HashMap
            PathPoints current_point = maxValuePoint(evaluated_points);
            evaluated_points.remove(current_point);
            vector.add(current_point);

            row = current_point.getRow();
            col = current_point.getCol();

            // If the current point is the end exits the cycle
            if (current_point.getRow() == finish_point.getRow() && current_point.getCol() == finish_point.getCol()) {
                evaluated_points.clear(); // Empty stack
                break;
            }

            /* Search Upwards */
            // Looks if Index in range AND if there's not an obstacle in that index
            if (row > 0 && !obstacles.getObstacles(row - 1, col)) {
                // Creates a point above the current position
                new_point = new PathPoints(current_point.getRow() - 1, current_point.getCol());

                // If the point was added before, it doesn't add again
                if (!compareInVector(new_point) && !compareInHashmap(new_point, evaluated_points)) {
                    evaluated_points.put(new_point, distance(new_point));
                }
            }

            /* Search Leftwards */
            // Looks if Index in range AND if there's not an obstacle in that index
            if (col > 0 && !obstacles.getObstacles(row, col - 1)) {
                // Creates a point to the left of the current position
                new_point = new PathPoints(current_point.getRow(), current_point.getCol() - 1);

                // If the point was added before, it doesn't add again
                if (!compareInVector(new_point) && !compareInHashmap(new_point, evaluated_points)) {
                    evaluated_points.put(new_point, distance(new_point));
                }
            }

            /* Search Downwards */
            // Looks if Index in range AND if there's not an obstacle in that index
            if (row < 9 && !obstacles.getObstacles(row + 1, col)) {
                // Creates a point under the current position
                new_point = new PathPoints(current_point.getRow() + 1, current_point.getCol());

                // If the point was added before, it doesn't add again
                if (!compareInVector(new_point) && !compareInHashmap(new_point, evaluated_points)) {
                    evaluated_points.put(new_point, distance(new_point));
                }
            }

            /* Search Rightwards */
            // Looks if Index in range AND if there's not an obstacle in that index
            if (col < 9 && !obstacles.getObstacles(row, col + 1)) {
                // Creates a point to the right of the current position
                new_point = new PathPoints(current_point.getRow(), current_point.getCol() + 1);

                // If the point was added before, it doesn't add again
                if (!compareInVector(new_point) && !compareInHashmap(new_point, evaluated_points)) {
                    evaluated_points.put(new_point, distance(new_point));
                }
            }
        }
    }

    // A* SEARCH ALGORITHM
    public void aSearch() {
        // Initialize vector, row, col and obstacles
        vector = new Vector<PathPoints>(1);
        int row = 0, col = 0;
        obstacles.getObstacles();
        PathPoints new_point;

        // initialize map to store evaluated points with its distance value
        HashMap<PathPoints, Integer> evaluated_points = new HashMap<PathPoints, Integer>();

        // set, evaluate and add initial point
        PathPoints initial_point = new PathPoints(start_point.getRow(), start_point.getCol());
        evaluated_points.put(initial_point, distance(initial_point));

        boolean isFirst = true;

        while (!evaluated_points.isEmpty()) {
            // Get Point with max Value from HashMap
            PathPoints current_point = maxValuePoint(evaluated_points);
            if (!isFirst) {
                evaluated_points.remove(current_point);
            } else {
                evaluated_points.clear();
                isFirst = false;
            }
            vector.add(current_point);

            row = current_point.getRow();
            col = current_point.getCol();

            // If the current point is the end exits the cycle
            if (current_point.getRow() == finish_point.getRow() && current_point.getCol() == finish_point.getCol()) {
                evaluated_points.clear(); // Empty stack
                break;
            }

            /* Search Upwards */
            // Looks if Index in range AND if there's not an obstacle in that index
            if (row > 0 && !obstacles.getObstacles(row - 1, col)) {
                // Creates a point above the current position
                new_point = new PathPoints(current_point.getRow() - 1, current_point.getCol());

                // If the point was added before, it doesn't add again
                if (!compareInVector(new_point) && !compareInHashmap(new_point, evaluated_points)) {
                    evaluated_points.put(new_point,
                            heuristic_distance(distance(new_point), manhattan_function(current_point)));
                }
            }

            /* Search Leftwards */
            // Looks if Index in range AND if there's not an obstacle in that index
            if (col > 0 && !obstacles.getObstacles(row, col - 1)) {
                // Creates a point to the left of the current position
                new_point = new PathPoints(current_point.getRow(), current_point.getCol() - 1);

                // If the point was added before, it doesn't add again
                if (!compareInVector(new_point) && !compareInHashmap(new_point, evaluated_points)) {
                    evaluated_points.put(new_point,
                            heuristic_distance(distance(new_point), manhattan_function(current_point)));
                }
            }

            /* Search Downwards */
            // Looks if Index in range AND if there's not an obstacle in that index
            if (row < 9 && !obstacles.getObstacles(row + 1, col)) {
                // Creates a point under the current position
                new_point = new PathPoints(current_point.getRow() + 1, current_point.getCol());

                // If the point was added before, it doesn't add again
                if (!compareInVector(new_point) && !compareInHashmap(new_point, evaluated_points)) {
                    evaluated_points.put(new_point,
                            heuristic_distance(distance(new_point), manhattan_function(current_point)));
                }
            }

            /* Search Rightwards */
            // Looks if Index in range AND if there's not an obstacle in that index
            if (col < 9 && !obstacles.getObstacles(row, col + 1)) {
                // Creates a point to the right of the current position
                new_point = new PathPoints(current_point.getRow(), current_point.getCol() + 1);

                // If the point was added before, it doesn't add again
                if (!compareInVector(new_point) && !compareInHashmap(new_point, evaluated_points)) {
                    evaluated_points.put(new_point,
                            heuristic_distance(distance(new_point), manhattan_function(current_point)));
                }
            }
        }
    }

    // EVALUATION FUNCTIONS
    private int distance(PathPoints point) {
        // Evaluation is how close the point is to the end, lower distance equals better
        // evaluation
        int row_dist = Math.abs(finish_point.getRow() - point.getRow());
        int col_dist = Math.abs(finish_point.getCol() - point.getCol());

        return row_dist + col_dist;
    }

    private int manhattan_function(PathPoints current_point) {
        // As the AI doesn't go diagonally, we'll be using manhattan formula to get the
        // heuristic values
        // d(x, y) = |x1 - x2| + |y1 - y2|
        int x_value = Math.abs(current_point.getRow() - finish_point.getRow());
        int y_value = Math.abs(current_point.getCol() - finish_point.getCol());

        return x_value + y_value;
    }

    private int heuristic_distance(int distance, int manhattan_function) {
        // Heuristic value for A* algorithm
        // f(n) = distance(n) + heuristic(n)
        int heuristic = distance + manhattan_function;

        return heuristic;
    }
}