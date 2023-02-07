import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            boolean[] prison = new boolean[N + 1]; // true : 잠겨짐, false: 열림
            for (int j = 2; j <= N; j++) {
                for (int m = 1; j * m < prison.length; m++) {
                    prison[j * m] = !prison[j * m];
                }
            }

            int answer = 0;
            for (int j = 1; j < prison.length; j++) {
                if (!prison[j]) {
                    answer++;
                }
            }
            System.out.println(answer);
        }

    }
}
