class Solution {
    /**
     * 1 ≤ n ≤ 100
     * 1 ≤ count ≤ n
     * 같은 높이를 가지는 빌딩 사이에는 그보다 높은 빌딩이 존재하지 않습니다.
     * 1,000,000,007 로 나눈 나머지를 return합니다.
     */
    long[][] cache;
    int DIVIDER = 1_000_000_007;

    public int solution(int n, int count) {
        cache = new long[n + 1][n + 1];
        cache[1][1] = 1;
        int maxCol = Math.min(n, count);

        for (int r = 2; r <= n; r++) {
            for (int c = 1; c <= maxCol; c++) {
                cache[r][c] = (cache[r - 1][c - 1] + 2 * (r - 1) * cache[r - 1][c]) % DIVIDER;
            }
        }

        return (int) cache[n][count];
    }
}