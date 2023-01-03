import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] data = br.readLine().split(" ");
        // 3 ≤ N, M ≤ 15
        int N = Integer.parseInt(data[0]);
        int M = Integer.parseInt(data[1]);
        // 1 ≤ D ≤ 10
        int D = Integer.parseInt(data[2]);

        int totalEnemy = 0;
        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++) {
            data = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(data[j]);
                if (map[i][j] != 0) {
                    totalEnemy++;
                }
            }
        }
        // data input end

        // 병사는 3명 배치
        int answer = 0;
        for (int f = 0; f < M - 2; f++) {
            for (int s = f + 1; s < M - 1; s++) {
                for (int t = s + 1; t < M; t++) {
                    answer = Math.max(answer, getScore(map, new int[] { f, s, t }, D, totalEnemy));
                    if (answer == totalEnemy) {
                        System.out.println(totalEnemy);
                        return;
                    }
                }
            }
        }

        System.out.println(answer);

    }

    /**
     * 최대 소요시간 O(N*N*N*3) // N = 15
     * 
     * @param map      : 적 배치
     * @param soldiers : 궁수 X좌표
     * @param D        : 사거리
     * @return
     */
    private static int getScore(int[][] mapOrigin, int[] soldiers, int D, int totalEnemy) {
        int[][] map = new int[mapOrigin.length][mapOrigin[0].length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] = mapOrigin[i][j];
            }
        }

        ArrayList<int[]>[] remove = new ArrayList[3];
        int[] minDistforRemove = new int[] { -1, -1, -1 };
        for (int i = 0; i < remove.length; i++) {
            remove[i] = new ArrayList<>();
        }

        int solY = 0;
        int result = 0;
        for (int round = map.length - 1; round >= 0; round--) { // N
            int shotCount = 0;
            solY = round + 1;
            for (int solIdx = shotCount; solIdx < soldiers.length; solIdx++) {
                for (int y = round; y >= 0 && y >= solY - D; y--) {
                    for (int x = 0; x < map[0].length; x++) { // M
                        if (map[y][x] == 1 && shotCount < 3) {
                            int distance = validDistance(x, y, soldiers[solIdx], solY);
                            if (distance <= D) {
                                if (remove[solIdx].isEmpty()) {
                                    remove[solIdx].add(new int[] { y, x });
                                    minDistforRemove[solIdx] = distance;
                                } else if (distance < minDistforRemove[solIdx]) {
                                    remove[solIdx].add(0, new int[] { y, x });
                                    minDistforRemove[solIdx] = distance;
                                } else if (distance == minDistforRemove[solIdx] && x < remove[solIdx].get(0)[1]) {
                                    remove[solIdx].add(0, new int[] { y, x });
                                }
                            }
                        }
                    }
                }
            }

            while (shotCount < 3) {
                if (remove[shotCount].size() != 0) {
                    int[] enemy = remove[shotCount].get(0);
                    if (map[enemy[0]][enemy[1]] == 1) {
                        result++;
                        map[enemy[0]][enemy[1]] = 0;
                    }
                }
                remove[shotCount].clear();
                shotCount++;
            }

            if (result == totalEnemy) {
                break;
            }
        }

        return result;

    }

    // 격자판의 두 위치 (r1, c1), (r2, c2)의 거리 |r1-r2| + |c1-c2|
    private static int validDistance(int enemyX, int enemyY, int solX, int solY) {
        return Math.abs(solX - enemyX) + Math.abs(solY - enemyY);
    }
}