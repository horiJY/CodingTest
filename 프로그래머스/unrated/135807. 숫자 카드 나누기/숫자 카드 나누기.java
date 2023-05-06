import java.util.Arrays;

class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        int answer = 0;
        Arrays.sort(arrayA);
        int gdcA = arrayA[0];
        if (gdcA != 0) {
            for (int i = gdcA; i > 1; i--) {
                if (isCommonDivisor(i, arrayA) && !isDivide(i, arrayB)) {
                    answer = Math.max(answer, i);
                }
            }
        }

        Arrays.sort(arrayB);
        int gdcB = arrayB[0];
        if (gdcB > answer) {
            for (int i = gdcB; i > 1; i--) {
                if (isCommonDivisor(i, arrayB) && !isDivide(i, arrayA)) {
                    answer = Math.max(answer, i);
                }
            }
        }
        return answer;
    }

    private boolean isDivide(int i, int[] array) {
        for (int j : array) {
            if (j % i == 0) {
                return true;
            }
        }
        return false;
    }

    private boolean isCommonDivisor(int i, int[] array) {
        for (int j : array) {
            if (j % i != 0) {
                return false;
            }
        }

        return true;
    }
}