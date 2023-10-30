import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

class Solution {
    HashSet<Integer> combineSet; // 중복 수 제거
    ArrayList<String> numberList; // input 값을 나누기 위한 요소
    boolean[] useNumberFlags; // 값 사용 여부 체크

    public int solution(String numbers) {
        numberList = new ArrayList<>();
        for (String string : numbers.split("")) {
            numberList.add(string);
        }
        useNumberFlags = new boolean[numberList.size()];

        Arrays.fill(useNumberFlags, false);

        combineSet = new HashSet<>();
        combineNumberCase("");

        int answer = 0;
        for (Integer combineNumber : combineSet) {
            boolean primeNumberFlag = true;
            if (combineNumber != 0 && combineNumber != 1) {
                for (int i = 2, e = (int) Math.sqrt(combineNumber) + 1; i < e; i++) {
                    if (combineNumber % i == 0) {
                        primeNumberFlag = false;
                        break;
                    }
                }
                if (primeNumberFlag) {
                    answer++;
                }
            }
        }

        return answer;
    }

    void combineNumberCase(String combineNumber) {
        for (int i = 0; i < numberList.size(); i++) {
            if (!useNumberFlags[i]) {
                combineNumber += numberList.get(i);
                useNumberFlags[i] = true;
                combineSet.add(Integer.valueOf(combineNumber));
                combineNumberCase(combineNumber);
                combineNumber = combineNumber.substring(0, combineNumber.length() - 1);
                useNumberFlags[i] = false;
            }
        }
    }
}