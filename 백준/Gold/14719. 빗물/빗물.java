import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        int[][] map = new int[H][W];
        st = new StringTokenizer(br.readLine(), " ");
        for (int w = 0; w < W; w++) {
            int d = Integer.parseInt(st.nextToken());
            for (int h = 1; h <= d; h++) {
                map[H - h][w] = 1;
            }
        }
        // data input end

        int answer = 0;
        for (int h = 0; h < H; h++) {
            int waterCount = 0;
            boolean startWall = false;
            for (int w = 0; w < W; w++) {
                if (map[h][w] == 1) {
                    if (startWall) {
                        answer += waterCount;
                        waterCount = 0;
                    } else {
                        startWall = true;
                    }
                } else if (startWall && map[h][w] == 0) {
                    waterCount++;
                }
            }
        }

        System.out.println(answer);
    }
}
