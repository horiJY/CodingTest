class Solution {
    static int[] memo;

    public int solution(int money[]) {
        if (money.length == 1) {
            return money[0];
        }

        int answer = 0;
        // 첫번째 선택, 마지막 포기
        memo = new int[money.length];
        memo[0] = money[0];
        memo[1] = money[0];
        for (int i = 2; i < money.length - 1; i++) {
            memo[i] = Math.max(memo[i - 1], memo[i - 2] + money[i]);
        }
        answer = Math.max(memo[memo.length - 1], memo[memo.length - 2]);

        // 두번째 선택, 마지막 선택
        memo = new int[money.length];
        memo[0] = 0;
        memo[1] = money[1];
        for (int i = 2; i < money.length; i++) {
            memo[i] = Math.max(memo[i - 1], memo[i - 2] + money[i]);
        }
        answer = Math.max(answer, Math.max(memo[memo.length - 1], memo[memo.length - 2]));

        return answer;
    }
}