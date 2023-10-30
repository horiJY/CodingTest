class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        String parseStr = Integer.toString(n, k);
        String number = "";
        for (int i = 0; i < parseStr.length(); i++) {
            if (parseStr.charAt(i) != '0') {
                number += parseStr.charAt(i);
            } else {
                if (number.length() != 0 && validPrime(Long.parseLong(number))) {
                    answer++;
                }
                number = "";
            }

        }

        if (number.length() != 0 && validPrime(Long.parseLong(number))) {
            answer++;
        }

        return answer;
    }

    private boolean validPrime(long number) {
        if (number < 2) {
            return false;
        }

        int limit = (int) Math.sqrt(number);
        for (int i = 2; i <= limit; i++) {
            if (number % i == 0)
                return false;
        }
        return true;
    }
}