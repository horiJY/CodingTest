class Solution {
    public int solution(int[] numbers, int target) {
        int answer = recursive(0, 0, numbers, target);

        System.out.println(answer);
        return answer;
    }

    private int recursive(int idx, int sum, int[] numbers, int target) {
        if (idx == numbers.length) {
            if (sum == target) {
                return 1;
            }
            return 0;
        }

        return recursive(idx + 1, sum + numbers[idx], numbers, target) + recursive(idx + 1, sum - numbers[idx], numbers,
                target);
    }
}