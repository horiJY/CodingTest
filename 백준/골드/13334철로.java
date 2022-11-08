import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        int[][] points = new int[size][2];
        for (int i = 0; i < points.length; i++) {
            String[] temp = br.readLine().split(" ");
            points[i][0] = Math.min(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]));
            points[i][1] = Math.max(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]));
        }
        Arrays.sort(points, (o1, o2) -> o1[1] - o2[1]); // 우측 이동 체크 -> 큰 좌표기준 정렬

        int l = Integer.parseInt(br.readLine()); // 요구 거리 길이

        PriorityQueue<Integer> que = new PriorityQueue<>();
        int answer = 0;
        for (int i = 0; i < points.length; i++) {
            que.add(points[i][0]); // 큰 좌표 추가

            while (!que.isEmpty() && que.peek() < points[i][1] - l) {
                // 큰 좌표 > 작은 좌표 + 거리 -> 좌표 간 거리가 요구 거리 초과
                que.poll();
            }

            answer = Math.max(answer, que.size());
        }

        System.out.println(answer);
    }
}