import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int solution(int[] info, int[][] edges) {
        int answer = 0;
        Deque<Simulation> que = new ArrayDeque<>();
        que.offer(new Simulation(0, 0, 0, new boolean[info.length]));
        while (!que.isEmpty()) {
            Simulation cur = que.poll();
            cur.visit[cur.node] = true;
            if (info[cur.node] == 0) { // 양
                cur.sheepCnt++;
                answer = Math.max(answer, cur.sheepCnt);
            } else {// 늑대
                cur.wolfCnt++;
            }

            if (cur.sheepCnt <= cur.wolfCnt) {
                continue;
            }
            for (int[] edge : edges) {
                if (cur.visit[edge[0]] && !cur.visit[edge[1]]) {
                    que.add(new Simulation(edge[1], cur.sheepCnt, cur.wolfCnt, cur.visit));
                }
                if (cur.visit[edge[1]] && !cur.visit[edge[0]]) {
                    que.add(new Simulation(edge[0], cur.sheepCnt, cur.wolfCnt, cur.visit));
                }
            }
        }
        return answer;
    }
}

class Simulation {
    int node;
    int sheepCnt;
    int wolfCnt;
    boolean[] visit;

    public Simulation(int node, int sheepCnt, int wolfCnt, boolean[] visit) {
        this.node = node;
        this.sheepCnt = sheepCnt;
        this.wolfCnt = wolfCnt;
        this.visit = visit.clone();
    }

}