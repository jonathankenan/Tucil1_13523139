import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.imageio.ImageIO;

public class Print {

    public static final Map<Character, String> colorMap = new HashMap<>(); 
    static {
        colorMap.put('A', "\u001B[31;1m"); // Merah terang
        colorMap.put('B', "\u001B[32;1m"); // Hijau terang
        colorMap.put('C', "\u001B[33;1m"); // Kuning terang
        colorMap.put('D', "\u001B[34;1m"); // Biru terang
        colorMap.put('E', "\u001B[35;1m"); // Ungu terang
        colorMap.put('F', "\u001B[36;1m"); // Cyan terang
        colorMap.put('G', "\u001B[91;1m"); // Merah terang
        colorMap.put('H', "\u001B[92;1m"); // Hijau terang
        colorMap.put('I', "\u001B[93;1m"); // Kuning terang
        colorMap.put('J', "\u001B[94;1m"); // Biru terang
        colorMap.put('K', "\u001B[95;1m"); // Ungu terang
        colorMap.put('L', "\u001B[96;1m"); // Cyan terang
        colorMap.put('M', "\u001B[97;1m"); // Putih terang
        colorMap.put('N', "\u001B[30;1m"); // Hitam terang
        colorMap.put('O', "\u001B[90;1m"); // Abu-abu terang
        colorMap.put('P', "\u001B[31m"); // Merah
        colorMap.put('Q', "\u001B[32m"); // Hijau
        colorMap.put('R', "\u001B[33m"); // Kuning
        colorMap.put('S', "\u001B[34m"); // Biru
        colorMap.put('T', "\u001B[35m"); // Ungu
        colorMap.put('U', "\u001B[36m"); // Cyan
        colorMap.put('V', "\u001B[37m"); // Putih
        colorMap.put('W', "\u001B[90m"); // Abu-abu
        colorMap.put('X', "\u001B[91m"); // Merah terang
        colorMap.put('Y', "\u001B[92m"); // Hijau terang
        colorMap.put('Z', "\u001B[93m"); // Kuning terang
        colorMap.put('.', "\u001B[37m"); // Putih (default)
    }
    public static final String RESET = "\u001B[0m"; // Reset warna ke default

    public static String getBoardAsPlainString(char[][] board) {
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                sb.append(board[i][j]);
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void printBoard(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                String color = colorMap.getOrDefault(board[i][j], "\u001B[37m"); // Default putih
                System.out.print(color + board[i][j] + RESET);
            }
            System.out.println();
        }
    }

    public static void printPieces(List<char[][]> pieces) {
        System.out.println("Isi pieces:");
        for (int i = 0; i < pieces.size(); i++) {
            char[][] piece = pieces.get(i);
            System.out.println("[");
            for (int j = 0; j < piece.length; j++) {
                char[] row = piece[j];
                System.out.print("  " + Arrays.toString(row));
                System.out.println();
            }
            System.out.println("]");
        }
    }

    public static void printListPemutaran(List<char[][]> ListPemutaran) {
        int index = 0;
        for (char[][] orientation : ListPemutaran) {
            System.out.println("Orientation " + index + ":");
            for (char[] row : orientation) {
                for (char cell : row) {
                    System.out.print(cell);
                }
                System.out.println();
            }
            System.out.println();
            index++;
        }
    }

    public static void printListPerHuruf(Map<Character, List<String>> listperhuruf) {
        for (Map.Entry<Character, List<String>> entry : listperhuruf.entrySet()) {
            System.out.println("Character: " + entry.getKey());
            for (String line : entry.getValue()) {
                System.out.println(line);
            }
            System.out.println();
        }
    }

    public static void saveSolutionAsImage(char[][] board, String filename) throws IOException {
        int cellSize = 50; // ukuran cell di gambar
        int N = board.length;
        int M = board[0].length;
        BufferedImage image = new BufferedImage(M * cellSize, N * cellSize, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();

        // Pendefinisian warna digambar
        Map<Character, Color> colorMap = new HashMap<>();
        colorMap.put('A', Color.RED);
        colorMap.put('B', Color.GREEN);
        colorMap.put('C', Color.BLUE);
        colorMap.put('D', Color.YELLOW);
        colorMap.put('E', Color.MAGENTA);
        colorMap.put('F', Color.CYAN);
        colorMap.put('G', Color.ORANGE);
        colorMap.put('H', Color.PINK);
        colorMap.put('I', Color.LIGHT_GRAY);
        colorMap.put('J', Color.DARK_GRAY);
        colorMap.put('K', Color.BLACK);
        colorMap.put('L', Color.WHITE);
        colorMap.put('M', Color.GRAY);
        colorMap.put('N', Color.DARK_GRAY);
        colorMap.put('O', Color.LIGHT_GRAY);
        colorMap.put('P', Color.RED.darker());
        colorMap.put('Q', Color.GREEN.darker());
        colorMap.put('R', Color.BLUE.darker());
        colorMap.put('S', Color.YELLOW.darker());
        colorMap.put('T', Color.MAGENTA.darker());
        colorMap.put('U', Color.CYAN.darker());
        colorMap.put('V', Color.ORANGE.darker());
        colorMap.put('W', Color.PINK.darker());
        colorMap.put('X', Color.LIGHT_GRAY.darker());
        colorMap.put('Y', Color.DARK_GRAY.darker());
        colorMap.put('Z', Color.BLACK.darker());

        // Menggambar papan
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                char cell = board[i][j];
                if (cell != '.') {
                    g.setColor(colorMap.getOrDefault(cell, Color.BLACK));
                    g.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
                }
                g.setColor(Color.BLACK);
                g.drawRect(j * cellSize, i * cellSize, cellSize, cellSize);
            }
        }

        g.dispose();
        ImageIO.write(image, "png", new File(filename));
    }
}