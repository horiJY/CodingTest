import java.util.Stack;

class Solution {
    public int solution(String s) {
        int answer = 0;
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            boolean pass = true;
            for (int j = 0; j < s.length(); j++) {
                switch (s.charAt((i + j) % s.length())) {
                    case '(' -> stack.add('p');
                    case ')' -> pass = valid(stack, 'p');
                    case '{' -> stack.add('c');
                    case '}' -> pass = valid(stack, 'c');
                    case '[' -> stack.add('s');
                    case ']' -> pass = valid(stack, 's');
                }

                if (!pass) {
                    break;
                }
            }

            if (pass && stack.size() == 0) {
                answer++;
            }
        }

        return answer;
    }

    private boolean valid(Stack<Character> stack, char target) {
        if (stack.size() == 0 || stack.pop() != target) {
            return false;
        }

        return true;
    }
}