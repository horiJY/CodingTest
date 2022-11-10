import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * row * col로 구성된 빙산맵에서 양수는 빙산의 높이 0은 바다이다.
 * 1년이 경과할 때마다 빙산과 바다가 인접한 개수만큼 빙산은 녹는다.
 * 빙산이 두덩이로 분리 될 때까지 걸리는 시간은?
*/

public class Main {
    static int row;
    static int col;
    final static int[][] moving = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        // 3 <= row, col <= 300
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());

        // 0 <= iceberg <= 10
        int[][] icebergs = new int[row][col];
        for (int i = 0; i < row; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < col; j++) {
                icebergs[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;
        while (true) {
            // 1. 빙산 덩어리 체크 2개이상인가?
            int icebergMass = checkMass(icebergs);
            if (icebergMass > 1) {
                System.out.println(answer);
                return;
            } else if (icebergMass == 0) {
                // 다녹을 때 까지 덩어리가 두개이상이 안되면 0
                System.out.println(0);
                return;
            }
            // 2. 인접 바다 수만큼 빙산 높이 감소
            meltIceberg(icebergs);
            answer++;
        }

    }

    private static void meltIceberg(int[][] icebergs) {
        boolean[][] icebergPoint = new boolean[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (icebergs[i][j] > 0) {
                    icebergPoint[i][j] = true;
                }
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (icebergs[i][j] > 0) {
                    int meltHeight = 0;
                    // 인접 바다체크
                    for (int k = 0; k < moving.length; k++) {
                        if (!isValid(i + moving[k][0], j + moving[k][1])) {
                            continue;
                        }
                        if (!icebergPoint[i + moving[k][0]][j + moving[k][1]]
                                && icebergs[i + moving[k][0]][j + moving[k][1]] <= 0) {
                            meltHeight++;
                        }
                    }
                    icebergs[i][j] -= meltHeight;
                }
            }
        }
    }

    private static int checkMass(int[][] icebergs) {
        boolean[][] visitPoint = new boolean[row][col]; // 기본생성 시 false로 초기화
        Queue<int[]> moveQue = new ArrayDeque<>();
        int result = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (icebergs[i][j] > 0 && visitPoint[i][j] == false) { // 방문한적이 없는 빙산이라면
                    moveQue.add(new int[] { i, j });

                    while (!moveQue.isEmpty()) {
                        int[] curPoint = moveQue.poll();
                        if (!visitPoint[curPoint[0]][curPoint[1]]) { // 빙산을 방문하고
                            visitPoint[curPoint[0]][curPoint[1]] = true;
                            for (int k = 0; k < moving.length; k++) { // 인접 빙산을 큐에 추가
                                if (!isValid(curPoint[0] + moving[k][0], curPoint[1] + moving[k][1])) {
                                    continue;
                                }
                                if (icebergs[curPoint[0] + moving[k][0]][curPoint[1] + moving[k][1]] <= 0) {
                                    continue;
                                }
                                moveQue.add(new int[] { curPoint[0] + moving[k][0], curPoint[1] + moving[k][1] });
                            }
                        }
                    }
                    result++;
                }
            }
        }

        return result;
    }

    private static boolean isValid(int x, int y) {
        if (x < 0 || x >= row) {
            return false;
        }
        if (y < 0 || y >= col) {
            return false;
        }
        return true;
    }

}