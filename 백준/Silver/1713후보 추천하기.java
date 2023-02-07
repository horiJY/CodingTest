import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] answer = new int[N][2];
        int M = Integer.parseInt(br.readLine());
        String[] input = br.readLine().split(" ");
        for (String s : input) {
            int target = Integer.parseInt(s);

            for (int i = 0; i < answer.length; i++) {
                if (answer[i][0] == 0) {
                    answer[i] = new int[] { target, 1 };
                    break;
                } else if (answer[i][0] == target) {
                    answer[i][1]++;
                    break;
                } else if (i == answer.length - 1) { // 사진틀이 다 차있는 경우
                    int delIndex = 0;
                    for (int j = 1; j < answer.length; j++) {
                        if (answer[j][1] < answer[delIndex][1]) {
                            delIndex = j;
                        }
                    }
                    answer[delIndex] = new int[] { -1, -1 };
                    Arrays.sort(answer, (o1, o2) -> o2[1] - o1[1]); // 투표수 내림차순 정렬
                    answer[answer.length - 1] = new int[] { target, 1 };
                }
            }
        }

        Arrays.sort(answer, (o1, o2) -> o1[0] - o2[0]);
        for (int[] is : answer) {
            if (is[0] != 0) {
                System.out.print(is[0] + " ");
            }
        }
    }
}
