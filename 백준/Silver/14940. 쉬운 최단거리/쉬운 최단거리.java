import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    static final int[][] moving = new int[][] { { 0, -1 }, { -1, 0 }, { 1, 0 }, { 0, 1 }, };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);
        Point start = new Point(0, 0);
        int[][] map = new int[N][M];
        int[][] answer = new int[N][M];
        for (int y = 0; y < N; y++) {
            input = br.readLine().split(" ");
            for (int x = 0; x < M; x++) {
                int e = Integer.parseInt(input[x]);
                if (e == 2) {
                    start.y = y;
                    start.x = x;
                }
                map[y][x] = Integer.parseInt(input[x]);
            }
        }

        Deque<Point> que = new ArrayDeque<>();
        que.add(start);
        while (!que.isEmpty()) {
            Point cur = que.poll();
            for (int i = 0; i < moving.length; i++) {
                int dy = cur.y + moving[i][0];
                int dx = cur.x + moving[i][1];

                if (dy < 0 || dy >= N || dx < 0 || dx >= M) {
                    continue;
                }

                if (map[dy][dx] == 1 && answer[dy][dx] == 0) {
                    answer[dy][dx] = answer[cur.y][cur.x] + 1;
                    que.add(new Point(dy, dx));
                }
            }
        }

        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                if (!(y == start.y && x == start.x) && map[y][x] != 0 && answer[y][x] == 0) {
                    boolean isReachable = false;
                    for (int i = 0; i < moving.length; i++) {
                        int dy = y + moving[i][0];
                        int dx = x + moving[i][1];
                        if (dy < 0 || dy >= N || dx < 0 || dx >= M) {
                            continue;
                        }
                        if (answer[dy][dx] > 0) {
                            isReachable = true;
                            break;
                        }
                    }
                    if (!isReachable) {
                        answer[y][x] = -1;
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int[] y : answer) {
            for (int i : y) {
                sb.append(i).append(' ');
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    public static class Point {
        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }

        int y;
        int x;
    }
}
