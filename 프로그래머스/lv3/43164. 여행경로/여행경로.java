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

    private Deque<String> DFS(String key) {
        if (!graph.containsKey(key) || graph.get(key).isEmpty()) {
            return new LinkedList<>(List.of(key));
        }

        Deque<String> right = DFS(graph.get(key).poll());
        if (!graph.get(key).isEmpty()) {
            Deque<String> left = DFS(graph.get(key).poll());
            if (left.size() > right.size()) {
                while (!right.isEmpty()) {
                    left.addLast(right.pollFirst());
                }
                right = left;
            } else {
                while (!left.isEmpty()) {
                    right.addFirst(left.pollLast());
                }
            }
        }
        right.addFirst(key);
        return right;
    }

}