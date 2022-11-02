import java.util.HashMap;
import java.util.Map.Entry;

class Solution {
    public int solution(String[][] clothes) {
        HashMap<String, Integer> clothesMap = new HashMap<>();
        int answer = 0;

        for (String[] s : clothes) {
            clothesMap.put(s[1], clothesMap.getOrDefault(s[1], 0) + 1);
        }

        if (!clothesMap.isEmpty()) {
            answer = 1;
            for (Entry<String, Integer> entry : clothesMap.entrySet()) {
                answer *= entry.getValue() + 1;
            }
            answer -= 1;
        }

        return answer;
    }
}