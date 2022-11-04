import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[] liquids;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int size = Integer.parseInt(br.readLine());

        liquids = new int[size];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < size; i++) {
            liquids[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(liquids);

        int firstPoint = 0;
        int lastPoint = size - 1;
        int[] answer = new int[] { Integer.MAX_VALUE / 2, Integer.MAX_VALUE / 2 };
        while (firstPoint < lastPoint) {
            if (Math.abs(answer[0] + answer[1]) > Math.abs(liquids[firstPoint] + liquids[lastPoint])) {
                answer[0] = liquids[firstPoint];
                answer[1] = liquids[lastPoint];
                if (answer[0] + answer[1] == 0) {
                    break;
                }
            }

            if (liquids[firstPoint] + liquids[lastPoint] < 0) {
                firstPoint++;
            } else {
                lastPoint--;
            }

        }
        System.out.println(answer[0] + " " + answer[1]);
    }

}
