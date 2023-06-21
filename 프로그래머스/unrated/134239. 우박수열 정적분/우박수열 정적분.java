import java.util.ArrayList;
import java.util.List;

class Solution {
    public double[] solution(int k, int[][] ranges) {
        List<Integer> graph = new ArrayList<>();
        while (true) {
            graph.add(k);
            if (k == 1) {
                break;
            }
            if (k % 2 == 0) {
                k /= 2;
            } else {
                k = k * 3 + 1;
            }
        }

        List<Double> areaOfGraph = new ArrayList<>();
        Double totalArea = 0.0;
        for (int i = 1; i < graph.size(); i++) {
            int e1 = graph.get(i - 1);
            int e2 = graph.get(i);
            double temp = Math.min(e1, e2) + Math.abs(e1 - e2) / 2.0;
            areaOfGraph.add(temp);
            totalArea += temp;
        }

        double[] answer = new double[ranges.length];
        for (int i = 0; i < answer.length; i++) {
            if (areaOfGraph.size() - ranges[i][0] < ranges[i][1] * -1) {
                answer[i] = -1.0;
                continue;
            }
            Double temp = totalArea;
            for (int j = 0; j < ranges[i][0]; j++) {
                temp -= areaOfGraph.get(j);
            }
            for (int j = -1; j >= ranges[i][1]; j--) {
                temp -= areaOfGraph.get(areaOfGraph.size() + j);
            }
            answer[i] = temp;
        }
        return answer;
    }
}