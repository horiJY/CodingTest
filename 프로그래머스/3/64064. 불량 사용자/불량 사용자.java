import java.util.ArrayList;
import java.util.HashSet;

class Solution {
    ArrayList<ArrayList<String>> candidateListWithBanId;
    HashSet<HashSet<String>> banCaseList;

    public int solution(String[] user_id, String[] banned_id) {
        candidateListWithBanId = new ArrayList<ArrayList<String>>();
        banCaseList = new HashSet<HashSet<String>>();

        // 벤 리스트와 매칭되는 유저 리스트
        for (int i = 0; i < banned_id.length; i++) {
            ArrayList<String> tempList = new ArrayList<>();
            // 벤 아이디의 *을 정규표현식 .로 변환
            String banId = banned_id[i].replaceAll("\\*", ".");
            for (String uid : user_id) {
                if (uid.matches(banId)) {
                    tempList.add(uid);
                }
            }
            candidateListWithBanId.add(tempList);
        }

        checkDuplicationUserId(new HashSet<>(), 0);

        return banCaseList.size();
    }

    void checkDuplicationUserId(HashSet<String> tempCase, int startIdx) {
        if (startIdx == candidateListWithBanId.size()
                && tempCase.size() != 0) {
            banCaseList.add(new HashSet<>(tempCase));
        } else {
            for (String uid : candidateListWithBanId.get(startIdx)) {
                if (!tempCase.contains(uid)) {
                    tempCase.add(uid);
                    checkDuplicationUserId(tempCase, startIdx + 1);
                    tempCase.remove(uid);
                }
            }
        }

    }
}
