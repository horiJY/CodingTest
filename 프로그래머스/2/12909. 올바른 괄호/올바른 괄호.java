class Solution { 
    boolean solution(String s) {
        int openBracketCount = 0;
        int closeBracketCount = 0;


        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                openBracketCount++;
            } else {
                if (closeBracketCount >= openBracketCount) {
                    return false;
                }
                closeBracketCount++;
            }
        }

        return openBracketCount == closeBracketCount;
    }
}