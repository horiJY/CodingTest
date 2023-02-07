import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 * V1에서 V2로 연결된 간선이 있다면, V2의 번호는 V1보다 커야 한다.
 * 첫째 줄에 수열의 각 원소를 차례대로 공백을 사이에 두고 출력한다. 만약 그래프의 번호를 수정할 수 없다면 -1을 출력한다. 답이
 * 여러개일 경우에는 인덱스가 낮은 것부터 출력
 */
public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 정점의 개수 N(N<=50)
        int n = Integer.parseInt(br.readLine());
        // 인접행렬 입력 N개: 0 연결되지 않음, 1 연결
        int[][] graph = new int[n][n];
        int[] outDegree = new int[n];
        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split("");
            for (int j = 0; j < n; j++) {
                graph[i][j] = Integer.parseInt(input[j]);
                if (graph[i][j] == 1) {
                    outDegree[i]++;
                }
            }

        }
        // data input end

        // 일반 큐를 사용했을 때 큐에 2개 이상 존재할 경우 인덱스가 낮은 것 부터 출력을 보장할 수 없다.
        PriorityQueue<Integer> que = new PriorityQueue<>(Collections.reverseOrder());
        int[] answer = new int[n];
        for (int i = 0; i < n; i++) {
            if (outDegree[i] == 0) {
                que.add(i);
            }
        }

        for (int i = n; i > 0; i--) {
            if (que.isEmpty()) { // n번 돌기 전 큐가 비었다면 사이클이 생긴것
                System.out.println(-1);
                return;
            }
            int curNode = que.poll();
            answer[curNode] = i;
            for (int j = 0; j < n; j++) {
                if (graph[j][curNode] == 1) {
                    graph[j][curNode] = 0;
                    if (--outDegree[j] == 0) {
                        que.add(j);
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i : answer) {
            sb.append(i).append(' ');
        }
        System.out.println(sb);
    }
}