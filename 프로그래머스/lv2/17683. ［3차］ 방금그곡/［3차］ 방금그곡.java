import java.io.IOException;
import java.util.Arrays;

class Solution {
    public String solution(String m, String[] musicInfos) {
        String answer = "(None)";
        int answerTime = 0;
        String[][] musicInfo = new String[musicInfos.length][4];
        m = replaceSharpToLowerCase(m);

        for (int i = 0; i < musicInfo.length; i++) {
            musicInfo[i] = musicInfos[i].split(",");
        }

        Arrays.sort(musicInfo, (o1, o2) -> {
            if (o1[0].compareTo(o2[0]) == 0) {
                return o2[1].compareTo(o1[1]);
            }
            return o1[0].compareTo(o2[0]);
        });

        for (String[] music : musicInfo) {
            String musicCode = replaceSharpToLowerCase(music[3]);
            int limitTime = convertTime(music[0], music[1]);
            String musicCodeWithLimitTime = "";
            for (int i = 0; i < limitTime; i++) {
                musicCodeWithLimitTime += musicCode.charAt(i % musicCode.length());
            }
            if (musicCodeWithLimitTime.contains(m) && answerTime < musicCodeWithLimitTime.length()) {
                answer = music[2];
                answerTime = musicCodeWithLimitTime.length();
            }
        }

        return answer;
    }

    private String replaceSharpToLowerCase(String m) {
        return m.replaceAll("C#", "c")
                .replaceAll("D#", "d")
                .replaceAll("F#", "f")
                .replaceAll("G#", "g")
                .replaceAll("A#", "a");
    }

    private int convertTime(String startTime, String endTime) {
        int startHour = Integer.parseInt(startTime.split(":")[0]);
        int startMinute = Integer.parseInt(startTime.split(":")[1]);
        int endHour = Integer.parseInt(endTime.split(":")[0]);
        int endMinute = Integer.parseInt(endTime.split(":")[1]);

        return (endHour - startHour) * 60 + (endMinute - startMinute);
    }
}