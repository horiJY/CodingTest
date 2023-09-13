import java.util.ArrayList;
import java.util.List;

class Solution {
    List<Integer>[] graph;
    boolean[] used;

    public int solution(int n, int[][] lighthouse) {
        graph = new ArrayList[n + 1];
        used = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] ea : lighthouse) {
            graph[ea[0]].add(ea[1]);
            graph[ea[1]].add(ea[0]);
        }

        checkLightHouse(1, 1);

        int answer = 0;
        for (boolean use : used) {
            if (use) {
                answer++;
            }
        }

        return answer;
    }

    private void checkLightHouse(int cur, int prev) {
        for (int c : graph[cur]) {
            if (c == prev) { // 연결된 노드가 상위노드 1개 -> 리프노드
                continue;
            }

            checkLightHouse(c, cur);

            if (!used[c] && !used[cur]) {
                used[cur] = true;
            }
        }
    }
}