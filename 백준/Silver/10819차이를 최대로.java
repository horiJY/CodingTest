import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int size;
    static int[] numberArray;
    static int answer;
    static boolean[] useFlag;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        size = Integer.parseInt(br.readLine());
        numberArray = new int[size];
        useFlag = new boolean[size];
        answer = 0;

        for (String s : br.readLine().split(" ")) {
            numberArray[answer++] = Integer.parseInt(s);
        }

        answer = 0;
        Arrays.fill(useFlag, false);
        recursion(new int[size], 0);

        System.out.println(answer);
    }

    static void recursion(int[] numberSequence, int depth) {
        if (depth == size) {
            int result = 0;
            for (int i = 0; i < numberSequence.length - 1; i++) {
                result += Math.abs(numberSequence[i] - numberSequence[i + 1]);
            }
            answer = Math.max(answer, result);
        } else {
            for (int i = 0; i < numberArray.length; i++) {
                if (!useFlag[i]) {
                    numberSequence[depth] = numberArray[i];
                    useFlag[i] = true;
                    recursion(numberSequence, depth + 1);
                    useFlag[i] = false;
                }
            }
        }
    }
}