class Solution {
    public int solution(String s) {
        int answer = s.length();
        int limit = s.length() / 2;
        for (int i = 1; i <= limit; i++) {
            String result = compressMorpheme(i, s);
            answer = Math.min(answer, result.length());
        }
        return answer;
    }

    private String compressMorpheme(int charLen, String s) {
        int len = s.length() % charLen == 0 ? s.length() / charLen : s.length() / charLen + 1;
        String[] temp = new String[len];
        for (int i = 0; i < len; i++) {
            int splitIndex = (i + 1) * charLen;
            if (splitIndex > s.length()) {
                temp[i] = s.substring(i * charLen, s.length());
            } else {
                temp[i] = s.substring(i * charLen, splitIndex);
            }
        }

        String result = "";
        for (int i = 0; i < temp.length; i++) {
            int sequence = 1;
            while (i + sequence < temp.length && temp[i].equals(temp[i + sequence])) {
                sequence++;
            }

            if (sequence == 1) {
                result += temp[i];
            } else {
                result += sequence + temp[i];
                i += sequence - 1;
            }
        }
        return result;
    }
}