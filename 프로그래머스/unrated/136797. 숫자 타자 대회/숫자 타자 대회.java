import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;

class Solution {
    List<String> keys = new ArrayList<>(
            Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "*", "0", "#"));
    HashMap<String, Integer> shortestMap = new HashMap<>();

    public int solution(String numbers) {
        initialize();
        int answer = Integer.MAX_VALUE;
        Deque<Simulation> que = new ArrayDeque<>();
        que.add(new Simulation("4", "6", 0, 0));
        HashMap<String, Integer> path = new HashMap<>();
        path.put("046", Integer.MAX_VALUE);
        while (!que.isEmpty()) {
            Simulation cur = que.pop();
            if (cur.numberIdx == numbers.length()) {
                answer = Math.min(answer, cur.answer);
                continue;
            }
            String curPass = cur.numberIdx + cur.f1 + cur.f2;
            int cost = path.get(curPass);
            if (cost >= cur.answer) {
                path.put(curPass, cur.answer);
                String nextKey = String.valueOf(numbers.charAt(cur.numberIdx));
                if (cur.f1.equals(nextKey) || cur.f2.equals(nextKey)) { // 제자리(이미 누르고있는 버튼)
                    que.add(new Simulation(cur.f1, cur.f2, cur.numberIdx + 1, cur.answer + 1));
                    if (cur.answer + 1 < path.getOrDefault((cur.numberIdx + 1) + cur.f1 + cur.f2, Integer.MAX_VALUE)) {
                        path.put((cur.numberIdx + 1) + cur.f1 + cur.f2, cur.answer + 1);
                    }
                } else {
                    int f1MoveCnt = shortestMap.get(cur.f1 + nextKey);
                    int f2MoveCnt = shortestMap.get(cur.f2 + nextKey);
                    // 왼손 이동
                    String leftPath = (cur.numberIdx + 1) + nextKey + cur.f2;
                    if (cur.answer + f1MoveCnt < path.getOrDefault(leftPath, Integer.MAX_VALUE)) {
                        que.add(new Simulation(nextKey, cur.f2, cur.numberIdx + 1, cur.answer + f1MoveCnt));
                        path.put(leftPath, cur.answer + f1MoveCnt);
                    }
                    // 오른손 이동
                    String rightPath = (cur.numberIdx + 1) + cur.f1 + nextKey;
                    if (cur.answer + f2MoveCnt < path.getOrDefault(rightPath, Integer.MAX_VALUE)) {
                        que.add(new Simulation(cur.f1, nextKey, cur.numberIdx + 1, cur.answer + f2MoveCnt));
                        path.put(rightPath, cur.answer + f2MoveCnt);
                    }
                }
            }
        }
        return answer;
    }

    private void initialize() {
        for (String s : keys) {
            int baseIdx = keys.indexOf(s);
            for (String s2 : keys) {
                if (!s.equals(s2)) {
                    int targetKeyIdx = keys.indexOf(s2);
                    int[] basePosition = new int[] { baseIdx / 3, baseIdx % 3 };
                    int[] targetPosition = new int[] { targetKeyIdx / 3, targetKeyIdx % 3 };
                    shortestMap.put(s + s2, getShortestRoute(basePosition, targetPosition));
                }
            }
        }
    }

    private int getShortestRoute(int[] finger, int[] next) { // 제자리 : 1, 상하좌우 : 2, 대각 : 3
        int moveX = Math.abs(finger[0] - next[0]);
        int moveY = Math.abs(finger[1] - next[1]);
        if (finger[0] == next[0] || finger[1] == next[1]) { // 같은 라인
            return moveX * 2 + moveY * 2;
        }
        int max = Math.max(moveX, moveY);
        int min = Math.min(moveX, moveY);
        return max * 2 + min;
    }
}

class Simulation {
    String f1;
    String f2;
    int numberIdx;
    int answer;

    public Simulation(String f1, String f2, int numberIdx, int answer) {
        this.f1 = f1;
        this.f2 = f2;
        this.numberIdx = numberIdx;
        this.answer = answer;
    }

}
