import java.util.ArrayList;
import java.util.Collections;

class Solution {
    static boolean[][][] check;// [행][열][방향]
    static int R, C; // 행, 열
    static int[] x = { -1, 0, 1, 0 }; // 아래쪽, 위쪽
    static int[] y = { 0, -1, 0, 1 }; // 오른쪽, 왼쪽

    public int[] solution(String[] grid) {
        ArrayList<Integer> ans = new ArrayList<Integer>();

        R = grid.length; // 행
        C = grid[0].length(); // 열

        check = new boolean[R][C][4];

        // 모든 정점에서 4가지 방향으로 빛 쏘기
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                for (int k = 0; k < 4; k++) {
                    if (!check[i][j][k]) {
                        ans.add(cycle(grid, i, j, k));
                    }
                }
            }
        }

        Collections.sort(ans);// 사이클 정렬
        int[] answer = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            answer[i] = ans.get(i); // 배열에 담기
        }

        return answer; // 반환
    }

    public static int cycle(String[] grid, int r, int c, int k) {
        int cnt = 0;

        while (true) {
            if (check[r][c][k])
                break;// 다시 되돌아와 졌다면 사이클 생성된거.

            cnt++;// 한번반복 할때마다 빛의 경로 이동.
            check[r][c][k] = true;// 빛을 쏜 정점 방문처리(방향포함)

            if (grid[r].charAt(c) == 'L') {
                k = (k + 3) % 4; // 좌회전
            } else if (grid[r].charAt(c) == 'R') {
                k = (k + 1) % 4; // 우회전
            }

            r = (r + x[k] + R) % R; // 빛쏘고나서 도착한 행
            c = (c + y[k] + C) % C; // 빛쏘고나서 도착한 열
        }

        return cnt;// 만들어진 사이클 리턴
    }
}