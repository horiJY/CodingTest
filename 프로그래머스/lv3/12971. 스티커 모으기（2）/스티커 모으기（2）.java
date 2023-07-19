class Solution {

    public int solution(int sticker[]) {
        int length = sticker.length;

        if (length == 1) {
            return sticker[0];
        }

        int answer = 0;
        // 첫번째 선택
        int[] memo = new int[length];
        memo[0] = sticker[0];
        memo[1] = sticker[0];
        for (int i = 2; i < length - 1; i++) { // 첫번째 선택 시 마지막스티커 선택못함(인접 스티커 선택 불가)
            memo[i] = Math.max(memo[i - 1], memo[i - 2] + sticker[i]);
        }
        answer = Math.max(memo[length - 1], memo[length - 2]);

        // 두번째 선택
        memo = new int[length];
        memo[0] = 0;
        memo[1] = sticker[1];
        for (int i = 2; i < length; i++) {
            memo[i] = Math.max(memo[i - 1], memo[i - 2] + sticker[i]);
        }
        answer = Math.max(answer, Math.max(memo[length - 1], memo[length - 2]));

        return answer;
    }
}