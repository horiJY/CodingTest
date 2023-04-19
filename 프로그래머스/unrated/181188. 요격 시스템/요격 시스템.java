import java.util.Arrays;

class Solution {
    public int solution(int[][] targets) {
        int mark = 0;
        int answer = 0;
        Arrays.sort(targets, (o1, o2) -> {
            
            return o1[1] - o2[1];
        });
        for (int i = 1; i < targets.length; i++) {
            if (targets[mark][1] <= targets[i][0]) {
                answer++;
                mark = i;
            }
        }
        if (mark != targets.length) {
            answer++;
        }

        return answer;
    }
}