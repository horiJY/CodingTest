import java.util.Arrays;

class Solution {
    public int solution(int alp, int cop, int[][] problems) {
        int targetAlp = alp;
        int targetCop = cop;
        for (int[] is : problems) {
            targetAlp = Math.max(targetAlp, is[0]);
            targetCop = Math.max(targetCop, is[1]);
        }

        int[][] memo = new int[targetAlp + 20][targetCop + 20];
        for (int i = 0; i < memo.length; i++) {
            Arrays.fill(memo[i], Integer.MAX_VALUE);
        }
        memo[alp][cop] = 0;

        for (int cur_alp = alp; cur_alp < memo.length - 1; cur_alp++) {
            for (int cur_cop = cop; cur_cop < memo[0].length - 1; cur_cop++) {
                memo[cur_alp + 1][cur_cop] = Math.min(memo[cur_alp + 1][cur_cop], memo[cur_alp][cur_cop] + 1);
                memo[cur_alp][cur_cop + 1] = Math.min(memo[cur_alp][cur_cop + 1], memo[cur_alp][cur_cop] + 1);
                
                for (int[] problem : problems) {
                    if (cur_alp >= problem[0] && cur_cop >= problem[1]) {
                        int nextAlp = cur_alp + problem[2] > targetAlp ? targetAlp : cur_alp + problem[2];
                        int nextCop = cur_cop + problem[3] > targetCop ? targetCop : cur_cop + problem[3];

                        memo[nextAlp][nextCop] = Math.min(memo[nextAlp][nextCop],
                                memo[cur_alp][cur_cop] + problem[4]);
                    }

                }

            }
        }

        return memo[targetAlp][targetCop];
    }
}