import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        int[] sequence = new int[size];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < sequence.length; i++) {
            sequence[i] = Integer.parseInt(st.nextToken());
        }

        int[] moreCount = new int[size];
        for (int i = 0; i < sequence.length; i++) {
            moreCount[i] = 1;
            for (int j = 0; j <= i; j++) {
                if (sequence[j] < sequence[i] && moreCount[i] < moreCount[j] + 1) {
                    moreCount[i] = moreCount[j] + 1;
                }
            }
        }

        int answer = 1;
        for (int count : moreCount) {
            answer = answer < count ? count : answer;
        }
        System.out.println(answer);
    }
}