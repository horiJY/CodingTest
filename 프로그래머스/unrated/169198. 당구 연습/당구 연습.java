import java.util.ArrayList;
import java.util.List;

class Solution {
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        int[] answer = new int[balls.length];
        int[] start = new int[] { startX, startY };

        for (int i = 0; i < answer.length; i++) {
            int tempDistance = Integer.MAX_VALUE;
            List<int[]> tempBallPosition = getSymmetricTranspositionWithBall(m, n, start, balls[i]);
            for (int[] ball : tempBallPosition) {
                tempDistance = Math.min(tempDistance, getDistanceWithTowPoint(start, ball));
            }
            answer[i] = tempDistance;
        }

        return answer;
    }

    private static List<int[]> getSymmetricTranspositionWithBall(int m, int n, int[] start, int[] ball) {
        List<int[]> tempPosition = new ArrayList<>();
        /**
         * 튕길 수 있는 4가지 조건
         * - 아래 쿠션
         * - 위 쿠션
         * - 오른쪽 쿠션
         * - 왼쪽 쿠션
         */
        if (start[0] != ball[0] || start[1] > ball[1]) {
            tempPosition.add(new int[] { ball[0], n + (n - ball[1]) }); // 상
        }
        if (start[0] != ball[0] || start[1] < ball[1]) {
            tempPosition.add(new int[] { ball[0], ball[1] * -1 }); // 하
        }
        if (start[0] < ball[0] || start[1] != ball[1]) {
            tempPosition.add(new int[] { ball[0] * -1, ball[1] }); // 좌
        }
        if (start[0] > ball[0] || start[1] != ball[1]) {
            tempPosition.add(new int[] { m + (m - ball[0]), ball[1] }); // 우
        }

        return tempPosition;
    }

    private int getDistanceWithTowPoint(int[] start, int[] target) {
        int maxX = Math.max(start[0], target[0]);
        int minX = Math.min(start[0], target[0]);
        int maxY = Math.max(start[1], target[1]);
        int minY = Math.min(start[1], target[1]);
        return (int) Math.pow(maxX - minX, 2) + (int) Math.pow(maxY - minY, 2);
    }
}