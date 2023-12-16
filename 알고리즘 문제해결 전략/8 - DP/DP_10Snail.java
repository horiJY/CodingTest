import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class DP_10Snail {
    static int N, M;
    static double[][] cache;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int C = Integer.parseInt(br.readLine());
        while (C-- > 0) {
            String[] input = br.readLine().split(" ");
            N = Integer.parseInt(input[0]);
            M = Integer.parseInt(input[1]);
            cache = new double[M][M * 2];
            for (int i = 0; i < cache.length; i++) {
                Arrays.fill(cache[i], -1.0);
            }

            System.out.println(String.format("%.10f", climbWall(0, 0)));
        }
    }

    private static double climbWall(int day, int length) {
        if (day == M) {
            return length >= N ? 1.0 : 0;
        }

        if (cache[day][length] != -1.0) {
            return cache[day][length];
        }

        return cache[day][length] = 0.25 * (climbWall(day + 1, length + 1))
                + 0.75 * (climbWall(day + 1, length + 2));
    }
}
