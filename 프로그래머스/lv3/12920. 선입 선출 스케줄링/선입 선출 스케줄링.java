class Solution {
    public int solution(int n, int[] cores) {
        int answer = 0;
        int min = 0;
        int max = n * cores[0];
        int remain = 0;
        int time = 0;

        while (min <= max) {
            int mid = (min + max) / 2;
            int workCount = getCompletedWork(mid, cores);

            if (workCount >= n) {
                max = mid - 1;
                time = mid;
                remain = workCount;
            } else {
                min = mid + 1;
            }
        }

        remain -= n;
        for (int i = cores.length - 1; i >= 0; i--) {
            if (time % cores[i] == 0) {
                if (remain == 0) {
                    answer = i + 1;
                    break;
                }
                remain--;
            }
        }

        return answer;
    }

    private int getCompletedWork(int time, int[] cores) {
        if (time == 0) {
            return cores.length;
        }

        int result = cores.length;
        for (int i = 0; i < cores.length; i++) {
            result += (time / cores[i]);
        }

        return result;
    }
}