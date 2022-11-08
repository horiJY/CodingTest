import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputData = br.readLine();
        if (!validData(inputData)) {
            System.out.println(0);
            return;
        }

        int answer = 0;
        int value = 1;
        Stack<Character> stack = new Stack<>();
        boolean openFlag = false;
        for (int i = 0; i < inputData.length(); i++) {
            char data = inputData.charAt(i);
            if (data == '(') {
                value *= 2;
                openFlag = true;
                stack.push(data);
            } else if (data == '[') {
                value *= 3;
                openFlag = true;
                stack.push(data);
            } else if (data == ')' && stack.peek().equals('(')) {
                if (openFlag) {
                    answer += value;
                    openFlag = false;
                }
                value /= 2;
                stack.pop();
            } else if (data == ']' && stack.peek().equals('[')) {
                if (openFlag) {
                    answer += value;
                    openFlag = false;
                }
                value /= 3;
                stack.pop();
            } else {
                answer = 0;
                break;
            }
        }
        System.out.println(answer);
    }

    private static boolean validData(String input) {
        int openParenthese = 0;
        int openBracket = 0;

        for (char c : input.toCharArray()) {
            if (c == '(') {
                openParenthese++;
            } else if (c == '[') {
                openBracket++;
            } else if (c == ')' && openParenthese > 0) {
                openParenthese--;
            } else if (c == ']' && openBracket > 0) {
                openBracket--;
            } else {
                return false;
            }
        }

        return openParenthese == 0 && openBracket == 0;
    }
}