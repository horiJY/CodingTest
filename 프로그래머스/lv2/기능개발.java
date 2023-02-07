import java.util.ArrayList;
import java.util.List;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> answerList = new ArrayList<>();

        int day = 0;
        int deployCount = 0;
        for (int i = 0; i < progresses.length;) {
            if (progresses[i] + (day * speeds[i]) >= 100) {
                deployCount++;
                i++;
            } else if (deployCount != 0) {
                answerList.add(deployCount);
                deployCount = 0;
            } else {
                day++;
            }
        }

        if (deployCount != 0) {
            answerList.add(deployCount);
        }
        System.out.println(answerList);
        return answerList.stream().mapToInt(Integer::intValue).toArray();
    }
}