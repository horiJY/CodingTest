import java.util.HashMap;

class Solution {
    public int solution(int[] nums) {
        HashMap<String, Integer> hm = new HashMap<String, Integer>();
        int answer = 0;

        for (int i : nums)
            hm.put(String.valueOf(i), hm.getOrDefault(String.valueOf(i), 0) + 1);

        if (hm.size() <= (nums.length / 2)) {
            answer = hm.size();
        } else {
            answer = (nums.length / 2);
        }
        return answer;
    }
}