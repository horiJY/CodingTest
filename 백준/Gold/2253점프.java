import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 2253 점프
 * 시간 제한 2초
 * 메모리 제한 128 MB
 */
public class Main {
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 돌 N(2 ≤ N ≤ 10,000)
        N = Integer.parseInt(st.nextToken());
        // 작은 돌의 개수 M(0 ≤ M ≤ N-2) : 올라갈수 없음
        int M = Integer.parseInt(st.nextToken());

        boolean[] move = new boolean[N + 1];
        Arrays.fill(move, true);
        for (int i = 0; i < M; i++) {
            move[Integer.parseInt(br.readLine())] = false;
        }
        // data input end

        // dp -> []:돌index, [][]:진입속도, value:최단점프횟수
        int[][] dp = new int[N + 1][(int) Math.sqrt(2 * N) + 2];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                dp[i][j] = N;
            }
        }
        dp[1][0] = 0;
        if (move[2]) {
            for (int i = 2; i <= N; i++) {
                if (!move[i]) {
                    continue;
                }

                for (int v = 1; v <= (int) Math.sqrt(2 * i); v++) {
                    if (move[i - v]) {
                        dp[i][v] = Math.min(dp[i - v][v - 1], Math.min(dp[i - v][v], dp[i - v][v + 1])) + 1;
                    }
                }
            }
        } else {
            System.out.println(-1);
            return;
        }

        int jumpCount = Integer.MAX_VALUE;
        for (int i = 1; i < dp[N].length; i++) {
            jumpCount = Math.min(jumpCount, dp[N][i]);
        }
        System.out.println(jumpCount);
    }

}