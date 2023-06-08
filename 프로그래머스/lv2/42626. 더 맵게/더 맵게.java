import java.util.PriorityQueue;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> scovilles = new PriorityQueue<>();
        for (int i : scoville) {
            scovilles.add(i);
        }

        while (scovilles.size() > 1) {
            if (scovilles.peek() >= K) {
                break;
            }

            int mixedScoville = scovilles.poll() + (scovilles.poll() * 2);
            answer++;
            scovilles.add(mixedScoville);
        }

        if (scovilles.peek() >= K) {
            return answer;
        }

        return -1;
    }
}