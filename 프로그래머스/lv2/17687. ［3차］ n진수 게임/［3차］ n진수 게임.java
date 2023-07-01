
class Solution {
    public String solution(int n, int t, int m, int p) {
        StringBuilder answer = new StringBuilder();
        int idx = p;
        for (int number = 0; answer.length() < t; number++) {
            String numberStr = Integer.toString(number, n).toUpperCase();

            int remainderCnt = numberStr.length() % m;
            while (idx - 1 < numberStr.length()) {
                answer.append(numberStr.charAt(idx - 1));
                if (answer.length() == t) {
                    break;
                }
                idx += m;
            }
            idx = (idx - remainderCnt) % m == 0 ? m : (idx - remainderCnt) % m;
        }
        return answer.toString();
    }
}