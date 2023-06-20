import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int solution(int[] order) {
        int answer = 0;
        Deque<Integer> mainConveyorQue = new ArrayDeque<>();
        Deque<Integer> subConveyorStack = new ArrayDeque<>();
        for (int i = 1; i <= order.length; i++) {
            mainConveyorQue.offer(i);
        }
        for (int i = 0; i < order.length;) {
            if (!mainConveyorQue.isEmpty() && order[i] == mainConveyorQue.peek()) {
                answer++;
                i++;
                mainConveyorQue.poll();
            } else if (!subConveyorStack.isEmpty() && subConveyorStack.peek() == order[i]) {
                answer++;
                i++;
                subConveyorStack.pop();
            } else if (!mainConveyorQue.isEmpty()) {
                subConveyorStack.push(mainConveyorQue.poll());
            } else {
                break;
            }
        }

        return answer;
    }
}