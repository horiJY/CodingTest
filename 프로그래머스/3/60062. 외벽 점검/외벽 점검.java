import java.util.ArrayList;
import java.util.List;

class Solution {
    int answer;

    public int solution(int n, int[] weak, int[] dist) {
        answer = Integer.MAX_VALUE;
        List<Integer> weakList = new ArrayList<>();
        for (Integer i : weak) {
            weakList.add(i);
            weakList.add(i + n);
        }
        weakList.sort(null);

        for (int i = 0; i < weak.length; i++) {
            DFS(weakList, dist, new boolean[dist.length], i, weak.length + i, 0);
        }

        return answer == Integer.MAX_VALUE ? -1 : answer;
    }

    public void DFS(List<Integer> weak, int[] dist, boolean[] visit, int start, int limit, int requireDist) {
        if (start >= limit) {
            answer = Math.min(answer, requireDist);
            return;
        }

        for (int i = 0; i < dist.length; i++) {
            if (visit[i]) {
                continue;
            }
            visit[i] = true;
            int end = start + 1;

            while (end < limit && (weak.get(end) - weak.get(start)) <= dist[i]) {
                end++;
            }

            DFS(weak, dist, visit, end, limit, requireDist + 1);
            visit[i] = false;
        }
        return;
    }
}