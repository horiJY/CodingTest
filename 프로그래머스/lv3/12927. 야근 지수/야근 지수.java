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

        while (n > 0) {
            pQue.add(pQue.poll() - 1);
            n--;
        }

        while (!pQue.isEmpty()) {
            answer += Math.pow(pQue.poll(), 2);
        }

        return answer;
    }
}