class Solution {
    public int[] solution(int n) {
        int[][] board = new int[n][n];
        int x = 0, y = -1, cnt = 1;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (i % 3 == 0) {
                    y++;
                } else if (i % 3 == 1) {
                    x++;
                } else {
                    y--;
                    x--;
                }
                board[y][x] = cnt++;
            }
        }

        int[] answer = new int[cnt - 1];
        cnt = 0;
        for (int[] is : board) {
            for (int i : is) {
                if (i != 0) {
                    answer[cnt++] = i;
                }
            }
        }

        return answer;
    }
}