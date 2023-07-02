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

        if(!pQue.isEmpty()){
            // answer = pQue.stream().parallel().mapToLong(e -> (long) Math.pow(e, 2)).sum(); // 130ms, 70MB
            answer = pQue.stream().mapToLong(e -> (long) Math.pow(e, 2)).sum(); // 
        // while (!pQue.isEmpty()) {
            // answer += Math.pow(pQue.poll(), 2); //127ms, 68MB
        }

        return answer;
    }
}