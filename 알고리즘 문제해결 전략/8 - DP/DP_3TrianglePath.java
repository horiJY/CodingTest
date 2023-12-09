import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class DP_3TrianglePath {
    static int[][] cache, board;
    static int N;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int C = Integer.parseInt(br.readLine());
        while (C-- > 0) {
            N = Integer.parseInt(br.readLine());
            cache = new int[N][N];
            board = new int[N][N];
            for (int i = 0; i < N; i++) {
                Arrays.fill(cache[i], -1);
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                int j = 0;
                while (st.hasMoreTokens()) {
                    board[i][j++] = Integer.parseInt(st.nextToken());
                }
            }
            System.out.println(getMaxValue(0, 0));
        }
    }

    private static int getMaxValue(int y, int x) {
        if (y == N - 1) {
            return board[y][x];
        }

        if (cache[y][x] == -1) {
            cache[y][x] = Math.max(getMaxValue(y + 1, x), getMaxValue(y + 1, x + 1)) + board[y][x];
        }

        return cache[y][x];
    }
}
