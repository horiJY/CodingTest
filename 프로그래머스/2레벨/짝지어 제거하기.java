import java.util.Stack;

class Solution {
    public int solution(String s) {
        if (s.length() % 2 != 0) {
            return 0;
        } else {
            Stack<Character> stack = new Stack<>();
            for (char c : s.toCharArray()) {
                if (stack.size() == 0) {
                    stack.push(c);
                } else {
                    if (stack.peek().equals(c)) {
                        stack.pop();
                    } else {
                        stack.push(c);
                    }
                }
            }

            return stack.size() == 0 ? 1 : 0;
        }
    }
}