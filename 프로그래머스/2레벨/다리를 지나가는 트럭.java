import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Integer> enterBridgeTimeQueue = new LinkedList<>();
        int remainBridgeWeight = weight;
        int enterTruckIndex = 0;
        int outTruckIndex = 0;
        int answer = 0;
        // 7 4 5 6
        while (enterTruckIndex < truck_weights.length || outTruckIndex < truck_weights.length) {
            answer++;
            // if (enterTruckIndex < truck_weights.length
            // && remainBridgeWeight - truck_weights[enterTruckIndex] >= 0) {
            // remainBridgeWeight -= truck_weights[enterTruckIndex++];
            // enterBridgeTimeQueue.add(answer);
            // }

            if (!enterBridgeTimeQueue.isEmpty() && outTruckIndex < truck_weights.length
                    && bridge_length + enterBridgeTimeQueue.peek() == answer) {
                enterBridgeTimeQueue.poll();
                if (outTruckIndex < truck_weights.length) {
                    remainBridgeWeight += truck_weights[outTruckIndex++];
                }

            }
            if (enterTruckIndex < truck_weights.length
                    && remainBridgeWeight - truck_weights[enterTruckIndex] >= 0) {
                remainBridgeWeight -= truck_weights[enterTruckIndex++];
                enterBridgeTimeQueue.add(answer);
            }
        }

        System.out.println(answer);
        return answer;
    }
}