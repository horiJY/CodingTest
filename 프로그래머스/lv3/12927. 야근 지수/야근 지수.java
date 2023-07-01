import java.util.Collections;
import java.util.PriorityQueue;

class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;

        PriorityQueue<Long> pQue = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < works.length; i++) {
            pQue.offer((long) works[i]);
        }

        if (pQue.stream().mapToLong(e -> e).sum() <= n) {
            return 0;
        }

        long maxTime = 0L;
        while (n > 0) {
            maxTime = pQue.poll();
            maxTime--;
            pQue.add(maxTime);
            n--;
        }

        if (!pQue.isEmpty()) {
            answer = pQue.stream().reduce(0L, (a, b) -> a + Math.round(Math.pow(b, 2)));
        }

        return answer;
    }
}