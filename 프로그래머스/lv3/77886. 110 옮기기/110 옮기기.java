import java.util.Stack;

class Solution {
    public String[] solution(String[] s) {
        String[] answer = new String[s.length];

        for (int i = 0; i < s.length; i++) {
            answer[i] = "";
            int matchCount = 0;
            Stack<Character> stack = new Stack<>();
            for (Character c : s[i].toCharArray()) {
                stack.push(c);
                while (stack.size() > 2) {
                    int lastIdx = stack.size() - 1;
                    if (stack.get(lastIdx) == '0' && stack.get(lastIdx - 1) == '1' && stack.get(lastIdx - 2) == '1') {
                        matchCount++;
                        stack.pop();
                        stack.pop();
                        stack.pop();
                    } else {
                        break;
                    }
                }
            }

            StringBuilder sb = new StringBuilder();
            while (!stack.isEmpty()) {
                Character c = stack.pop();
                if (matchCount > 0 && c == '0') {
                    while (matchCount-- > 0) {
                        sb.insert(0, "110");
                    }
                    answer[i] = sb + answer[i];
                    sb.setLength(0);
                }
                sb.insert(0, c);
            }
            if (matchCount > 0) {
                while (matchCount-- > 0) {
                    sb.insert(0, "110");
                }
            }
            answer[i] = sb + answer[i];
        }
        return answer;
    }
}