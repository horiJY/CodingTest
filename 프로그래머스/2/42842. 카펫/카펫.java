class Solution {
    public int[] solution(int brown, int yellow) {
        boolean successFlag = true;
        int totalArea = (brown + yellow);
        int x = 2, y = 0;
        while (successFlag) {
            y = totalArea / (++x);
            if (x >= y && ((x - 2) * (y - 2) == yellow) && (2 * x + 2 * (y - 2) == brown)) {
                successFlag = false;
            }
        }
        return new int[] { x, y };
    }
}