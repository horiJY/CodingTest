import java.util.Arrays;

class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        int[] skillSequence = new int[skill.length()];

        for (String tree : skill_trees) {
            Arrays.fill(skillSequence, Integer.MAX_VALUE);

            for (int i = 0; i < skillSequence.length; i++) {
                if (tree.indexOf(skill.charAt(i)) != -1) {
                    skillSequence[i] = tree.indexOf(skill.charAt(i));
                }
            }

            if (skill.length() == 1 && skillSequence[0] == Integer.MAX_VALUE) {
                answer++;
                continue;
            }

            answer++;
            for (int i = 1; i < skillSequence.length; i++) {
                if (skillSequence[i - 1] > skillSequence[i]) {
                    answer--;
                    break;
                }
            }
        }

        return answer;
    }
}