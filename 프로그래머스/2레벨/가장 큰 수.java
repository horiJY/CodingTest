import java.util.Arrays;

class Solution {

    public String solution(int[] numbers) {
        String[] numbersStringArray = new String[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            numbersStringArray[i] = String.valueOf(numbers[i]);
        }
        Arrays.sort(numbersStringArray, (o1, o2) -> (o2 + o1).compareTo(o1 + o2));

        String answer = "";
        for (int i = 0; i < numbers.length; i++) {
            if (answer.length() == 0 && numbersStringArray[i].equals("0")) {
                continue;
            }
            answer += numbersStringArray[i];
        }

        return answer.length() == 0 ? "0" : answer;
    }
}