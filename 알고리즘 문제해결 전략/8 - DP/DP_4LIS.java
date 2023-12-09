import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class DP_4LIS {
    static int[] sequence, lis;
    static int N;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int C = Integer.parseInt(br.readLine());
        while (C-- > 0) {
            N = Integer.parseInt(br.readLine());
            sequence = new int[N]; // 5 4 3 2 1 6 7 8
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < N; i++) {
                sequence[i] = Integer.parseInt(st.nextToken());
            }

            lis = new int[N]; // dp[]
            Arrays.fill(lis, Integer.MAX_VALUE);
            lis[0] = sequence[0];
            for (int si = 0, li = 0; si < N; si++) {
                if (lis[li] < sequence[si]) {
                    lis[++li] = sequence[si];
                } else {
                    changeCache(sequence[si]);
                }
            }

            System.out.println(Arrays.stream(lis).filter(n -> n != Integer.MAX_VALUE).count());
        }
    }

    private static void changeCache(int base) {
        for (int j = 0; j < N; j++) {
            if (base < lis[j]) {
                lis[j] = base;
                break;
            }
        }
    }

    // private static int findIdx(int lo, int hi, int target) {
    // int mid = 0;
    // while (lo < hi) {
    // mid = (lo + hi) / 2;
    // if (cache[mid] < target) {
    // lo = mid + 1;
    // } else {
    // hi = mid;
    // }
    // }

    // return hi;
    // }
}