import java.util.Arrays;
import java.util.List;

class Solution {
    public int solution(String word) {
        List<String> wordElementList = Arrays.asList("A", "E", "I", "O", "U");

        int[] wordPositionSequence = new int[wordElementList.size()];
        for (int i = wordElementList.size() - 1; i >= 0; i--) {
            if (i == wordElementList.size() - 1) {
                wordPositionSequence[i] = 1;
            } else {
                wordPositionSequence[i] = wordPositionSequence[i + 1] * 5 + 1;
            }
        }

        int answer = 0;
        for (int i = 0; i < word.length(); i++) {
            answer += wordPositionSequence[i] * wordElementList.indexOf(String.valueOf(word.charAt(i))) + 1;
        }

        return answer;
    }
}