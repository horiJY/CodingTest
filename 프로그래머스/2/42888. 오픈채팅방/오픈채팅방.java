import java.util.ArrayList;
import java.util.HashMap;

class Solution {
    public String[] solution(String[] record) {
        HashMap<String, String> nickname = new HashMap<>();
        ArrayList<String> chatHistory = new ArrayList<>();
        for (String s : record) {
            String[] data = s.split(" ");
            if (data[0].equals("Enter")) {
                nickname.put(data[1], data[2]);
                chatHistory.add(data[1]);
                chatHistory.add("님이 들어왔습니다.");
            } else if (data[0].equals("Leave")) {
                chatHistory.add(data[1]);
                chatHistory.add("님이 나갔습니다.");
            } else {
                nickname.put(data[1], data[2]);
            }
        }

        String[] answer = new String[chatHistory.size() / 2];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = nickname.get(chatHistory.get(i * 2)) + chatHistory.get(i * 2 + 1);
        }
        return answer;
    }
}