import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        ArrayList<String> hotKeys = new ArrayList<>();
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            boolean changeFlag = false;
            String[] input = br.readLine().split("\\s");
            for (int j = 0; j < input.length; j++) { // 각 단어의 첫 대문자 부터
                if (input[j].length() < 1) {
                    continue;
                }
                String search = String.valueOf(input[j].charAt(0)).toLowerCase();
                if (!hotKeys.contains(search)) {
                    hotKeys.add(search);
                    input[j] = input[j].replaceFirst(String.valueOf(input[j].charAt(0)),
                            "[" + input[j].charAt(0) + "]");
                    changeFlag = true;
                    break;
                }
            }

            if (!changeFlag) { // 각 단어 대문자가 이미 사용중인 경우
                for (int j = 0; j < input.length; j++) {
                    if (input[j].length() < 1) {
                        continue;
                    }

                    if (changeFlag) {
                        break;
                    }

                    for (int k = 1; k < input[j].length(); k++) {
                        String search = String.valueOf(input[j].charAt(k)).toLowerCase();
                        if (!hotKeys.contains(search)) {
                            hotKeys.add(search);
                            input[j] = input[j].replaceFirst(String.valueOf(
                                    input[j].charAt(k)), "[" + input[j].charAt(k) + "]");
                            changeFlag = true;
                            break;
                        }
                    }
                }
            }

            for (String s : input) {
                if (s.length() < 1) {
                    continue;
                }
                sb.append(s).append(' ');
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }
}
