import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    static final int[][] moving = new int[][] { { 0, -1 }, { -1, 0 }, { 1, 0 }, { 0, 1 }, };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] answer = new int[N][M];
        Deque<int[]> que = new ArrayDeque<>();

        int e;
        for (int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < M; x++) {
                e = Integer.parseInt(st.nextToken());
                if (e != 1) {
                    answer[y][x] = 0;
                } else {
                    answer[y][x] = -1;
                }

                if (e == 2) {
                    que.offer(new int[] { y, x });
                }
            }
        }

        int[] cur;
        int dy, dx;
        while (!que.isEmpty()) {
            cur = que.poll();
            for (int[] m : moving) {
                dy = cur[0] + m[0];
                dx = cur[1] + m[1];

                if (dy < 0 || dy >= N || dx < 0 || dx >= M || answer[dy][dx] != -1) {
                    continue;
                }

                answer[dy][dx] = answer[cur[0]][cur[1]] + 1;
                que.add(new int[] { dy, dx });
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
}
