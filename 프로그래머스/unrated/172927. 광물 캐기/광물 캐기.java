import java.util.Arrays;

class Solution {
    public int solution(int[] picks, String[] minerals) {
        int[][] dp = new int[(int) Math.ceil(minerals.length / 5.0)][picks.length];
        int pickCnt = picks[0] + picks[1] + picks[2];
        int mineralIdx = 0;
        int answer = 0;
        for (int i = 0; i < dp.length && i < pickCnt; i++) {
            int diaCnt = 0;
            int ironCnt = 0;
            int stoneCnt = 0;
            for (int j = 0; j < 5 && mineralIdx < minerals.length; j++) {
                switch (minerals[mineralIdx++]) {
                    case "diamond" -> diaCnt++;
                    case "iron" -> ironCnt++;
                    case "stone" -> stoneCnt++;
                }
            }
            dp[i] = new int[] {
                    diaCnt + ironCnt + stoneCnt,
                    diaCnt * 5 + ironCnt + stoneCnt,
                    diaCnt * 25 + ironCnt * 5 + stoneCnt
            };
        }

        Arrays.sort(dp, (o1, o2) -> o2[2] - o1[2]);
        for (int i = 0; i < dp.length; i++) {
            if (picks[0] > 0) {
                picks[0]--;
                answer += dp[i][0];
            } else if (picks[1] > 0) {
                picks[1]--;
                answer += dp[i][1];
            } else if (picks[2] > 0) {
                picks[2]--;
                answer += dp[i][2];
            }

        }
        System.out.println(answer);
        return answer;
    }
}