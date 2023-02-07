import java.util.ArrayList;

class Solution {
    public int solution(int[][] board, int[] moves) {
        ArrayList<Integer> list = new ArrayList<>();
        int answer = 0;
        // 1.ArrayList로 찾기
        for (int col : moves) {
            for (int j = 0; j < board.length; j++) {
                if (board[j][col - 1] != 0) {
                    list.add(board[j][col - 1]);
                    board[j][col - 1] = 0;
                    break;
                }
            }
        }
        for (int l = 0, e = list.size(); l < e; l++) {
            for (int i = 0; i < list.size() - 1; i++) {
                if (list.get(i) == list.get(i + 1)) {
                    list.remove(i + 1);
                    list.remove(i);
                    answer += 2;
                }
            }
        }
        return answer;
    }
}