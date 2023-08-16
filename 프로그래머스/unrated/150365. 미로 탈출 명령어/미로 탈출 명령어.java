class Solution {
    // d l r u
    int[] dx = { 1, 0, 0, -1 };
    int[] dy = { 0, -1, 1, 0 };
    Character[] moveDir = { 'd', 'l', 'r', 'u' };
    boolean isFinish = false;
    int n, m, ex, ey;
    String answer = "impossible";

    // n x m
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        this.n = n;
        this.m = m;
        ex = r;
        ey = c;

        if (x == ex && y == ey)
            return "";
        if (!validArrival(x, y, k))
            return answer;
        DFS(x, y, k - 1, "");
        return answer;
    }

    // 거리, 움직일 횟수
    public boolean validArrival(int sx, int sy, int k) {
        int d = Math.abs(sx - ex) + Math.abs(sy - ey);
        if (d > k || (k - d) % 2 == 1)
            return false;
        else
            return true;
    }

    public void DFS(int x, int y, int cnt, String cur) {
        if (isFinish || cnt < 0)
            return;

        for (int k = 0; k < dx.length; k++) {
            if (isFinish)
                return;
            int nx = x + dx[k];
            int ny = y + dy[k];
            if (nx < 1 || ny < 1 || nx > n || ny > m || !validArrival(nx, ny, cnt)) {
                continue;
            }

            if (cnt > 0) {
                DFS(nx, ny, cnt - 1, cur + moveDir[k]);
            }

            if (nx == ex && ny == ey && cnt == 0) {
                isFinish = true;
                answer = cur + moveDir[k];
                return;
            }
        }
    }
}