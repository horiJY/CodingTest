
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Solution {
    public int solution(int[] cards) {
        List<Integer> answer = new ArrayList<>();
        answer.add(0);
        boolean[] isOpen = new boolean[cards.length];
        int countForOpenBox = 0;
        while (countForOpenBox < cards.length) {
            boolean[] tempCountForOpenBox = new boolean[cards.length];
            int tempOpenCnt = 0;
            int boxIdx = 0;
            for (int j = 0; j < isOpen.length; j++) {
                if (!isOpen[j]) {
                    boxIdx = j;
                    break;
                }
            }

            while (true) {
                if (tempCountForOpenBox[cards[boxIdx] - 1] || isOpen[cards[boxIdx] - 1]) {
                    countForOpenBox += tempOpenCnt;
                    for (int i = 0; i < tempCountForOpenBox.length; i++) {
                        if (tempCountForOpenBox[i]) {
                            isOpen[i] = true;
                        }
                    }
                    answer.add(tempOpenCnt);
                    break;
                } else {
                    tempOpenCnt++;
                    boxIdx = cards[boxIdx] - 1;
                    tempCountForOpenBox[boxIdx] = true;
                }
            }

        }

        answer.sort(Comparator.reverseOrder());
        return answer.get(0) * answer.get(1);
    }
}