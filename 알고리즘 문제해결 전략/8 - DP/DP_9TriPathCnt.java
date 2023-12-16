import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class DP_9TriPathCnt {
    static int[][] number, sum, cnt;
    static int N, max, answer;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int C = Integer.parseInt(br.readLine());
        while (C-- > 0) {
            N = Integer.parseInt(br.readLine());
            sum = new int[N][N];
            cnt = new int[N][N];
            number = new int[N][N];
            for (int i = 0; i < N; i++) {
                String[] input = br.readLine().split(" ");
                for (int j = 0; j <= i; j++) {
                    number[i][j] = Integer.parseInt(input[j]);
                }
                Arrays.fill(cnt[i], -1);
            }
            sum[N - 1] = number[N - 1];
            Arrays.fill(cnt[N - 1], 1);
            execPath(N - 1);

            System.out.println(cnt[0][0]);

        }
    }

    private static void execPath(int line) {
        if (line == 0) {
            return;
        }

        for (int i = 0; i <= line; i++) {
            int temp = 0;
            // 좌상
            if (i != 0) {
                temp = sum[line][i] + number[line - 1][i - 1];
                validMaxCnt(sum[line - 1][i - 1], temp, new int[] { line - 1, i - 1 }, new int[] { line, i });
            }
            // 상
            if (i != line) {
                temp = sum[line][i] + number[line - 1][i];
                validMaxCnt(sum[line - 1][i], temp, new int[] { line - 1, i }, new int[] { line, i });
            }

        }

        execPath(line - 1);
    }

    private static void validMaxCnt(int base, int temp, int[] bPoint, int[] tPoint) {
        if (base == temp) {
            cnt[bPoint[0]][bPoint[1]] += cnt[tPoint[0]][tPoint[1]];
            return;
        }

        if (base < temp) {
            sum[bPoint[0]][bPoint[1]] = temp;
            cnt[bPoint[0]][bPoint[1]] = cnt[tPoint[0]][tPoint[1]];
        }
    }
}
