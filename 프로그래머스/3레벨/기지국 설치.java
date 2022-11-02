class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0, startIdx = 1;
        // stations는 0 ~ n 이다.
        for (int i = 0; i < stations.length; i++) {
            answer += buildNumberOfStation((stations[i] - w) - startIdx, w);
            startIdx = stations[i] + w + 1;
        }

        answer += buildNumberOfStation(n - startIdx + 1, w);

        return answer;
    }

    private int buildNumberOfStation(int n, int w) {
        // 커버 범위에 필요한 최소 개수는 (범위/커버범위)의 올림수
        if (n <= 0) {
            return 0;
        }
        int mod = n % (w * 2 + 1);
        return mod == 0 ? n / (w * 2 + 1) : n / (w * 2 + 1) + 1;
    }

}