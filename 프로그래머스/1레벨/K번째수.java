import java.util.Arrays;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int n = 0;
        int[] answer = new int[commands.length];

        while (n < commands.length) {
            // 잘라낸 길이가 1
            int sliceLength = commands[n][1] - commands[n][0] + 1;
            if (sliceLength == 1) {
                answer[n] = array[commands[n++][0] - 1];
                continue;
            }

            int[] tempAnswer = new int[sliceLength];
            for (int i = commands[n][0] - 1, j = 0; i < commands[n][1]; i++) {
                tempAnswer[j++] = array[i];
            }
            Arrays.sort(tempAnswer);
            answer[n] = tempAnswer[commands[n++][2] - 1];
        }

        return answer;
    }
}