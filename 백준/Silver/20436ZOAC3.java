import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws IOException {
        HashMap<String, int[]> keyMap = new HashMap<>() {
            {
                put("q", new int[] { 0, 0 });
                put("w", new int[] { 0, 1 });
                put("e", new int[] { 0, 2 });
                put("r", new int[] { 0, 3 });
                put("t", new int[] { 0, 4 });
                put("y", new int[] { 0, 5 });
                put("u", new int[] { 0, 6 });
                put("i", new int[] { 0, 7 });
                put("o", new int[] { 0, 8 });
                put("p", new int[] { 0, 9 });
                put("a", new int[] { 1, 0 });
                put("s", new int[] { 1, 1 });
                put("d", new int[] { 1, 2 });
                put("f", new int[] { 1, 3 });
                put("g", new int[] { 1, 4 });
                put("h", new int[] { 1, 5 });
                put("j", new int[] { 1, 6 });
                put("k", new int[] { 1, 7 });
                put("l", new int[] { 1, 8 });
                put("z", new int[] { 2, 0 });
                put("x", new int[] { 2, 1 });
                put("c", new int[] { 2, 2 });
                put("v", new int[] { 2, 3 });
                put("b", new int[] { 2, 4 });
                put("n", new int[] { 2, 5 });
                put("m", new int[] { 2, 6 });
            }
        };
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int[] p1 = keyMap.get(input[0]);
        int[] p2 = keyMap.get(input[1]);
        input = br.readLine().split("");
        // data input end

        int answer = 0;
        int[][] p1p2Border = new int[][] { keyMap.get("y"), keyMap.get("h"), keyMap.get("b") };
        for (String s : input) {
            int[] target = keyMap.get(s);
            for (int[] border : p1p2Border) {
                if (target[0] == border[0]) {
                    if (target[1] < border[1]) {
                        answer += getDistance(p1, keyMap.get(s)) + 1;
                        p1 = keyMap.get(s);
                    } else {
                        answer += getDistance(p2, keyMap.get(s)) + 1;
                        p2 = keyMap.get(s);
                    }
                }
            }
        }
        System.out.println(answer);
    }

    static int getDistance(int[] start, int[] dest) {
        return Math.abs(start[0] - dest[0]) + Math.abs(start[1] - dest[1]);
    }
}
