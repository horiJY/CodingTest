class Solution {
    public int solution(String name) {
        int answer = 0;
        int move = name.length() - 1; // 우측진행 최소횟수
        int lastIndex = 0;
        for (int startIndex = 0; startIndex < name.length(); startIndex++) {
            if (name.charAt(startIndex) != 'A') {
                // A가 아닐 때 최소 이동횟수
                answer += Math.min(name.charAt(startIndex) - 'A', 'Z' - name.charAt(startIndex) + 1);
            }

            lastIndex = startIndex + 1;
            // 연속되는 A 구간확인
            while (lastIndex < name.length() && name.charAt(lastIndex) == 'A') {
                lastIndex++;
            }
            // 연속 A구간 기준으로
            // 1.우측으로 이동 후 좌측이동
            move = Math.min(move, startIndex * 2 + (name.length() - lastIndex));
            // 2.좌측이동 후 우측이동
            move = Math.min(move, (name.length() - lastIndex) * 2 + startIndex);
        }

        return answer + move;
    }
}