class Solution {
    public int[] solution(int n, long left, long right) {
        int[] answer = new int[Long.valueOf(right - left + 1).intValue()];
        int[] position = new int[] { Long.valueOf(left / n).intValue(), Long.valueOf(left % n).intValue() };
        for (int i = 0; i < answer.length; i++) {
            answer[i] = Math.max(position[0], position[1]) + 1;
            position[1]++;
            if (position[1] >= n) {
                position[0]++;
                position[1] = 0;
            }
        }
        return answer;
    }
}