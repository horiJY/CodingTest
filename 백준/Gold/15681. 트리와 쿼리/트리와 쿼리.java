import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    static HashMap<Integer, ArrayList<Integer>> tree;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] data = br.readLine().split(" ");
        int N = Integer.parseInt(data[0]);
        int R = Integer.parseInt(data[1]);
        int Q = Integer.parseInt(data[2]);

        tree = new HashMap<>();
        for (int i = 1; i < N; i++) {
            data = br.readLine().split(" ");
            if (!tree.containsKey(Integer.parseInt(data[0]))) {
                tree.put(Integer.parseInt(data[0]), new ArrayList<>());
            }
            tree.get(Integer.parseInt(data[0])).add(Integer.parseInt(data[1]));
            if (!tree.containsKey(Integer.parseInt(data[1]))) {
                tree.put(Integer.parseInt(data[1]), new ArrayList<>());
            }
            tree.get(Integer.parseInt(data[1])).add(Integer.parseInt(data[0]));
        }
        // data input end

        dp = new int[N + 1];
        getChildNode(R);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            int answer = Integer.parseInt(br.readLine());
            sb.append(dp[answer]).append(System.lineSeparator());
        }
        System.out.println(sb);
    }

    private static int getChildNode(int node) {
        dp[node] += 1;
        for (Integer i : tree.get(node)) {
            if (dp[i] == 0) {
                dp[node] += getChildNode(i);
            }
        }
        return dp[node];
    }

}