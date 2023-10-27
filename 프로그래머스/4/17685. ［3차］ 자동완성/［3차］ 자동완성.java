import java.util.HashMap;
import java.util.Map;

class Solution {

    public int solution(String[] words) {
        Tree tree = new Tree();
        for (int i = 0; i < words.length; i++) {
            tree.insert(words[i]);
        }

        int result = 0;
        for (int i = 0; i < words.length; i++) {
            result += tree.getMinInputCnt(words[i]);
        }

        return result;
    }

    private class Node {
        Map<Character, Node> child = new HashMap<>();
        Node parent = null;
        Boolean isWordEnd = false;
    }

    private class Tree {
        final Node root = new Node();

        public void insert(String word) {
            Node cur = this.root;

            for (int i = 0; i < word.length(); i++) {
                Node parent = cur;
                cur = cur.child.computeIfAbsent(word.charAt(i), k -> new Node());
                cur.parent = parent;
            }

            cur.isWordEnd = true;
        }

        public int getMinInputCnt(String word) {
            Node cur = this.root;
            for (int i = 0; i < word.length(); i++) {
                cur = cur.child.get(word.charAt(i));
            }

            // 마지막 단어가 끝이 X -> 전체 입력
            if (cur.child.size() != 0) {
                return word.length();
            }

            // 처음 단어가 아니면서
            // 다른 완성 단어 입력된 곳 or 두번이상 입력된 곳 찾기
            int cnt = 0;
            while (cur != null) {
                cur = cur.parent;

                if (cur != null && !cur.isWordEnd && cur.child.size() == 1) {
                    cnt++;
                } else {
                    break;
                }
            }

            return word.length() - cnt;
        }
    }
}