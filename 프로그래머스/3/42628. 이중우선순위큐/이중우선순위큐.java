import java.util.ArrayList;
import java.util.List;

class Solution {
    public int[] solution(String[] operations) {
        int[] answer = { 0, 0 };
        List<Integer> que = new ArrayList<>();
        boolean addElement = false;
        for (String e : operations) {
            String[] input = e.split(" ");
            if (input[0].equals("I")) {
                que.add(Integer.parseInt(input[1]));
                addElement = true;
            } else if (!que.isEmpty()) {
                if (addElement) {
                    que.sort(null);
                    addElement = false;
                }

                if (input[1].equals("1")) {
                    que.remove(que.size() - 1);
                    continue;
                }
                que.remove(0);
            }
        }
        if (!que.isEmpty()) {
            que.sort(null);
            answer[0] = que.get(que.size() - 1);
            answer[1] = que.get(0);
        }
        return answer;
    }
}