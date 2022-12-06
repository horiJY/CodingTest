import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    static int R;
    static int C;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 격자판 R x C 상어 수 M (2 ≤ R, C ≤ 100, 0 ≤ M ≤ R×C)
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        R = input[0];
        C = input[1];
        map = new int[R + 1][C + 1];
        int M = input[2];
        ArrayList<Shark> sharks = new ArrayList<>();
        for (int m = 0; m < M; m++) {
            /**
             * 상어의 정보 r, c, s, d, z
             * (r, c)는 상어의 위치, s는 속력, d는 이동 방향, z는 크기
             */
            input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            sharks.add(new Shark(sharks.size() + 1, input[0], input[1], input[2], input[3], input[4]));
        }
        // data input end

        int fishingPoint = 0;
        int totalFishSize = 0;
        while (++fishingPoint <= C) {
            // fishingPoint와 같은 col 상어 중 수면(col == 1)에 가까운 상어 잡기
            int[] nearShark = new int[] { -1, R + 1 };
            for (int i = 0; i < sharks.size(); i++) {
                if (sharks.get(i).col == fishingPoint) {
                    if (nearShark[1] > sharks.get(i).row) {
                        nearShark[1] = sharks.get(i).row;
                        nearShark[0] = i;
                    }
                }
            }

            if (nearShark[0] != -1) {
                totalFishSize += sharks.get(nearShark[0]).size;
                sharks.remove(nearShark[0]);
            }
            // 상어 잡기 끝

            // 각 상어 별 이동체크
            map = new int[R + 1][C + 1];
            sharks.forEach(s -> s.move());

            // 이동 후 같은 col,row에 위치했다면 size가 큰 상어 제외하고 전부 제거
            for (int r = 1; r <= R; r++) {
                for (int c = 1; c <= C; c++) {
                    if (map[r][c] > 1) {
                        ArrayList<Integer> removeList = new ArrayList<>();
                        int maxSize = 0;
                        for (Shark shark : sharks) {
                            if (shark.row == r && shark.col == c) {
                                removeList.add(shark.number);
                                maxSize = Math.max(maxSize, shark.size);
                            }
                        }
                        final int removeSize = maxSize;
                        for (int removeNumber : removeList) {
                            sharks.removeIf((s) -> s.number == removeNumber && s.size < removeSize);
                        }
                    }

                }
            }
        }

        System.out.println(totalFishSize);
    }

    /**
     * @param row       현재 row index; 1 ≤ r ≤ R
     * @param col       현재 col index; 1 ≤ c ≤ C
     * @param speed     1초당 이동 거리 0 ≤ s ≤ 1000
     * @param direction 이동 방향; 1 ≤ d ≤ 4, 1-위 2-아래 3-우 4-좌
     * @param size      크기 1* ≤ z ≤ 10000
     */
    static private class Shark {
        private int number;
        private int row;
        private int col;
        private int speed;
        private int direction;
        private int size;

        Shark(int number, int row, int col, int speed, int direction, int size) {
            this.number = number;
            this.row = row;
            this.col = col;
            this.speed = speed;
            this.direction = direction;
            this.size = size;
        }

        private void move() {
            if (this.direction == 1 || this.direction == 2) {
                // 방향체크
                int move = this.speed % ((R - 1) * 2);
                // 이동
                while (move > 0) {
                    move--;
                    if (this.direction == 1) {
                        if (this.row == 1) {
                            this.direction = 2;
                            this.row++;
                        } else {
                            this.row--;
                        }
                    } else {
                        if (this.row == R) {
                            this.direction = 1;
                            this.row--;
                        } else {
                            this.row++;
                        }
                    }
                }

            } else if (this.direction == 3 || this.direction == 4) { // 우
                // 방향체크
                int move = this.speed % ((C - 1) * 2);
                // 이동
                while (move > 0) {
                    move--;
                    if (this.direction == 3) {
                        if (this.col == C) {
                            this.direction = 4;
                            this.col--;
                        } else {
                            this.col++;
                        }
                    } else {
                        if (this.col == 1) {
                            this.direction = 3;
                            this.col++;
                        } else {
                            this.col--;
                        }
                    }
                }
            }

            map[this.row][this.col]++;
        }

    }

}