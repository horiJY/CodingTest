import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int answer = Integer.MAX_VALUE;
        for (int i = 0; i * 5 <= N; i++) {
            answer = Math.min(answer, getDivision(N, i));
        }

        if (answer == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
    }

    private static int getDivision(int n, int i) {
        n -= 5 * i;
        int result = n / 3;
        n -= result * 3;
        if (n == 0) {
            return result + i;
        }
        return Integer.MAX_VALUE;
    }
}