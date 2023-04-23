import java.util.HashMap;

class Solution {
    public int solution(int[] topping) {
        int answer = 0;
        HashMap<Integer, Integer> leftT = new HashMap<>();
        HashMap<Integer, Integer> rightT = new HashMap<>();
        for (int i = 0; i < topping.length; i++) {
            rightT.put(topping[i], rightT.getOrDefault(topping[i], 0) + 1);
        }
        for (int i = 0; i < topping.length; i++) {
            leftT.put(topping[i], rightT.getOrDefault(topping[i], 0) + 1);
            int cnt = rightT.put(topping[i], rightT.getOrDefault(topping[i], null) - 1);
            if (cnt == 1) {
                rightT.remove(topping[i]);
            }

            if (leftT.size() == rightT.size()) {
                answer++;
            }
        }
        return answer;
    }
}