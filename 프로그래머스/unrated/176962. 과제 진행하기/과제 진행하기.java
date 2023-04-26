import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

class Solution {

    public String[] solution(String[][] plans) {
        List<String> answer = new ArrayList<>();
        Queue<Assignment> task = new PriorityQueue<>((h1, h2) -> h1.startTime - h2.startTime);
        Deque<Assignment> suspendStack = new ArrayDeque<>();
        for (String[] plan : plans) {
            task.add(new Assignment(plan[0], plan[1], plan[2]));
        }
        int curTime = task.peek().startTime;
        suspendStack.add(task.poll());
        while (!task.isEmpty()) {
            if (task.peek().startTime < curTime + suspendStack.peekLast().playTime) {
                // 새 과제 시작전에 진행중인 작업을 끝내지 못함
                Assignment next = task.poll();
                suspendStack.peekLast().playTime -= next.startTime - curTime;
                curTime = next.startTime;
                suspendStack.add(next);
            } else {
                // 진행중인 작업 끝
                curTime += suspendStack.peekLast().playTime;
                answer.add(suspendStack.pollLast().name);
            }
            if (suspendStack.isEmpty()) {
                curTime = task.peek().startTime;
                suspendStack.add(task.poll());
            }
        }
        while (!suspendStack.isEmpty()) {
            answer.add(suspendStack.pollLast().name);
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