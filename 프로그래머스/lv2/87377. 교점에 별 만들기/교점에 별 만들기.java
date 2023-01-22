import java.util.HashSet;

class Solution {
    static HashSet<long[]> crossP;

    public String[] solution(int[][] line) {
        crossP = new HashSet<>();
        for (int pre = 0; pre < line.length; pre++) {
            for (int sur = pre + 1; sur < line.length; sur++) {
                calculatorCrossPoint(line[pre], line[sur]);
            }
        }

        long minX = Long.MAX_VALUE;
        long maxX = Long.MIN_VALUE;
        long minY = Long.MAX_VALUE;
        long maxY = Long.MIN_VALUE;
        for (long[] is : crossP) {
            minX = Math.min(minX, is[1]);
            maxX = Math.max(maxX, is[1]);
            minY = Math.min(minY, is[0]);
            maxY = Math.max(maxY, is[0]);
        }

        char[][] map = new char[(int) (maxY - minY + 1)][(int) (maxX - minX + 1)];
        for (long[] is : crossP) {
            map[(int) (is[0] - minY)][(int) (is[1] - minX)] = '*';
        }

        String[] answer = new String[map.length];
        for (int i = map.length - 1; i >= 0; i--) {
            answer[map.length - i - 1] = "";
            for (char c : map[i]) {
                if (c == '*') {
                    answer[map.length - i - 1] += '*';
                } else {
                    answer[map.length - i - 1] += '.';
                }
            }
        }
        return answer;
    }

    private void calculatorCrossPoint(int[] pre, int[] sur) {
        long down = ((long) pre[0] * (long) sur[1] - (long) pre[1] * (long) sur[0]);
        if (down == 0) {
            return;
        }

        long xu = ((long) pre[1] * (long) sur[2] - (long) pre[2] * (long) sur[1]);
        long yu = ((long) pre[2] * (long) sur[0] - (long) pre[0] * (long) sur[2]);
        if (xu % down == 0 && yu % down == 0) {
            crossP.add(new long[] { yu / down, xu / down });
        }
    }
}