class Solution {
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        for (int i = 0; i < answer.length; i++) {
            if (numbers[i] == 0) {
                answer[i] = 1;
            }
            String numberBinaryStr = getFullBinaryStrLength(Long.toBinaryString(numbers[i]));
            if (isBinaryTree(numberBinaryStr)) {
                answer[i] = 1;
            } else {
                answer[i] = 0;
            }
        }
        return answer;
    }

    private String getFullBinaryStrLength(String binaryStr) {
        int binaryStrLen = binaryStr.length();
        int fullLength = 1;
        int level = 1;
        while (binaryStrLen > fullLength) {
            level *= 2;
            fullLength += level;
        }

        int offset = fullLength - binaryStrLen;
        return "0".repeat(offset) + binaryStr;
    }

    private boolean isBinaryTree(String binaryStr) {
        int len = binaryStr.length();

        if (binaryStr.length() == 0) {
            return true;
        }

        int root = len / 2;
        String leftSubStr = binaryStr.substring(0, root);
        String rightSubStr = binaryStr.substring(root + 1);

        if (binaryStr.charAt(root) == '0') {
            return !leftSubStr.contains("1") && !rightSubStr.contains("1");
        }

        return isBinaryTree(leftSubStr) && isBinaryTree(rightSubStr);
    }
}