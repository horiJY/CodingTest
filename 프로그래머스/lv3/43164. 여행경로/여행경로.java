import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

class Solution {

    static Map<String, PriorityQueue<String>> graph = new HashMap<>();

    public String[] solution(String[][] tickets) {

        for (String[] ea : tickets) {
            PriorityQueue<String> value = graph.computeIfAbsent(ea[0], e -> new PriorityQueue<String>());
            value.offer(ea[1]);
        }

        return DFS("ICN").toArray(new String[0]);
    }

    private Deque<String> DFS(String next) {
        if (!graph.containsKey(next) || graph.get(next).isEmpty()) {
            return new LinkedList<>(List.of(next));
        }

        Deque<String> postfix = DFS(graph.get(next).poll());
        if (!graph.get(next).isEmpty()) { // 후위 경로를 뽑았는데도 남아있다는건 남아있는 경로를 돌 수 없다는 뜻 -> 후위 경로앞에 나머지경로를 붙여준다.
            Deque<String> prefix = DFS(graph.get(next).poll());
            prefix.addAll(postfix);
            postfix = prefix;
        }
        postfix.addFirst(next);
        return postfix;
    }

}