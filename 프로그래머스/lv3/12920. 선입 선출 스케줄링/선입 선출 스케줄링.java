class Solution {
    public int solution(int n, int[] cores) {
        if (n <= cores.length) {
            return n;
        }

        int maxTime = 100000;
        int[] process = new int[maxTime + 1];
        for (int i = 0; i < cores.length; i++) {
            for (int j = 1; j < process.length; j += cores[i]) {
                process[j]++;
            }
        }

        int work = 0;
        for (int i = 0; i < process.length; i++) {
            work += process[i];
            if (work < n && n <= work + process[i + 1]) {
                n -= work;
                maxTime = i;
                break;
            }

        }

        int answer = 0;
        for (int i = 0; i < cores.length; i++) {
            if (maxTime % cores[i] == 0) {
                n--;
                if (n == 0) {
                    answer = i + 1;
                    break;
                }
            }
        }

        return answer;
    }
}