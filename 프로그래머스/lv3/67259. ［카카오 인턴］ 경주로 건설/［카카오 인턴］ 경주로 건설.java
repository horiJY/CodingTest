import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    // 탐색방향은 상 우 하 좌
    int[] dy = new int[] { -1, 0, 1, 0 };
    int[] dx = new int[] { 0, 1, 0, -1 };

    public int solution(int[][] board) {
        int N = board.length;
        Deque<Simulation> que = new ArrayDeque<>();
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                if (board[y][x] != 1) {
                    board[y][x] = Integer.MAX_VALUE / 2;
                }
            }
        }

        que.offer(new Simulation(0, 0, 0, 0));

        while (!que.isEmpty()) {
            Simulation cur = que.poll();

            if (cur.x < 0 || cur.x >= N || cur.y < 0 || cur.y >= N ||
                    board[cur.y][cur.x] == 1 || cur.cost > board[cur.y][cur.x] + 500) {
                continue;
            }

            board[cur.y][cur.x] = Math.min(board[cur.y][cur.x], cur.cost);

            for (int i = 0; i < dx.length; i++) {
                int ny = cur.y + dy[i];
                int nx = cur.x + dx[i];

                if (cur.y == 0 && cur.x == 0) {
                    que.offer(new Simulation(cur.cost + 100, ny, nx, i));
                    continue;
                }

                if (cur.dir == i) {
                    que.offer(new Simulation(cur.cost + 100, ny, nx, i));
                } else {
                    que.offer(new Simulation(cur.cost + 600, ny, nx, i));
                }
            }
        }

        return board[N - 1][N - 1];
    }
}

class Simulation {
    int cost, y, x, dir;

    public Simulation(int cost, int y, int x, int dir) {
        this.cost = cost;
        this.y = y;
        this.x = x;
        this.dir = dir;
    }
}