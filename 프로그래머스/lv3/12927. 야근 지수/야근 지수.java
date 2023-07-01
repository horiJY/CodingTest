import java.util.Collections;
import java.util.PriorityQueue;

class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;

        PriorityQueue<Integer> pQue = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < works.length; i++) {
            pQue.offer(works[i]);
        }

        if (pQue.stream().mapToInt(e -> e).sum() <= n) {
            return 0;
        }

        int maxTime = 0;
        while (n > 0) {
            maxTime = pQue.poll();
            maxTime--;
            pQue.add(maxTime);
            n--;
        }

        while (!pQue.isEmpty()) {
            answer += Math.pow(pQue.poll(), 2);
        }

        return answer;
    }
}