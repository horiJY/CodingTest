import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] histogram;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        while (true) {
            st = new StringTokenizer(br.readLine(), " ");

            int size = Integer.parseInt(st.nextToken());
            if (size == 0) {
                break;
            }
            histogram = new int[size];

            for (int i = 0; i < histogram.length; i++) {
                histogram[i] = Integer.parseInt(st.nextToken());
            }

            long answer = 0;
            int[] stack = new int[size];
            int stackIndex = -1;
            for (int i = 0; i < size; i++) {
                while (stackIndex > -1 && histogram[stack[stackIndex]] >= histogram[i]) {
                    long height = histogram[stack[stackIndex]];
                    stackIndex--;
                    int width = stackIndex == -1 ? i : i - stack[stackIndex] - 1;

                    answer = Math.max(answer, height * width);
                }
                stackIndex++;
                stack[stackIndex] = i;
            }

            while (stackIndex > -1) {
                long height = histogram[stack[stackIndex]];
                stackIndex--;
                int width = stackIndex == -1 ? size : size - stack[stackIndex] - 1;

                answer = Math.max(answer, height * width);
            }

            sb.append(answer).append("\n");
        }
        System.out.println(sb);
    }
}