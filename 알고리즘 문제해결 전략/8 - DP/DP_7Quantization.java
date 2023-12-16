import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class DP_7Quantization {
    static int[][] cache; // cache[그룹의 시작 idx][남은 그룹 수]
    static int[] numbers, sums, sqSums;
    static int N, S;
    static private int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int C = Integer.parseInt(br.readLine().trim());
        while (C-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            S = Integer.parseInt(st.nextToken());
            numbers = new int[N + 1];
            cache = new int[N + 1][N + 1];
            for (int i = 1; i <= N; i++) {
                Arrays.fill(cache[i], -1);
            }

            st = new StringTokenizer(br.readLine(), " ");
            for (int i = 1; i <= N; i++) {
                numbers[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(numbers);
            int answer = INF;
            if (S < N) {
                answer = Math.min(answer, searchGroup(1, 1, S - 1));
            } else {
                answer = 0;
            }

            System.out.println(answer);
        }
    }

    private static int searchGroup(int si, int ei, int remainPart) {
        int ret = INF;
        if (cache[si][remainPart] != -1) {
            return cache[si][remainPart];
        }

        if (remainPart == 0) {
            int tempAvg = 0;
            int quantizePow = 0;
            for (int i = si; i <= N; i++) {
                tempAvg += numbers[i];
            }
            tempAvg = (int) (0.5 + (double) tempAvg / (N - si + 1));

            for (int i = si; i <= N; i++) {
                quantizePow += (numbers[i] - tempAvg) * (numbers[i] - tempAvg);
            }

            return cache[si][remainPart] = quantizePow;
        }

        for (int eIdx = ei; eIdx <= N - remainPart; eIdx++) {
            int tempAvg = 0;
            int cal = 0;
            for (int i = si; i <= eIdx; i++) {
                tempAvg += numbers[i];
            }
            tempAvg = (int) (0.5 + (double) tempAvg / (eIdx - si + 1));

            for (int i = si; i <= eIdx; i++) {
                cal += (numbers[i] - tempAvg) * (numbers[i] - tempAvg);
            }

            ret = Math.min(ret, cal + searchGroup(eIdx + 1, eIdx + 1, remainPart - 1));
        }

        return cache[si][remainPart] = ret;
    }

}
