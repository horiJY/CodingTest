import java.util.Arrays;

class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int[][] map = new int[n + 1][n + 1];

        for (int i = 1; i < map.length; i++) {
            Arrays.fill(map[i], 1_234_567);
            map[i][i] = 0;
        }

        for (int[] e : fares) {
            map[e[0]][e[1]] = map[e[1]][e[0]] = e[2];
        }

        for (int m = 1; m <= n; m++)
            for (int u = 1; u <= n; u++)
                for (int v = 1; v <= n; v++)
                    map[u][v] = Math.min(map[u][v], map[u][m] + map[m][v]);

        int answer = Integer.MAX_VALUE;
        for (int m = 1; m <= n; m++) {
            answer = Math.min(answer, map[s][m] + map[m][a] + map[m][b]);
        }

        return answer;
    }
}