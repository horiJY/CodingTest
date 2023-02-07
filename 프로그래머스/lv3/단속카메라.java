import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int solution(int[][] routes) {
        int answer = 0;
        Arrays.sort(routes, Comparator.comparingInt(o -> o[1]));
        for (int i = 0; i < routes.length; i++) {
            for (int j = i + 1; j < routes.length; j++) {
                if ((routes[i][0] <= routes[j][0] && routes[j][0] <= routes[i][1])
                        || (routes[j][0] <= routes[i][1] && routes[i][1] <= routes[j][1])) {
                    if (j == routes.length - 1) {
                        answer++;
                        i = j; // 전체와 매칭되었기 때문에 종료
                    }
                } else {
                    answer++;
                    if (j == routes.length - 1) {
                        answer++; // 마지막 요소와 일치하지 않을 경우 + 1 더 필요
                    }
                    i = j - 1;
                    break;
                }
            }
        }

        return answer;
    }
}