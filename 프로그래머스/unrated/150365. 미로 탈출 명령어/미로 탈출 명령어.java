class Solution {
    // 사전순 d l r u
    int[] dx = { 1, 0, 0, -1 };
    int[] dy = { 0, -1, 1, 0 };
    Character[] moveDir = { 'd', 'l', 'r', 'u' };
    boolean isFinish = false;
    int n, m, ex, ey;
    String answer = "impossible";

    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        this.n = n;
        this.m = m;
        ex = r;
        ey = c;

        if (!validArrival(x, y, k)) {
            return answer;
        }

        DFS(x, y, k, "");
        return answer;
    }

    public boolean validArrival(int x, int y, int remainMove) {
        int minRequireMove = Math.abs(x - ex) + Math.abs(y - ey);

        if (minRequireMove > remainMove || (remainMove - minRequireMove) % 2 == 1) {
            return false;
        } else {
            return true;
        }
    }

    public void DFS(int x, int y, int cnt, String cur) {
        if (isFinish || cnt < 0) {
            return;
        }

        if (x == ex && y == ey && cnt == 0) {
            isFinish = true;
            answer = cur;
            return;
        }

        for (int k = 0; k < dx.length; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];
            if (nx < 1 || ny < 1 || nx > n || ny > m || !validArrival(nx, ny, cnt - 1)) {
                continue;
            }

            if (cnt > 0) {
                DFS(nx, ny, cnt - 1, cur + moveDir[k]);
            }

        }
    }
}