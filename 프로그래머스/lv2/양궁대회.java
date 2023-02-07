class Solution {
    static int[] ryan, apeach, ans;
    static boolean isFind;
    static int differenceScore;

    public int[] solution(int n, int[] info) {
        apeach = info.clone();
        ryan = new int[11];

        differenceScore = Integer.MIN_VALUE;
        ans = new int[11];
        isFind = false;
        getMaxDifferenceScore(0, n, 0, 0);

        return isFind == true ? ans : new int[] { -1 };
    }

    private static void getMaxDifferenceScore(int depth, int arrow, int apeachScore, int ryanScore) {
        if (depth == 11) {
            if (arrow == 0 && ryanScore > apeachScore) {
                isFind = true;
                if (differenceScore < (ryanScore - apeachScore)) {
                    differenceScore = ryanScore - apeachScore;
                    ans = ryan.clone();
                } else if (differenceScore == (ryanScore - apeachScore)) {
                    // 라이언이 우승할 수 있는 방법이 여러개면 가장 낮은 점수를 더 많이 맞힌 경우 return
                    for (int i = 10; i >= 0; i--) {
                        if (ryan[i] > ans[i]) {
                            ans = ryan.clone();
                            return;
                        } else if (ryan[i] < ans[i])
                            return;
                    }
                }
            }
            return;
        }

        if (apeach[depth] == 0) {
            getMaxDifferenceScore(depth + 1, arrow, apeachScore, ryanScore);
        }

        // 승리
        if (arrow > apeach[depth]) {
            ryan[depth] = apeach[depth] + 1;
            getMaxDifferenceScore(depth + 1, arrow - apeach[depth] - 1, apeachScore, ryanScore + (10 - depth));
            ryan[depth] = 0;
        }

        // 패배
        if (apeach[depth] != 0) {
            for (int i = 0; i < apeach[depth]; i++) {
                if (arrow >= i) {
                    ryan[depth] = i;
                    getMaxDifferenceScore(depth + 1, arrow - i, apeachScore + (10 - depth), ryanScore);
                    ryan[depth] = 0;
                }
            }
        }
    }
}