import java.util.Arrays;

class Solution {
    public int solution(int[][] scores) {
        int[] target = scores[0];
        int targetScore = Arrays.stream(target).sum();
        // 근무태도 내림차순, 동료평가 오름차순
        Arrays.sort(scores, (a, b) -> a[0] == b[0] ? a[1] - b[1] : b[0] - a[0]);

        int answer = 1;
        int maxCoopScore = 0;
        for (int[] score : scores) {
            /*
             * 근무태도 및 동료평가 두 점수가 앞 element보다 낮을 경우 인센 제외
             * 먼저나오는 element는 근무태도가 높음
             * 따라서 동료평가가 이전 elements보다 낮을경우 제외대상 or target보다 인센 우선순위가 낮음
             */
            if (score[1] < maxCoopScore) {
                // 제외대상(answer no count)
                if (score.equals(target)) {
                    return -1;
                }
            } else {
                maxCoopScore = Math.max(maxCoopScore, score[1]);
                if (Arrays.stream(score).sum() > targetScore) {
                    answer++;
                }
            }
        }

        return answer;
    }
}