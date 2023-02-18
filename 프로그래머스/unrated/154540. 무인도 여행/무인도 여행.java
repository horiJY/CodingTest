import java.util.ArrayList;

class Solution {
    public int[] solution(String[] maps) {
        int[][] map = new int[maps.length][maps[0].length()];
        for (int y = 0; y < map.length; y++) {
            ;
            for (int x = 0; x < map[0].length; x++) {
                if (maps[y].charAt(x) != 'X') {
                    map[y][x] = maps[y].charAt(x) - '0';
                }
            }
        }

        boolean[][] visit = new boolean[map.length][map[0].length];
        ArrayList<Integer> answer = new ArrayList<>();
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[0].length; x++) {
                if (map[y][x] != 0 && !visit[y][x]) {
                    int result = sumFood(map, visit, y, x);
                    answer.add(result);
                }
            }
        }

        if (answer.size() == 0) {
            answer.add(-1);
        }
        answer.sort(null);
        return answer.stream().mapToInt(i -> i).toArray();
    }

    static final int[] dy = new int[] { 1, -1, 0, 0 };
    static final int[] dx = new int[] { 0, 0, -1, 1 };

    private int sumFood(int[][] map, boolean[][] visit, int y, int x) {
        int result = 0;
        if (map[y][x] != 0 && !visit[y][x]) {
            visit[y][x] = true;
            result += map[y][x];
            for (int i = 0; i < dx.length; i++) {
                if (0 <= y + dy[i] && y + dy[i] < map.length
                        && 0 <= x + dx[i] && x + dx[i] < map[0].length) {
                    result += sumFood(map, visit, y + dy[i], x + dx[i]);
                }
            }
        }

        return result;
    }
}
