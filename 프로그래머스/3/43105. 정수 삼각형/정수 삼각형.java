class Solution {
    static int[][] dp;
    static int[][] triangles;

    public int solution(int[][] triangle) {
        triangles = triangle;
        dp = new int[triangle.length][triangle.length];
        int answer = getMaxValue(0, 0);

        return answer;
    }

    private int getMaxValue(int row, int col) {
        if (row == dp.length) {
            return 0;
        }
        if (dp[row][col] > 0) {
            return dp[row][col];
        }

        return dp[row][col] = triangles[row][col] + Math.max(getMaxValue(row + 1, col), getMaxValue(row + 1, col + 1));
    }
}
