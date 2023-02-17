import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static final int MAX_DAYS = 365;

    public static void main(String[] args) throws IOException {
        int[] dp = new int[MAX_DAYS];

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            int startDay = Integer.parseInt(input[0]);
            int endDay = Integer.parseInt(input[1]);
            for (int j = startDay; j <= endDay; j++) {
                dp[j - 1]++;
            }
        }

        int answer = 0;
        int sequence = 0;
        int depth = 0;
        for (int i = 0; i < dp.length; i++) {
            if (dp[i] != 0) {
                sequence++;
                depth = Math.max(depth, dp[i]);
            }
            if (dp[i] == 0 || i == dp.length - 1) {
                answer += sequence * depth;
                sequence = 0;
                depth = 0;
            }
        }

        System.out.println(answer);
    }
}
