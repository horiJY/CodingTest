import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int solution(int[] order) {
        int answer = 0;
        Deque<Integer> subConveyorStack = new ArrayDeque<>();
        int idx = 0;
        for (int i = 0; i < order.length; i++) {
            subConveyorStack.push(i + 1);
            while (!subConveyorStack.isEmpty()) {
                if (subConveyorStack.peek() == order[idx]) {
                    answer++;
                    idx++;
                    subConveyorStack.pop();
                } else {
                    break;
                }
            }
        }

        return answer;
    }
}