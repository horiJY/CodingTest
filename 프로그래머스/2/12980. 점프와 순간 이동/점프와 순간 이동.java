public class Solution {
    public int solution(int n) {
        int ans = 0;
        boolean calFlag = true;

        while (calFlag) {
            if (n % 2 != 0) {
                ans++;
            }
            n = n / 2;
            if (n <= 0) {
                calFlag = false;
            }
        }

        return ans;
    }
}