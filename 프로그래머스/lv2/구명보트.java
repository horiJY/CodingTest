import java.util.ArrayDeque;
import java.util.Arrays;

class Solution {
    public int solution(int[] people, int limit) {
        Arrays.sort(people);
        ArrayDeque<Integer> peopleDeque = new ArrayDeque<Integer>();
        for (int i : people) {
            peopleDeque.addLast(i);
        }

        int answer = 0;
        int boatWeight = 0;
        int boatCap = 0;
        while (!peopleDeque.isEmpty()) {
            if (limit - (boatWeight + peopleDeque.peekLast()) >= 0 && boatCap <= 2) {
                boatWeight += peopleDeque.pollLast();
                boatCap++;
                if (!peopleDeque.isEmpty() && limit - (boatWeight + peopleDeque.peekFirst()) >= 0 && boatCap <= 2) {
                    boatWeight += peopleDeque.pollFirst();
                    boatCap++;
                }
            } else {
                answer++;
                boatCap = 0;
                boatWeight = 0;
            }
        }

        return boatWeight > 0 ? answer + 1 : answer;
    }
}