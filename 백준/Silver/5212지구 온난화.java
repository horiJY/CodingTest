import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static String[][] map;
    static int R;
    static int C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 지도의 크기 R과 C (1 ≤ R, C ≤ 10)
        String[] input = br.readLine().split(" ");
        R = Integer.parseInt(input[0]);
        C = Integer.parseInt(input[1]);
        map = new String[R][C];
        for (int r = 0; r < R; r++) {
            input = br.readLine().split("");
            for (int c = 0; c < C; c++) {
                map[r][c] = input[c];
            }
        }
        // data input end

        laterMap();

        int[] start = new int[] { -1, -1 };
        int[] end = new int[] { -1, -1 };
        setPrintArea(start, end);

        StringBuilder sb = new StringBuilder();
        for (int r = start[0]; r <= end[0]; r++) {
            for (int c = start[1]; c <= end[1]; c++) {
                sb.append(map[r][c]);
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }

    private static void setPrintArea(int[] start, int[] end) {
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (map[r][c].equals("X")) {
                    if (start[0] == -1) {
                        start[0] = r;
                    }
                    if (end[0] < r) {
                        end[0] = r;
                    }

                    if (start[1] == -1 || start[1] > c) {
                        start[1] = c;
                    }
                    if (end[1] < c) {
                        end[1] = c;
                    }
                }
            }
        }
    }

    private static void laterMap() {
        final int[][] moving = new int[][] { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };
        String[][] beforeMap = new String[R][C];
        for (int r = 0; r < R; r++) {
            System.arraycopy(map[r], 0, beforeMap[r], 0, map[r].length);
        }

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (map[r][c].equals("X")) {
                    int coastCount = 0;
                    for (int i = 0; i < moving.length; i++) {
                        if (validCoast(r + moving[i][0], c + moving[i][1], beforeMap)) {
                            coastCount++;
                        }
                    }

                    if (coastCount >= 3) {
                        map[r][c] = ".";
                    }
                }
            }
        }
    }

    private static boolean validCoast(int row, int col, String[][] beforeMap) {
        if ((row < 0 || row >= R) || (col < 0 || col >= C)) {
            return true;
        }
        if (beforeMap[row][col].equals(".")) {
            return true;
        }
        return false;
    }
}