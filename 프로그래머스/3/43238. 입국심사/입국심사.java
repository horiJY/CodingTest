import java.util.Arrays;

class Solution {
    public long solution(int n, int[] times) {
        Arrays.sort(times);

        long answer = 0;
        long min = 0;
        long max = (long) n * (long) times[0];
        
        while (min <= max) {
            long mid = (min + max) / 2;
            long tempCount = 0;

            for (int time : times) {
                tempCount += mid / time;
            }

            if (tempCount >= n) {
                max = mid - 1;
                answer = mid;
            } else {
                min = mid + 1;
            }
        }

        return answer;
    }
}