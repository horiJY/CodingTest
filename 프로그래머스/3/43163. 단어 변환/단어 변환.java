import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        Deque<Simulation> que = new ArrayDeque<>();
        que.add(new Simulation(begin, 0, new boolean[words.length]));
        while (!que.isEmpty()) {
            Simulation cur = que.poll();
            if (cur.word.equals(target)) {
                answer = cur.changeCnt;
                break;
            }

            for (int i = 0; i < words.length; i++) {
                if (!cur.isChange[i]) {
                    int notMatchChar = 0;
                    for (int j = 0; j < cur.word.length(); j++) {
                        if (words[i].charAt(j) != cur.word.charAt(j)) {
                            notMatchChar++;
                        }
                    }

                    if (notMatchChar == 1) {
                        cur.isChange[i] = true;
                        que.add(new Simulation(words[i], cur.changeCnt + 1, cur.isChange));
                        cur.isChange[i] = false;
                    }

                }
            }
        }
        return answer;
    }
}

class Simulation {
    String word;
    int changeCnt;
    boolean[] isChange;

    public Simulation(String word, int changeCnt, boolean[] isChange) {
        this.word = word;
        this.changeCnt = changeCnt;
        this.isChange = isChange.clone();
    }
}