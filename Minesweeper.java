import java.util.*;

public class Minesweeper {

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
                System.out.println("Congratulations!! You Won!!!");
                board.printBoard(true);
                break;
            }

            try {
                System.out.println("\n\nEnter row(<" + size + ")");
                int row = scan.nextInt();
                System.out.println("Enter column(<" + size + ")");
                int col = scan.nextInt();

                if (board.isOpen(row, col)) {
                    System.out.println("Selected position is already open");
                    continue;
                }

                if (row > size) {
                    System.out.println("Invalid Row Entry!! Cannot be greater than" + size);
                    continue;
                }

                if (col > size) {
                    System.out.println("Invalid Column Entry!! Cannot be greater than" + size);
                    continue;
                }

                if (board.isMine(row, col)) {
                    System.out.println("You Lost!!!");
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
