import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class DP_2WildCard {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int C = Integer.parseInt(br.readLine());
        while (C-- > 0) {
            String pattern = br.readLine();
            int N = Integer.parseInt(br.readLine());
            List<String> words = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                words.add(br.readLine());
            }

            List<String> result = new ArrayList<>();
            pattern = pattern.replace("?", "\\w?").replace("*", "\\w*");
            for (String str : words) {
                if (str.matches(pattern)) {
                    result.add(str);
                }
            }

            result.sort(null);
            for (String str : result) {
                System.out.println(str);
            }
        }
    }
}
