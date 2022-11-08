import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken()); // 사대 수
        int N = Integer.parseInt(st.nextToken()); // 동물 수
        int L = Integer.parseInt(st.nextToken()); // 사거리

        int[] shotPoints = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < shotPoints.length; i++) {
            shotPoints[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(shotPoints);

        int answer = 0;
        int x;
        int y;
        int low;
        int high;
        int start;
        int end;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());

            low = x + y - L;
            high = L + x - y;

            start = 0;
            end = M - 1;
            while (start <= end) {
                int mid = (start + end) / 2;
                if (low <= shotPoints[mid] && shotPoints[mid] <= high) {
                    answer++;
                    break;
                } else if (shotPoints[mid] < high) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }

        System.out.println(answer);
    }
}