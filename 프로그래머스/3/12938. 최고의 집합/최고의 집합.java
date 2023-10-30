import java.util.Arrays;

class Solution {
    public int[] solution(int n, int s) {
        if (n > s) {
            return new int[] { -1 };
        }

        int[] answer = new int[n];
        int remainder = s % n;
        int normal = n - remainder;
        for (int i = 0; i < normal; i++) {
            answer[i] = s / n;
        }
        for (int i = normal; i < answer.length; i++) {
            answer[i] = s / n + 1;
        }

        return answer;
    }
}