import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());

        ArrayList<Integer> amazingPrimes = new ArrayList<>();

        // 1부터 시작하여 신기한 소수를 생성
        generateAmazingPrimes(0, N, 0, amazingPrimes);

        // 신기한 소수를 오름차순으로 정렬하여 출력
        for (int prime : amazingPrimes) {
            System.out.println(prime);
        }
    }

    // 신기한 소수를 생성하는 재귀 함수
    public static void generateAmazingPrimes(int num, int N, int depth, ArrayList<Integer> amazingPrimes) {
        if (depth == N) {
            amazingPrimes.add(num);
            return;
        }

        for (int i = 1; i <= 9; i++) {
            int candidate = num * 10 + i;
            if (isPrime(candidate)) {
                generateAmazingPrimes(candidate, N, depth + 1, amazingPrimes);
            }
        }
    }

    // 소수인지 판별하는 함수
    public static boolean isPrime(int number) {
        if (number < 2)
            return false;

        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0)
                return false;
        }

        return true;
    }
}