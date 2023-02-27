import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    static int[][] moving = new int[][] { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

    public int solution(String[] maps) {
        char[][] map = new char[maps.length][maps[0].length()];
        Point start = null, lever = null, end = null;
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[0].length; x++) {
                char e = maps[y].charAt(x);
                map[y][x] = e;
                if (e == 'S') {
                    start = new Point(y, x, map.length * map[0].length);
                } else if (e == 'L') {
                    lever = new Point(y, x, map.length * map[0].length);
                } else if (e == 'E') {
                    end = new Point(y, x, map.length * map[0].length);
                }

            }
        }

        int startToLeverCnt = findRoute(map, start, lever);
        int leverToEndCnt = findRoute(map, lever, end);

        if (startToLeverCnt == -1 || leverToEndCnt == -1) {
            return -1;
        }

        return startToLeverCnt + leverToEndCnt;
    }

    private int findRoute(char[][] map, Point start, Point goal) {
        boolean[][] visit = new boolean[map.length][map[0].length];
        Deque<Point> que = new ArrayDeque<>();
        que.add(new Point(start.y, start.x, 0));
        visit[start.y][start.x] = true;

        while (!que.isEmpty()) {
            Point cur = que.poll();
            if (cur.moveCnt >= goal.moveCnt) {
                continue;
            }

            for (int i = 0; i < moving.length; i++) {
                int dy = cur.y + moving[i][0];
                int dx = cur.x + moving[i][1];
                if (0 > dy || dy >= map.length || 0 > dx || dx >= map[0].length) {
                    continue;
                }

                if (dy == goal.y && dx == goal.x) {
                    return cur.moveCnt + 1;
                }

                if (map[dy][dx] != 'X' && !visit[dy][dx]) {
                    que.add(new Point(dy, dx, cur.moveCnt + 1));
                    visit[dy][dx] = true;
                }
            }
        }

        return -1;
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
}