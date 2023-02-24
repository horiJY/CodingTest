import java.util.ArrayList;
import java.util.HashMap;

class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i : tangerine) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        ArrayList<Integer> keySet = new ArrayList<>(map.keySet());
        keySet.sort((o1, o2) -> map.get(o2) - map.get(o1));

        while (k > 0) {
            k -= map.get(keySet.remove(0));
            answer++;
        }

        return answer;
    }
}