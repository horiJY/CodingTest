import java.util.Arrays;

class Solution {
    int N, M;

    public boolean solution(int[][] key, int[][] lock) {
        N = key.length;
        M = lock.length;

        for (int i = 0; i < 4; i++) {
            key = rotate90Degree(key);
            for (int padX = -N; padX <= M; padX++) {
                for (int padY = -N; padY <= M; padY++) {
                    if (validOpen(lock, key, padX, padY)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private boolean validOpen(int[][] lock, int[][] key, int padX, int padY) {
        boolean keyOut = false, lockOut = false, keyOutRange = false;
        for (int y = 0; y < M; y++) {
            for (int x = 0; x < M; x++) {
                lockOut = lock[y][x] == 1 ? true : false;
                int dy = y - padY;
                int dx = x - padX;
                keyOutRange = (dy < 0 || dy >= N || dx < 0 || dx >= N) ? true : false;

                if (keyOutRange) {
                    if (!lockOut) {
                        return false;
                    }
                } else {
                    keyOut = key[dy][dx] == 1 ? true : false;
                    if ((lockOut && keyOut) || (!lockOut && !keyOut)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    private int[][] rotate90Degree(int[][] key) {
        int[][] temp = new int[N][N];

        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                temp[x][N - y - 1] = key[y][x];
            }
        }

        return temp;
    }
}