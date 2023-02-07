import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] counselConsumeDay = new int[N + 1];
        int[] counselCost = new int[N + 1];
        int[] dp = new int[N + 1];

        // i 번째 날
        // D(i) = D(i-1) + C(i)
        for (int i = 1; i <= N; i++) {
            String[] input = br.readLine().split(" ");
            counselConsumeDay[i] = Integer.parseInt(input[0]);
            counselCost[i] = Integer.parseInt(input[1]);
        }

        for (int i = 1; i < counselConsumeDay.length; i++) {
            int consumeDay = counselConsumeDay[i] - 1;
            dp[i] = Math.max(dp[i], dp[i - 1]);

            if (i + consumeDay <= N) {
                dp[i + consumeDay] = Math.max(dp[i + consumeDay], dp[i - 1] + counselCost[i]);
            }
        }

        System.out.println(dp[N]);
    }
}