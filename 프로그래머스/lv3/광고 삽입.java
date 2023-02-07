class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
        int playTime = convertTimeToSecond(play_time);
        int advTime = convertTimeToSecond(adv_time);

        int[] viewCount = new int[playTime + 1];
        for (int i = 0; i < logs.length; i++) {
            String[] data = logs[i].split("-");
            int start = convertTimeToSecond(data[0]);
            int end = convertTimeToSecond(data[1]);

            for (int j = start; j < end; j++) {
                viewCount[j]++;
            }
        }
        // data input end

        long sum = 0;
        for (int i = 0; i < advTime; i++) {
            sum += viewCount[i];
        }

        int answer = 0;
        long max = sum;
        for (int i = 1, j = advTime; j <= playTime; i++, j++) {
            sum += viewCount[j] - viewCount[i - 1];

            if (max < sum) {
                max = sum;
                answer = i;
            }
        }

        return convertSecondToTime(answer);
    }

    private int convertTimeToSecond(String time) {
        String[] data = time.split(":");
        return Integer.parseInt(data[0]) * 3600 + Integer.parseInt(data[1]) * 60 + Integer.parseInt(data[2]);
    }

    private String convertSecondToTime(int second) {
        int hour, minute;
        hour = second / 3600;
        second %= 3600;
        minute = second / 60;
        second %= 60;

        return String.format("%02d:%02d:%02d", hour, minute, second);
    }
}