public class Conditions {
    public static boolean canPlace(char[][] piece, char[][] board, int r, int c) {
        int rows = piece.length, cols = piece[0].length;
        int N = board.length, M = board[0].length;
        if (r + rows > N || c + cols > M) return false;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (piece[i][j] != '.' && board[r + i][c + j] != '.') {
                    return false;
                }
            }
        }
        return true;
    }

    public static void placePiece(char[][] piece, char[][] board, int r, int c) {
        int rows = piece.length, cols = piece[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (piece[i][j] != '.') {
                    board[r + i][c + j] = piece[i][j];
                }
            }
        }
    }

    public static void removePiece(char[][] piece, char[][] board, int r, int c) {
        int rows = piece.length, cols = piece[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (piece[i][j] != '.') {
                    board[r + i][c + j] = '.';
                }
            }
        }
    }
    public static boolean isBoardFilled(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == '.') {
                    return false;
                }
            }
        }
        return true;
    }
}
