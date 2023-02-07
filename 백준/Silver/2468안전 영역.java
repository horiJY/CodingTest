import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class Main {
    static int n;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        int[][] heightOrigin = new int[n][n];
        int maxHeight = 0;
        for (int i = 0, j = 0; i < n; i++) {
            for (String s : br.readLine().split(" ")) {
                heightOrigin[i][j] = Integer.parseInt(s);
                maxHeight = Math.max(maxHeight, heightOrigin[i][j++]);
            }
            j = 0;
        }
        br.close();

        int answer = 1;
        for (int i = 1; i < maxHeight; i++) {
            answer = Math.max(answer, countNoneFloodArea(i, heightOrigin.clone()));
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(answer + "\n");
        bw.close();
    }

    private static int countNoneFloodArea(int floodedHeight, int[][] heightOrigin) {
        final int[][] moving = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
        Stack<Integer> moveQue = new Stack<>();
        // Queue<Integer> moveQue = new ArrayDeque<>();
        int[][] height = new int[n][n]; // C 선언,
        for (int i = 0; i < n; i++) { // 반복문 + ArrayCopy
            System.arraycopy(heightOrigin[i], 0, height[i], 0, height[i].length);
        }
        int result = 0;
        int visitPoint;
        int x, y;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (height[i][j] > floodedHeight) {
                    height[i][j] = 0;
                    moveQue.add(n * i + j);
                    while (!moveQue.isEmpty()) {
                        visitPoint = moveQue.pop();
                        // visitPoint = moveQue.poll();
                        x = visitPoint / n;
                        y = visitPoint % n;
                        for (int k = 0; k < moving.length; k++) {
                            if (!isValid(x + moving[k][0], y + moving[k][1])) {
                                continue;
                            }
                            if (height[x + moving[k][0]][y + moving[k][1]] <= floodedHeight) {
                                continue;
                            }
                            moveQue.push((x + moving[k][0]) * n + (y + moving[k][1]));
                            // moveQue.add((x + moving[k][0]) * n + (y + moving[k][1]));
                            height[x + moving[k][0]][y + moving[k][1]] = 0;
                        }
                    }
                    result++;
                }
            }
        }

        return result;

    }

    private static boolean isValid(int x, int y) {
        if (x < 0 || x >= n) {
            return false;
        }
        if (y < 0 || y >= n) {
            return false;
        }
        return true;
    }

}
