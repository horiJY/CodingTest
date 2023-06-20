
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Solution {
    public int solution(int[] cards) {
        List<Integer> answer = new ArrayList<>();
        answer.add(0);
        boolean[] open = new boolean[cards.length];
        int openCnt = 0;
        while (openCnt < cards.length) {
            boolean[] tempOpen = new boolean[cards.length];
            int tempOpenCnt = 0;
            int boxIdx = 0;
            for (int j = 0; j < open.length; j++) {
                if (!open[j]) {
                    boxIdx = j;
                    break;
                }
            }

            while (true) {
                if (tempOpen[cards[boxIdx] - 1] || open[cards[boxIdx] - 1]) {
                    openCnt += tempOpenCnt;
                    for (int i = 0; i < tempOpen.length; i++) {
                        if (tempOpen[i]) {
                            open[i] = true;
                        }
                    }
                    answer.add(tempOpenCnt);
                    break;
                } else {
                    tempOpenCnt++;
                    boxIdx = cards[boxIdx] - 1;
                    tempOpen[boxIdx] = true;
                }
            }

        }

        answer.sort(Comparator.reverseOrder());
        return answer.get(0) * answer.get(1);
    }
}