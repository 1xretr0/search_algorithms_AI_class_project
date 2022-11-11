// This class generates the different obstacles the AI will use 

public class Obstacles{
    // Array of Arrays to store obstacles positions
    private static boolean obstacles[][];
    private static int template = 0;

    public Obstacles(){
        obstacles = new boolean[10][10];
    }

    // Get obstacle in specific coord
    public boolean getObstacles(int row, int col){
        return obstacles[row][col];
    }

    public boolean[][] getObstacles(){
        return obstacles;
    }

    // Set new obstacles by template
    public void setObstacles(int t){
        obstacles = new boolean[10][10];

        template = t;

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

    // Set obstacles by hand
    public void setObstacles(int row, int col){
        if(obstacles[row][col]) { obstacles[row][col] = false; }
        else if(!obstacles[row][col]) { obstacles[row][col] = true; }
    }
}
