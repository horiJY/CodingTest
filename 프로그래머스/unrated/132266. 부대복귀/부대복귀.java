import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

class Solution {
    static List<List<Integer>> route;
    static int[] minDist;

    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        route = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            route.add(new ArrayList<>());
        }
        for (int[] road : roads) {
            route.get(road[0]).add(road[1]);
            route.get(road[1]).add(road[0]);
        }

        minDist = new int[n + 1];
        Arrays.fill(minDist, Integer.MAX_VALUE);
        executeMinDist(destination);

        int[] answer = new int[sources.length];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = minDist[sources[i]] < Integer.MAX_VALUE ? minDist[sources[i]] : -1;
        }

        return answer;
    }

    private void executeMinDist(int destination) {
        minDist[destination] = 0;
        Deque<Integer> que = new ArrayDeque<>();
        que.add(destination);
        while (!que.isEmpty()) {
            int cur = que.pop();
            for (int next : route.get(cur)) {
                if (minDist[next] > minDist[cur] + 1) {
                    minDist[next] = minDist[cur] + 1;
                    que.add(next);
                }
            }
        }

    }
}