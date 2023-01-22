import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        printStar(0, 0, N);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < map.length; i++) {
            for (char c : map[i]) {
                if (c == '*') {
                    sb.append(c);
                } else {
                    sb.append(' ');
                }
            }
            System.out.println(sb);
            sb.setLength(0);
        }
    }

    private static void printStar(int sy, int sx, int N) {
        if (N > 3) {
            for (int y = 0; y < 3; y++) {
                for (int x = 0; x < 3; x++) {
                    if (y == 1 && x == 1) {
                        continue;
                    }
                    printStar(N / 3 * y + sy, N / 3 * x + sx, N / 3);
                }
            }
        } else {
            for (int y = 0; y < 3; y++) {
                for (int x = 0; x < 3; x++) {
                    if (y == 1 && x == 1) {
                        continue;
                    }
                    map[sy + y][sx + x] = '*';
                }
            }
        }
    }
}
