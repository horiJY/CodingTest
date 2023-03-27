import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    static int[][] moving = new int[][] { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    public int solution(String[] board) {
        char[][] map = new char[board.length][board[0].length()];
        Point start = null;
        Point goal = null;
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                char val = board[y].charAt(x);
                map[y][x] = val;
                if (val == 'R') {
                    start = new Point(y, x, 0);
                }
                if (val == 'G') {
                    goal = new Point(y, x, 0);
                }
            }
        }

        return findRoute(map, start, goal);
    }

    private boolean validGoal(char[][] map, Point goal) {
        int wallCnt = 0;
        boolean pass = false;
        for (int i = 0; i < moving.length; i++) {
            int dy = goal.y + moving[i][0];
            int dx = goal.x + moving[i][1];
            if (dy < map.length && dy >= 0 && dx < map[0].length && dx >= 0 && map[dy][dx] == '.') {
                pass = true;
            }

            if ((dy < map.length && dy >= 0 && dx < map[0].length && dx >= 0 && map[dy][dx] == 'D')
                    || dy == 0 || dy == map.length - 1 || dx == 0 || dx == map[0].length - 1) {
                wallCnt++;
            }
        }
        if (wallCnt > 0 && pass) {
            return true;
        }

        return false;
    }

    private int findRoute(char[][] map, Point start, Point goal) {
        if (!validGoal(map, goal)) {
            return -1;
        }

        boolean[][] visit = new boolean[map.length][map[0].length];
        Deque<Point> que = new ArrayDeque<>();
        que.add(new Point(start.y, start.x, 0));
        visit[start.y][start.x] = true;

        while (!que.isEmpty()) {
            Point cur = que.poll();

            for (int i = 0; i < moving.length; i++) {
                int dy = cur.y;
                int dx = cur.x;
                while (true) {
                    dy += moving[i][0];
                    dx += moving[i][1];
                    if (0 > dy || dy >= map.length || 0 > dx || dx >= map[0].length || map[dy][dx] == 'D') {
                        dy -= moving[i][0];
                        dx -= moving[i][1];
                        break;
                    }
                }

                if (dy == goal.y && dx == goal.x) {
                    return cur.moveCnt + 1;
                }

                if (!visit[dy][dx]) {
                    que.add(new Point(dy, dx, cur.moveCnt + 1));
                    visit[dy][dx] = true;
                }
            }
        }

        return -1;
    }
}

class Point {
    int y;
    int x;
    int moveCnt;

    public Point(int y, int x, int moveCnt) {
        this.y = y;
        this.x = x;
        this.moveCnt = moveCnt;
    }
}