import java.util.ArrayList;

class Solution {

    public long solution(int[] queue1, int[] queue2) {
        ArrayList<Integer> queueList = new ArrayList<>();
        long q1Sum = 0;
        long q2Sum = 0;
        for (int i : queue1) {
            queueList.add(i);
            q1Sum += i;
        }
        for (int i : queue2) {
            queueList.add(i);
            q2Sum += i;
        }

        if ((q1Sum + q2Sum) % 2 != 0) {
            // 합이 홀수 -> 양 큐 합을 동일하게 맞출 수 없음
            return -1;
        }

        int q1Idx = 0;
        int q2Idx = queueList.size() / 2;
        long queueAvgValue = (q1Sum + q2Sum) / 2;
        long answer = 0;
        for (int i = 0; i < queueList.size() * 3; i++) {
            // 큐길이 *3 -> 큐 데이터가 서로 swap 후 다시 원래 자리로 돌아오는 횟수
            if (q1Sum == queueAvgValue) {
                return answer;
            } else if (q1Sum < queueAvgValue) {
                q1Sum += queueList.get(q2Idx);
                if (q2Idx < queueList.size() - 1) {
                    q2Idx++;
                } else {
                    q2Idx = 0;
                }
            } else {
                q1Sum -= queueList.get(q1Idx);
                if (q1Idx < queueList.size() - 1) {
                    q1Idx++;
                } else {
                    q1Idx = 0;
                }
            }
            answer++;
        }
        // for 를 다돌았음 -> 케이스 없음
        return -1;
    }
}
