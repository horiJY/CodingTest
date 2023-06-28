import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

class Solution {
    static private int positionLimit = 5;

    public int solution(String dirs) {
        int[] cur = new int[] { 0, 0 };
        HashMap<Character, int[]> move = new HashMap<>();
        move.put('U', new int[] { 0, 1 });
        move.put('D', new int[] { 0, -1 });
        move.put('L', new int[] { -1, 0 });
        move.put('R', new int[] { 1, 0 });
        Set<String> visit = new HashSet<>();
        
        for (char next : dirs.toCharArray()) {
            int[] dxdy = move.get(next);
            if (isValid(cur, dxdy)) {
                String path = "" + cur[0] + cur[1] + dxdy[0] + dxdy[1];
                cur[0] += dxdy[0];
                cur[1] += dxdy[1];
                visit.add(path);
                visit.add("" + cur[0] + cur[1] + (dxdy[0] * -1) + (dxdy[1] * -1));
            }
        }
        return visit.size()/2;
    }

    private boolean isValid(int[] cur, int[] dxdy) {
        if (Math.abs(cur[0] + dxdy[0]) <= positionLimit && Math.abs(cur[1] + dxdy[1]) <= positionLimit) {
            return true;
        }
        return false;
    }
}