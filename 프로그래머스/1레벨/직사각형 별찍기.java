import java.util.Scanner;

class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();

        for (int i = 0, f = 0; i < a * b; i++, f++) {
            if (f == a) {
                System.out.println();
                f = 0;
            }

            System.out.print("*");
        }
    }
}