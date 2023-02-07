class Solution {
    public long solution(int a, int b) {
        long answer = 0;
        if (a > b) {
            a = b + (b = a) * 0;
        }

        for (; a <= b; a++) {
            answer += a;
        }
        return answer;
    }
}