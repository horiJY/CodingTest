import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 완제품 N(3 ≤ N ≤ 100), 1 ~ N-1까지는 부품
        int N = Integer.parseInt(br.readLine());
        // 부품 간 관계 M(3 ≤ M ≤ 100)
        int M = Integer.parseInt(br.readLine());
        ArrayList<Material>[] link = new ArrayList[N + 1]; // i에서 갈 수 있는 노드 리스트
        for (int i = 0; i <= N; i++) {
            link[i] = new ArrayList<Material>();
        }
        int[] inDegree = new int[N + 1];
        StringTokenizer st;
        for (int i = 0; i < M; i++) {
            // M line -> X Y K :X를 만드는데 Y가 K개 필요
            st = new StringTokenizer(br.readLine(), " ");
            int X = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            link[X].add(new Material(Y, K));
            inDegree[Y]++;
        }
        // data input end

        // 완제품 1개를 만드는데 필요한 쟤료 개수
        int[] answer = new int[N + 1];
        answer[N] = 1;
        // Deque<Integer> que = new ArrayDeque<>();
        PriorityQueue<Integer> que = new PriorityQueue<>(Collections.reverseOrder());
        que.add(N);
        while (!que.isEmpty()) {
            int cur = que.poll();
            for (Material next : link[cur]) {
                answer[next.material] += answer[cur] * next.cost;
                if (--inDegree[next.material] == 0) {
                    que.add(next.material);
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            if (link[i].size() == 0) {
                System.out.println(i + " " + answer[i]);
            }
        }
    }

    static class Material {
        int material, cost;

        public Material(int material, int cost) {
            this.material = material;
            this.cost = cost;
        }
    }
}