class Solution {
    /*
     * return : 최선의 경우 던질 다트 수와 그 때의 "싱글" 또는 "불"을 맞춘 횟수(합)
     * 우선순위
     * 1. 최소한의 다트로 0점
     * 2. 여러가지가 있다면 "싱글" 또는 "불"을 최대한 많이 던지는 방법
     */
    public int[] solution(int target) {
        int[][] cache = new int[target + 1][2];
        cache[0][0] = 0;
        cache[0][1] = 0;
        for (int i = 1; i <= target; i++) {
            cache[i] = new int[] { 100_000, -100_000 };
        }

        // f(n) : n점 최소 다트 수
        // f(n) =
        // (f(n) != f(n - score) + 1) ? MIN(f(n), f(n - score) + 1)
        // : MAX(k(n), k(n - score) + 1)
        for (int n = 0; n < target; n++) {

            // single ~ triple
            for (int m = 1; m <= 3; m++) {
                for (int d = 1 * m; d <= 20 * m; d += m) {
                    if (n + d > target) {
                        break;
                    }

                    if (cache[n][0] + 1 < cache[n + d][0]) {
                        cache[n + d][0] = cache[n][0] + 1;
                        if (m == 1) {
                            cache[n + d][1] = cache[n][1] + 1;
                        } else {
                            cache[n + d][1] = cache[n][1];
                        }
                    } else if (cache[n][0] + 1 == cache[n + d][0] && cache[n][1] + 1 > cache[n + d][1]) {
                        if (m == 1) {
                            cache[n + d][1] = cache[n][1] + 1;
                        } else {
                            cache[n + d][1] = cache[n][1];
                        }
                    }
                }
            }
            // s ~ t end

            // bull
            if (n + 50 > target) {
                continue;
            }
            if (cache[n][0] + 1 < cache[n + 50][0]) {
                cache[n + 50][0] = cache[n][0] + 1;
                cache[n + 50][1] = cache[n][1] + 1;
            } else if (cache[n][0] + 1 == cache[n + 50][0] && cache[n][1] + 1 > cache[n + 50][1]) {
                cache[n + 50][1] = cache[n][1] + 1;
            }
            // bull end
        }

        return cache[target];
    }
}
