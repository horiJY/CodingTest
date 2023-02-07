import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    static int queenQuantity;
    static int answer;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        queenQuantity = Integer.parseInt(br.readLine());
        answer = 0;
        nQueen(new ArrayList<Integer>());

        System.out.println(answer);
    }

    static void nQueen(ArrayList<Integer> field) {
        if (field.size() == queenQuantity) {
            answer++;
        } else {
            for (int nextY = 0; nextY < queenQuantity; nextY++) {
                if (!field.contains(nextY) && field.size() <= queenQuantity && checkRedzone(field, nextY)) {
                    field.add(nextY);
                    nQueen(field);
                    field.remove((Integer) nextY);
                }
            }
        }
    }

    static boolean checkRedzone(ArrayList<Integer> field, int y) {
        for (int lineIdx = 0; lineIdx < field.size(); lineIdx++) {
            if (Math.abs(lineIdx - field.size()) == Math.abs(field.get(lineIdx) - y)) {
                return false;
            }
        }
        return true;
    }
}
