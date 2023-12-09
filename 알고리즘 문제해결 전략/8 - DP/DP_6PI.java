import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class DP_6PI {
    static int[] pi, level;
    static int INF = 9_999_999;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int C = Integer.parseInt(br.readLine().trim());
        while (C-- > 0) {
            String input = br.readLine().trim();
            pi = new int[input.length()];
            level = new int[input.length()];
            Arrays.fill(level, -1);
            int i = 0;
            for (char c : input.toCharArray()) {
                pi[i++] = (int) c - 48;
            }

            calculatePi();
            System.out.println(level[level.length - 1]);
            // System.out.println(memorize(0));
        }
    }

    private static void calculatePi() {
        level[0] = 999;
        level[1] = 999;
        level[2] = validLevel(0, 2);
        level[3] = validLevel(0, 3);
        level[4] = validLevel(0, 4);
        for (int i = 5; i < pi.length; i++) {
            int split3 = level[i - 3] + validLevel(i - 2, i);
            int split4 = level[i - 4] + validLevel(i - 3, i);
            int split5 = level[i - 5] + validLevel(i - 4, i);
            level[i] = Math.min(Math.min(split3, split4), split5);
        }

    }

    // private static int memorize(int begin) {
    // if (begin == pi.length) {
    // return 0;
    // }

    // if (level[begin] != -1) {
    // return level[begin];
    // }

    // level[begin] = INF;
    // for (int i = 3; i <= 5; i++) {
    // if (begin + i <= pi.length) {
    // level[begin] = Math.min(level[begin], memorize(begin + i) + validLevel(begin,
    // begin + i - 1));
    // }
    // }
    // return level[begin];
    // }

    private static int validLevel(int s, int e) {
        // 난이도 1검사
        for (int i = s; i <= e; i++) {
            if (pi[s] != pi[i]) {
                break;
            }
            if (i == e) {
                return 1;
            }
        }

        // 난이도 2 & 5
        int gap = pi[s] - pi[s + 1];
        for (int i = s; i <= e - 1; i++) {
            if (pi[i] - pi[i + 1] != gap) {
                break;
            }
            if (i == e - 1) {
                return Math.abs(gap) == 1 ? 2 : 5;
            }
        }

        // 난이도 4
        for (int i = s; i <= e - 2; i++) {
            if (pi[i] != pi[i + 2]) {
                break;
            }
            if (i == e - 2) {
                return 4;
            }
        }

        return 10;
    }
}
