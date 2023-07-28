import java.util.Arrays;

class Solution {
    static final int NOT_REACHABLE = 999_999;
    static final int MIN_TEMPERATURE = 0;
    static final int MAX_TEMPERATURE = 50;

    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        temperature += 10;
        t1 += 10;
        t2 += 10;

        // dp[time][temperature]
        int[][] dp = new int[onboard.length][MAX_TEMPERATURE + 1];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], NOT_REACHABLE);
        }
        dp[0][temperature] = 0;

        for (int time = 1; time < onboard.length; time++) {
            int startTemp = MIN_TEMPERATURE;
            int endTemp = MAX_TEMPERATURE;
            if (onboard[time] == 1) {
                startTemp = t1;
                endTemp = t2;
            }

            for (int temp = startTemp; temp <= endTemp; temp++) {
                // temp-1 -> temp
                if (temp > MIN_TEMPERATURE && dp[time - 1][temp - 1] != NOT_REACHABLE) {
                    if (temp > temperature) { // 외기가 낮을 때 on
                        dp[time][temp] = Math.min(dp[time][temp], dp[time - 1][temp - 1] + a);
                    } else { // off 시 +1
                        dp[time][temp] = Math.min(dp[time][temp], dp[time - 1][temp - 1]);
                    }
                }
                // 유지
                if (dp[time - 1][temp] != NOT_REACHABLE) {
                    if (temp == temperature) { // 외기 내기 같다면 off 시 유지
                        dp[time][temp] = Math.min(dp[time][temp], dp[time - 1][temp]);
                    } else { // 다르다면 on 시 b 소모
                        dp[time][temp] = Math.min(dp[time][temp], dp[time - 1][temp] + b);
                    }
                }
                // temp+1 -> temp
                if (temp < MAX_TEMPERATURE && dp[time - 1][temp + 1] != NOT_REACHABLE) {
                    if (temp < temperature) { // 외기가 높을 때 on
                        dp[time][temp] = Math.min(dp[time][temp], dp[time - 1][temp + 1] + a);
                    } else {
                        dp[time][temp] = Math.min(dp[time][temp], dp[time - 1][temp + 1]);
                    }
                }
            }

        }

        return Arrays.stream(dp[onboard.length - 1]).filter(e -> e != NOT_REACHABLE).min().getAsInt();
    }
}
