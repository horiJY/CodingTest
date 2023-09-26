import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

class Solution {
    public int[] solution(int[][] edges, int[] target) {
        int[] nextNodeIdx = new int[target.length + 1];
        List<Integer>[] tree = new ArrayList[target.length + 1];

        for (int[] edge : edges) {
            if (tree[edge[0]] == null) {
                tree[edge[0]] = new ArrayList<>();
            }
            tree[edge[0]].add(edge[1]);
            tree[edge[0]].sort(null);
        }

        Deque<Integer> sequence = new ArrayDeque<>();
        int[] nodeCnt = new int[target.length + 1];
        int[] targetOrig = target.clone();
        int idx = 0;
        int next = 1;
        while (true) {
            if (tree[next] != null) {
                idx = next;
                next = tree[idx].get(nextNodeIdx[idx]++);
                nextNodeIdx[idx] %= tree[idx].size();
            } else {
                sequence.offer(next);
                target[next - 1] -= 3;
                nodeCnt[next]++;

                if (nodeCnt[next] > targetOrig[next - 1]) {
                    return new int[] { -1 };
                }

                if ((int) Arrays.stream(target).filter(e -> e > 0).count() == 0) {
                    break;
                }
                next = 1;
            }
        }

        int[] answer = new int[sequence.size()];
        for (int i = 0; i < answer.length; i++) {
            int node = sequence.poll();
            nodeCnt[node]--;
            answer[i] = Math.max(1, targetOrig[node - 1] - 3 * nodeCnt[node]);
            targetOrig[node - 1] -= answer[i];
        }

        return answer;
    }
}