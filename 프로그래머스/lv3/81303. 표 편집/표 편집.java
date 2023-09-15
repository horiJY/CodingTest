import java.util.Stack;

class Solution {
    public String solution(int n, int k, String[] cmd) {
        int maxLen = n;
        Stack<Integer> delStack = new Stack<>();
        
        for (String e : cmd) {
            String command = e.split(" ")[0];
            switch (command) {
                case "U" -> k = k - Integer.parseInt(e.split(" ")[1]);
                case "D" -> k = k + Integer.parseInt(e.split(" ")[1]);
                case "C" -> {
                    delStack.push(k);
                    maxLen--;
                    if (k == maxLen) {
                        k--;
                    }
                }
                case "Z" -> {
                    int i = delStack.pop();
                    maxLen++;
                    if (k >= i) {
                        k++;
                    }
                }
            }
        }

        StringBuilder answer = new StringBuilder("O".repeat(maxLen));
        while (!delStack.isEmpty()) {
            answer.insert(delStack.pop().intValue(), 'X');
        }

        return answer.toString();
    }
}