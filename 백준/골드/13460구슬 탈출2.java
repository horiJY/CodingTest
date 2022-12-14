import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Main {
    static boolean[][] map;
    // down, left, right, up
    static int[] dy = { 1, 0, 0, -1 };
    static int[] dx = { 0, -1, 1, 0 };
    static int[] goal;
    static int[] red;
    static int[] blue;
    static int ROW;
    static int COL;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 세로 N, 가로 M (3 ≤ N, M ≤ 10)
        String[] input = br.readLine().split(" ");
        ROW = Integer.parseInt(input[0]);
        COL = Integer.parseInt(input[1]);

        map = new boolean[ROW][COL];

        for (int i = 0; i < ROW; i++) {
            input = br.readLine().split("");
            for (int j = 0; j < COL; j++) {
                if (!input[j].equals("#")) {
                    map[i][j] = true;

                    if (input[j].equals("O")) {
                        goal = new int[] { i, j };
                    } else if (input[j].equals("R")) {
                        red = new int[] { i, j };
                    } else if (input[j].equals("B")) {
                        blue = new int[] { i, j };
                    }
                }
            }
        }
        // data input end
        Deque<Simulation> que = new ArrayDeque<>();
        que.add(new Simulation(red, blue, 0));
        boolean[][][][] visit = new boolean[ROW][COL][ROW][COL];
        int answer = Integer.MAX_VALUE;

        while (!que.isEmpty()) {
            Simulation target = que.poll();
            visit[target.red[0]][target.red[1]][target.blue[0]][target.blue[1]] = true;

            for (int j = 0; j < dx.length; j++) {
                Simulation cur = new Simulation(target.red, target.blue, target.count);
                if (cur.count >= 10) {
                    break;
                }

                // 이동 가능 방향일 때만
                if (isValid(cur.red[0] + dy[j], cur.red[1] + dx[j])
                        || isValid(cur.blue[0] + dy[j], cur.blue[1] + dx[j])) {
                    cur.count++;
                    // red 이동
                    int redMoveCount = 0;
                    while (map[cur.red[0] + dy[j]][cur.red[1] + dx[j]]) {
                        cur.red[0] += dy[j];
                        cur.red[1] += dx[j];
                        redMoveCount++;
                        if (Arrays.equals(cur.red, goal)) {
                            break;
                        }
                    }

                    // blue 이동
                    int blueMoveCount = 0;
                    while (map[cur.blue[0] + dy[j]][cur.blue[1] + dx[j]]) {
                        cur.blue[0] += dy[j];
                        cur.blue[1] += dx[j];
                        blueMoveCount++;
                        if (Arrays.equals(cur.blue, goal)) {
                            break;
                        }
                    }

                    // 공 겹치지 않게 위치 보정
                    if (!Arrays.equals(cur.red, goal) && Arrays.equals(cur.red, cur.blue)) {
                        if (redMoveCount < blueMoveCount) {
                            cur.blue[0] -= dy[j];
                            cur.blue[1] -= dx[j];
                        } else {
                            cur.red[0] -= dy[j];
                            cur.red[1] -= dx[j];
                        }
                    }

                    if (Arrays.equals(cur.blue, goal)) { // blue 골일 때
                        // 남은 방향에서 통과가능성 있으므로 진행
                        continue;
                    } else if (Arrays.equals(cur.red, goal)) { // red만 골일 때
                        answer = Math.min(answer, cur.count);
                    } else if (!visit[cur.red[0]][cur.red[1]][cur.blue[0]][cur.blue[1]]) {
                        que.add(new Simulation(cur.red, cur.blue, cur.count));
                    }
                }
            }
        }

        if (answer != Integer.MAX_VALUE) {
            System.out.println(answer);
        } else {
            System.out.println(-1);
        }
    }

    static boolean isValid(int r, int c) {
        if ((r > 0 && r < ROW) && (c > 0 && c < COL)) {
            return map[r][c];
        } else {
            return false;
        }
    }

    static class Simulation {
        int[] red, blue;
        int count;

        public Simulation(int[] red, int[] blue, int count) {
            this.red = new int[] { red[0], red[1] };
            this.blue = new int[] { blue[0], blue[1] };
            this.count = count;
        }
    }
}
