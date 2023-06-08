class Solution {
    // dp[i] = 가로 길이갸 i 일때 타일 배치 경우의수
    // dp[i] = dp[i-1] + dp[i-2]
    // dp[i] = i-1번째에는 타일 세로 배치만 가능, i-2 번째에는 타일 가로 배치만 가능
    private static int[] dp;

    public static int solution(int n) {
        dp = new int[60_001];
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % 1_000_000_007;
        }

        return dp[n];
    }
}