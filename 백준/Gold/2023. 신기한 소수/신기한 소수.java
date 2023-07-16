
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int lenLimit = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        lenLimit = Integer.parseInt(br.readLine().trim());
        printDecimal(0, 0);
    }

    private static void printDecimal(int num, int len) {
        if (len == lenLimit) {
            System.out.println(num);
            return;
        }
        for (int i = 0; i < 10; i++) {
            int next = num * 10 + i;
            if (isPrime(next)) {
                printDecimal(next, len + 1);
            }
        }
    }

    public static boolean isPrime(int num) {
        if (num < 2) {
            return false;
        }

        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

}
