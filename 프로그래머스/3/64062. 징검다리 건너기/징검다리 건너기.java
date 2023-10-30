import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int solution(int[] stones, int k) {
        int answer = Integer.MAX_VALUE;
        Deque<Integer> que = new ArrayDeque<>();
        Deque<Integer> idxQue = new ArrayDeque<>();
        for (int i = 0; i < stones.length; i++) {
            while (!que.isEmpty() && que.peekLast() < stones[i]) { // 범위k의 제일 큰 max 값만 남김
                que.pollLast();
                idxQue.pollLast();
            }

            while (!idxQue.isEmpty() && idxQue.peekFirst() <= i - k) { // que에 k개만 유지
                que.pollFirst();
                idxQue.pollFirst();
            }

            que.offer(stones[i]);
            idxQue.offer(i);

            if (i >= k - 1) {
                answer = Math.min(answer, que.peekFirst());
            }
        }

        return answer;
    }
}