class Solution {
    static long[][] count;

    public int solution(int m, int n, int[][] puddles) {
        count = new long[m + 1][n + 1];
        for (int[] is : puddles) {
            count[is[0]][is[1]] = -1;
        }
        count[1][1] = 1;
        calculateMoveCount(m, n);

        return (int) count[m][n];
    }

    private void calculateMoveCount(int height, int width) {
        for (int y = 1; y <= height; y++) {
            for (int x = 1; x <= width; x++) {
                if ((y == 1 && x == 1) || count[y][x] == -1) {
                    continue;
                }
                
                if (count[y - 1][x] != -1) {
                    count[y][x] += count[y - 1][x];
                }
                
                if (count[y][x - 1] != -1) {
                    count[y][x] += count[y][x - 1];
                }
                
                count[y][x] %= 1_000_000_007;
            }
        }
    }
}