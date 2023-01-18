import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

class Solution {
    static int[] movingY = new int[] { 0, 1, 1 };
    static int[] movingX = new int[] { 1, 0, 1 };

    public int solution(int m, int n, String[] board) {
        int answer = 0;
        String[][] boardMap = new String[m][n];
        for (int i = 0; i < boardMap.length; i++) {
            boardMap[i] = board[i].split("");
        }

        HashSet<List<Integer>> removeSet = new HashSet<>();
        for (int y = 0; y < m - 1; y++) {
            for (int x = 0; x < n - 1; x++) {
                if (!boardMap[y][x].equals("-")) {
                    validBrokenElement(removeSet, boardMap, y, x, m, n);
                }
            }

            if (y == m - 2 && !removeSet.isEmpty()) {
                for (List<Integer> ls : removeSet) {
                    if (!boardMap[ls.get(0)][ls.get(1)].equals("-")) {
                        boardMap[ls.get(0)][ls.get(1)] = "-";
                        answer++;
                    }
                }
                removeSet.clear();
                refreshBoard(boardMap);
                y = -1;
            }
        }

        return answer;
    }

    private void refreshBoard(String[][] boardMap) {
        for (int x = 0; x < boardMap[0].length; x++) {
            for (int y = boardMap.length - 1; y >= 0; y--) {
                if (boardMap[y][x].equals("-")) {
                    int targetY = y;
                    while (--targetY >= 0) {
                        if (!boardMap[targetY][x].equals("-")) {
                            boardMap[y][x] = boardMap[targetY][x];
                            boardMap[targetY][x] = "-";
                            break;
                        }
                    }
                }
            }
        }
    }

    private void validBrokenElement(HashSet<List<Integer>> removeSet, String[][] boardMap, int y, int x, int m, int n) {
        boolean addFlag = true;
        for (int j = 0; j < movingY.length; j++) {
            if (!boardMap[y][x].equals(boardMap[y + movingY[j]][x + movingX[j]])) {
                addFlag = false;
                break;
            }
        }

        if (addFlag) {
            removeSet.add(Arrays.asList(y, x));
            for (int j = 0; j < movingY.length; j++) {
                removeSet.add(Arrays.asList(y + movingY[j], x + movingX[j]));
            }
        }
    }
}

