class Solution {
    public int solution(int n) {
        int answer = 1;
        for (int i = 1; i <= n / 2; i++) {
            if (sequenceOfNaturalNumber(i, n)) {
                answer++;
            }
        }
        return answer;
    }

    boolean sequenceOfNaturalNumber(int startNum, int maxNum) {
        int result = 0;
        for (; result < maxNum; startNum++) {
            result += startNum;
        }
        return result == maxNum;
    }
}