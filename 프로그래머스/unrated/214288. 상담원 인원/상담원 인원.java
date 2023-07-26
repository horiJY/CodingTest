import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.PriorityQueue;

class Solution {
    public int solution(int k, int n, int[][] reqs) {
        int answer = Integer.MAX_VALUE;
        List<Request> requests = new ArrayList<>();
        Deque<int[]> mentorCombine = new ArrayDeque<>();
        int[] requestTypeCount = new int[k + 1];
        PriorityQueue<Integer>[] endTimeList = new PriorityQueue[k + 1];
        for (int[] req : reqs) {
            requests.add(new Request(req[0], req[1], req[2]));
            requestTypeCount[req[2]]++;
        }

        getDivideMentors(n, k, new int[k + 1], mentorCombine); // 가능한 멘토 조합 생성

        while (!mentorCombine.isEmpty()) {
            int[] curMentor = mentorCombine.poll();
            int tempWaitingTime = 0;

            for (int i = 1; i < endTimeList.length; i++) {
                endTimeList[i] = new PriorityQueue<>();
            }

            for (int i = 0; i < requests.size(); i++) {
                Request request = requests.get(i);
                int type = request.type;

                // 해당 상담 유형을 상담하는 멘토가 없으면 상담 요청을 받음
                if (endTimeList[type].size() < curMentor[type]) {
                    endTimeList[type].offer(request.time + request.duration);
                } else {
                    // 해당 상담 유형을 상담하는 멘토가 있으면, 끝나는 시간 이후에 상담을 받음
                    int waiting = endTimeList[type].poll() - request.time;
                    waiting = waiting < 0 ? 0 : waiting;
                    tempWaitingTime += waiting;
                    endTimeList[type].offer(waiting + request.time + request.duration);
                }
            }

            answer = Math.min(answer, tempWaitingTime);
        }

        return answer;
    }

    public static void getDivideMentors(int n, int k, int[] tempCombine, Deque<int[]> numberOfMentor) {
        if (k == 1) {
            tempCombine[k] = n;
            numberOfMentor.add(tempCombine.clone());
        } else {
            for (int i = 1; i < n; i++) {
                tempCombine[k] = i;
                getDivideMentors(n - i, k - 1, tempCombine, numberOfMentor);
                tempCombine[k] = 1;
            }
        }
    }

    class Request {
        int time;
        int duration;
        int type;

        public Request(int createTime, int duration, int type) {
            this.time = createTime;
            this.duration = duration;
            this.type = type;
        }
    }
}