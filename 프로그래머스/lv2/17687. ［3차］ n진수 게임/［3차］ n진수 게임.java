class Solution {
    public String solution(int n, int t, int m, int p) {
        StringBuilder answer = new StringBuilder();
        int idx = p;
        for (int number = 0; answer.length() < t; number++) {
            // String numberStr = Integer.toString(number,n).toUpperCase();
            String numberStr = getNumberStringWithNotation(number, n);

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

    private String getNumberStringWithNotation(int number, int notation) {
        StringBuilder sb = new StringBuilder();
        while (number >= 0) {
            if (number < notation) {
                sb.insert(0, parseStringWithNumber(number));
                break;
            }
            sb.insert(0, parseStringWithNumber(number % notation));

            number /= notation;
        }
        return sb.toString();
    }

    private String parseStringWithNumber(int number) {
        return switch (number) {
            case 10 -> "A";
            case 11 -> "B";
            case 12 -> "C";
            case 13 -> "D";
            case 14 -> "E";
            case 15 -> "F";
            default -> String.valueOf(number);
        };
    }
}