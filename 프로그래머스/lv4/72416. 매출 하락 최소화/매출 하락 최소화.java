import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {

    Map<Integer, List<Integer>> memberMap;
    int[][] cache;
    int[] cost;

    public int solution(int[] sales, int[][] links) {
        memberMap = new HashMap<>();
        for (int[] is : links) {
            List<Integer> ml = memberMap.get(is[0]);
            if (ml == null) {
                ml = new ArrayList<Integer>();
            }

            ml.add(is[1]);
            memberMap.put(is[0], ml);
        }

        cost = sales;
        // cache[미참][참여]
        cache = new int[sales.length + 1][2];
        DFS(1);

        return Math.min(cache[1][0], cache[1][1]);
    }

    private void DFS(int num) {
        List<Integer> cl = memberMap.get(num);
        cache[num][1] = cost[num - 1];

        if (cl == null) {
            return;
        }

        int childCost = Integer.MAX_VALUE;
        for (Integer child : cl) {
            DFS(child);

            if (cache[child][0] < cache[child][1]) {
                // 자식 미참여
                cache[num][0] += cache[child][0];
                cache[num][1] += cache[child][0];
                childCost = Math.min(childCost, cache[child][1] - cache[child][0]);
            } else {
                // 자식 참여
                cache[num][0] += cache[child][1];
                cache[num][1] += cache[child][1];
                childCost = 0;
            }
        }

        cache[num][0] += childCost;
    }
}