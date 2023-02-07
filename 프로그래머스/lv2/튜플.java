import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class Solution {
    public int[] solution(String s) {
        String[] inputData = s.split("},");
        HashMap<String, Integer> dataCountMap = new HashMap<>();

        for (String data : inputData) {
            data = data.replaceAll("[{}]", "");
            for (String element : data.split(",")) {
                dataCountMap.put(element, dataCountMap.getOrDefault(element, 0) + 1);
            }
        }

        // 집합을 보고 튜플을 만들때 :"가장 빈번히 나온 숫자" 순으로 튜플이 구성
        // ? 집합은 원소 순서가 바뀌어도 상관없으나 튜플은 원소 순서가 정해져있음(= 원소 요소는 같으나 원소 순서가 다르면 다른 튜플)
        List<Map.Entry<String, Integer>> answerList = new LinkedList<>(dataCountMap.entrySet());
        answerList.sort((o1, o2) -> dataCountMap.get(o2.getKey()) - dataCountMap.get(o1.getKey()));

        return answerList.stream().mapToInt(e -> Integer.parseInt(e.getKey())).toArray();
    }
}