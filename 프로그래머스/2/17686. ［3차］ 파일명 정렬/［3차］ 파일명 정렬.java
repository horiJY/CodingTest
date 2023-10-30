import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Solution {
    private static final Pattern NUMBER_PATTERN = Pattern.compile("[0-9]+");

    public String[] solution(String[] files) {
        ArrayList<String[]> fileList = new ArrayList<>();
        for (String file : files) {
            String[] data = file.split("[\\d]");
            String[] temp = new String[] { file, data[0].toLowerCase(), "" };

            Matcher matcher = NUMBER_PATTERN.matcher(file);
            if (matcher.find()) {
                temp[2] = matcher.group();
            }
            fileList.add(temp);
        }

        fileList.sort(new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                if (o1[1].equals(o2[1])) {
                    return Integer.parseInt(o1[2]) - Integer.parseInt(o2[2]);
                }
                return o1[1].compareTo(o2[1]);
            }
        });

        return fileList.stream().map(e -> e[0]).toArray(String[]::new);
    }
}