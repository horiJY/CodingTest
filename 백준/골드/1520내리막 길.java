import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[][] moving = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
    static int[][] map;
    static int[][] dp;
    static int ROW;
    static int COL;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 지도 세로 크기 M, 가로 크기 N (1 <= N,M <= 500)
        String[] input = br.readLine().split(" ");
        ROW = Integer.parseInt(input[0]);
        COL = Integer.parseInt(input[1]);

        map = new int[ROW][COL];
        dp = new int[ROW][COL];

        for (int i = 0; i < ROW; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < COL; j++) {
                map[i][j] = Integer.parseInt(input[j]);
                dp[i][j] = -1;
            }
        }

        System.out.println(dfs(0, 0));
    }

    // 제일 왼쪽 위 지점에서 출발하여 제일 오른쪽 아래 지점까지 항상 내리막길로만 이동
    static int dfs(int r, int c) {
        if (r == ROW - 1 && c == COL - 1) {
            return 1;
        }

        if (dp[r][c] != -1) {
            return dp[r][c];
        }

        dp[r][c] = 0;
        for (int i = 0; i < moving.length; i++) { // 지점 사이의 이동은 지도에서 상하좌우 이웃한 곳끼리만 가능
            int dr = r + moving[i][0];
            int dc = c + moving[i][1];

            if (dr < 0 || dr >= ROW || dc < 0 || dc >= COL) {
                continue;
            }

            if (map[r][c] > map[dr][dc]) {
                dp[r][c] += dfs(dr, dc);
            }
        }

        return dp[r][c];
    }
}
