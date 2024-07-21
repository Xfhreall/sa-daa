public class lcs {
    public static void main(String[] args) {
        String X = "ABDECAFG";
        String Y = "BDEACGFCA";
        int m = X.length();
        int n = Y.length();

        int[][] c = new int[m + 1][n + 1];
        char[][] b = new char[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (X.charAt(i - 1) == Y.charAt(j - 1)) {
                    c[i][j] = c[i - 1][j - 1] + 1;
                    b[i][j] = '\\';
                } else if (c[i - 1][j] >= c[i][j - 1]) {
                    c[i][j] = c[i - 1][j];
                    b[i][j] = '^';
                } else {
                    c[i][j] = c[i][j - 1];
                    b[i][j] = '<';
                }
            }
        }

        System.out.println("Tabel c:");
        System.out.print("    ");
        for (int j = 0; j < n; j++) {
            System.out.print(" " + Y.charAt(j));
        }
        System.out.println();
        for (int i = 0; i <= m; i++) {
            if (i > 0) {
                System.out.print(X.charAt(i - 1) + " ");
            } else {
                System.out.print("  ");
            }
            for (int j = 0; j <= n; j++) {
                System.out.print(" " + c[i][j]);
            }
            System.out.println();
        }

        System.out.println("\nTabel b:");
        System.out.print("    ");
        for (int j = 0; j < n; j++) {
            System.out.print(" " + Y.charAt(j));
        }
        System.out.println();
        for (int i = 0; i <= m; i++) {
            if (i > 0) {
                System.out.print(X.charAt(i - 1) + " ");
            } else {
                System.out.print("  ");
            }
            for (int j = 0; j <= n; j++) {
                System.out.print(" " + (b[i][j] != 0 ? b[i][j] : '0'));
            }
            System.out.println();
        }

        String lcs = getLCS(b, X, m, n);

        System.out.println("\nLCS nya adalah: " + lcs);
    }

    private static String getLCS(char[][] b, String X, int i, int j) {
        StringBuilder lcs = new StringBuilder();
        while (i > 0 && j > 0) {
            if (b[i][j] == '\\') {
                lcs.insert(0, X.charAt(i - 1));
                i--;
                j--;
            } else if (b[i][j] == '^') {
                i--;
            } else {
                j--;
            }
        }
        return lcs.toString();
    }
}
