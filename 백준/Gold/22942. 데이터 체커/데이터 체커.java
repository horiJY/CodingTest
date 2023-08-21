import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<int[]> radiusList = new ArrayList<>();
        String answer = "YES";
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            int x = Integer.parseInt(input[0]);
            int r = Integer.parseInt(input[1]);

            radiusList.add(new int[] { x, r });
        }

        radiusList.sort((int[] ea1, int[] ea2) -> {
            return (ea1[0] - ea1[1]) - (ea2[0] - ea2[1]);
        });

        Deque<Integer> openStack = new ArrayDeque<>();
        Deque<Integer> closeStack = new ArrayDeque<>();
        for (int[] ea : radiusList) {
            if (openStack.isEmpty()) {
                openStack.push(ea[0] - ea[1]);
                closeStack.push(ea[0] + ea[1]);
                continue;
            }

            if (openStack.peek() < ea[0] - ea[1] && closeStack.peek() > ea[0] + ea[1]) {
                openStack.push(ea[0] - ea[1]);
                closeStack.push(ea[0] + ea[1]);
                continue;
            }

            if (openStack.peek() < ea[0] + ea[1] && closeStack.peek() < ea[0] - ea[1]) {
                while (!openStack.isEmpty() && openStack.peek() < ea[0] + ea[1] && closeStack.peek() < ea[0] - ea[1]) {
                    openStack.pop();
                    closeStack.pop();
                }
                openStack.push(ea[0] - ea[1]);
                closeStack.push(ea[0] + ea[1]);
                continue;
            }

            answer = "NO";
            break;

        }

        System.out.println(answer);
    }
}
