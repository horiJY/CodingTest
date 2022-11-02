class Solution {
    boolean solution(String s) {
        s = s.toLowerCase();
        long jCount = s.chars().filter(c -> c == 'p').count();
        long yCount = s.chars().filter(c -> c == 'y').count();

        return jCount == yCount;
    }
}