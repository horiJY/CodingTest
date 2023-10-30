class Solution {
     int[] dy = { 0, 0, 1, -1 };
     int[] dx = { 1, -1, 0, 0 };

    public int[] getBeforeRange(int s, int e, int move, int max) {
        int beforeS = (s == 0 && move > 0) ? 0 : s + move;
        int beforeE = (e == max - 1 && move < 0) ? max - 1 : e + move;
        // 1 둘 다 범위 벗어남 -> 도달할 수 없음
        if ((beforeS < 0 || beforeS >= max) && (beforeE < 0 || beforeE >= max)) {
            return new int[] { -1, -1 };
        }
        // 2 시작점만 범위 벗어남
        if (beforeS < 0 && (beforeE >= 0 && beforeE < max)) {
            return new int[] { 0, beforeE };
        }
        // 3 종료점만 범위 벗어남
        if ((beforeS >= 0 && beforeS < max) && beforeE >= max) {
            return new int[] { beforeS, max - 1 };
        }
        // 4 둘 다 범위 이내
        return new int[] { beforeS, beforeE };
    }

    public long solution(int n, int m, int y, int x, int[][] queries) {

        int startX, endX, startY, endY;
        startY = endY = y;
        startX = endX = x;
        for (int i = queries.length - 1; i >= 0; i--) {
            int dir = queries[i][0];
            int cnt = queries[i][1];

            // 행(y)
            if (dir > 1) {
                int[] res = getBeforeRange(startY, endY, cnt * dy[dir], n);
                if (res[0] == -1)
                    return 0;
                startY = res[0];
                endY = res[1];
            } else {// 열(x)
                int[] res = getBeforeRange(startX, endX, cnt * dx[dir], m);
                if (res[0] == -1)
                    return 0;
                startX = res[0];
                endX = res[1];
            }
        }
        return (long) (endX - startX + 1) * (long) (endY - startY + 1);
    }
}