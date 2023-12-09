import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DP_5JLIS {
    static int[][] lis;
    static int[] A, B;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int C = Integer.parseInt(br.readLine());

        while (C-- > 0) {
            String[] st = br.readLine().split(" ");
            A = new int[Integer.parseInt(st[0])];
            B = new int[Integer.parseInt(st[1])];
            lis = new int[A.length + 1][B.length + 1];

            st = br.readLine().split(" ");
            for (int i = 0; i < A.length; i++) {
                A[i] = Integer.parseInt(st[i]);
            }

            st = br.readLine().split(" ");
            for (int i = 0; i < B.length; i++) {
                B[i] = Integer.parseInt(st[i]);
            }

            // 44,45line의 MIN값을 2번 추가하였으므로 2번 뺌
            int answer = getMax(-1, -1) - 2;
            // A, B의 요소로 -Integer.MIN_VALUE가 들어오면 비교수행이 안됨 -> 결과가 0 return 됨
            System.out.println(answer == 0 ? 1 : answer);
        }

    }

    private static int getMax(int ai, int bi) {
        int ret = lis[ai + 1][bi + 1];
        if (ret != 0) {
            return ret;
        }

        // 시작점(A[0],B[0])에서 LIS의 시작 수 모두 확인해보기 위해 A와 B 앞에 MIN값을 추가하여 수행
        int a = (ai == -1) ? Integer.MIN_VALUE : A[ai];
        int b = (bi == -1) ? Integer.MIN_VALUE : B[bi];
        int max = Math.max(a, b);
        ret = 2;
        for (int i = ai + 1; i < A.length; i++) {
            if (max < A[i]) {
                ret = Math.max(ret, getMax(i, bi) + 1);
            }
        }

        for (int i = bi + 1; i < B.length; i++) {
            if (max < B[i]) {
                ret = Math.max(ret, getMax(ai, i) + 1);
            }
        }

        return lis[ai + 1][bi + 1] = ret;
    }

}