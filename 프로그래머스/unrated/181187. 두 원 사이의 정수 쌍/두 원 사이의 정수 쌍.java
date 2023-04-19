class Solution {
    public long solution(int r1, int r2) { // 1<=r1<r2<=1000000
        long answer = 0;
        double dr1 = Math.pow(r1, 2);
        double dr2 = Math.pow(r2, 2);
        for (int y = 1; y <= r2; y++) {
            int startX = (int) Math.ceil(Math.sqrt(dr1 - Math.pow(y, 2)));
            int endX = (int) Math.sqrt(dr2 - Math.pow(y, 2));
            answer += endX - startX + 1;
        }
        return answer * 4;
    }
}