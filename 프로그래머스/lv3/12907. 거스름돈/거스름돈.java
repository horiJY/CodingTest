class Solution {
    public int solution(int n, int[] money) {
        int[] cache = new int[n + 1];
        cache[0] = 1;
        
        for (int coin : money) {
            for (int i = coin; i <= n; i++) {
                cache[i] += cache[i - coin];
                cache[i] %= 1_000_000_007;    
            }
        }

        return cache[n];
    }
}