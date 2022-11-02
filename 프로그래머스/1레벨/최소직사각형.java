class Solution {
    public int solution(int[][] sizes) {
        int topOfWidthSize = 0;
        int topOfHeightSize = 0;
        for (int[] ia : sizes) {
            // sizes의 각 요소가 (width,height)라고 가정할 때
            if (ia[0] > ia[1]) {
                if (ia[0] > topOfWidthSize) {
                    topOfWidthSize = ia[0];
                }
                if (ia[1] > topOfHeightSize) {
                    topOfHeightSize = ia[1];
                }
            } else {
                if (ia[1] > topOfWidthSize) {
                    topOfWidthSize = ia[1];
                }
                if (ia[0] > topOfHeightSize) {
                    topOfHeightSize = ia[0];
                }
            }
        }

        return topOfWidthSize * topOfHeightSize;
    }
}