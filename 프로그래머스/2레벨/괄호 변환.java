class Solution {
    public String solution(String p) {
        String answer = sortBracket(p);
        System.out.println(answer);
        return answer;
    }

    private String sortBracket(String p) {
        // 1. 입력이 빈 문자열인 경우, 빈 문자열을 반환합니다.
        if (p.equals("")) {
            return "";
        }

        // 2. 문자열 w를 두 "균형잡힌 괄호 문자열" u, v로 분리합니다. 단, u는 "균형잡힌 괄호 문자열"로 더 이상 분리할 수 없어야
        // 하며, v는 빈 문자열이 될 수 있습니다.
        // '(' 와 ')' 로만 이루어진 문자열이 있을 경우, '(' 의 개수와 ')' 의 개수가 같다면 이를 균형잡힌 괄호 문자열이라고 부릅니다.
        int sIndex = splitBracket(p);
        String u = p.substring(0, sIndex + 1);
        String v = p.substring(sIndex + 1, p.length());

        if (validBracket(u)) {
            // 3. 문자열 u가 "올바른 괄호 문자열" 이라면 문자열 v에 대해 1단계부터 다시 수행합니다.
            // 3-1. 수행한 결과 문자열을 u에 이어 붙인 후 반환합니다.
            return u + sortBracket(v);
        } else {
            // 4. 문자열 u가 "올바른 괄호 문자열"이 아니라면 아래 과정을 수행합니다.
            // 4-1. 빈 문자열에 첫 번째 문자로 '('를 붙입니다.
            // 4-2. 문자열 v에 대해 1단계부터 재귀적으로 수행한 결과 문자열을 이어 붙입니다.
            // 4-3. ')'를 다시 붙입니다.
            // 4-4. u의 첫 번째와 마지막 문자를 제거하고, 나머지 문자열의 괄호 방향을 뒤집어서 뒤에 붙입니다.
            // 4-5. 생성된 문자열을 반환합니다.
            char[] temp = u.substring(1, u.length() - 1).toCharArray();
            for (int i = 0; i < temp.length; i++) {
                if (temp[i] == ')') {
                    temp[i] = '(';
                } else {
                    temp[i] = ')';
                }
            }
            return '(' + sortBracket(v) + ')' + new String(temp);
        }
    }

    private int splitBracket(String p) {
        int preCount = 0;
        int surCount = 0;
        for (int i = 0; i < p.length(); i++) {
            char c = p.charAt(i);
            if (c == '(') {
                preCount++;
            } else {
                surCount++;
            }

            if (surCount == preCount) {
                return i;
            }
        }
        return p.length() - 1;
    }

    private boolean validBracket(String p) {
        int preCount = 0;
        int surCount = 0;
        for (int i = 0; i < p.length(); i++) {
            char c = p.charAt(i);
            if (c == '(') {
                preCount++;
            } else {
                surCount++;
            }

            if (surCount > preCount) {
                return false;
            }
        }

        return true;
    }
}