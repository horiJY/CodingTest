
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public long[] solution(long k, long[] room_number) {
        long[] answer = new long[room_number.length];
        Map<Long, Long> nextRoom = new HashMap<>();
        Deque<Long> refreshRoom = new ArrayDeque<>();

        for (int i = 0; i < room_number.length; i++) {
            long targetNum = room_number[i];

            while (true) {
                if (nextRoom.get(targetNum) == null) {
                    answer[i] = targetNum;
                    nextRoom.put(targetNum, targetNum + 1);

                    if (!refreshRoom.isEmpty()) {
                        while (!refreshRoom.isEmpty()) {
                            nextRoom.put(refreshRoom.pollFirst(), targetNum + 1);
                        }
                    }
                    break;
                } else {
                    refreshRoom.offer(targetNum);
                    targetNum = nextRoom.get(targetNum);
                }
            }
        }

        return answer;
    }
}