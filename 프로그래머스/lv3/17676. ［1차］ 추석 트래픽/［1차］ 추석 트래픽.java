import java.util.Arrays;

class Solution {
    public int solution(String[] lines) {
        int answer = 0;
        int[][] logTimeArr = new int[lines.length][2];
        for (int i = 0; i < lines.length; i++) {
            String[] l = lines[i].split(" ");
            l[2] = l[2].replaceAll("[.s]", "");
            logTimeArr[i][1] = convertMilSecond(l[1]);
            logTimeArr[i][0] = logTimeArr[i][1] - Integer.valueOf(l[2] + "0".repeat(4 - l[2].length())) + 1;
        }

        for (int i = 0; i < logTimeArr.length; i++) {
            int tempCnt = 1;
            for (int j = i + 1; j < logTimeArr.length; j++) {
                if (logTimeArr[i][1] + 999 >= logTimeArr[j][0]) {
                    tempCnt++;
                }
            }
            answer = Math.max(answer, tempCnt);
        }

        return answer;
    }

    private int convertMilSecond(String sts) {
        String[] st = sts.split("[:.]");
        return Integer.valueOf(st[0]) * 60 * 60 * 1000 + Integer.valueOf(st[1]) * 60 * 1000
                + Integer.valueOf(st[2]) * 1000 + Integer.valueOf(st[3]);
    }

}