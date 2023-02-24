import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        Stack<Character> opStack = new Stack<>();
        String answer = "";
        for (int i = 0; i < input.length(); i++) {
            // 문자는 그대로
            if (input.charAt(i) >= 'A' && input.charAt(i) <= 'Z') {
                answer += input.charAt(i);
            } else if (input.charAt(i) == '(') {
                opStack.push(input.charAt(i));
            } else if (input.charAt(i) == ')') {
                while (!opStack.isEmpty() && opStack.peek() != '(') {// ( 가 나올 때 까지 후위 표현식으로 표현
                    answer += opStack.pop();
                }

                if (!opStack.isEmpty()) { // 남아있는 ( 제거
                    opStack.pop();
                }
            } else { // 나머지 모든 연산자 일 경우 현재 연산자보다 우선순위가 높은 연산자를 후위표현식에 추출
                while (!opStack.isEmpty() && checkPriority(opStack.peek()) >= checkPriority(input.charAt(i))) {
                    answer += opStack.pop();
                }
                opStack.push(input.charAt(i));
            }
        }

        while (!opStack.isEmpty()) {
            answer += opStack.pop();
        }

        System.out.println(answer);
    }

    private static int checkPriority(char op) {
        if (op == '*' || op == '/') {
            return 2;
        }
        if (op == '+' || op == '-') {
            return 1;
        }
        return 0;
    }
}
