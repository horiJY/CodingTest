class Solution {
    static int answer;

    public int solution(int[] numbers, int target) {
        answer = 0;
        recursive(0, 0, numbers, target);
        System.out.println(answer);
        return answer;
    }

    private void recursive(int idx, int sum, int[] numbers, int target) {
        if (idx == numbers.length) {
            if (sum == target) {
                answer++;
            }
            return;
        }

        recursive(idx + 1, sum + numbers[idx], numbers, target);
        recursive(idx + 1, sum - numbers[idx], numbers, target);
    }
}