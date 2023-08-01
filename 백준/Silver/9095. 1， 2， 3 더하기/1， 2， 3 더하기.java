import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        while (N-- > 0) {
            int number = Integer.parseInt(br.readLine());
            Set<int[]> combineSet = new HashSet<>();
            divideNumber(number, new ArrayList<Integer>(), combineSet);

            System.out.println(combineSet.size());
        }
    }

    private static void divideNumber(int n, ArrayList<Integer> temp, Set<int[]> combineSet) {
        if (n == 0) {
            combineSet.add(temp.stream().mapToInt(i -> i).toArray());
        } else {
            for (int i = 1; i <= 3; i++) {
                if (n - i < 0) {
                    break;
                }
                temp.add(i);
                divideNumber(n - i, temp, combineSet);
                temp.remove(temp.size() - 1);
            }
        }
    }
}
