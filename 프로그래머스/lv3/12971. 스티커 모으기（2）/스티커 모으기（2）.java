class Solution {
    static int[] memo;

    public int solution(int sticker[]) {
        if (sticker.length == 1) {
            return sticker[0];
        }

        int answer = 0;
        // 첫번째 스티커 선택
        memo = new int[sticker.length];
        memo[0] = sticker[0];
        memo[1] = sticker[0];
        for (int i = 2; i < sticker.length - 1; i++) {
            memo[i] = Math.max(memo[i - 1], memo[i - 2] + sticker[i]);
        }
        answer = Math.max(memo[memo.length - 1], memo[memo.length - 2]);

        // 두번째 스티커 선택
        memo = new int[sticker.length];
        memo[0] = 0;
        memo[1] = sticker[1];
        for (int i = 2; i < sticker.length; i++) {
            memo[i] = Math.max(memo[i - 1], memo[i - 2] + sticker[i]);
        }
        answer = Math.max(answer, Math.max(memo[memo.length - 1], memo[memo.length - 2]));

        return answer;
    }
}