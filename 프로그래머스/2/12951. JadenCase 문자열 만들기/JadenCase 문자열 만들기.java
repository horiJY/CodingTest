class Solution {
    public String solution(String s) {
        String answer = "";
        boolean wordFlag = true;
        s = s.toLowerCase();

        for (char c : s.toCharArray()) {
            if (c == ' ') {
                wordFlag = true;
                answer += c;
            } else if (wordFlag) {
                if (Character.isDigit(c)) {
                    answer += c;
                } else {
                    answer += Character.toUpperCase(c);
                }
                wordFlag = false;
            } else {
                answer += c;
            }
        }

        return answer;
    }
}