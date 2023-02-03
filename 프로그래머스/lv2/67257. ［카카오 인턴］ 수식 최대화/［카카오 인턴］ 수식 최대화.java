import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Solution {
    static long answer;

    public long solution(String expression) {
        List<Long> number = Stream.of(expression.split("[^0-9]+")).map(Long::parseLong).collect(Collectors.toList());
        List<String> operator = Stream.of(expression.split("[0-9]+")).filter(n -> !n.equals(""))
                .collect(Collectors.toList());

        String[] operatorType = operator.stream().distinct().toArray(String[]::new);
        answer = 0;
        combineExpression(number, operator, operatorType, "");

        return answer;
    }

    private void combineExpression(List<Long> number, List<String> operator, String[] operatorType,
            String priority) {
        if (priority.length() == operatorType.length) {
            System.out.println(priority);
            answer = Math.max(answer, calculate(new ArrayList<>(number), new ArrayList<>(operator), priority));
        }

        for (int i = 0; i < operatorType.length; i++) {
            if (!priority.contains(operatorType[i])) {
                combineExpression(number, operator, operatorType, priority + operatorType[i]);
            }
        }
    }

    private long calculate(List<Long> number, List<String> operator, String priority) {
        for (String op : priority.split("")) {
            for (int i = 0; i < operator.size(); i++) {
                if (op.equals(operator.get(i))) {
                    switch (op) {
                        case "*":
                            number.add(i, number.remove(i) * number.remove(i));
                            break;
                        case "-":
                            number.add(i, number.remove(i) - number.remove(i));
                            break;
                        case "+":
                            number.add(i, number.remove(i) + number.remove(i));
                            break;
                    }
                    operator.remove(i);
                    i = -1;
                }
            }
        }

        return Math.abs(number.get(0));
    }
}