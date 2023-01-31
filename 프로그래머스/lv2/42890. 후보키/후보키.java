import java.util.ArrayList;
import java.util.HashSet;

class Solution {
    static ArrayList<String> candidateKeyList;

    static public int solution(String[][] relation) {
        candidateKeyList = new ArrayList<>();
        for (int i = 1; i <= relation[0].length; i++) {
            combineCandidateKey(relation, i, 0, "");
        }

        return candidateKeyList.size();
    }

    static private void combineCandidateKey(String[][] relation, int combineKeyLimit, int startIdx, String key) {
        if (key.length() == combineKeyLimit) {

            if (validCandidateKey(relation, key)) {
                candidateKeyList.add(key);
            }
            return;
        }

        for (int i = startIdx; i < relation[0].length; i++) {
            if (!key.contains(String.valueOf(i))) {
                combineCandidateKey(relation, combineKeyLimit, i + 1, key + String.valueOf(i));
            }
        }
    }

    private static boolean validCandidateKey(String[][] relation, String key) {
        // 최소성 검사
        for (String candidateKey : candidateKeyList) {
            if (key.contains(candidateKey)) {
                return false;
            }
        }
        
        // 유효성 검사
        HashSet<String> tempSet = new HashSet<>();
        for (String[] data : relation) {
            String tempData = "";
            for (String s : key.split("")) {
                tempData += data[Integer.parseInt(s)];
            }
            tempSet.add(tempData);
        }
        
        if (tempSet.size() == relation.length) {
            return true;
        }
        
        return false;

    }
}