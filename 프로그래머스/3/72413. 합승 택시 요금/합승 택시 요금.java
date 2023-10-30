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

        for (int mid = 1; mid <= n; mid++)
            for (int start = 1; start <= n; start++)
                for (int end = 1; end <= n; end++)
                    map[start][end] = Math.min(map[start][end], map[start][mid] + map[mid][end]);

        int answer = Integer.MAX_VALUE;
        for (int m = 1; m <= n; m++) {
            answer = Math.min(answer, map[s][m] + map[m][a] + map[m][b]);
        }

        return answer;
    }
}