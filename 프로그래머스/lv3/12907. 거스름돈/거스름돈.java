class Solution {
    public int solution(int n, int[] money) {
        int[] cache = new int[n + 1];
        cache[0] = 1;
        for (int e : money) {
            for (int i = e; i <= n; i++) {
                cache[i] += cache[i - e];
                cache[i] %= 1_000_000_007;
            }
        }

        return cache[n];
    }
}