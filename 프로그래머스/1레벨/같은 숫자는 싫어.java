import java.util.Stack;

class Solution {
    public int[] solution(int[] arr) {
        Stack<Integer> answerStack = new Stack<>();

        for (int i : arr) {
            if (answerStack.isEmpty()) {
                answerStack.add(i);
            } else if (!(i == answerStack.peek())) {
                answerStack.add(i);
            }
        }

        return answerStack.stream().mapToInt(Integer::intValue).toArray();
    }
}