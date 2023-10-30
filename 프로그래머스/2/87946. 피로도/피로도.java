import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

class Solution {
    Set<LinkedHashSet<Integer>> clearSet;
    int[][] dungeonCost;
    boolean[] clearFlag;

    public int solution(int k, int[][] dungeons) {
        dungeonCost = dungeons;
        clearFlag = new boolean[dungeons.length];
        Arrays.fill(clearFlag, false);

        clearSet = new LinkedHashSet<>();
        checkClearCase(new LinkedHashSet<Integer>(), k);

        int answer = -1;
        for (LinkedHashSet<Integer> i : clearSet) {
            answer = i.size() > answer ? i.size() : answer;
        }
        return answer;
    }

    void checkClearCase(LinkedHashSet<Integer> tempCase, int remainCost) {
        if (tempCase.size() == dungeonCost.length) {
            clearSet.add(new LinkedHashSet<>(tempCase));
        } else {
            for (int i = 0; i < dungeonCost.length; i++) {
                if (!clearFlag[i] && remainCost - dungeonCost[i][0] >= 0) {
                    tempCase.add(i);
                    clearFlag[i] = true;
                    remainCost -= dungeonCost[i][1];
                    checkClearCase(tempCase, remainCost);
                    tempCase.remove(i);
                    clearFlag[i] = false;
                    remainCost += dungeonCost[i][1];
                } else {
                    clearSet.add(new LinkedHashSet<>(tempCase));
                }
            }
        }
    }
}