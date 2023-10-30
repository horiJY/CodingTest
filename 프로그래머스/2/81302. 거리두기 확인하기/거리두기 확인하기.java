import java.util.ArrayList;

class Solution {
    public int[] solution(String[][] places) {
        ArrayList<Integer> answer = new ArrayList<>();
        for (String[] place : places) {
            char[][] map = new char[place.length][place[0].length()];
            for (int j = 0; j < place.length; j++) {
                map[j] = place[j].toCharArray();
            }

            boolean add = true;
            for (int y = 1; y < map.length; y++) {
                for (int x = 1; x < map[0].length; x++) {
                    if (!valid2x2Distance(map, y, x)
                            || (y > 1 && !valid1x2VerticalDistance(map, y, x))
                            || (x > 1 && !valid1x2HorizonDistance(map, y, x))) {
                        add = false;
                        break;
                    }
                }
                if (!add) {
                    break;
                }
            }

            if (!add) {
                answer.add(0);
            } else {
                answer.add(1);
            }
        }
        return answer.stream().mapToInt(i -> i).toArray();
    }

    private boolean valid1x2VerticalDistance(char[][] map, int y, int x) {
        int pCount = 0;
        int xCount = 0;
        ArrayList<Integer> position = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            switch (map[y - i][x]) {
                case 'P':
                    pCount++;
                    position.add(y - i);
                    break;
                case 'X':
                    xCount++;
                    break;
            }
        }

        if (pCount < 2) {
            return true;
        } else if (xCount == 1 &&
                Math.abs(position.get(0) - position.get(1)) > 1) {
            return true;
        }

        return false;
    }

    private boolean valid1x2HorizonDistance(char[][] map, int y, int x) {
        int pCount = 0;
        int xCount = 0;
        ArrayList<Integer> position = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            switch (map[y][x - i]) {
                case 'P':
                    pCount++;
                    position.add(x - i);
                    break;
                case 'X':
                    xCount++;
                    break;
            }
        }

        if (pCount < 2) {
            return true;
        } else if (xCount == 1 &&
                Math.abs(position.get(0) - position.get(1)) > 1) {
            return true;
        }

        return false;
    }

    private boolean valid2x2Distance(char[][] map, int y, int x) {
        int[] dy = new int[] { -1, -1, 0, 0 };
        int[] dx = new int[] { -1, 0, -1, 0 };
        int pCount = 0;
        int xCount = 0;
        ArrayList<int[]> position = new ArrayList<>();
        for (int i = 0; i < dy.length; i++) {
            switch (map[y + dy[i]][x + dx[i]]) {
                case 'P':
                    pCount++;
                    position.add(new int[] { y + dy[i], x + dx[i] });
                    break;
                case 'X':
                    xCount++;
                    break;
            }
        }

        if (pCount < 2) {
            return true;
        } else if (xCount == 2 &&
                validInterval(position.get(0), position.get(1))) {
            return true;
        }

        return false;

    }

    private boolean validInterval(int[] is, int[] is2) {
        if (Math.abs(is[0] - is2[0]) + Math.abs(is[1] - is2[1]) > 1) {
            return true;
        }
        return false;
    }
}