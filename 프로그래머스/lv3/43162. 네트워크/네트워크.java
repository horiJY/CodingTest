class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        boolean[] connection = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!connection[i]) {
                checkConnection(i, computers, connection);
                answer++;
            }
        }

        return answer;
    }

    private void checkConnection(int curCom, int[][] computers, boolean[] connection) {
        connection[curCom] = true;
        for (int i = 0; i < computers[curCom].length; i++) {
            if (i != curCom && !connection[i] && computers[curCom][i] == 1) {
                checkConnection(i, computers, connection);
            }
        }
    }
}