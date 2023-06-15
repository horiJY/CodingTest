
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        int answer = calculateBracket(input);
        System.out.println(answer);
    }

    private static int calculateBracket(String input) {
        Stack<Character> stack = new Stack<>();
        int result = 0;
        int temp = 1;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '(') {
                stack.push(input.charAt(i));
                temp *= 2;
            } else if (input.charAt(i) == '[') {
                stack.push(input.charAt(i));
                temp *= 3;
            } else if (input.charAt(i) == ')') {
                if (stack.isEmpty() || stack.peek() != '(') {
                    return 0;
                } else if (input.charAt(i - 1) == '(') {
                    result += temp;
                }
                stack.pop();
                temp /= 2;
            } else if (input.charAt(i) == ']') {
                if (stack.isEmpty() || stack.peek() != '[') {
                    return 0;
                } else if (input.charAt(i - 1) == '[') {
                    result += temp;
                }
                stack.pop();
                temp /= 3;
            }
        }

        if (!stack.isEmpty()) {
            return 0;
        }
        return result;
    }
}
