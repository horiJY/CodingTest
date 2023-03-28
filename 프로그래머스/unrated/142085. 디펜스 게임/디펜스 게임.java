import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Solution {
    private static int dn, dk;
    private static int[] enemyClone;

    public static int solution(int n, int k, int[] enemy) {
        dn = n;
        dk = k;
        enemyClone = enemy;

        return binarySearch();
    }

    private static int binarySearch() {
        int left = 0;
        int right = enemyClone.length;

        while (left < right) {
            int mid = (left + right) / 2;
            // mid 라운드 까지 막을 수 있는지 확인
            if (validDefence(mid)) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    private static boolean validDefence(int mid) {
        List<Integer> stage = Arrays.stream(enemyClone, 0, mid + 1)
                .boxed()
                .sorted()
                .collect(Collectors.toList());
        int n = dn;
        int stageSize = stage.size();

        for (int i = 0; i < stageSize; i++) {
            Integer val = stage.get(i);
            if (n >= val) {
                n -= val;
                continue;
            }
            // 남은 라운드를 무적권을 통해 skip
            return dk >= stageSize - i;
        }
        return true;
    }
}