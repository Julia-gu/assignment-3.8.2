public class TicTacToe {
    public static final int EMPTY = 0;
    public static final int PLAYER_X = 1;
    public static final int PLAYER_O = -1;

    public static boolean isValidMove(int[][] board, int row, int col) {
        return row >= 0 && row < board.length && col >= 0 && col < board[0].length
                && board[row][col] == EMPTY;
    }

    public static boolean makeMove(int[][] board, int row, int col, int player) {
        if (isValidMove(board, row, col)) {
            board[row][col] = player;
            return true;
        }
        return false;
    }

    public static int checkWin(int[][] board) {
        // Check rows
        for (int row = 0; row < 3; row++) {
            int sum = board[row][0] + board[row][1] + board[row][2];
            if (sum == 3) return PLAYER_X;
            if (sum == -3) return PLAYER_O;
        }

        // Check columns
        for (int col = 0; col < 3; col++) {
            int sum = board[0][col] + board[1][col] + board[2][col];
            if (sum == 3) return PLAYER_X;
            if (sum == -3) return PLAYER_O;
        }

        // Check main diagonal
        int mainDiagonalSum = board[0][0] + board[1][1] + board[2][2];
        if (mainDiagonalSum == 3) return PLAYER_X;
        if (mainDiagonalSum == -3) return PLAYER_O;

        // Check anti-diagonal
        int antiDiagonalSum = board[0][2] + board[1][1] + board[2][0];
        if (antiDiagonalSum == 3) return PLAYER_X;
        if (antiDiagonalSum == -3) return PLAYER_O;

        // No winner
        return EMPTY;
    }

    public static boolean isBoardFull(int[][] board) {
        for (int[] row : board) {
            for (int cell : row) {
                if (cell == EMPTY) return false;
            }
        }
        return true;
    }

    public static void printBoard(int[][] board) {
        System.out.println(" c  0 1 2");
        System.out.println("r  -------");
        for (int row = 0; row < board.length; row++) {
            System.out.print(row + " | ");
            for (int col = 0; col < board[row].length; col++) {
                int cell = board[row][col];
                if (cell == PLAYER_X) System.out.print("X ");
                else if (cell == PLAYER_O) System.out.print("O ");
                else System.out.print(". ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] board = new int[3][3];
        int currentPlayer = PLAYER_X;
        boolean gameRunning = true;

        while (gameRunning) {
            printBoard(board);
            System.out.println("Current player: " + (currentPlayer == PLAYER_X ? "X" : "O"));

            // Simulate a move
            int row, col;
            while (true) {
                row = (int) (Math.random() * 3);
                col = (int) (Math.random() * 3);
                if (isValidMove(board, row, col)) break;
            }

            if (makeMove(board, row, col, currentPlayer)) {
                int winner = checkWin(board);
                if (winner != EMPTY) {
                    printBoard(board);
                    System.out.println("Player " + (winner == PLAYER_X ? "X" : "O") + " wins!");
                    gameRunning = false;
                } else if (isBoardFull(board)) {
                    printBoard(board);
                    System.out.println("It's a draw!");
                    gameRunning = false;
                } else {
                    currentPlayer *= -1; // Switch player
                }
            }
        }
    }
}
