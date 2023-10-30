import java.util.Arrays;

class Solution {

    public int solution(String[] words) {
        int answer = 0;
        Arrays.sort(words);

        for (int i = 0; i < words.length; i++) {
            int len = 0;
            
            if (i > 0) {
                len = compare(words[i - 1], words[i]);
            }

            if (i < words.length - 1) {
                len = Math.max(len, compare(words[i], words[i + 1]));
            }

            if (len != words[i].length()) {
                len++;
            }

            answer += len;
        }

        return answer;
    }

    private int compare(String s1, String s2) {
        int len = 0;
        
        for (int i = 0; i < Math.min(s1.length(), s2.length()); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                break;
            }
            len++;
        }
        
        return len;
    }

}