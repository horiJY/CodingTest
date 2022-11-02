import java.util.ArrayList;

class Solution {
    public int[] solution(int n, String[] words) {
        int[] answer = { 0, 0 };

        ArrayList<String> endWordList = new ArrayList<>();
        boolean failFlag = false;
        endWordList.add(words[0]);
        for (int i = 1; i < words.length; i++) {
            if (endWordList.indexOf(words[i]) < 0 // 단어가 없을 경우 -1, 있을 경우 index return
                    && (words[i].charAt(0) == endWordList.get(i - 1).charAt(endWordList.get(i - 1).length() - 1))) {
                endWordList.add(words[i]);
            } else {
                failFlag = true;
            }

            if (failFlag) {
                answer[0] = (i % n) + 1;
                answer[1] = (i / n) + 1;
                break;
            }
        }

        return answer;
    }
}