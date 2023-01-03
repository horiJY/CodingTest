import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

class Solution {
    static int[] maxNumberOfCourse;
    static char[] orderArray;
    static HashMap<String, Integer> combineMap;

    public String[] solution(String[] orders, int[] course) {
        maxNumberOfCourse = new int[11];
        combineMap = new HashMap<>();

        for (String order : orders) {
            for (int numberOfCourse : course) {
                if (order.length() >= numberOfCourse) {
                    orderArray = order.toCharArray();
                    Arrays.sort(orderArray);
                    getCombineCourse(numberOfCourse, 0, "");
                }
            }
        }

        HashSet<String> result = new HashSet<>();
        for (Entry<String, Integer> e : combineMap.entrySet()) {
            for (int numberOfCourse : course) {
                if (e.getValue() > 1 && e.getKey().length() == numberOfCourse
                        && e.getValue() >= maxNumberOfCourse[e.getKey().length()]) {
                    result.add(e.getKey());
                } else if (e.getKey().length() <= numberOfCourse) {
                    break;
                }
            }
        }

        String[] answer = result.toArray(new String[0]);
        Arrays.sort(answer);
        return answer;
    }

    private void getCombineCourse(int numberOfCourse, int idx, String course) {
        if (course.length() == numberOfCourse) {
            combineMap.put(course, combineMap.getOrDefault(course, 0) + 1);
            if (maxNumberOfCourse[numberOfCourse] < combineMap.get(course)) {
                maxNumberOfCourse[numberOfCourse] = combineMap.get(course);
            }
            return;
        }

        for (int i = idx; i < orderArray.length; i++) {
            getCombineCourse(numberOfCourse, i + 1, course + orderArray[i]);
        }
    }
}