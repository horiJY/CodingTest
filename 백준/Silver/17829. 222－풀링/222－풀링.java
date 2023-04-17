import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int[][] origin;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        origin = new int[N][N];
        for (int y = 0; y < origin.length; y++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int x = 0; x < origin.length; x++) {
                origin[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] result = null;
        while (N > 1) {
            N /= 2;
            result = new int[N][N];
            for (int y = 0; y < N; y++) {
                for (int x = 0; x < N; x++) {
                    result[y][x] = find222Polling(y * 2, x * 2);
                }
            }
            origin = result;
        }

        System.out.println(result[0][0]);
    }

    private static int find222Polling(int y, int x) {
        List<Integer> tempList = new ArrayList<>();
        for (int dy = 0; dy < 2; dy++) {
            for (int dx = 0; dx < 2; dx++) {
                tempList.add(origin[y + dy][x + dx]);
            }
        }
        tempList.sort(null);
        return tempList.get(2);
    }
}
