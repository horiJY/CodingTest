import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class Solution {
    public int[] solution(String s) {
        String[] inputData = s.split("},\\{");
        HashMap<String, Integer> dataCountMap = new HashMap<>();

        for (String data : inputData) {
            data = data.replaceAll("[{}]", "");
            for (String element : data.split(",")) {
                dataCountMap.put(element, dataCountMap.getOrDefault(element, 0) + 1);
            }
        }

        List<Map.Entry<String, Integer>> answerList = new LinkedList<>(dataCountMap.entrySet());
        answerList.sort((o1, o2) -> dataCountMap.get(o2.getKey()) - dataCountMap.get(o1.getKey()));

        return answerList.stream().mapToInt(e -> Integer.parseInt(e.getKey())).toArray();
    }
}