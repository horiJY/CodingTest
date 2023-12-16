import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DP_8Tiling2 {
    static int[] cache;
    static int MOD = 1_000_000_007;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int C = Integer.parseInt(br.readLine().trim());
        while (C-- > 0) {
            int N = Integer.parseInt(br.readLine());
            cache = new int[N + 1];

            System.out.println(getTiling(N));
        }
    }

    private static int getTiling(int n) {
        if (n <= 1) {
            return 1;
        }

        if (cache[n] != 0) {
            return cache[n];
        }

        return cache[n] = (getTiling(n - 2) + getTiling(n - 1)) % MOD;
    }

}
