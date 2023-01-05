import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        if (cacheSize == 0) {
            return 5 * cities.length;
        }

        Deque<String> cache = new ArrayDeque<>(cacheSize);
        boolean hit;
        for (String city : cities) {
            hit = false;
            city = city.toLowerCase();
            for (String data : cache) {
                if (data.equals(city)) {
                    hit = true;
                    cache.remove(city);
                    cache.add(city);
                    break;
                }
            }

            if (hit) {
                answer++;
                continue;
            }

            if (cache.size() == cacheSize) {
                cache.removeFirst();
            }
            cache.add(city);
            answer += 5;
        }

        return answer;
    }
}