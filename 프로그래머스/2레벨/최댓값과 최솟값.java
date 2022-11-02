class Solution {
    public String solution(String s) {
        int maxValue = Integer.parseInt(s.split(" ")[0]);
        int minValue = Integer.parseInt(s.split(" ")[0]);
        for (String i : s.split(" ")) {
            maxValue = Integer.max(maxValue, Integer.parseInt(i));
            minValue = Integer.min(minValue, Integer.parseInt(i));
        }

        return minValue + " " + maxValue;
    }
}