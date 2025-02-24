import java.io.*;
import java.util.*;

public class solve {
    private static int N, M, P;
    private static char[][] board;
    private static List<char[][]> pieces = new ArrayList<>();
    private static int caseCount = 0;
    private static long startTime; 

    
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan nama file input : ");
        String filename = scanner.nextLine();
        String filepath = "/Users/jonathankenanbudianto/Documents/coding/java/Tucil_stima_1_(IQ puzzler pro)/test/input/" + filename;

        startTime = System.currentTimeMillis(); 
        loadPuzzle(filepath);

        if (solvePuzzle(0)) {
            handleSolution();
        } else {
            System.out.println("No solution found.");
        }
    }

    private static void handleSolution() throws IOException {
        String solusi = Print.getBoardAsPlainString(board); // Menggunakan warna dalam ngeprint
        Print.printBoard(board);

        // Output waktu dan casecount
        long endTime = System.currentTimeMillis();
        System.out.println("Waktu pencarian: " + (endTime - startTime) + " ms");
        System.out.println("Banyak kasus yang ditinjau: " + caseCount);
        System.out.print("Apakah anda ingin menyimpan solusi dalam file? (ya/tidak): ");
        Scanner scanner = new Scanner(System.in);
        String response = scanner.nextLine();

        if (response.equalsIgnoreCase("ya")) {
            saveSolution(solusi);
            System.out.print("Apakah anda ingin menyimpan solusi sebagai gambar? (ya/tidak): ");
            response = scanner.nextLine();
            if (response.equalsIgnoreCase("ya")) {
                Print.saveSolutionAsImage(board, "/Users/jonathankenanbudianto/Documents/coding/java/Tucil_stima_1_(IQ puzzler pro)/test/output/solusi.png");
                System.out.println("Solusi telah disimpan sebagai gambar solusi.png");
            } else {
                System.out.println("Solusi tidak disimpan sebagai gambar.");
            }
        } else {
            System.out.println("Solusi tidak disimpan.");
        }
    }

    private static void saveSolution(String solusi) {
        try {
            FileWriter writer = new FileWriter("/Users/jonathankenanbudianto/Documents/coding/java/Tucil_stima_1_(IQ puzzler pro)/test/output/solusi.txt");
            writer.write(solusi);
            writer.close();
            System.out.println("Solusi telah disimpan dalam berkas solusi.txt");
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat menyimpan solusi.");
            e.printStackTrace();
        }
    }

    private static void loadPuzzle(String filename) throws IOException {
        Scanner scanner = new Scanner(new File(filename));
        N = scanner.nextInt();
        M = scanner.nextInt();
        P = scanner.nextInt();
        scanner.nextLine(); // Pindah ke baris berikutnya

        String boardType = scanner.nextLine(); // Baca tipe board

        if (boardType.equals("DEFAULT")) {
        board = new char[N][M];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    board[i][j] = '.';
                }
            }
        } 
        else if (boardType.equals("CUSTOM")) {
            board = new char[N][M];
            for (int i = 0; i < N; i++) {
                String line = scanner.nextLine();
                for (int j = 0; j < M; j++) {
                    board[i][j] = line.charAt(j);
                }
            }
        }

        int jumlahjenishuruf = 0; // Penghitung jumlah jenis huruf yang sudah diproses
        Map<Character, List<String>> listperhuruf = new LinkedHashMap<>();

        while (scanner.hasNextLine() && jumlahjenishuruf < P+10) {
            String line = scanner.nextLine();
            if (line.isEmpty()) continue; // Lewati baris kosong

            // Ambil huruf pertama sebagai label
            char jenishuruf = '\0';
            for (char ch : line.toCharArray()) {
                if (ch != ' ') {
                    jenishuruf = ch;
                    break;
                }
            }

            // Jika huruf baru ditemukan dan jumlah sudah mencapai P, berhenti membaca
            if (!listperhuruf.containsKey(jenishuruf)) {
                if (jumlahjenishuruf >= P+10) break;
                listperhuruf.put(jenishuruf, new ArrayList<>());
                jumlahjenishuruf++;
            }

            listperhuruf.get(jenishuruf).add(line);
        }
        scanner.close();
        
        // Konversi setiap bentuk ke dalam matriks dan simpan ke daftar pieces
        for (Map.Entry<Character, List<String>> entry : listperhuruf.entrySet()) {
            char jenishuruf = entry.getKey();
            pieces.add(convertToMatrix(entry.getValue(), jenishuruf));
        }
    }
    
    private static char[][] convertToMatrix(List<String> listperhuruf, char jenishuruf) {
        int rows = listperhuruf.size();
        int cols = 0;

        // Hitung panjang maksimal dari semua baris
        for (int i = 0; i < listperhuruf.size(); i++) {
            String spesificline = listperhuruf.get(i);
            if (spesificline.length() > cols) {
                cols = spesificline.length();
            }
        }

        char[][] matrix = new char[rows][cols];

        // Mengisi matriks dengan karakter yang sesuai
        for (int i = 0; i < rows; i++) {
            String spesificline = listperhuruf.get(i);
            for (int j = 0; j < cols; j++) {
                if (j < spesificline.length() && spesificline.charAt(j) == jenishuruf) {
                    matrix[i][j] = jenishuruf;
                } else {
                    matrix[i][j] = '.'; // Mengisi bagian kosong dengan titik
                }
            }
        }
        return matrix;
    }

    private static boolean solvePuzzle(int pieceIndex) {
        if (pieceIndex == pieces.size()) {
            caseCount++;
            return Conditions.isBoardFilled(board);
        }
        char[][] piece = pieces.get(pieceIndex);
        List<char[][]> ListPemutaran = Rotation.pemutaranPiece(piece);

        for (char[][] currentPiece : ListPemutaran) {
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < M; c++) {
                    if (Conditions.canPlace(currentPiece, board, r, c)) {
                        Conditions.placePiece(currentPiece, board, r, c);
                        if (solvePuzzle(pieceIndex + 1)) return true;
                        Conditions.removePiece(currentPiece, board, r, c);
                        caseCount++;
                    }
                }
            }
        }
        return false;
    }
}
   