import java.util.HashSet;
import java.util.Set;

class Solution {
    public int solution(int[] elements) {
        Set<Integer> answer = new HashSet<>();
        for (int limit = 1; limit <= elements.length; limit++) {
            for (int startIdx = 0; startIdx < elements.length; startIdx++) {
                answer.add(getElement(startIdx, startIdx + limit, elements));
            }
        }

        return answer.size();
    }

    private Integer getElement(int startIdx, int endIdx, int[] elements) {
        int result = 0;
        for (int i = startIdx; i < endIdx; i++) {
            if (i < elements.length) {
                result += elements[i];
            } else {
                result += elements[i % elements.length];
            }
        }
        return result;
    }

}