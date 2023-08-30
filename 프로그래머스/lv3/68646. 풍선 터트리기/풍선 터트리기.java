import java.util.HashSet;
import java.util.Set;

class Solution {
    // 큰 풍선을 터트린다 -> i 풍선이 제일 작은 값이어야 남는다
    // 풍선마다 1번 작은 풍선을 터트릴 수 있는 기회가 있다. -> 2번째로 작은 값이어도 된다.
    // 따라서, i를 기준으로 오른쪽과 왼쪽에서 제일 작은 값을 가져와 세가지를 비교했을 때 i가 제일 작거나 2번째로 작으면 된다.
    public int solution(int[] a) {
        Set<Integer> answer = new HashSet<>();
        // i위치에서 왼쪽의 최소값(0~i-1)과 오른쪽의 최소값(i+1 ~ a.length)까지 값을 매번 찾으려면 시간 초과
        // 1 <= a.length <= 1_000_000
        // 왼쪽에서 i까지 최소값을 저장하는 leftMinNumberCache
        // 오른쪽에서 i까지 최소값을 저장하는 rightMinNumberCache
        int[] leftMinNumberCache = new int[a.length];
        int[] rightMinNumberCache = new int[a.length];
        leftMinNumberCache[0] = a[0];
        for (int i = 1; i < leftMinNumberCache.length; i++) {
            leftMinNumberCache[i] = Math.min(leftMinNumberCache[i - 1], a[i]);
        }

        rightMinNumberCache[a.length - 1] = a[a.length - 1];
        for (int i = rightMinNumberCache.length - 2; i >= 0; i--) {
            rightMinNumberCache[i] = Math.min(rightMinNumberCache[i + 1], a[i]);
        }

        for (int i = 0; i < a.length; i++) {
            if (i == 0 || i == a.length - 1) {
                answer.add(a[i]);
                continue;
            }

            if (a[i] <= leftMinNumberCache[i] || a[i] <= rightMinNumberCache[i]) {
                answer.add(a[i]);
            }
        }

        return answer.size();
    }

}