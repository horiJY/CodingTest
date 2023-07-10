import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    static int[][] moving = new int[][] { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
    static boolean[][] visit;

    public int solution(int[][] maps) {
        int answer = -1;
        int height = maps.length;
        int width = maps[0].length;
        Deque<BFS> que = new ArrayDeque<>();

        visit = new boolean[maps.length][maps[0].length];
        visit[height - 1][width - 1] = true;
        que.offer(new BFS(height - 1, width - 1, 1));

        while (!que.isEmpty()) {
            BFS cur = que.poll();
            if (cur.x == 0 && cur.y == 0) {
                answer = cur.move;
                break;
            }
            int dy = 0;
            int dx = 0;
            for (int i = 0; i < moving.length; i++) {
                dy = cur.x + moving[i][0];
                dx = cur.y + moving[i][1];
                if (dy < 0 || dy >= height || dx < 0 || dx >= width) {
                    continue;
                }
                if (maps[dy][dx] == 1 && !visit[dy][dx]) {
                    visit[dy][dx] = true;
                    que.offer(new BFS(dy, dx, cur.move + 1));
                }
            }
        }
        return answer;
    }
}

class BFS {
    int x;
    int y;
    int move;

    public BFS(int x, int y, int move) {
        this.x = x;
        this.y = y;
        this.move = move;
    }

}