import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    static ArrayList<Integer>[] link;
    static int[] answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 노드의 개수 N (2 ≤ N ≤ 100,000)
        int N = Integer.parseInt(br.readLine());
        // N-1개의 줄에 트리 상에서 연결된 두 정점
        link = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            link[i] = new ArrayList<>();
        }
        for (int i = 0; i < N - 1; i++) {
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            link[input[0]].add(input[1]);
            link[input[1]].add(input[0]);
        }
        // data input end

        // 2번 노드부터 N번 노드까지 부모노드 출력
        answer = new int[N + 1];
        answer[1] = 1;
        DFS(1);

        StringBuilder sb = new StringBuilder();
        for (int i = 2; i <= N; i++) {
            sb.append(answer[i]).append('\n');
        }
        System.out.println(sb);
    }

    static void DFS(int curNode) {
        for (int i : link[curNode]) {
            if (answer[i] == 0) {
                answer[i] = curNode;
                DFS(i);
            }
        }
    }
}