import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split("");
        int answer = 0;
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < input.length; i++) {
            if (input[i].equals("(")) {
                stack.push(i);
            } else {
                if (stack.peek() == i - 1) {
                    answer += stack.size() - 1;
                } else {
                    answer += 1;
                }
                stack.pop();
            }
        }

        System.out.println(answer);
    }
}