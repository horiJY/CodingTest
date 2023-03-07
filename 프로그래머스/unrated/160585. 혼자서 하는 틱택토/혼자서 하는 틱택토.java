class Solution {
    public int solution(String[] board) {
        int firstCnt = 0, lastCnt = 0;
        char[][] map = new char[3][3];
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                char val = board[y].charAt(x);
                map[y][x] = val;
                if (val == 'O') {
                    firstCnt++;
                } else if (val == 'X') {
                    lastCnt++;
                }
            }
        }

        boolean firstWin = checkBingo(map, 'O'), lastWin = checkBingo(map, 'X');
        if (firstCnt < lastCnt || firstCnt > lastCnt + 1 || (firstWin && lastWin)
                || (lastWin && firstCnt > lastCnt) || (firstWin && firstCnt == lastCnt)) {
            return 0;
        }

        return 1;
    }

    private boolean checkBingo(char[][] map, char val) {
        for (int y = 0; y < map.length; y++) {
            if (val == map[y][0] && val == map[y][1] && val == map[y][2]) {
                return true;
            }
        }

        for (int x = 0; x < map[0].length; x++) {
            if (val == map[0][x] && val == map[1][x] && val == map[2][x]) {
                return true;
            }
        }

        if ((val == map[0][0] && val == map[1][1] && val == map[2][2]) ||
                (val == map[2][0] && val == map[1][1] && val == map[0][2])) {
            return true;
        }

        return false;
    }
}