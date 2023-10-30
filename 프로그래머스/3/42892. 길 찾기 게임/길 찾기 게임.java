import java.util.ArrayList;
import java.util.Arrays;

class Solution {
    public int[][] solution(int[][] nodeInfo) {
        ArrayList<int[]> nodeList = new ArrayList<>();
        for (int i = 0; i < nodeInfo.length; i++) {
            nodeList.add(new int[] { nodeInfo[i][0], nodeInfo[i][1], i + 1 });
        }
        nodeList.sort((int[] a, int[] b) -> {
            if (a[1] == b[1]) {
                return a[0] - b[0];
            }
            return b[1] - a[1];
        });

        int[][] answer = new int[2][];
        answer[0] = Arrays.stream(searchOrder(nodeList, "pre").trim().split("\\s+")).mapToInt(Integer::parseInt)
                .toArray();
        answer[1] = Arrays.stream(searchOrder(nodeList, "post").trim().split("\\s+")).mapToInt(Integer::parseInt)
                .toArray();

        return answer;
    }

    private String searchOrder(ArrayList<int[]> nodeList, String order) {
        if (nodeList.size() == 0) {
            return "";
        }

        if (nodeList.size() == 1) {
            return String.valueOf(nodeList.get(0)[2]);
        }

        int[] top = nodeList.get(0);
        ArrayList<int[]> left = new ArrayList<>();
        ArrayList<int[]> right = new ArrayList<>();

        for (int i = 1; i < nodeList.size(); i++) {
            if (nodeList.get(i)[0] < top[0]) {
                left.add(nodeList.get(i));
            } else {
                right.add(nodeList.get(i));
            }
        }

        if (order.equals("pre")) {
            return String.valueOf(top[2]) + " " + searchOrder(left, order) + " " + searchOrder(right, order);
        } else {
            return searchOrder(left, order) + " " + searchOrder(right, order) + " " + String.valueOf(top[2]);
        }
    }

}