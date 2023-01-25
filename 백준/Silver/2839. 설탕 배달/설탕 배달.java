import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for (int five = N / 5; five >= 0; five--) {
            int three = (N - (five * 5)) / 3;
            if (five * 5 + three * 3 == N) {
                System.out.println(five + three);
                return;
            }
        }

        System.out.println(-1);
    }
}