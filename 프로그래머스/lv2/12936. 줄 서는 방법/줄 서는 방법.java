import java.util.ArrayList;
import java.util.List;

class Solution {
    public int[] solution(int n, long k) {
        int[] answer = new int[n];
        List<Integer> list = new ArrayList<>();

        long remainPossibility = k - 1;
        long totalPossibility = 1;
        for (int i = 1; i <= n; i++) {
            list.add(i);
            totalPossibility *= i;
        }

        int idx = 0;
        while (idx < n) {
            totalPossibility /= n - idx; // 현재 위치에서의 남은 경우의 수
            answer[idx++] = list.remove((int) (remainPossibility / totalPossibility));
            remainPossibility %= totalPossibility;
        }

        return answer;
    }
}