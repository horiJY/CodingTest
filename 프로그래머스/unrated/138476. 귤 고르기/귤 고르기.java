import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int solution(int k, int[] tangerine) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : tangerine) {
            map.computeIfAbsent(i, key -> 0);
            map.computeIfPresent(i, (key, v) -> v + 1);
        }
        ArrayList<Integer> keySet = new ArrayList<>(map.keySet());
        keySet.sort((o1, o2) -> map.get(o2).compareTo(map.get(o1)));
        int answer = 0;
        while (k > 0) {
            k -= map.get(keySet.remove(0));
            answer++;
        }

        return answer;
    }
}