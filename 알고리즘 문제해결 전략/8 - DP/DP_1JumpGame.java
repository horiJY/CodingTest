import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class DP_1JumpGame {
    static int[][] board, cache;
    static int N;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int C = Integer.parseInt(br.readLine());
        while (C-- > 0) {
            N = Integer.parseInt(br.readLine());
            board = new int[N][N];
            cache = new int[N][N];
            for (int i = 0; i < N; i++) {
                Arrays.fill(cache[i], -1);
                String[] input = br.readLine().split(" ");
                for (int j = 0; j < N; j++) {
                    board[i][j] = Integer.parseInt(input[j]);
                }
            }

            System.out.println(validArrival(0, 0) <= 0 ? "NO" : "YES");
        }
    }

    private static int validArrival(int y, int x) {
        if (x >= N || y >= N) {
            return 0;
        }

        if (x == N - 1 && y == N - 1) {
            return 1;
        }

        if (cache[y][x] == -1) {
            int next = board[y][x];
            cache[y][x] = Math.max(validArrival(y, x + next), validArrival(y + next, x));
        }

        return cache[y][x];
    }
}
