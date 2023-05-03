import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    static double[] distance = new double[] { 2.0, 3.0, 4.0 };

    public long solution(int[] weights) {
        long answer = 0;
        Map<Double, Integer> weightMap = new HashMap();
        Arrays.sort(weights);
        for (int w : weights) {
            if (weightMap.containsKey((1.0) * w)) {
                answer += weightMap.get((1.0) * w);
            }

            for (int i = 0; i < distance.length; i++) {
                for (int j = i + 1; j < distance.length; j++) {
                    if (weightMap.containsKey((distance[i] / distance[j]) * w)) {
                        answer += weightMap.get((distance[i] / distance[j]) * w);
                    }
                }
            }
            weightMap.put(1.0 * w, weightMap.getOrDefault(1.0 * w, 0) + 1);
        }
        return answer;
    }
}