import java.io.IOException;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        int[] buy = new int[number.length];
        int start = 0, end = 0;
        while (end < 10) {
            for (int i = 0; i < want.length; i++) {
                if (discount[end].equals(want[i])) {
                    buy[i]++;
                    break;
                }
            }
            end++;
        }

        for (; start < discount.length; start++, end++) {
            if (compareWantCount(number, buy)) {
                answer++;
            }

            for (int i = 0; i < want.length; i++) {
                if (want[i].equals(discount[start])) {
                    buy[i]--;
                }
                if (end < discount.length && want[i].equals(discount[end])) {
                    buy[i]++;
                }
            }
        }

        return answer;
    }

    private boolean compareWantCount(int[] number, int[] buy) {
        for (int i = 0; i < buy.length; i++) {
            if (number[i] > buy[i]) {
                return false;
            }
        }

        return true;
    }
}