import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class Main {
    static ArrayList<Integer>[] link;
    static int[] answer;

    public static void main(String[] args) throws IOException {
        StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        // 노드의 개수 N (2 ≤ N ≤ 100,000)
        st.nextToken();
        int N = (int) st.nval;
        // N-1개의 줄에 트리 상에서 연결된 두 정점
        link = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            link[i] = new ArrayList<>();
        }

        for (int i = 0; i <= N; i++) {
            st.nextToken();
            int a = (int) st.nval;
            st.nextToken();
            int b = (int) st.nval;

            link[a].add(b);
            link[b].add(a);
        }
        // data input end

        // 2번 노드부터 N번 노드까지 부모노드 출력
        ArrayDeque<Integer> que = new ArrayDeque<>();
        answer = new int[N + 1];
        answer[1] = 1;
        que.add(1);

        while (!que.isEmpty()) {
            int cur = que.poll();
            for (int i : link[cur]) {
                if (answer[i] == 0) {
                    answer[i] = cur;
                    que.add(i);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 2; i <= N; i++) {
            sb.append(answer[i]).append('\n');
        }
        System.out.println(sb);
    }
}