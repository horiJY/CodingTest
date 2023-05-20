import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int solution(int x, int y, int n) {
        Deque<Simulation> que = new ArrayDeque<>();
        que.add(new Simulation(y, 0));
        while (!que.isEmpty()) {
            Simulation cur = que.pop();
            if (cur.x < x) {
                continue;
            }
            if (cur.x == x) {
                return cur.cnt;
                // answer = Math.min(answer, cur.cnt);
            }
            if (cur.x - n >= x) {
                que.add(new Simulation(cur.x - n, cur.cnt + 1));
            }
            if (cur.x % 2 == 0 && cur.x / 2 >= x) {
                que.add(new Simulation(cur.x / 2, cur.cnt + 1));
            }
            if (cur.x % 3 == 0 && cur.x / 3 >= x) {
                que.add(new Simulation(cur.x / 3, cur.cnt + 1));
            }
        }

        return -1;
    }
}

class Simulation {
    int x;
    int cnt;

    public Simulation(int x, int cnt) {
        this.x = x;
        this.cnt = cnt;
    }
}