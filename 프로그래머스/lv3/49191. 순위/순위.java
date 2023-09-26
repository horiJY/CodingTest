import java.util.Arrays;

class Solution {
    public int solution(int n, int[][] results) {
        int[] rank = new int[n + 1];
        int[][] scoreBoard = new int[n + 1][n + 1];

        for (int[] result : results) {
            scoreBoard[result[0]][result[1]] = 1; // win
            scoreBoard[result[1]][result[0]] = -1; // lose
        }

        for (int i = 1; i < scoreBoard.length; i++) {

            for (int j = 1; j < scoreBoard[i].length; j++) {
                if (scoreBoard[i][j] == 1) {
                    for (int k = 1; k < scoreBoard[j].length; k++) {
                        if (scoreBoard[i][k] == -1) {
                            scoreBoard[j][k] = -1;
                        }
                    }
                }
                if (scoreBoard[i][j] == -1) {
                    for (int k = 1; k < scoreBoard[j].length; k++) {
                        if (scoreBoard[i][k] == 1) {
                            scoreBoard[j][k] = 1;
                        }
                    }
                }
            }
        }

        for (int i = 1; i < scoreBoard.length; i++) {
            int win = (int) Arrays.stream(scoreBoard[i]).filter(e -> e == 1).count();
            int lose = (int) Arrays.stream(scoreBoard[i]).filter(e -> e == -1).count();

            if (win + lose == n - 1) {
                rank[i] = (n - win);
            }
        }

        return (int) Arrays.stream(rank).filter(e -> e != 0).count();
    }
}