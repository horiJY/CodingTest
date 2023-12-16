import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class DP_13NUMB3RS {
    static int N, D, P;
    static int[][] map;
    static double[] connect;
    // static double[][] cache;
    static double[] cache;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int C = Integer.parseInt(br.readLine());
        while (C-- > 0) {
            String[] input = br.readLine().split(" ");
            N = Integer.parseInt(input[0]);
            D = Integer.parseInt(input[1]);
            P = Integer.parseInt(input[2]);
            map = new int[N][N];
            connect = new double[N];
            cache = new double[N];

            for (int i = 0; i < N; i++) {
                input = br.readLine().split(" ");
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(input[j]);
                    if (input[j].equals("1")) {
                        connect[i]++;
                    }
                }
            }

            cache[P] = 1d;
            getPercent(D);
            printResult(br, input);
        }
    }

    private static void getPercent(int d) {
        while (d-- > 0) {
            double[] tmpCache = new double[N];
            for (int from = 0; from < map.length; from++) {
                for (int to = 0; to < map[from].length; to++) {
                    if (map[from][to] == 1) {
                        tmpCache[to] += cache[from] / connect[from];
                    }
                }
            }

            cache = tmpCache;
        }

    }

    private static void printResult(BufferedReader br, String[] input) throws IOException {
        int t = Integer.parseInt(br.readLine());
        input = br.readLine().split(" ");
        for (int i = 0; i < t; i++) {
            System.out.print(String.format("%.8f", cache[Integer.parseInt(input[i])]));
            System.out.print(i != t - 1 ? " " : "\n");
        }
    }
}
