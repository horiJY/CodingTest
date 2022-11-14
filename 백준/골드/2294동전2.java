import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[] coins;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        // 동전 종류 n (1 ≤ n ≤ 100), 합 k(1 ≤ k ≤ 10,000)
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        coins = new int[n];
        for (int i = 0; i < n; i++) {
            // 동전의 가치는 100,000보다 작거나 같은 자연수, 같은 동전이 여러번 주어질 수 있음
            coins[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(coins);
        // data input end

        int[] dp = new int[k + 1];
        Arrays.fill(dp, k + 1);
        dp[0] = 0;
        for (int i = 0; i < n; i++) {
            for (int j = coins[i]; j <= k; j++) {
                dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
            }
        }

        if (dp[k] == k + 1) {
            dp[k] = -1;
        }
        System.out.println(dp[k]);
    }
}