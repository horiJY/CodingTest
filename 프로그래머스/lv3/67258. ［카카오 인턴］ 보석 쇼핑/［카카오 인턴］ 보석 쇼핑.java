import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] solution(String[] gems) {
        int[] answer = { 0, gems.length }; // gems 구매 인덱스(시작, 끝)
        Map<String, Integer> gemMap = new HashMap<>();
        int maxGemTypeCount = (int) Arrays.stream(gems).distinct().count();
        for (int start = 0, end = 0; end < gems.length; end++) {
            String endGem = gems[end];
            gemMap.put(endGem, gemMap.getOrDefault(endGem, 0) + 1);
            String startGem = gems[start];
            int startGemCount = gemMap.getOrDefault(startGem, 0);

            while (gemMap.getOrDefault(startGem, 0) > 1) {
                gemMap.put(startGem, startGemCount - 1);
                start++;
                startGem = gems[start];
                startGemCount = gemMap.getOrDefault(startGem, 0);
            }

            if (gemMap.size() == maxGemTypeCount && answer[1] - answer[0] > end - start) {
                answer[0] = start + 1;
                answer[1] = end + 1;
            }
        }
        return answer;
    }
}