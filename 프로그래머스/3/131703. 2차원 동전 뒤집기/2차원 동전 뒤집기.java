import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

class Solution {
    public int solution(int[][] beginning, int[][] target) {
        int answer = Integer.MAX_VALUE;
        Deque<Board> que = new ArrayDeque<>();
        que.add(new Board(beginning, 0));
        que.add(new Board(beginning, 0));
        turnHorizon(que.peekLast().board, 0);
        que.peekLast().count++;

        while (!que.isEmpty()) {
            Board cur = que.pop();
             // col 동기화
            for (int x = 0; x < target[0].length; x++) {
                if (cur.board[0][x] != target[0][x]) {
                    turnVertical(cur.board, x);
                    cur.count++;
                }
            }
            // row 동기화
            for (int y = 0; y < target.length; y++) {
                if (cur.board[y][0] != target[y][0]) {
                    turnHorizon(cur.board, y);
                    cur.count++;
                }
            }

            if (isEquals(cur.board, target)) {
                answer = Math.min(answer, cur.count);
            }
        }

        if (answer != Integer.MAX_VALUE) {
            return answer;
        }
        return -1;
    }

    public boolean isEquals(int[][] board, int[][] target) {
        for (int i = 0; i < board.length; i++) {
            if (!Arrays.equals(board[i], target[i])) {
                return false;
            }
        }
        return true;
    }

    public void turnVertical(int[][] board, int col) {
        for (int i = 0; i < board.length; i++) {
            board[i][col] = (board[i][col] + 1) % 2;
        }
    }

    public void turnHorizon(int[][] board, int row) {
        for (int i = 0; i < board[0].length; i++) {
            board[row][i] = (board[row][i] + 1) % 2;
        }
    }

    class Board {
        int[][] board;
        int count;

        public Board(int[][] board, int count) {
            this.board = new int[board.length][board[0].length];
            this.count = count;
            for (int i = 0; i < this.board.length; i++) {
                for (int j = 0; j < board.length; j++) {
                    this.board[i] = board[i].clone();
                }
            }
        }
    }

}