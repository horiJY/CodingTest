import java.util.Arrays;

class Solution {
    public int solution(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            if (Arrays.stream(board[i]).filter(e -> e == 0).count() == board.length) {
                if (i == board.length - 1) {
                    return 0;
                }
            } else {
                break;
            }
        }

        int answer = 1;
        for (int y = 1; y < board.length; y++) {
            for (int x = 1; x < board[0].length; x++) {
                if (board[y][x] == 0)
                    continue;
                board[y][x] = Math.min(Math.min(board[y - 1][x], board[y][x - 1]), board[y - 1][x - 1]) + 1;
                answer = Math.max(answer, board[y][x]);
            }
        }

        return answer * answer;
    }
}

// 왜 dp 인가??