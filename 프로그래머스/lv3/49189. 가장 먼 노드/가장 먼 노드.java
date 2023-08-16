import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

class Solution {
    public int solution(int n, int[][] edge) {
        int answer = 0;
        List<Integer>[] graph = new ArrayList[n + 1];
        for (int i = 1; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] is : edge) {
            int a = is[0];
            int b = is[1];
            graph[a].add(b);
            graph[b].add(a);
        }

        int max = 0;
        int[] memo = new int[n + 1];
        Arrays.fill(memo, Integer.MAX_VALUE);
        memo[1] = 0;

        Deque<Integer> que = new ArrayDeque<>();
        que.offer(1);
        while (!que.isEmpty()) {
            int cur = que.poll();

            for (int e : graph[cur]) {
                if (memo[e] == Integer.MAX_VALUE) {
                    memo[e] = memo[cur] + 1;
                    que.offer(e);
                    max = Math.max(max, memo[e]);
                }
            }
        }

        for (int i : memo) {
            if (i == max) {
                answer++;
            }
        }

        return answer;
    }
}