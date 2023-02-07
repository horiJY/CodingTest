import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    List<Integer>[] connectionNodes;

    public int solution(int n, int[][] costs) {
        Arrays.sort(costs, Comparator.comparingInt((int[] o) -> o[2]));
        connectionNodes = new List[n];
        for (int i = 0; i < connectionNodes.length; i++) {
            connectionNodes[i] = new ArrayList<>();
        }

        int answer = 0;
        for (int[] is : costs) {
            if (!validConnection(is)) {
                connectionNodes[is[0]].add(is[1]);
                connectionNodes[is[1]].add(is[0]);
                answer += is[2];
            }
        }

        return answer;
    }

    boolean validConnection(int[] is) {
        Queue<Integer> nextNodeQueue = new LinkedList<>();
        boolean[] visitNode = new boolean[connectionNodes.length];
        Arrays.fill(visitNode, false);

        nextNodeQueue.add(is[0]);
        visitNode[is[0]] = true;
        while (!nextNodeQueue.isEmpty()) {
            int currentNode = nextNodeQueue.poll();

            for (int i : connectionNodes[currentNode]) {
                if (i == is[1]) {
                    return true;
                } else if (!visitNode[i]) {
                    nextNodeQueue.add(i);
                    visitNode[i] = true;
                }
            }

        }

        return false;
    }
}