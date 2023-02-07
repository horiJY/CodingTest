import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    List<Integer>[] wireConnectionList;

    public int solution(int n, int[][] wires) {
        wireConnectionList = new List[n + 1];
        for (int i = 1; i < wireConnectionList.length; i++) {
            wireConnectionList[i] = new ArrayList<>();
        }

        for (int[] wire : wires) {
            wireConnectionList[wire[0]].add(wire[1]);
            wireConnectionList[wire[1]].add(wire[0]);
        }

        int answer = n;
        for (int[] wire : wires) {
            answer = Math.min(answer, checkTreeGap(wire, n));
            if (answer == 0) {
                break;
            }
        }
        System.out.println(answer);

        return answer;
    }

    int checkTreeGap(int[] wire, int n) {
        Queue<Integer> nextNodeQueue = new LinkedList<>();
        boolean[] visitNode = new boolean[n + 1];
        Arrays.fill(visitNode, false);

        int cnt = 0;
        nextNodeQueue.add(wire[0]);
        visitNode[wire[0]] = true;

        while (!nextNodeQueue.isEmpty()) {
            int currentNode = nextNodeQueue.poll();
            cnt++;

            for (int i : wireConnectionList[currentNode]) {
                if (!visitNode[i] && i != wire[1]) {
                    nextNodeQueue.add(i);
                    visitNode[i] = true;
                }
            }
        }

        return (int) Math.abs(n - (2 * cnt));
    }
}