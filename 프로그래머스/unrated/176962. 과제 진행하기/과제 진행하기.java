import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

class Solution {

    public String[] solution(String[][] plans) {
        List<String> answer = new ArrayList<>();
        Queue<Assignment> remainTask = new PriorityQueue<>((h1, h2) -> h1.startTime - h2.startTime);
        Deque<Assignment> proceedTask = new ArrayDeque<>();
        for (String[] plan : plans) {
            remainTask.add(new Assignment(plan[0], plan[1], plan[2]));
        }

        int curTime = remainTask.peek().startTime;
        proceedTask.add(remainTask.poll());
        while (!remainTask.isEmpty()) {
            if (remainTask.peek().startTime < curTime + proceedTask.peekLast().playTime) {
                // 새 과제 시작전에 진행중인 작업을 끝내지 못함
                Assignment next = remainTask.poll();
                proceedTask.peekLast().playTime -= next.startTime - curTime;
                curTime = next.startTime;
                proceedTask.add(next);
            } else {
                // 진행중인 작업 끝
                curTime += proceedTask.peekLast().playTime;
                answer.add(proceedTask.pollLast().name);
            }

            if (proceedTask.isEmpty()) {
                curTime = remainTask.peek().startTime;
                proceedTask.add(remainTask.poll());
            }
        }

        while (!proceedTask.isEmpty()) {
            answer.add(proceedTask.pollLast().name);
        }

        return answer.toArray(String[]::new);
    }

    class Assignment {
        String name;
        int startTime, playTime;

        public Assignment(String name, String startTime, String playTime) {
            this.name = name;
            this.startTime = convertToMinute(startTime);
            this.playTime = Integer.parseInt(playTime);
        }

        private int convertToMinute(String time) {
            String[] t = time.split(":");
            return Integer.parseInt(t[0]) * 60 + Integer.parseInt(t[1]);
        }
    }
}