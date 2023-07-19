class Solution {
    public int solution(int[] sticker) {
        int length = sticker.length;

        if (length == 1) {
            return sticker[0];
        }

        int[] dp1 = new int[length]; // 첫 번째 스티커를 뜯는 경우
        int[] dp2 = new int[length]; // 첫 번째 스티커를 뜯지 않는 경우

        // 첫 번째 스티커를 뜯는 경우
        dp1[0] = sticker[0];
        dp1[1] = sticker[0];

        for (int i = 2; i < length - 1; i++) {
            dp1[i] = Math.max(dp1[i - 2] + sticker[i], dp1[i - 1]);
        }

        // 첫 번째 스티커를 뜯지 않는 경우
        dp2[0] = 0;
        dp2[1] = sticker[1];

        for (int i = 2; i < length; i++) {
            dp2[i] = Math.max(dp2[i - 2] + sticker[i], dp2[i - 1]);
        }

        return Math.max(dp1[length - 2], dp2[length - 1]);
    }
}