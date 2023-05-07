import java.util.ArrayList;
import java.util.List;

class Solution {
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        int[] answer = new int[balls.length];
        int[] start = new int[] { startX, startY };

        for (int i = 0; i < answer.length; i++) {
            int minDistance = Integer.MAX_VALUE;
            List<int[]> tempTarget = getSymmetricTranspositionWithBall(m, n, start, balls[i]);
            for (int[] target : tempTarget) {
                minDistance = Math.min(minDistance, getDistanceWithTowPoint(start, target));
            }
            answer[i] = minDistance;
        }

        return answer;
    }

    private static List<int[]> getSymmetricTranspositionWithBall(int m, int n, int[] start, int[] ball) {
        List<int[]> result = new ArrayList<>();
        /**
         * 튕길 수 있는 4가지 조건
         * - 아래 쿠션
         * - 위 쿠션
         * - 오른쪽 쿠션
         * - 왼쪽 쿠션
         */
        if (!(start[0] == ball[0] && start[1] > ball[1])) { // 아래 쿠션
            result.add(new int[] { ball[0], ball[1] * -1 });
        }
        if (!(start[0] == ball[0] && start[1] < ball[1])) { // 위 쿠션
            result.add(new int[] { ball[0], n + (n - ball[1]) });
        }
        if (!(start[1] == ball[1] && start[0] < ball[0])) { // 오른 쿠션
            result.add(new int[] { m + (m - ball[0]), ball[1] });
        }
        if (!(start[1] == ball[1] && start[0] > ball[0])) { // 왼 쿠션
            result.add(new int[] { ball[0] * -1, ball[1] });
        }

        return result;
    }

    private int getDistanceWithTowPoint(int[] start, int[] target) {
        int maxX = Math.max(start[0], target[0]);
        int minX = Math.min(start[0], target[0]);
        int maxY = Math.max(start[1], target[1]);
        int minY = Math.min(start[1], target[1]);
        return (int) Math.pow(maxX - minX, 2) + (int) Math.pow(maxY - minY, 2);
    }
}