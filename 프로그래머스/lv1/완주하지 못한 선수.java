import java.util.HashMap;

class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";

        HashMap<String, Integer> participantMap = new HashMap<String, Integer>();

        for (String s : completion) {
            participantMap.put(s, participantMap.getOrDefault(s, 0) + 1);
        }

        for (String s : participant) {
            if (!participantMap.containsKey(s)) {
                answer = s;
                break;
            } else if (participantMap.get(s) > 1) {
                participantMap.put(s, participantMap.get(s) - 1);
            } else {
                participantMap.remove(s);
            }
        }

        return answer;
    }
}