import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

class Solution {
    Map<String, Map<Integer, Integer>> playsMap;
    Map<String, Integer> genresCountMap;
    ArrayList<Integer> answerList;

    public int[] solution(String[] genres, int[] plays) {
        playsMap = new HashMap<>();
        genresCountMap = new HashMap<>();

        // 장르별 grouping, 장르별 total plays count
        for (int i = 0; i < plays.length; i++) {
            if (!playsMap.containsKey(genres[i])) {
                HashMap<Integer, Integer> tempMap = new HashMap<>();
                for (int j = 0; j < plays.length; j++) {
                    if (genres[i].equals(genres[j])) {
                        tempMap.put(j, plays[j]);
                        genresCountMap.put(genres[i], genresCountMap.getOrDefault(genres[i], 0) + plays[j]);
                    }
                    playsMap.put(genres[i], tempMap);
                }
            }
        }

        // genresCountMap Value 기준 내림차순 정렬
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(genresCountMap.entrySet());
        entries.sort((v1, v2) -> v2.getValue().compareTo(v1.getValue()));

        answerList = new ArrayList<>();
        for (Entry<String, Integer> entry : entries) {
            pickPlaysIndex(entry.getKey());
        }

        int[] answer = new int[answerList.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = answerList.get(i);
        }

        System.out.println(answerList);
        return answer;
    }

    void pickPlaysIndex(String genresName) {
        List<Map.Entry<Integer, Integer>> entries = new ArrayList<>(playsMap.get(genresName).entrySet());
        entries.sort((v1, v2) -> v2.getValue().compareTo(v1.getValue()));
        for (int i = 0, j = 0; i < entries.size(); i++, j++) {
            if (j > 1) {
                break;
            }
            answerList.add(entries.get(i).getKey());
        }
    }
}