import java.util.HashMap;
import java.util.Map.Entry;

class Solution {
    public int solution(String str1, String str2) {
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();
        HashMap<String, Integer> str1Group = new HashMap<>();
        HashMap<String, Integer> str2Group = new HashMap<>();

        for (int i = 2; i <= str1.length(); i++) {
            String target = str1.substring(i - 2, i);
            if (target.matches("[a-z]{2}")) {
                str1Group.put(target, str1Group.getOrDefault(target, 0) + 1);
            }
        }

        for (int i = 2; i <= str2.length(); i++) {
            String target = str2.substring(i - 2, i);
            if (target.matches("[a-z]{2}")) {
                str2Group.put(target, str2Group.getOrDefault(target, 0) + 1);
            }
        }
        if (str1Group.size() == 0 && str2Group.size() == 0) {
            return 65536;
        }

        HashMap<String, Integer> unionGroup = new HashMap<>();
        int union = 0;
        int intersection = 0;
        for (Entry<String, Integer> str1Entry : str1Group.entrySet()) {
            unionGroup.put(str1Entry.getKey(), str1Entry.getValue());
            boolean intersectionFlag = false;
            for (Entry<String, Integer> str2Entry : str2Group.entrySet()) {
                if (str1Entry.getKey().equals(str2Entry.getKey())) {
                    intersectionFlag = true;
                    intersection += Math.min(str1Entry.getValue(), str2Entry.getValue());
                    union += Math.max(str1Entry.getValue(), str2Entry.getValue());
                    break;
                }
            }
            if (!intersectionFlag) {
                union += str1Entry.getValue();
            }
        }
        for (Entry<String, Integer> str2Entry : str2Group.entrySet()) {
            if (unionGroup.get(str2Entry.getKey()) == null) {
                union += str2Entry.getValue();
            }
        }

        double answer = Double.valueOf(intersection) / Double.valueOf(union) * 65536;
        return (int) Math.floor(answer);
    }
}