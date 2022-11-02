import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        boolean[] clothesList = new boolean[n + 1];
        Arrays.fill(clothesList, true);
        int answer = n;
        List<Integer> reserveList = new ArrayList<>();
        for (int i : reserve) {
            reserveList.add(i);
        }

        for (int i : lost) {
            if (reserveList.contains(i)) {
                reserveList.remove((Integer) i);
                continue;
            }
            clothesList[i] = false;
            answer--;
        }
        reserveList.sort(null);
        for (int i : reserveList) {
            if (i - 1 >= 1 && !clothesList[i - 1]) {
                clothesList[i - 1] = true;
                answer++;
            } else if (i + 1 <= n && !clothesList[i + 1]) {
                clothesList[i + 1] = true;
                answer++;
            }
        }

        return answer;
    }
}