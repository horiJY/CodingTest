import java.util.ArrayList;
import java.util.List;

class Solution {
    public long[] solution(long[] numbers) {
        List<Long> answer = new ArrayList<>();
        for (int i = 0; i < numbers.length; i++) {
            String number = Long.toBinaryString(numbers[i]);
            for (int j = number.length() - 1; j >= 0; j--) {
                if (number.charAt(j) == '0') {
                    if (j == number.length() - 1) {
                        number = number.substring(0, j) + "1";
                        break;
                    } else {
                        number = number.substring(0, j) + "10" + number.substring(j + 2, number.length());
                        break;
                    }
                } else if (j == 0) {
                    number = "10" + number.substring(1, number.length());
                }
            }
            answer.add(Long.parseLong(number, 2));
        }
        return answer.stream().mapToLong(e -> e).toArray();
    }
}