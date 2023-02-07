import java.util.LinkedList;

class Solution {
    public String solution(String number, int k) {
        LinkedList<Character> numberQue = new LinkedList<>();
        char[] answer = new char[number.length() - k];

        for (int i = 0; i < number.length(); i++) {
            while (!numberQue.isEmpty() && numberQue.peekLast() < number.charAt(i) && k-- > 0) {
                numberQue.pollLast();
            }

            numberQue.add(number.charAt(i));
        }

        for (int i = 0; i < answer.length; i++) {
            answer[i] = numberQue.poll();
        }

        return new String(answer);
    }
}