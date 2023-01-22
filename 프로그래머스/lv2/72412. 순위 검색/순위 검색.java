import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

class Solution {
    static HashMap<String, ArrayList<Integer>> devMap;

    public int[] solution(String[] info, String[] query) {
        devMap = new HashMap<>();
        for (String data : info) { // data : language, role of develop, experience, food, score of cording test
            String[] devCondition = data.split(" ");
            int score = Integer.parseInt(devCondition[4]);
            setDevMap(Arrays.copyOfRange(devCondition, 0, 4), score, "", 0);
        }

        for (ArrayList<Integer> list : devMap.values()) {
            list.sort(null);
        }

        int[] answer = new int[query.length];
        for (int i = 0; i < answer.length; i++) {
            String[] searchCondition = query[i].replaceAll(" and ", "").split(" ");
            int count = 0;
            int searchScore = Integer.parseInt(searchCondition[1]);
            if (devMap.containsKey(searchCondition[0])) {
                int startIdx = 0, endIdx = devMap.get(searchCondition[0]).size() - 1;

                while (startIdx <= endIdx) {
                    int mid = (startIdx + endIdx) / 2;
                    if (devMap.get(searchCondition[0]).get(mid) < searchScore)
                        startIdx = mid + 1;
                    else
                        endIdx = mid - 1;
                }

                count = devMap.get(searchCondition[0]).size() - startIdx;
            }
            answer[i] = count;
        }
        return answer;
    }

    private void setDevMap(String[] devCondition, int score, String key, int depth) {
        if (depth == 4) {
            if (!devMap.containsKey(key)) {
                devMap.put(key, new ArrayList<>());
            }
            devMap.get(key).add(score);
        } else {
            setDevMap(devCondition, score, key + "-", depth + 1);
            setDevMap(devCondition, score, key + devCondition[depth], depth + 1);
        }

    }

}