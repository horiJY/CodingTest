import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

class Solution {
    static HashMap<String, Integer> wordMap;

    public int[] solution(String msg) {
        wordMap = new HashMap<>();
        initializeWordMap();
        ArrayList<Integer> answer = new ArrayList<>();
        // String word = msg.substring(0, 1);
        for (int start = 0, end = 1; start < msg.length() && end <= msg.length(); end++) {
            if (end == msg.length()) {
                answer.add(wordMap.get(msg.substring(start, end)));
                break;
            }

            if (wordMap.get(msg.substring(start, end + 1)) == null) {
                answer.add(wordMap.get(msg.substring(start, end)));
                wordMap.put(msg.substring(start, end + 1), wordMap.size() + 1);
            } else {
                continue;
            }
            start = end;
        }
        return answer.stream().mapToInt(n -> n).toArray();
    }

    private void initializeWordMap() {
        char ch = 'A';

        for (int i = 1; i <= 26; i++) {
            wordMap.put(String.valueOf(ch), i);
            ch += 1;
        }
    }
}