import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 유사문제 : https://www.acmicpc.net/problem/6549
 * 
 * 첫줄에 테스트 케이스의 개수 C(C<=50)
 * 각 테스트 케이스의 첫 줄에는 판자의 수 N(1<=N<=20000)
 * 판자의 높이는 모두 10_000 이하의 자연수
 */
public class BS_2Fence {
    static int[] fence;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int C = Integer.parseInt(br.readLine());

        while (C-- > 0) {
            int len = Integer.parseInt(br.readLine());
            fence = new int[len];
            StringTokenizer sc = new StringTokenizer(br.readLine());

            for (int i = 0; i < fence.length; i++) {
                fence[i] = Integer.parseInt(sc.nextToken());
            }

            System.out.println(getMaxSquareArea(0, fence.length - 1));

        }
    }

    private static int getMaxSquareArea(int left, int right) {
        // 좌, 우 비교
        if (left == right) {
            return fence[left];
        }

        int mid = (left + right) / 2;
        int result = Math.max(getMaxSquareArea(left, mid), getMaxSquareArea(mid + 1, right));
        int s = mid;
        int e = mid + 1;
        int h = Math.min(fence[s], fence[e]);
        result = Math.max(result, h * 2);

        // 가운데 구간 비교
        while (left < s || e < right) {
            if (e < right && ((s == left) || fence[s - 1] < fence[e + 1])) {
                e++;
                h = Math.min(h, fence[e]);
            } else {
                s--;
                h = Math.min(h, fence[s]);
            }
            result = Math.max(result, h * (e - s + 1));
        }

        return result;
    }
}
