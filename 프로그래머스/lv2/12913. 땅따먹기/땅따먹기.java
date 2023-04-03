class Solution {
    int solution(int[][] land) {
        int[][] dp = new int[land.length][land[0].length];
        dp[0] = land[0];
        for (int row = 1; row < land.length; row++) {
            for (int curCol = 0; curCol < land[row].length; curCol++) {
                for (int preCol = 0; preCol < land[row].length; preCol++) {
                    if (curCol == preCol) {
                        continue;
                    }
                    dp[row][curCol] = Math.max(dp[row][curCol], dp[row - 1][preCol] + land[row][curCol]);
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < dp[0].length; i++) {
            answer = Math.max(answer, dp[dp.length - 1][i]);
        }
        return answer;
    }
}