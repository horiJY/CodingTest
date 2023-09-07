import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class Solution {
    // 산봉우리 중 한 곳만 방문한 뒤 다시 원래의 출입구로 돌아오는 등산코스 중
    // intensity가 최소가 되는 등산코스
    Set<Integer> gateSet;
    Set<Integer> summitSet;
    List<int[]>[] graph;

    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        int[] answer = new int[] { Integer.MAX_VALUE, Integer.MAX_VALUE };
        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        gateSet = Arrays.stream(gates).boxed().collect(Collectors.toSet());
        summitSet = Arrays.stream(summits).boxed().collect(Collectors.toSet());

        for (int[] path : paths) {
            if (gateSet.contains(path[0]) || summitSet.contains(path[1])) {
                // gate : 다른 곳으로 이동만 가능
                graph[path[0]].add(new int[] { path[1], path[2] });
            } else if (gateSet.contains(path[1]) || summitSet.contains(path[0])) {
                // summit : 다른곳에서 올 수만 있음
                graph[path[1]].add(new int[] { path[0], path[2] });
            } else {
                graph[path[0]].add(new int[] { path[1], path[2] });
                graph[path[1]].add(new int[] { path[0], path[2] });
            }
        }

        Deque<int[]> que = new ArrayDeque<>();
        int[] intensity = new int[n + 1];
        Arrays.fill(intensity, Integer.MAX_VALUE);
        for (int e : gates) {
            intensity[e] = 0;
            que.add(new int[] { e, 0 });
        }

        while (!que.isEmpty()) {
            // cur = {connection node, weight}
            int[] cur = que.poll();

            if (cur[1] > intensity[cur[0]]) {
                continue;
            }

            for (int[] next : graph[cur[0]]) {
                int dist = Math.max(intensity[cur[0]], next[1]);
                if (intensity[next[0]] > dist) {
                    intensity[next[0]] = dist;
                    que.add(next);
                }
            }
        }

        Arrays.sort(summits);
        for (int s : summits) {
            if (intensity[s] < answer[1]) {
                answer[0] = s;
                answer[1] = intensity[s];
            }
        }

        return answer;
    }
}