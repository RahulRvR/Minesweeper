import java.util.Random;

/**
 * This class replicates the Minesweeper board. Handles initialization, cell updates and
 * game decision.
 */
public class GameBoard {

    private static char UNTOUCHED_CELL = '◼';
    private static char OPENED_CELL = '.';
    private static char MINE_CELL = 'M';

    char[][] grid;
    int[][] mines;

    int numOfMines;
    int gridSize;

    public GameBoard(final int size, final int mines) {
        this.gridSize = size;
        this.grid = new char[size][size];
        this.mines = new int[size][size];
        this.numOfMines = mines;

        initializeBoard();
    }

    private void initializeBoard() {
        System.out.println("Initializing Board..");
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                grid[i][j] = UNTOUCHED_CELL;
            }
        }
        loadMines();
        System.out.println("Board is Ready!!!");
        printBoard(false);
    }

    /**
     * Load mines in the board randomly.
     */
    private void loadMines() {
        Random random = new Random();
        int row, col, minesCount = 0;

        while (minesCount != numOfMines) {
            row = random.nextInt(gridSize);
            col = random.nextInt(gridSize);
            if (mines[row][col] == 1) {
                continue;
            }

            // 1 denotes a mine.
            mines[row][col] = 1;
            minesCount++;
        }
    }

    /**
     * Print current board.
     */
    public void printBoard(boolean showMines) {
        System.out.println("\n");
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                if (showMines && mines[i][j] == 1) {
                    System.out.print(MINE_CELL + "\t");
                } else {
                    System.out.print(grid[i][j] + "\t");
                }
            }
            System.out.println("");
        }
    }

    /**
     * Updates selected cell to OPENED_CELL and recursively updates
     * adjcent cell
     *
     * @param row    position
     * @param column position
     */
    public void updateCell(final int row, final int column) {
        if (row < 0 || row >= gridSize || column < 0 || column >= gridSize
                || grid[row][column] != UNTOUCHED_CELL) {
            return;
        }

        int adjacentMines = hasMinesinAdjacentCell(row, column);

        if (adjacentMines > 0) {
            grid[row][column] = Character.forDigit(adjacentMines, 10);
            return;
        }

        grid[row][column] = OPENED_CELL;

        updateCell(row - 1, column);
        updateCell(row, column - 1);
        updateCell(row + 1, column);
        updateCell(row, column + 1);
    }

    /**
     * Checks for mines in adjacent cells. A cell which is not on the edges can have
     * a maximum of 6 mines in the adjacent cell.
     **/
    private int hasMinesinAdjacentCell(int row, int col) {
        int minesCount = 0;
        if (row - 1 >= 0 && mines[row - 1][col] == 1) {
            minesCount++;
        }
        if (row + 1 < gridSize && mines[row + 1][col] == 1) {
            minesCount++;
        }
        if (row - 1 >= 0 && col - 1 >= 0 && mines[row - 1][col - 1] == 1) {
            minesCount++;
        }
        if (row + 1 < gridSize && col + 1 < gridSize && mines[row + 1][col + 1] == 1) {
            minesCount++;
        }
        if (col - 1 >= 0 && mines[row][col - 1] == 1) {
            minesCount++;
        }
        if (col + 1 < gridSize && mines[row][col + 1] == 1) {
            minesCount++;
        }

        return minesCount;
    }

    /**
     * Checks if the specified position is a mine.
     **/
    public boolean isMine(final int row, final int column) {
        return mines[row - 1][column - 1] == 1;
    }

    /**
     * Checks if the specified position is open.
     **/
    public boolean isOpen(final int row, final int column) {
        return grid[row - 1][column - 1] == OPENED_CELL;
    }

    /**
     * Checks if user won the game
     */
    public boolean hasWon() {
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                if (grid[i][j] == UNTOUCHED_CELL && mines[i][j] != 1) {
                    return false;
                }
            }
        }
        return true;
    }

}
