import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    int[][] moving = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
    long[][] arrivalMatrix, baseMatrix;
    int ROW, COL, N;
    int DIVISOR = 1_000_000_007;

    public int solution(int[][] grid, int[] d, int k) {
        ROW = grid.length;
        COL = grid[0].length;
        N = ROW * COL;
        // dp[순서][출발칸][목적지칸]
        long[][][] cache = new long[d.length + 1][N][N];
        int answer = 0;

        for (int i = 0; i < N; i++) {
            cache[0][i][i] = 1;
        }

        for (int di = 1; di <= d.length; di++) {
            for (int n = 0; n < N; n++) {
                int y = n / COL;
                int x = n % COL;

                for (int i = 0; i < 4; i++) {
                    int ny = y + moving[i][0];
                    int nx = x + moving[i][1];

                    if (nx < 0 || nx >= COL || ny < 0 || ny >= ROW || grid[ny][nx] - grid[y][x] != d[di - 1]) {
                        continue;
                    }

                    for (int j = 0; j < N; j++) {
                        cache[di][j][ny * COL + nx] += cache[di - 1][j][n] % DIVISOR;
                        cache[di][j][ny * COL + nx] %= DIVISOR;
                    }
                }
            }
        }

        Deque<Boolean> squareStack = new ArrayDeque<>();
        while (k > 1) {
            if (k % 2 == 0) {
                k /= 2;
                squareStack.push(true);
            } else {
                k -= 1;
                squareStack.push(false);
            }
        }

        arrivalMatrix = cache[cache.length - 1].clone();
        baseMatrix = arrivalMatrix.clone();
        while (!squareStack.isEmpty()) {
            if (squareStack.pop()) {
                arrivalMatrix = multipleMatrix(arrivalMatrix, arrivalMatrix);
            } else {
                arrivalMatrix = multipleMatrix(arrivalMatrix, baseMatrix);
            }
        }

        for (long[] is : arrivalMatrix) {
            for (long e : is) {
                answer += e;
                answer %= DIVISOR;
            }
        }
        return answer;
    }

    private long[][] multipleMatrix(long[][] matrix1, long[][] matrix2) {
        long[][] result = new long[N][N];

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                for (int k = 0; k < N; k++) {
                    result[r][c] += (matrix1[r][k] % DIVISOR) * (matrix2[k][c] % DIVISOR);
                    result[r][c] %= DIVISOR;
                }
            }
        }

        return result;
    }
}