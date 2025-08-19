import java.util.Scanner;
import java.util.InputMismatchException;


public class TicTacToe {
    static char[][] board = new char[3][3];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean playAgain = true;

        while (playAgain) {
            resetBoard();
            char currentPlayer = 'X';
            boolean gameOver = false;

            System.out.println(" Welcome to Enhanced Tic-Tac-Toe!");
            printBoard();

            while (!gameOver) {
                System.out.println("Player " + currentPlayer + "'s turn:");
                int row = -1, col = -1;

                while (true) {
                    try {
                        System.out.print("Enter row (0,1,2): ");
                        row = sc.nextInt();
                        System.out.print("Enter col (0,1,2): ");
                        col = sc.nextInt();

                        if (row < 0 || row > 2 || col < 0 || col > 2 || board[row][col] != ' ') {
                            System.out.println(" Invalid move! Try again.");
                        } else {
                            break;
                        }
                    } catch (InputMismatchException e) {
                        System.out.println(" Please enter valid numbers!");
                        sc.nextLine(); // clear buffer
                    }
                }

                board[row][col] = currentPlayer;
                printBoard();

                if (hasWon(currentPlayer)) {
                    System.out.println(" Player " + currentPlayer + " wins!");
                    gameOver = true;
                } else if (isBoardFull()) {
                    System.out.println(" It's a draw!");
                    gameOver = true;
                } else {
                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                }
            }

            System.out.print(" Play again? (y/n): ");
            char choice = sc.next().charAt(0);
            playAgain = (choice == 'y' || choice == 'Y');
        }

        sc.close();
        System.out.println(" Thanks for playing!");
    }

    public static void resetBoard() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                board[i][j] = ' ';
    }

    public static void printBoard() {
        System.out.println("   0   1   2");
        for (int i = 0; i < 3; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < 3; j++) {
                System.out.print("| " + board[i][j] + " ");
            }
            System.out.println("|");
            System.out.println("  -----------");
        }
    }

    public static boolean hasWon(char player) {
        for (int i = 0; i < 3; i++)
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player)
                return true;

        for (int j = 0; j < 3; j++)
            if (board[0][j] == player && board[1][j] == player && board[2][j] == player)
                return true;

        if (board[0][0] == player && board[1][1] == player && board[2][2] == player)
            return true;

        if (board[0][2] == player && board[1][1] == player && board[2][0] == player)
            return true;

        return false;
    }

    public static boolean isBoardFull() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j] == ' ')
                    return false;
        return true;
    }
}