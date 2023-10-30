import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        List<Integer> q = new ArrayList<>();

        for (int i : priorities) {
            q.add(i);
        }

        for (int l = 9, loopCount = q.size(); l > 0; l--) { // i -> priorities level
            for (int j = 0; j < loopCount; j++) {
                if (q.contains(l)) {
                    if (q.get(0) == l) { // 우선순위 높은 것 출력
                        answer++;
                        if (location == 0) {
                            l = 0;
                            break;
                        }
                    } else {
                        q.add(q.get(0));
                    }
                    q.remove(0);
                    location--;

                    if (location == -1) {
                        location = q.size() - 1;
                    }
                } else {
                    break;
                }
            }
        }

        return answer;
    }
}