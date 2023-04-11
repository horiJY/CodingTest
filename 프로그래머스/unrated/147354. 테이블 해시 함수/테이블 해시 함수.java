import java.util.Arrays;

class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        Arrays.sort(data, (o1, o2) -> {
            if (o1[col - 1] == o2[col - 1]) {
                return o2[0] - o1[0];
            }
            return o1[col - 1] - o2[col - 1];
        });

        int[] modSum = new int[row_end - row_begin + 1];
        for (int sumIdx = 0, row = row_begin; row <= row_end; sumIdx++, row++) {
            for (int i = 0; i < data[row - 1].length; i++) {
                modSum[sumIdx] += data[row - 1][i] % row;
            }
        }

        int answer = modSum[0];
        for (int i = 1; i < modSum.length; i++) {
            answer ^= modSum[i];
        }

        return answer;
    }
}