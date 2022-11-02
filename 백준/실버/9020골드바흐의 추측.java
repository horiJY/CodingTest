import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int inputSize = Integer.parseInt(br.readLine());
        String answer;
        for (int i = 0; i < inputSize; i++) {
            int targetNumber = Integer.parseInt(br.readLine());
            answer = "";
            for (int j2 = targetNumber / 2; j2 > -1; j2--) {
                if (validPrimeNumber(j2) && validPrimeNumber((targetNumber - j2))) {
                    answer += j2 + " " + (targetNumber - j2);
                    break;
                }

            }
            System.out.println(answer);
        }
    }

    static boolean validPrimeNumber(int num) {
        if (num == 2 || num == 0) {
            return true;
        }
        for (int i = 2; i < Math.sqrt(num) + 1; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
