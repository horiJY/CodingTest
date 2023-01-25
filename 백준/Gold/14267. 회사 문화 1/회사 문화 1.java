import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    static HashMap<Integer, ArrayList<Integer>> tree;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] superior = new int[N + 1];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N; i++) {
            superior[i] = Integer.parseInt(st.nextToken());
        }

        int[] answer = new int[N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int employee = Integer.parseInt(st.nextToken());
            int point = Integer.parseInt(st.nextToken());
            answer[employee] += point;
        }
        // data input end

        for (int i = 2; i < answer.length; i++) {
            if (answer[i] != 0) {
                for (int j = i + 1; j < superior.length; j++) {
                    if (superior[j] == i) {
                        answer[j] += answer[i];
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < answer.length; i++) {
            sb.append(answer[i]).append(' ');
        }
        System.out.println(sb);
    }

}