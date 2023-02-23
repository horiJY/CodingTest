class Solution {
    public int solution(String[][] book_time) {
        int[] dp = new int[convertHourToMinute("24:10")];
        for (String[] is : book_time) {
            int start = convertHourToMinute(is[0]);
            int end = convertHourToMinute(is[1]);
            for (int i = start; i < end + 10; i++) {
                dp[i]++;
            }
        }

        int answer = 0;
        for (int i : dp) {
            answer = Math.max(answer, i);
        }
        return answer;
    }

    private int convertHourToMinute(String timeStr) {
        int hour = Integer.parseInt(timeStr.split(":")[0]);
        int minute = Integer.parseInt(timeStr.split(":")[1]);
        return (hour * 60) + minute;
    }
}