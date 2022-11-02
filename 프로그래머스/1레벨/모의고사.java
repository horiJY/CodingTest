import java.util.ArrayList;
import java.util.List;

class Solution {
    public int[] solution(int[] answers) {

        int firstPersonScore = countScore(answers, new int[] { 1, 2, 3, 4, 5, 1, 2, 3, 4, 5 });
        int secondPersonScore = countScore(answers, new int[] { 2, 1, 2, 3, 2, 4, 2, 5 });
        int thirdPersonScore = countScore(answers, new int[] { 3, 3, 1, 1, 2, 2, 4, 4, 5, 5 });

        List<Integer> answerList = new ArrayList<>();
        int topScore = Integer.max(firstPersonScore, Integer.max(secondPersonScore, thirdPersonScore));
        if (topScore == firstPersonScore) {
            answerList.add(1);
        }
        if (topScore == secondPersonScore) {
            answerList.add(2);
        }
        if (topScore == thirdPersonScore) {
            answerList.add(3);
        }

        return answerList.stream().mapToInt(Integer::intValue).toArray();
    }

    int countScore(int[] answers, int[] patterns) {
        int score = 0;
        int answerIdx = 0;
        int patternIdx = 0;

        while (answerIdx < answers.length) {
            if (answers[answerIdx++] == patterns[patternIdx++ % patterns.length]) {
                score++;
            }
        }

        return score;
    }
}