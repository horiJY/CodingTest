class Solution {
    public int solution(int n, int[] cores) {
        int cnt = 0;
        while (true) {
            for (int i = 0; i < cores.length; i++) {
                if (cnt % cores[i] == 0) {
                    n--;
                    if (n == 0) {
                        return i + 1;
                    }
                }
            }
            cnt++;
        }

    }
}