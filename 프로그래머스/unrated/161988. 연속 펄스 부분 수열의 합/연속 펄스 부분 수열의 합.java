import java.util.Arrays;

class Solution {
    public long solution(int[] sequence) {
        long answer = 0;
        long[] memo = new long[sequence.length];

        // 1 -1 1 -1 ...
        for (int i = 0; i < sequence.length; i++) {
            if (i % 2 == 1) {
                sequence[i] *= -1;
            }
        }
        memo[0] = sequence[0];
        for (int i = 1; i < memo.length; i++) {
            memo[i] = Math.max(0, memo[i - 1]) + sequence[i];
        }
        answer = Math.max(answer, Arrays.stream(memo).max().getAsLong());

        // -1 1 -1 1 ...
        memo = new long[sequence.length];
        memo[0] = -sequence[0];
        for (int i = 1; i < memo.length; i++) {
            memo[i] = Math.max(0, memo[i - 1]) -sequence[i];
        }
        answer = Math.max(answer, Arrays.stream(memo).max().getAsLong());

        return answer;
    }
}