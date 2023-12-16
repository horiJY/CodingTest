import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class DP_11AsymTiling {
    static int MOD = 1_000_000_007;
    static int[] cache;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int C = Integer.parseInt(br.readLine());
        while (C-- > 0) {
            int N = Integer.parseInt(br.readLine());
            cache = new int[N + 1];
            Arrays.fill(cache, -1);

            System.out.println((getTile(N) - getAsymTile(N) + MOD) % MOD);
        }
    }

    private static int getAsymTile(int n) {
        if (n <= 2) {
            return n;
        }

        if (n % 2 == 1) {
            return getTile(n / 2) % MOD;
        }

        return (getTile(n / 2) + getTile(n / 2 - 1)) % MOD;
    }

    private static int getTile(int n) {
        if (n <= 1) {
            return 1;
        }

        if (cache[n] != -1) {
            return cache[n];
        }

        return cache[n] = (getTile(n - 1) + getTile(n - 2)) % MOD;
    }
}
