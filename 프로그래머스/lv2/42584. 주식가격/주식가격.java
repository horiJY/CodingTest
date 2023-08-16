import java.util.ArrayList;
import java.util.List;

class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];

        for (int startIdx = 0; startIdx < prices.length; startIdx++) {
            for (int i = startIdx + 1; i < prices.length; i++) {
                if (prices[startIdx] > prices[i]) {
                    answer[startIdx] = i - startIdx;
                    break;
                } else if (i == prices.length - 1) {
                    answer[startIdx] = i - startIdx;
                }
            }
        }

        return answer;
    }
}