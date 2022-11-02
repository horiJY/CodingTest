import java.util.Arrays;

class Solution {
    public boolean solution(String[] phone_book) {
        Arrays.sort(phone_book);

        for (int compareIdx = 1; compareIdx < phone_book.length; compareIdx++) {
            if (phone_book[compareIdx].startsWith(phone_book[compareIdx - 1])) {
                return false;
            }
        }

        return true;
    }
}