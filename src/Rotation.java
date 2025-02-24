import java.util.*;

public class Rotation {

    public static char[][] rotate90(char[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        char[][] hasil = new char[cols][rows];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                hasil[c][rows - r - 1] = matrix[r][c];
            }
        }
        return hasil;
    }

    public static char[][] reflectHorizontally(char[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        char[][] hasil = new char[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                hasil[r][cols - c - 1] = matrix[r][c];
            }
        }
        return hasil;
    }

    public static char[][] reflectVertically(char[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        char[][] hasil = new char[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                hasil[rows - r - 1][c] = matrix[r][c];
            }
        }
        return hasil;
    }

    public static List<char[][]> pemutaranPiece(char[][] piece) {
        List<char[][]> ListPemutaran = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            ListPemutaran.add(copyMatrix(piece)); 
            piece = rotate90(piece);
        }

        List<char[][]> mirror = new ArrayList<>();
        for (char[][] orientation : ListPemutaran) {
            mirror.add(reflectHorizontally(orientation));
            mirror.add(reflectVertically(orientation));
        }

        ListPemutaran.addAll(mirror);

        Set<String> uniqueOrientations = new HashSet<>();
        List<char[][]> uniqueListPemutaran = new ArrayList<>();

        for (char[][] orientation : ListPemutaran) {
            String orientationString = matrixToString(orientation);
            if (uniqueOrientations.add(orientationString)) {
                uniqueListPemutaran.add(orientation);
            }
        }

        return uniqueListPemutaran;
    }

    public static String matrixToString(char[][] matrix) {
        StringBuilder sb = new StringBuilder();
        for (char[] row : matrix) {
            for (char ch : row) {
                sb.append(ch);
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    private static char[][] copyMatrix(char[][] matrix) {
        char[][] copy = new char[matrix.length][];
        for (int i = 0; i < matrix.length; i++) {
            copy[i] = Arrays.copyOf(matrix[i], matrix[i].length);
        }
        return copy;
    }
}