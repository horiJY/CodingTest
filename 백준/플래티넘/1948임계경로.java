import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 도시의 개수 n(1 ≤ n ≤ 10,000)
        int n = Integer.parseInt(br.readLine());
        // 도로의 개수 m(1 ≤ m ≤ 100,000)
        int m = Integer.parseInt(br.readLine());
        ArrayList<Node>[] link = new ArrayList[n + 1]; // i에서 갈 수 있는 노드 리스트
        ArrayList<Node>[] rLink = new ArrayList[n + 1]; // i로 인입되는 노드 리스트
        int[] inDegree = new int[n + 1]; // 노드i의 인입경로 수
        for (int i = 0; i <= n; i++) {
            link[i] = new ArrayList<Node>();
            rLink[i] = new ArrayList<Node>();
        }
        StringTokenizer st;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int node = Integer.parseInt(st.nextToken());
            int dest = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            link[node].add(new Node(dest, cost));
            rLink[dest].add(new Node(node, cost));
            inDegree[dest]++;
        }
        st = new StringTokenizer(br.readLine(), " ");
        int startNode = Integer.parseInt(st.nextToken());
        int endNode = Integer.parseInt(st.nextToken());
        // data input end

        // startNode에서 endNode까지 임계경로(가장 긴 경우) 계산
        int[] time = new int[n + 1];
        Deque<Integer> que = new ArrayDeque<>();
        que.add(startNode);
        while (!que.isEmpty()) {
            int cur = que.pollFirst();
            for (Node next : link[cur]) {
                // 노드i까지의 max cost
                time[next.node] = Math.max(time[next.node], time[cur] + next.cost);
                if (--inDegree[next.node] == 0) {
                    que.add(next.node);
                }
            }
        }
        System.out.println(time[endNode]);

        // endNode에서 startNode까지 임계경로 도로 수(중복x)
        boolean[] visit = new boolean[n + 1];
        int count = 0;
        que.add(endNode);
        while (!que.isEmpty()) {
            int cur = que.pollFirst();
            for (Node pre : rLink[cur]) {
                if (time[cur] == time[pre.node] + pre.cost) {
                    count++;
                    if (!visit[pre.node]) { // 도로 중복 count 방지
                        que.add(pre.node);
                        visit[pre.node] = true;
                    }
                }
            }
        }
        System.out.println(count);
    }

    static class Node {
        int node, cost;

        public Node(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }
    }
}