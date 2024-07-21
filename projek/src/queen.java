import java.util.Scanner;

public class queen {
    int N;

    // Konstruktor untuk N
    queen(int N) {
        this.N = N;
    }

    // Fungsi untuk mencetak solusi
    void printSolution(int board[][]) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 1)
                    System.out.print("Q ");
                else
                    System.out.print(". ");
            }
            System.out.println();
        }
    }

    // Fungsi utilitas untuk memeriksa apakah ratu dapat ditempatkan di
    // board[row][col].
    boolean isSafe(int board[][], int row, int col) {
        int i, j;
        // Sisi kiri
        for (i = 0; i < col; i++)
            if (board[row][i] == 1)
                return false;
        // Diagonal atas di sisi kiri
        for (i = row, j = col; i >= 0 && j >= 0; i--, j--)
            if (board[i][j] == 1)
                return false;
        // Diagonal bawah di sisi kiri
        for (i = row, j = col; j >= 0 && i < N; i++, j--)
            if (board[i][j] == 1)
                return false;
        return true;
    }

    // Fungsi rekursif
    boolean solveNQUtil(int board[][], int col) {
        // Base case (Ratu sudah ditempatkan semua)
        if (col >= N)
            return true;
        // Pertimbangkan kolom ini dan coba tempatkan ratu di semua baris satu per satu
        for (int i = 0; i < N; i++) {
            // Jika ratu dapat ditempatkan board[i][col]
            if (isSafe(board, i, col)) {
                // Tempatkan ratu di board[i][col]
                board[i][col] = 1;
                // Rekursif untuk meletakkan ratu selanjutnya
                if (solveNQUtil(board, col + 1))
                    return true;
                // Jika penempatan ratu tidak menghasilkan solusi, hapus ratu dari board[i][col]
                board[i][col] = 0; // Ini adalah backtrack
            }
        }
        // Jika ratu tidak dapat ditempatkan di baris mana pun di kolom ini, artinya
        // tidak bisa
        return false;
    }

    // Fungsi utama
    boolean solveNQ() {
        // Board berisi 0 semua
        int board[][] = new int[N][N];
        if (!solveNQUtil(board, 0)) {
            System.out.print("Solusi tidak ada");
            return false;
        }
        printSolution(board);
        return true;
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan jumlah ratu: ");
        int N = scanner.nextInt();
        queen Queen = new queen(N);
        Queen.solveNQ();
    }
}
