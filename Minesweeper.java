import java.util.*;

public class Minesweeper {

    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_GREEN = "\u001B[32m";

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the grid size:");
        int size = scan.nextInt();
        System.out.println("Enter the number of mines:");
        int mines = scan.nextInt();
        char a = 1;

        GameBoard board = new GameBoard(size, mines);

        while (true) {
            if (board.hasWon()) {
                System.out.println(ANSI_GREEN + "\nCongratulations!! You Won!!!" + ANSI_RESET);
                board.printBoard(true);
                break;
            }

            try {
                System.out.println("\n\nEnter row(1 - " + size + ")");
                int row = scan.nextInt();
                System.out.println("Enter column(1 -" + size + ")");
                int col = scan.nextInt();

                if (row > size) {
                    System.out.println("Invalid Row Entry!! Cannot be greater than " + size);
                    continue;
                }

                if (col > size) {
                    System.out.println("Invalid Column Entry!! Cannot be greater than " + size);
                    continue;
                }

                if (board.isOpen(row, col)) {
                    System.out.println("Selected position is already open");
                    continue;
                }

                if (board.isMine(row, col)) {
                    System.out.println(ANSI_RED + "You Lost!!!" + ANSI_RESET);
                    board.printBoard(true);
                    break;
                }

                board.updateCell(row - 1, col - 1);
                board.printBoard(false);
            } catch (InputMismatchException e) {
                System.out.println("Invalid Input");
                break;
            }
        }
    }

}
