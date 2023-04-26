import java.util.ArrayList;

class Solution {
    static int queenQuantity;
    static int answer;

    public int solution(int n) {
        queenQuantity = n;
        answer = 0;
        nQueen(new ArrayList<Integer>());

        System.out.println(answer);
        return answer;
    }

    private void nQueen(ArrayList<Integer> field) {
        if (field.size() == queenQuantity) {
            answer++;
        } else {
            for (int nextY = 0; nextY < queenQuantity; nextY++) {
                if (!field.contains(nextY) && checkRedzone(field, nextY)) {
                    field.add(nextY);
                    nQueen(field);
                    field.remove((Integer) nextY);
                }
            }
        }
    }

    private boolean checkRedzone(ArrayList<Integer> field, int y) {
        for (int lineIdx = 0; lineIdx < field.size(); lineIdx++) {
            if (Math.abs(lineIdx - field.size()) == Math.abs(field.get(lineIdx) - y)) { // 대각선 판별
                return false;
            }
        }
        return true;
    }
}