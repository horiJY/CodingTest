import java.util.Arrays;

class Solution {
    public int solution(int n, int[][] results) {
        int answer=0;
        int[] rank = new int[n + 1];
        int[][] scoreBoard = new int[n + 1][n + 1];

        for (int[] result : results) {
            scoreBoard[result[0]][result[1]] = 1; // win
            scoreBoard[result[1]][result[0]] = -1; // lose
        }

        for (int base = 1; base < scoreBoard.length; base++) {
            for (int target = 1; target < scoreBoard[base].length; target++) {
                if (scoreBoard[base][target] == 1) {
                    for (int i = 1; i < scoreBoard[target].length; i++) {
                        if (scoreBoard[base][i] == -1) {
                            scoreBoard[target][i] = -1;
                        }
                    }
                }
                if (scoreBoard[base][target] == -1) {
                    for (int k = 1; k < scoreBoard[target].length; k++) {
                        if (scoreBoard[base][k] == 1) {
                            scoreBoard[target][k] = 1;
                        }
                    }
                }
            }
        }

        for (int i = 1; i < scoreBoard.length; i++) {
            int win = (int) Arrays.stream(scoreBoard[i]).filter(e -> e == 1).count();
            int lose = (int) Arrays.stream(scoreBoard[i]).filter(e -> e == -1).count();

            if (win + lose == n - 1) {
                answer++;
            }
        }

        return answer;
    }
}