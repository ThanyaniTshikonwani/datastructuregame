import java.util.ArrayList;
import java.util.Random;




    public class DataStructureGame {
        private static final String LIFE = "#";
        private static final String DEATH = "+";

        public static void main(String... args) {

            String[][] board = generateBoard (10,10);

            int gen = 0;

            while(true) {
                clearScreen();
                printGrid(board);
                System.out.println("Gen " + (gen++) + "");

                String[][] superGrid = copyGrid(board);

                for (int i = 0; i < board.length; i++) {
                    for (int j = 0; j < board[i].length; j++) {
                        //System.out.println(board[i][j] + " " + i + ":" + j);

                        int life_forms = 0;

                        ArrayList<String> neighbors = new ArrayList<String>();

                        // find the neighbor cells to the current one.
                        // the board closes in itself due to the modulus operator
                        neighbors.add(board[Math.floorMod(i - 1, board.length)][Math.floorMod(j - 1, board[i].length)]);
                        neighbors.add(board[Math.floorMod(i - 1, board.length)][j]);
                        neighbors.add(board[Math.floorMod(i - 1, board.length)][Math.floorMod(j + 1, board[i].length)]);
                        neighbors.add(board[i][Math.floorMod(j - 1, board[i].length)]);
                        neighbors.add(board[i][Math.floorMod(j + 1, board[i].length)]);
                        neighbors.add(board[Math.floorMod(i + 1, board.length)][Math.floorMod(j - 1, board[i].length)]);
                        neighbors.add(board[Math.floorMod(i + 1, board.length)][j]);
                        neighbors.add(board[Math.floorMod(i + 1, board.length)][Math.floorMod(j + 1, board[i].length)]);

                        for (int k = 0; k < neighbors.size(); k++)
                            if (neighbors.get(k).compareTo(LIFE) == 0)
                                life_forms += 1;

                        if (board[i][j].compareTo(DEATH) == 0) {
                            // check if I can spawn a new cell
                            if (life_forms == 3) {
                                superGrid[i][j] = LIFE;
                            }
                        } else { // else current cell lives
                            // check if cell must die of over-population or under-population
                            if (life_forms < 2 || life_forms > 3) {
                                superGrid[i][j] = DEATH;
                            }
                        }
                    }
                }

                // copy board with new generations to the one
                // previously created
                board = copyGrid(superGrid);

                // wait 200 milliseconds before repeating the loop
                try {
                    Thread.sleep(100);
                } catch(InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        private static String[][] copyGrid(String[][] grid) {
            String[][] tempGrid = new String[grid.length][grid[0].length];

            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    tempGrid[i][j] = grid[i][j];
                }
            }

            return tempGrid;
        }

        public static String[][] generateBoard(int width, int height) {
            String[][] randomGrid = new String[height][width];
            Random rand = new Random();

            for (int i = 0; i < randomGrid.length; i++) {
                for (int j = 0; j < randomGrid[i].length; j++) {
                    Boolean god = rand.nextBoolean();

                    if (god)
                        randomGrid[i][j] = LIFE;
                    else
                        randomGrid[i][j] = DEATH;
                }
            }

            return randomGrid;
        }

        private static void clearScreen() {
            for(int i = 0; i < 100; i++)
                System.out.println();
        }

        public static void printGrid(String[][] matrix) {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    System.out.print(" " + matrix[i][j] + " ");
                }

                System.out.println();
            }
        }
    }
