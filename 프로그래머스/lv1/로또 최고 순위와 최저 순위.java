class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        int zeroCnt = 0;
        int equalCnt = 0;
        for (int checkNum : lottos) {
            // 0개수,맞은개수 체크
            if (checkNum == 0) {
                zeroCnt++;
            } else {
                for (int compareNum : win_nums) {
                    if (checkNum == compareNum) {
                        equalCnt++;
                    }
                }
            }
        }
        // 1등(6), ... 5등(2) 6등(낙첨 1~0)
        int topRank = 6;
        int rowRank = 6;
        if (zeroCnt + equalCnt > 1) {
            topRank = 7 - (zeroCnt + equalCnt);
            if (equalCnt > 1) {
                rowRank = 7 - equalCnt;
            }
        }

        // 최고순위, 최저순위
        int[] answer = { topRank, rowRank };
        return answer;
    }
}