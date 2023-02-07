import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int[][] edge, dp;
    static int N, NOT_VISIT, BLOCK;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        /*
         * BLOCK: 출발지로 돌아갈 수 없는 경우, 모든 경우에서 조합될 수 없는 수로 지정
         * NOT_VISIT보다 크면 dp 갱신안된다.
         */
        BLOCK = N * 1000000 + 1;
        NOT_VISIT = BLOCK * 2;

        // dp[i][j] : 출발지까지 남은 최소 비용
        // 배열 길이 == 노드 개수
        dp = new int[N][(1 << N) - 1];
        // edge[i][j] 도시 i -> j 이동 비용
        edge = new int[N][N];

        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                edge[i][j] = Integer.valueOf(input[j]);
            }
            Arrays.fill(dp[i], NOT_VISIT);
        }

        dp[0][0] = 0;
        System.out.println(tsp(0, 0));
    }

    static int tsp(int node, int visited) {
        // 현재 노드 방문 처리
        visited = visited | (1 << node);

        /* 마지막 노드에서 처음노드 복귀 체크 */
        if (visited == (1 << N) - 1) {
            if (edge[node][0] == 0) {
                // 갈 수 없는 경우 NOT_VISIT을 return 시킨다면 나중에 방문하지 않은 상황으로 판단하여 다시 방문하는 상황 발생 => 시간초과
                return BLOCK;
            }
            return edge[node][0];
        }

        if (dp[node][visited] != NOT_VISIT) {
            return dp[node][visited];
        }

        for (int next = 0; next < N; next++) {
            // (visited & (1 << next)) == 0 : 미방문 노드 체크
            if (edge[node][next] != 0 && (visited & (1 << next)) == 0) {
                /**
                 * dp[node][visited] : visited를 경유하여 node에 있을 경우 나머지를 경유하여 출발지로 돌아가는 최소 비용
                 */
                int temp = tsp(next, visited) + edge[node][next];
                if (dp[node][visited] > temp) {
                    dp[node][visited] = temp;
                }
            }
        }

        return dp[node][visited];
    }
}